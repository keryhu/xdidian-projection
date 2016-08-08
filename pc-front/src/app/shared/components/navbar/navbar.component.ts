/**
 * @Description : 设置整个系统的导航
 * @date : 16/6/20 上午10:42
 * @author : keryHu keryhu@hotmail.com
 */

import {Component, Input, ChangeDetectionStrategy, OnInit, OnDestroy} from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {ROUTER_DIRECTIVES} from '@angular/router';

import {AuthService} from "../../services/auth/auth.service";
import {LocalToken} from "../../model/local-token";
import {Router} from "@angular/router";


@Component({
  selector: 'my-navbar',
  template: require('./navbar.component.html'),
  styles: [require('./navbar.component.css')],
  changeDetection: ChangeDetectionStrategy.OnPush,
  directives: [ROUTER_DIRECTIVES, CORE_DIRECTIVES]
})


export class NavbarComponent implements OnInit {


  constructor(private authService:AuthService,private router:Router) {
  }

  @Input() brand:string;

  ngOnInit() {

  }



  logout() {
    this.authService.logout();
    return false;
  }

  getLoggedIn() {
    return this.authService.getLoggedIn();
  }

  // 已经登录的,导航到个人会员首页
  gotoPersonalHome(){
    const tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));
    this.router.navigate(['/profile',tokenObj.userId]);
  }

}
