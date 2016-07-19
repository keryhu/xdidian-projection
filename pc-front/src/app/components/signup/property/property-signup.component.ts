/**
 * @Description : 物业公司注册的ts
 * @date : 16/6/19 上午10:36
 * @author : keryHu keryhu@hotmail.com
 */
import {Component, OnInit} from  '@angular/core';
import {REACTIVE_FORM_DIRECTIVES, FormControl, FormGroup, Validators} from '@angular/forms';
import {Title} from "@angular/platform-browser";


import {UserQueryService} from "../../../shared/services/query/user-query.service";
import {StringFormatValidator}  from "../../../shared/services/validation/string-format.validator";
import EmailExist from "../../../shared/services/validation/remote/email-exist";
import {SignupService} from "./signup.service";
import {Router} from "@angular/router";
import {Subscription} from "rxjs/Rx";
import CompanyNameRemote from "../../../shared/services/validation/remote/companyName-remote";
import PhoneRemote from "../../../shared/services/validation/remote/phone-remote";


@Component({
  selector: 'property-signup',
  template: require('./property-signup.component.html'),
  styles: [require('./property-signup.component.css')],
  providers: [UserQueryService,SignupService],
  directives: [REACTIVE_FORM_DIRECTIVES]
})


export class PropertySignupComponent implements OnInit {
  private signupSub:Subscription;
  form:FormGroup;
  companyName = new FormControl('',[Validators.required,StringFormatValidator.companyName],
  [CompanyNameRemote]);

  email = new FormControl('', [Validators.required, StringFormatValidator.email],
    [EmailExist]);

  phone = new FormControl('',[Validators.required,StringFormatValidator.phone],
  [PhoneRemote]);

  password = new FormControl('',[Validators.required,StringFormatValidator.passwordInSize,
    StringFormatValidator.passwordContainsTwoTypes]);

  directName = new FormControl('',[Validators.required,StringFormatValidator.peopleName]);


  constructor(private titleService:Title, private signupService:SignupService,
              private router:Router) {
  }


  ngOnInit() {
    this.form = new FormGroup({
      companyName: this.companyName,
      email: this.email,
      phone: this.phone,
      password: this.password,
      directName: this.directName
    });

    this.setTitle();

  }


  public setTitle() {
    this.titleService.setTitle('新地点物业公司注册页面')

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
              email:email,resendToken:resendToken,resignupToken:resignupToken}});
        }


      }
    )


  }

  ngOnDestroy(){
    if(!Object.is(this.signupSub,undefined)){
      this.signupSub.unsubscribe();
    }
  }


}

