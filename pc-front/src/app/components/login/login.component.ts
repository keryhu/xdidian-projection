/**
 * @Description : please enter the description
 * @date : 16/6/29 上午10:06
 * @author : keryHu keryhu@hotmail.com
 */

import {Component, OnInit, OnDestroy} from '@angular/core';
import {REACTIVE_FORM_DIRECTIVES, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from "../../shared/services/auth/auth.service";
import {Router} from "@angular/router";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'login',
  template: require('./login.component.html'),
  styles: [require('./login.component.css')],
  directives: [REACTIVE_FORM_DIRECTIVES],
  providers: []
})

export class LoginComponent implements OnInit,OnDestroy {

  private loginForm:FormGroup;
  private usernameCtrl = new FormControl('', [Validators.required]);
  private passwordCtrl = new FormControl('', [Validators.required]);

  constructor(private authService:AuthService, private router:Router,private titileService:Title) {

  }

  //如果用户没有登录,被动转到login页面,没有必要出现提示语句"请先登录",,因为感觉 比较啰嗦

  ngOnInit() {
    this.loginForm = new FormGroup({
      username: this.usernameCtrl,
      password: this.passwordCtrl
    });

    this.setTitle();
  }

  func() {
    console.log('现在时间是: ' + Date.now());
  }

  onSubmit(data) {

    this.authService.login(data.username, data.password)
      .subscribe(
        r=> {
          if (r) {
            this.router.navigate(['/property']);
          }
        },
        err=> {

        }
      );
  }

  public setTitle(){
    this.titileService.setTitle('新地点登录页面');
  }


  ngOnDestroy() {

  }

}
