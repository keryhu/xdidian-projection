/**
 * @Description : please enter the description
 * @date : 16/6/29 上午10:06
 * @author : keryHu keryhu@hotmail.com
 */

import {Component, OnInit, OnDestroy} from '@angular/core';
import {REACTIVE_FORM_DIRECTIVES, FormControl, FormGroup, Validators, FormBuilder} from '@angular/forms';
import {AuthService} from "../../shared/services/auth/auth.service";
import {Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {IpBlockStatus} from "../../shared/services/query/ip-block.service";
import {BehaviorSubject, Subscription} from "rxjs/Rx";
import {StringFormatValidator} from "../../shared/services/validation/string-format.validator";
import {UserQueryService} from "../../shared/services/query/user-query.service";
import UsernameRemoteValidator from "../../shared/services/validation/remote/username-remote";

@Component({
  selector: 'login',
  template: require('./login.component.html'),
  styles: [require('./login.component.css')],
  directives: [REACTIVE_FORM_DIRECTIVES],
  providers: [IpBlockStatus,UserQueryService]
})

export class LoginComponent implements OnInit,OnDestroy {

  private _ipBlock = new BehaviorSubject(false);
  private _blockMsg = new BehaviorSubject('');
  private _afterLoginMsg = new BehaviorSubject('');
  private loginForm:FormGroup;
  private loginSub:Subscription;
  private ipBlockSub:Subscription;

  private username = new FormControl('',  [Validators.required, StringFormatValidator.emailOrPhone],
    //异步验证必须单独分开一个数组,多个状态可以合并到一个数组里。
    [UsernameRemoteValidator]);

  private password = new FormControl('', [Validators.required, StringFormatValidator.passwordInSize,
    StringFormatValidator.passwordContainsTwoTypes]);

  constructor(private authService:AuthService, private router:Router, private titileService:Title,
              private ipBlockStauts:IpBlockStatus) {

  }

  //如果用户没有登录,被动转到login页面,没有必要出现提示语句"请先登录",,因为感觉 比较啰嗦

  ngOnInit() {
    this.loginForm = new FormGroup({
      username: this.username,
      password: this.password
    });

    this.setTitle();
  }

  public setTitle() {
    this.titileService.setTitle('新地点登录页面');
  }

  checkIpBlock() {
    this.ipBlockSub=this.ipBlockStauts.query().subscribe(
      e=> {
        this._ipBlock.next(e.blockStatus);
        if (e.blockStatus) {
          this._blockMsg.next(e.msg);
        }
      }
    )
  }

  //如果加载login页面的时候,该ip已经被冻结了,那么就直接冻结页面。
  getBlockStatus() {
    return this._ipBlock;
  }

  //获取拥堵的提示信息。
  getBlockMsg() {
    return this._blockMsg;
  }

  getAfterLoginMsg() {
    return this._afterLoginMsg;
  }


  onSubmit(data) {

    this.loginSub=this.authService.login(data.username, data.password)
      .subscribe(
        r=> {
          if (r) {
            this.router.navigate(['/property']);
          }
        },
        err=> {
          console.log(err);
          this._afterLoginMsg.next(err);
        }
      );
  }


  ngOnDestroy() {

    if(!Object.is(this.loginSub,undefined)){
      this.loginSub.unsubscribe();
    }

    if(!Object.is(this.ipBlockSub,undefined)){
      this.ipBlockSub.unsubscribe();
    }
  }

}
