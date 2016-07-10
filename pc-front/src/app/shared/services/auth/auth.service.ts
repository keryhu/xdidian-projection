/**
 * @Description : please enter the description
 * @date : 16/6/22 下午3:16
 * @author : keryHu keryhu@hotmail.com
 */

import {Injectable} from '@angular/core';

import {Router} from "@angular/router";
import tokenExpired from "./token-expired";
import {BehaviorSubject, Observable, Subscription} from "rxjs/Rx";
import {Response, Http} from "@angular/http";
import {TokenRest} from "./token-rest";
import {ConstantService} from "../constant.service";
import {RequestService} from "./request.service";
import {LocalToken} from "../../model/local-token";
import {RefreshToken} from "../../model/refresh-token";


//主动设置一个 按钮用户 前台点击 退出,

@Injectable()
export class AuthService {
  private refreshSubscription:Subscription;
  private principalSubsription:Subscription;
  private clientId:string = ConstantService.clientId;
  private _loginedIn = new BehaviorSubject(false);
  private loginUrl:string = ConstantService.authUrl;
  private refreshToken_expired_in:number = ConstantService.refreshToken_expired_in;
  //还剩多少秒就要刷新access-token
  private leftRefreshTokenSeconds:number = ConstantService.minLeftRefreshTokenSeconds;
  //临时记录refreshToken
  private refreshToken:string;
  //临时记录access-token
  private _access_token = new BehaviorSubject('');
  private _access_token_expired_in = new BehaviorSubject(0);
  public roles = Array.of();
  private userData:any=null;

//service , contstrctor 中的方法,不能放到 ngOnInit 中

  constructor(private http:Http, private router:Router,
              private tokenRest:TokenRest, private request:RequestService) {
    //如果token未过期,那么就执行刷新refreshtoken
    if (!tokenExpired()) {
      this._loginedIn.next(true);

      const tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));

      //将两者的最大值,就是最大的过期时间,保存到本地
      tokenObj.expires_in = Math.max(+tokenObj.expires_in, this._access_token_expired_in.getValue());
      localStorage.setItem('token', JSON.stringify(tokenObj));

      if (!this.refreshExpired()) {
        console.log('constructor - tokenObj.expires_in is : ' + tokenObj.expires_in);
        //当刷新页面的时候,如果发现过期时间小于  正常过期时间一般的时候,自动刷新 access-token--这个就是提前刷新
        if (+tokenObj.expires_in - this.leftRefreshTokenSeconds * 1000 <= Date.now()) {
          this.getNewAccessToken();
        }

        this.userData=this.decodeAccessToken(tokenObj.access_token);
        this.roles=this.userData['authorities'];

        //因为页面刷新了,同时更新下次刷新的间隔时间
        this.scheduleRefresh();
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
          this._access_token_expired_in.next(Date.now() + r['expires_in'] * 1000);
          const refresh_token:RefreshToken = {
            loginName: username,
            refreshToken: r['refresh_token']
          };

          this.userData=this.decodeAccessToken(r['access_token']);
          this.roles=this.userData['authorities'];

          //保存在loginStorage中的信息
          const token:LocalToken = {
            loginName: username,
            access_token: r['access_token'],
            expires_in: Date.now() + r['expires_in'] * 1000,
            refreshToken_expires_in: Date.now() + this.refreshToken_expired_in * 1000

          };

          localStorage.setItem('token', JSON.stringify(token));
          //因为存储 refreshtoken的时候,异步调用 locastorage里的access_token有可能是过时的,所以在这里  特别引入新的
          this.storeRefreshToken(refresh_token, r['access_token']);
          this.scheduleRefresh();
          return r;
        }
        return r;
      })

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


  scheduleRefresh() {
    //开始启动refreshToken post的过期时间,这个时间是动态的,因为用户可能中途刷新页面
    // 计算方法---(access-token到期时间 减去当前时间,再减去leftRefreshTokenSeconds备份时间)
    const tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));

    const dynamicRefreshInterval:number = +tokenObj.expires_in - Date.now() - 1000 * this.leftRefreshTokenSeconds;
    console.log('距离下次刷新时间还有多久 ? ' + dynamicRefreshInterval / 1000);
    const source = Observable.interval(dynamicRefreshInterval);
    this.refreshSubscription = source.subscribe(()=> {
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
    return Observable.throw(error.json() || 'Server error');
  }

  /**
   * 利用refreshtoken 获取新的access——token
   * @param refresh_token
   */
  public getNewAccessToken() {


    //如果refreshToken没有过期,才有这些
    if (!this.refreshExpired()) {
      console.log('refreshToken 过期了吗? ' + this.refreshExpired() + '  accessToken 过期了吗? ' + tokenExpired());

      const tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));

      let access_token:string;

      if (this._access_token.getValue()) {
        access_token = this._access_token.getValue();
      } else {
        access_token = tokenObj.access_token;
      }

      this.tokenRest.get(tokenObj.loginName, access_token)
        .switchMap(e=> {
          const body = "refresh_token=" + e + "&grant_type=refresh_token&client_id=" + (this.clientId);
          return this.http.post(this.loginUrl, body, {headers: this.request.getLoginHeaders()})
        })
        .map(e=>e.json())
        .subscribe(
          e=> {
            if (e.access_token) {
              this._access_token.next(e.access_token);
              const tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));
              tokenObj.access_token = e.access_token;
              tokenObj.expires_in = Date.now() + e['expires_in'] * 1000;
              this._access_token_expired_in.next(Date.now() + e['expires_in'] * 1000);
              localStorage.setItem('token', JSON.stringify(tokenObj));
              console.log('stroe new access_token success ! ');

            }
          },
          err=> {
            //当更新失败后,自动切换到登录页面
            console.log('更新refreshToken 失败!');
            this.router.navigate(['/login']);
            this.refreshSubscription.unsubscribe();
            this.principalSubsription.unsubscribe();
            this._loginedIn.next(false);
          }
        )

    }


  }


  public hasRole(role:string):boolean {
    if (!this.refreshExpired()) {
      return this.roles.includes(role);
    }
    return false;
  }

  public hasAnyRole(roles:string[]):boolean {
   //当前用户的权限,是否包含有数组中的任何一个role
    if (!this.refreshExpired()) {
      return roles.some(role=>this.hasRole(role));

    }
    return false;
  }

  //refreshToken 过期检查的 方法
  private  refreshExpired():boolean {
    const tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));
    if (tokenObj && tokenObj.refreshToken_expires_in) {
      const e:boolean = tokenObj.refreshToken_expires_in <= Date.now();
      if (e) {
        this._loginedIn.next(false);
      }
      return e;
    }

    return true;
  }

  //解析jwt

  private decodeAccessToken(access_token:string) {
    return JSON.parse(window.atob(access_token.split('.')[1]));
  }

  ngOnDestroy() {
    this.principalSubsription.unsubscribe();
    this.refreshSubscription.unsubscribe();

  }

}
