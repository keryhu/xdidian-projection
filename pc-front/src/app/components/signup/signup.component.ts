/**
 * @Description : 会员注册的ts
 * @date : 16/6/19 上午10:36
 * @author : keryHu keryhu@hotmail.com
 */
import {Component, OnInit} from  '@angular/core';
import {REACTIVE_FORM_DIRECTIVES, FormControl, FormGroup, Validators} from '@angular/forms';
import {Title} from "@angular/platform-browser";


import {UserQueryService} from "../../shared/services/query/user-query.service";
import {StringFormatValidator}  from "../../shared/services/validation/string-format.validator";
import EmailExist from "../../shared/services/validation/remote/email-exist";
import {SignupService} from "./signup.service";
import {Router} from "@angular/router";
import {Subscription} from "rxjs/Rx";
import CompanyNameRemote from "../../shared/services/validation/remote/companyName-remote";
import PhoneRemote from "../../shared/services/validation/remote/phone-remote";
import {ConstantService} from "../../shared/services/constant.service";


@Component({
  selector: 'signup',
  template: require('./signup.component.html'),
  styles: [require('./signup.component.css')],
  providers: [UserQueryService,SignupService],
  directives: [REACTIVE_FORM_DIRECTIVES]
})


export class SignupComponent implements OnInit {
  private signupSub:Subscription;
  form:FormGroup;

  email = new FormControl('', [Validators.required, StringFormatValidator.email],
    [EmailExist]);

  phone = new FormControl('',[Validators.required,StringFormatValidator.phone],
  [PhoneRemote]);

  password = new FormControl('',[Validators.required,StringFormatValidator.passwordInSize,
    StringFormatValidator.passwordContainsTwoTypes]);



  constructor(private titleService:Title, private signupService:SignupService,
              private router:Router) {
  }


  ngOnInit() {
    this.form = new FormGroup({
      email: this.email,
      phone: this.phone,
      password: this.password
    });

    this.setTitle();

  }


  public setTitle() {
    this.titleService.setTitle('新地点注册页面')

  }


  onSubmit(data) {

    console.log(JSON.stringify(data));
    this.signupSub=this.signupService.create(data).subscribe(
      e=>{
        if(e&&e.email){
          const email=e.email;
          const resendToken=e.resendToken;
          const resignupToken=e.resignupToken;

          //转到email激活的页面。
          this.router.navigate(['/emailActivate'],{
            queryParams:{
              email:email,resendToken:resendToken,resignupToken:resignupToken,first:true}});
        }

        //当注册成功后,自动开启"重新发送的" 倒计时。
        localStorage.setItem('num', ConstantService.clickCoolingSeconds.toString());


      }
    )


  }

  ngOnDestroy(){
    if(!Object.is(this.signupSub,undefined)){
      this.signupSub.unsubscribe();
    }
  }


}

