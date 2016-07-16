/**
 * @Description : please enter the description
 * @date : 16/6/22 下午3:16
 * @author : keryHu keryhu@hotmail.com
 */

import {Injectable} from '@angular/core';

import {Router} from "@angular/router";
import tokenExpired from "./token-expired";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {Response, Http} from "@angular/http";
import {TokenRest} from "./token-rest";
import {ConstantService} from "../constant.service";
import {RequestService} from "./request.service";
import {LocalToken} from "../../model/local-token";
import {RefreshToken} from "../../model/refresh-token";
import DynamicTokenRefreshInterval from "./dynamic-token-refresh-interval";
import RefreshTokenExpired from "./refreshToken-expired";
import CurrentLoginName from "./current-loginName";


//主动设置一个 按钮用户 前台点击 退出,

@Injectable()
export class AuthService {
  private tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));
  private refreshSubscription:any;
  private clientId:string = ConstantService.clientId;
  private _loginedIn = new BehaviorSubject(false);
  private loginUrl:string = ConstantService.authUrl;
  private refreshToken_expired_in:number = ConstantService.refreshToken_expired_in;
  private accessTokenRefreshInterval:number = ConstantService.accessTokenValiditySeconds -
    ConstantService.minLeftRefreshTokenSeconds;
  //临时记录refreshToken
  private refreshToken:string;
  //临时记录access-token
  private _access_token = new BehaviorSubject('');
  private _refresh_token = new BehaviorSubject('');
  public roles = Array.of();
  private userData:any = null;

//service , contstrctor 中的方法,不能放到 ngOnInit 中

  constructor(private http:Http, private router:Router,
              private tokenRest:TokenRest, private request:RequestService) {
    //如果token未过期,那么就执行刷新refreshtoken
    if (!tokenExpired()) {
      this._loginedIn.next(true);



      if (!RefreshTokenExpired()) {

        //每次浏览器刷新,都将保存在本地的  access token,更新到 BehaviorSubject里
        this._access_token.next(this.tokenObj.access_token);

        const inte = DynamicTokenRefreshInterval();
        //当刷新页面的时候,如果发现过期时间小于  正常过期时间一般的时候,自动刷新 access-token--这个就是提前刷新
        /**
         *  if (inte <= 0) {
          console.log('因为小于正常的刷新时间了,所以提前刷新 ! ')
          this.getNewAccessToken();
        }
         */

        this.userData = this.decodeAccessToken(this.tokenObj.access_token);
        this.roles = this.userData['authorities'];

        //因为页面刷新了,所以设置下一次刷新的时间为动态时间。 而周期性的间隔时间不会变
        this.scheduleRefresh(inte);

      }
    }
  }

  login(username:string, password:string) {

    const body = "username=" + encodeURI(username) + "&password=" + encodeURI(password) +
      "&grant_type=password&client_id=" + encodeURI(this.clientId);

    return this.http.post(this.loginUrl, body, {headers: this.request.getLoginHeaders()})
      .map(r=>r.json())
      .map(r=> {
        if (r.access_token) {
          this._loginedIn.next(true);
          this.refreshToken = (r['refresh_token']);
          this._access_token.next(r['access_token']);
          this._refresh_token.next(r['refresh_token']);
          const refresh_token:RefreshToken = {
            loginName: username,
            refreshToken: r['refresh_token']
          };

          this.userData = this.decodeAccessToken(r['access_token']);
          this.roles = this.userData['authorities'];

          //保存在loginStorage中的信息
          const token:LocalToken = {
            loginName: username,
            access_token: r['access_token'],
            refreshToken_expires_in: Date.now() + this.refreshToken_expired_in * 1000

          };

          localStorage.setItem('token', JSON.stringify(token));
          //因为存储 refreshtoken的时候,异步调用 locastorage里的access_token有可能是过时的,所以在这里  特别引入新的
          this.storeRefreshToken(refresh_token, r['access_token']);
          this.scheduleRefresh(this.accessTokenRefreshInterval * 1000);
          return r;
        }
        return r;
      }).catch(this.handleError);

  }


  public  logout() {
    this._loginedIn.next(false);
    localStorage.removeItem('token');
    this.router.navigate(['']);
    this.refreshSubscription.unsubscribe();


  }

  public isLoggedIn() {
    return this._loginedIn.getValue();
  }

  public getLoggedIn() {
    return this._loginedIn;
  }


  //当页面刷新后,且用户处于登录状态,的 schedule
  scheduleRefresh(initDelay:number) {
    //Observable.timer, 间隔周期性时间为  accesstokenRefresh 刷新,且第一次刷新时间为  initDelay之后。
    //如果用户刚刚登录完,设置 initDelay=this.accessTokenRefreshInterval*1000。  如果用户刷新页面,initDelay就是动态的时间
    //值为 DynamicTokenRefreshInterval(),这个值在 ngInit里面设置
    const source = Observable.timer(initDelay, this.accessTokenRefreshInterval * 1000);

    console.log('initDelay is : ' + initDelay / 1000 + ' 秒');

    this.refreshSubscription = source.subscribe(()=> {
      console.log('正在启动 固定的系统刷新 !!!');
      this.getNewAccessToken();
    })

  }


  private storeRefreshToken(token:RefreshToken, accessToken:string) {
    this.tokenRest.save(token, accessToken).subscribe(
      e=> {
        console.log('store refreshToken success !');
      },
      err=> {
        console.log('store refreshToken failer ')
      }
    );
  }


  private handleError(error:Response) {
    const m=error.json();
    const j=m.error_description;
    return Observable.throw(j|| 'Server error');
  }

  /**
   * 利用refreshtoken 获取新的access——token
   * @param refresh_token
   */
  public getNewAccessToken() {
    console.log('refreshToken 过期了吗? ' + RefreshTokenExpired() + '  accessToken 过期了吗? ' + tokenExpired());

    let access_token:string = this._access_token.getValue();

    this.tokenRest.get(CurrentLoginName(), access_token)
      .switchMap(e=> {
        let t:string;
        if (this._refresh_token.getValue()) {
          t = this._refresh_token.getValue();
        } else {
          t = e;
        }
        const body = "refresh_token=" + t + "&grant_type=refresh_token&client_id=" + (this.clientId);
        return this.http.post(this.loginUrl, body, {headers: this.request.getLoginHeaders()})
      })
      .map(e=>e.json())
      .subscribe(
        e=> {
          if (e.access_token) {
            this._access_token.next(e.access_token);
            this.tokenObj.access_token = e.access_token;
            localStorage.setItem('token', JSON.stringify(this.tokenObj));
            console.log('stroe new access_token success ! ');

          }
        },
        err=> {
          //当更新失败后,自动切换到登录页面
          console.log('更新refreshToken 失败!');
          this.router.navigate(['/login']);
          this.refreshSubscription.unsubscribe();
          this._loginedIn.next(false);
        }
      )

  }


  public hasRole(role:string):boolean {
    if (!tokenExpired()) {
      return this.roles.includes(role);
    }
    return false;
  }

  public hasAnyRole(roles:string[]):boolean {
    //当前用户的权限,是否包含有数组中的任何一个role
    if (!tokenExpired()) {
      return roles.some(role=>this.hasRole(role));

    }
    return false;
  }


  //解析jwt

  private decodeAccessToken(access_token:string) {
    return JSON.parse(window.atob(access_token.split('.')[1]));
  }


  ngOnDestroy() {
    this.refreshSubscription.unsubscribe();

  }

}
