/**
 * @Description : 用户刚刚注册完,需要验证email的程序
 * @date : 16/7/18 上午8:38
 * @author : keryHu keryhu@hotmail.com
 */


import {Component, OnInit, OnDestroy} from "@angular/core";
import {Title} from "@angular/platform-browser";
import {Router} from "@angular/router";
import {BehaviorSubject} from "rxjs/Rx";
import {CountdownComponent} from "../countdown/countdown.component";
import {Http} from "@angular/http";
import {RequestService} from "../../services/auth/request.service";
import {ClickResendService} from "./click-resend.service";
import ClickNotExpired from "./click-expired";
import {ConstantService} from "../../services/constant.service";


@Component({
  selector: 'email-activate',
  template: require('./email-activate.component.html'),
  styles: [require('./email-activate.component.css')],
  providers: [ClickResendService],
  directives: [CountdownComponent]
})


export class EmailActivateComponent implements OnInit,OnDestroy {

  private paramsSub:any;
  private resendSub:any;
  private resignupSub:any;
  private resendUrl = new BehaviorSubject('');
  private resignupUrl = new BehaviorSubject('');

  //可以点击 "重新发送按钮。"
  private _disableClickResend = new BehaviorSubject(false);

  private baseUrl:string = '/email-activate/email';
  private resendBody:any;
  private resignupBody:any;


//将这个传给 子 component
  private num:number = 120  //2分钟作为倒计时

  constructor(private titleService:Title, private router:Router, private http:Http,
              private request:RequestService, private clickResendService:ClickResendService) {
  }

  ngOnInit() {

    this.setTitle();
    this.getParam();
  }

  setTitle() {
    this.titleService.setTitle('新地点电子邮箱激活确认页面');
  }

  //获取email,token等参数。
  getParam() {
    //注意使用查询参数。routerState.queryParams
    this.paramsSub = this.router.routerState.queryParams.subscribe(
      params=> {

        const _resendUrl = `${this.baseUrl}/resend?
        email=${params['email']}&
        token=${params['resendToken']}`;

        this.resendBody = {
          email: params['email'],
          token: params['resendToken']
        }

        this.resendUrl.next(_resendUrl);

        const _resignupUrl = `${this.baseUrl}/resignup?
        email=${params['email']}&
        token=${params['resignupToken']}`;

        this.resignupBody = {
          email: params['email'],
          token: params['resignupToken']
        }

        this.resignupUrl.next(_resignupUrl);

      }
    )
  }


  //用户点击重新发送,促发的事件
  resend() {
    /**
     * this.http.post(
     `${this.baseUrl}/resend`,
     JSON.stringify(this.resendBody),
     {headers: this.request.getJsonHeaders()}
     ).subscribe(
     e=>{
          console.log(e);
        }
     )
     */

    console.log('re send email activate ...');

    //将 点击 消息 传递给 对应的service。
    this.clickResendService.click(ConstantService.emailActivate);

  }

  //用户点击重新注册,促发的 事件
  resignup() {

    this.resignupSub = this.http.post(`${this.baseUrl}/resignup`,
      JSON.stringify(this.resignupBody),
      {headers: this.request.getJsonHeaders()}
    ).subscribe(
      e=> {
        console.log(e);
      }
    )
  }


  //设置当前的"重新发送"是否可以点击。
  disableClickResend() {

    if (ClickNotExpired()) {
      this._disableClickResend.next(true);
    }
    else {
      this._disableClickResend.next(false);
    }
    return this._disableClickResend;
  }


  ngOnDestroy() {

    if (!Object.is(this.paramsSub, undefined)) {
      this.paramsSub.unsubscribe();
    }

    if(!Object.is(this.resendSub,undefined)){
      this.resendSub.unsubscribe();
    }

    if (!Object.is(this.resignupSub, undefined)) {
      this.resignupSub.unsubscribe();
    }


  }


}
