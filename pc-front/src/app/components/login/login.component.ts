/**
 * @Description : please enter the description
 * @date : 16/6/29 上午10:06
 * @author : keryHu keryhu@hotmail.com
 */

import {Component, OnInit} from '@angular/core';
import {REACTIVE_FORM_DIRECTIVES, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from "../../shared/services/auth/auth.service";
import {Router} from "@angular/router";
import {Observable} from "rxjs/Rx";

@Component({
  selector: 'login',
  template: require('./login.component.html'),
  styles: [require('./login.component.css')],
  directives: [REACTIVE_FORM_DIRECTIVES],
  providers: []
})

export class LoginComponent implements OnInit {
  loginForm:FormGroup;
  usernameCtrl = new FormControl('', [Validators.required]);
  passwordCtrl = new FormControl('', [Validators.required]);

  constructor(private authService:AuthService,private router:Router ) {
    this.loginForm = new FormGroup({
      username: this.usernameCtrl,
      password: this.passwordCtrl
    });
  }

  ngOnInit() {
   // let timer = Observable.timer(2000,4000);
   // timer.subscribe(()=> {
      //this.func();
   // });
  }

  func(){
    console.log('现在时间是: '+Date.now());
  }

  onSubmit() {

      this.authService.login(this.loginForm.value.username, this.loginForm.value.password)
        .subscribe(
          r=>{
            if(r){
              this.router.navigate(['/property']);
            }
          },
          err=>{

          }
        );
    }

}
