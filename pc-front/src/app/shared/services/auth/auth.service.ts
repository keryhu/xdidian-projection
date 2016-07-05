/**
 * @Description : please enter the description
 * @date : 16/6/22 下午3:16
 * @author : keryHu keryhu@hotmail.com
 */

import {Injectable, OnInit} from '@angular/core';

import {Router} from "@angular/router";
import tokenExpired from "./token-expired";
import {BehaviorSubject, Observable, Subscription} from "rxjs/Rx";
import {Response, Http} from "@angular/http";
import {TokenRest} from "./token-rest";
import {RefreshToken} from "../../model/refresh-token";
import {ConstantService} from "../constant.service";
import {RequestService} from "./request.service";
import {LocalToken} from "../../model/local-token";


//主动设置一个 按钮用户 前台点击 退出,

@Injectable()
export class AuthService implements OnInit {
  private refreshSubscription:Subscription;
  private clientId:string = ConstantService.clientId;
  private _loginedIn = new BehaviorSubject(false);
  private loginUrl:string = ConstantService.authUrl;
  private refreshToken_expired_in:number = ConstantService.refreshToken_expired_in;
  private leftRefreshTokenSeconds:number=ConstantService.minLeftRefreshTokenSeconds;

  private accessTokenValiditySeconds:number=ConstantService.accessTokenValiditySeconds;
  private refreshToken:string;
  private _access_token = new BehaviorSubject('');
  public _access_token_expired_in=new BehaviorSubject(0);


  constructor(private http:Http, private router:Router,
              private tokenRest:TokenRest, private request:RequestService) {

    if(!tokenExpired()){
      this._loginedIn.next(true);
    }

    let tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));

    if(+tokenObj.expires_in<=this._access_token_expired_in.getValue()){
      tokenObj.expires_in =this._access_token_expired_in.getValue();
      localStorage.setItem('token', JSON.stringify(tokenObj));
    }

    if (!this.refreshExpired()) {
      console.log('constructor - tokenObj.expires_in is : '+tokenObj.expires_in);
      //当刷新页面的时候,如果发现过期时间小于  正常过期时间一般的时候,自动刷新 access-token--这个就是提前刷新
      if(+tokenObj.expires_in-this.leftRefreshTokenSeconds*1000<=Date.now()){
        this.getNewAccessToken();
      }

      this.scheduleRefresh();
    }

  }

  ngOnInit() {

  }

  login(username:string, password:string) {
    let loginForm = JSON.stringify({
      grant_type: 'password',
      client_id: this.clientId,
      username: username,
      password: password
      //state: 'djxiki'
    });

    let body = "username=" + username + "&password=" + password + "&grant_type=password&client_id=" + this.clientId;


    return  this.http.post(this.loginUrl, body, {headers: this.request.getLoginHeaders()})
      .map(r=>r.json())
      .map(r=> {
        if (r.access_token) {
          this._loginedIn.next(true);
          this.refreshToken = (r['refresh_token']);
          this._access_token.next(r['access_token']);
          this._access_token_expired_in.next(Date.now() + r['expires_in'] * 1000);
          let refresh_token:RefreshToken = {
            loginName: username,
            refreshToken: r['refresh_token']
          };

          //保存在loginStorage中的信息
          let token:LocalToken = {
            loginName: username,
            access_token: r['access_token'],
            expires_in: Date.now() + r['expires_in'] * 1000,
            refreshToken_expires_in: Date.now() + this.refreshToken_expired_in * 1000

          };

          localStorage.setItem('token', JSON.stringify(token));
          //因为存储 refreshtoken的时候,异步调用 locastorage里的access_token有可能是过时的,所以在这里  特别引入新的
          this.storeRefreshToken(refresh_token, r['access_token']);
          this.scheduleRefresh();
          console.log('login完成的时间为:  '+Date.now())
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
    //开始启动refreshToken post的过期时间,这个时间的计算方法---(固定的access-token时间-最小的剩余刷新时间)

    let source = Observable.interval(1000 * (this.accessTokenValiditySeconds-this.leftRefreshTokenSeconds));
    this.refreshSubscription = source.subscribe(()=> {
      console.log('准备刷新的时间为:  '+Date.now())
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
      console.log('refreshToken 过期了吗? ' + this.refreshExpired()+'  accessToken 过期了吗? '+tokenExpired());

      console.log('准备从服务器下载refreshToken');
      let tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));

      let access_token:string;

      if(this._access_token.getValue()){
        access_token=this._access_token.getValue();
      } else {
        access_token=tokenObj.access_token;
      }

      this.tokenRest.get(tokenObj.loginName, access_token)
        .flatMap(e=> {
          let body = "refresh_token=" + e + "&grant_type=refresh_token&client_id=" + this.clientId;
          return this.http.post(this.loginUrl, body, {headers: this.request.getLoginHeaders()})
        })
        .map(e=>e.json())
        .subscribe(
          e=> {
            if (e.access_token) {
              this._access_token.next(e.access_token);
              let tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));
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
            this._loginedIn.next(false);
          }
        )

    }


  }


  //refreshToken 过期检查的 方法
  private  refreshExpired():boolean {
    let tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));
    if (tokenObj && tokenObj.refreshToken_expires_in) {
      console.log('refreshtoken expire in : ' + tokenObj.refreshToken_expires_in);
      let e:boolean = tokenObj.refreshToken_expires_in <= Date.now();
      if (e) {
        this._loginedIn.next(false);
      }
      return e;
    }

    return true;
  }



  ngOnDestroy() {
    this.refreshSubscription.unsubscribe();
  }

}
