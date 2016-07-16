/**
 * @Description : 物业公司注册的ts
 * @date : 16/6/19 上午10:36
 * @author : keryHu keryhu@hotmail.com
 */
import {Component, OnInit} from  '@angular/core';
import {REACTIVE_FORM_DIRECTIVES, FormControl, FormGroup, Validators} from '@angular/forms';
import {Http} from '@angular/http';
import {Title} from "@angular/platform-browser";


import {UserQueryService} from "../../../shared/services/query/user-query.service";
import {StringFormatValidator}  from "../../../shared/services/validation/string-format.validator";
import EmailExist from "../../../shared/services/validation/remote/email-exist";


@Component({
  selector: 'property-signup',
  template: require('./property-signup.component.html'),
  styles: [require('./property-signup.component.css')],
  providers: [UserQueryService],
  directives: [REACTIVE_FORM_DIRECTIVES]
})


export class PropertySignupComponent implements OnInit {
  form:FormGroup;
  companyNameCtrl = new FormControl();
  emailCtrl = new FormControl('', [Validators.required, StringFormatValidator.email],
    [EmailExist]);

  phoneCtrl = new FormControl();
  passwordCtrl = new FormControl();
  directNameCtrl = new FormControl();

  now:number;

  after:number;

  na:number;
  m:number;

  constructor(private http:Http, private userQueryService:UserQueryService, private titleService:Title) {
  }


  ngOnInit() {
    this.form = new FormGroup({
      companyName: this.companyNameCtrl,
      email: this.emailCtrl,
      phone: this.phoneCtrl,
      password: this.passwordCtrl,
      directName: this.directNameCtrl
    });

    this.setTitle();

  }


  public setTitle() {
    this.titleService.setTitle('新地点物业公司注册页面')

  }


  onSubmit(data) {

    console.log(JSON.stringify(data));


  }


}

