/**
 * @Description : 用户登陆成功后,跳转的首页面,如果有公司的,在此加载公司的信息,包含上下级人员。
 * @date : 16/6/21 下午3:57
 * @author : keryHu keryhu@hotmail.com
 */
import {Component, OnInit, OnDestroy} from  '@angular/core';
import {Router, ROUTER_DIRECTIVES} from "@angular/router";
import {Title} from "@angular/platform-browser";
import {SideMenuComponent} from "../../../shared/components/side-menu/side-menu.component";


@Component({
  selector: 'profile-home',
  template: require('./profile-home.component.html'),
  styles: [require('./profile-home.component.css')],
  providers: [],
  directives: [SideMenuComponent,ROUTER_DIRECTIVES]
})


export class ProfileHomeComponent implements OnInit,OnDestroy {

  constructor(private titleService:Title) {
  }

  ngOnInit() {

    this.setTitle();
  }

  public setTitle() {
    this.titleService.setTitle('新地点个人主页');
  }

  ngOnDestroy(){

  }

}
