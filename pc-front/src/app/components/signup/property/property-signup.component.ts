/**
 * @Description : 物业公司注册的ts
 * @date : 16/6/19 上午10:36
 * @author : keryHu keryhu@hotmail.com
 */
import {Component, OnInit} from  '@angular/core';
import {REACTIVE_FORM_DIRECTIVES, FormControl, FormGroup, Validators} from '@angular/forms';
import {Http} from '@angular/http';

import { EmailRemoteValidate } from "../../../shared/services/validation/email-remote.validator";
import { UserQueryService } from "../../../shared/services/query/user-query.service";
import { StringFormatValidator }  from "../../../shared/services/validation/StringFormatValidator";



@Component({
  selector: 'property-signup',
  template: require('./property-signup.component.html'),
  styles: [require('./property-signup.component.css')],
  providers: [UserQueryService,EmailRemoteValidate],
  directives: [REACTIVE_FORM_DIRECTIVES]
})


export class PropertySignupComponent implements OnInit {
  form:FormGroup;
  companyNameCtrl = new FormControl();
  emailCtrl = new FormControl('', [Validators.required, StringFormatValidator.email],
    [(control:FormControl)=>this.emailRemoteValidate.email(control)]);

  phoneCtrl = new FormControl();
  passwordCtrl = new FormControl();
  directNameCtrl = new FormControl();

  ngOnInit() {
  }

  constructor(private http:Http, private userQueryService:UserQueryService,
              private emailRemoteValidate:EmailRemoteValidate) {


    this.form = new FormGroup({
      companyName: this.companyNameCtrl,
      email: this.emailCtrl,
      phone: this.phoneCtrl,
      password: this.passwordCtrl,
      directName: this.directNameCtrl
    });


  }



  onSubmit() {
    if (this.form.dirty && this.form.valid) {
      console.log(JSON.stringify(this.form.value));
    }


  }


}

