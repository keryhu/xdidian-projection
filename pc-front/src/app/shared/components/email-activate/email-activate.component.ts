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
import {ClickResendService} from "./click-resend.service";
import ClickNotExpired from "./click-expired";
import {ConstantService} from "../../services/constant.service";
import {FormGroup, Validators, REACTIVE_FORM_DIRECTIVES, FormControl} from "@angular/forms";
import {TokenValidator} from "../../services/validation/token-validator";
import {UserQueryService} from "../../services/query/user-query.service";


@Component({
  selector: 'email-activate',
  template: require('./email-activate.component.html'),
  styles: [require('./email-activate.component.css')],
  providers: [ClickResendService, TokenValidator, UserQueryService],
  directives: [CountdownComponent, REACTIVE_FORM_DIRECTIVES]
})


export class EmailActivateComponent implements OnInit,OnDestroy {
  form:FormGroup;
  private paramsSub:any;
  private resendSub:any;
  private resignupSub:any;
  private emailStatusSub:any;

  //可以点击 "重新发送按钮。"
  private _disableClickResend = new BehaviorSubject(false);

  private email:string;

  private resendToken:string;
  private resignupToken:string;

  //用户填写错误的token,报错的信息,来自于后台。
  private _tokenErrorMsg = new BehaviorSubject('');
  //用户点击"重新发送"遇到的错误提示信息
  private _resendErrorMsg = new BehaviorSubject('');
  //用户点击"重新注册"遇到的错误提示信息
  private _resignupErrorMsg = new BehaviorSubject('');


  //用户输入的token
  token = new FormControl('', [Validators.required, Validators.pattern('^[0-9]{6}$')]);



  constructor(private titleService:Title, private router:Router, private clickResendService:ClickResendService,
              private tokenValidator:TokenValidator, private userQuery:UserQueryService) {
  }

  ngOnInit() {

    //初始化的判断,如果email已经激活,那么直接转到login页面。

    this.setTitle();
    this.getParam();
    this.checkEmailStatus();

    this.form = new FormGroup({
        token: this.token
      }
    )



  }

  setTitle() {
    this.titleService.setTitle('新地点电子邮箱激活确认页面');
  }

  //获取email,token等参数。
  getParam() {
    //注意使用查询参数。routerState.queryParams
    this.paramsSub = this.router.routerState.queryParams.subscribe(
      params=> {
        this.email = params['email'];
        this.resendToken = params['resendToken'];
        this.resignupToken = params['resignupToken'];

      }
    )
  }


  //用户点击重新发送,促发的事件
  resend() {
    const dto = {
      //resend token
      token: this.resendToken,
      type: 'EMAIL',
      email: this.email
    };

    this.resendSub = this.tokenValidator.emailResend(dto)
      .subscribe(
        e=> {
          this._resendErrorMsg.next('');
          //如果resendToken 存在,那么就更新它们
          if (e.resendToken) {

            //转到email激活的页面。
            this.router.navigate(['/emailActivate'], {
              queryParams: {
                email: this.email,
                resendToken: e.resendToken,
                resignupToken: e.resignupToken
              }
            });

          }

          //将 点击 消息 传递给 对应的service。
          this.clickResendService.click(ConstantService.emailActivate);

          //如果验证码过期,   那么根据之前的role分配到不同的注册页面
          if(Object.is(e,ConstantService.emailActivateExpired)){
            this.router.navigate(['/signup']);
            localStorage.setItem('num', '-1');
            if (!Object.is(this.emailStatusSub, undefined)) {
              this.emailStatusSub.unsubscribe();
            }
          }


        },
        err=> {
          this._resendErrorMsg.next(err);
          if(err){
            localStorage.setItem('num','-1');
          }
        });




  }

  //用户点击重新注册,促发的 事件
  resignup() {
    const dto = {
      //resignup token
      token: this.resignupToken,
      type: 'EMAIL',
      email: this.email
    }

    this.resignupSub = this.tokenValidator.emailResignup(dto)
      .subscribe(
        e=> {
          this._resignupErrorMsg.next('');
          console.log(e);

          //分配到不同的注册页面
          this.router.navigate(['/signup']);

        },
        err=> {
          this._resignupErrorMsg.next(err);
        }
      );


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

  //初始化的查询email状态,如果已经激活了,直接跳转login页面。
  checkEmailStatus() {
    this.emailStatusSub = this.userQuery.emailStatus(this.email).subscribe(
      e=> {
        if (e) {
          this.router.navigate(['login']);
        }
      }
    )
  }


  //提交验证码
  onSubmit(data) {
    const dto = {
      token: data.token,
      type: 'EMAIL',
      email: this.email
    }

    this.tokenValidator.email(dto)
      .subscribe(
        e=> {
          this._tokenErrorMsg.next('');
          console.log(e);

          //如果验证码过期, 导航到注册页面,且设置  num 为 -1

          if(Object.is(e,ConstantService.emailActivateExpired)){
            this.router.navigate(['/signup']);
            localStorage.setItem('num', '-1');
          }

          //如果激活成功,那么转到login页面
          if (Object.is(e, ConstantService.emailActivateSuccess)) {
            //既然登录成功了,那么就设置 '重新发送'的冷却时间失效
            localStorage.setItem('num', '-1');

            this.router.navigate(['login']);
          }

        },
        err=> {
          this._tokenErrorMsg.next(err);
          console.log(err);
        }
      )

  }

  //当用户提交验证码后,如果后台发现错误,则显示到前端。
  getTokenError() {
    const t = this.form.value.token;
    const TOKEN = /^[0-9]{6}$/;
    const result = TOKEN.test(t);
    //如果token不是6位数字,那么不显示远程的token error msg
    if (!result) {
      this._tokenErrorMsg.next('')
    }

    return this._tokenErrorMsg;
  }

  getResendError() {
    return this._resendErrorMsg;
  }

  getResignupError() {
    return this._resignupErrorMsg;
  }

  ngOnDestroy() {

    if (!Object.is(this.paramsSub, undefined)) {
      this.paramsSub.unsubscribe();
    }

    if (!Object.is(this.resendSub, undefined)) {
      this.resendSub.unsubscribe();
    }

    if (!Object.is(this.resignupSub, undefined)) {
      this.resignupSub.unsubscribe();
    }

    if (!Object.is(this.emailStatusSub, undefined)) {
      this.emailStatusSub.unsubscribe();
    }

  }


}
