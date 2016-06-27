/**
 * @Description : 设置整个系统的导航
 * @date : 16/6/20 上午10:42
 * @author : keryHu keryhu@hotmail.com
 */

import {Component, Input, ChangeDetectionStrategy, OnInit} from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {ROUTER_DIRECTIVES} from '@angular/router';

import {ConstantService} from "../../services/constant.service";
import {AuthService} from "../../services/auth/auth.service";
import {PrincipalService} from "../../services/auth/principal.service"; //use core-js

@Component({
  selector: 'my-navbar',
  template: require('./navbar.component.html'),
  styles: [require('./navbar.component.css')],
  changeDetection: ChangeDetectionStrategy.OnPush,
  directives: [ROUTER_DIRECTIVES, CORE_DIRECTIVES]
})

export class NavbarComponent implements OnInit {
  loginUrl:string;
  isLogged:boolean = false;

  constructor(private principal:PrincipalService) {
  }

  @Input() brand:string;

  ngOnInit() {

    this.setLoginUrl();
    this.setIsLogged();
    this.g();
  }

  setLoginUrl() {
    this.loginUrl = ConstantService.loginUrl;
  }

  setIsLogged() {

    this.principal.currentUser().subscribe(e=>{
      if (e){
        console.log('e is ====');
        this.isLogged=true;
      }
    })
  }

  g(){
    return this.isLogged;
  }



}
