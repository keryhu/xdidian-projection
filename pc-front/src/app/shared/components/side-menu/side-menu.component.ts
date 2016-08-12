/**
 * @Description : 左边菜单栏
 * @date : 16/8/5 上午9:07
 * @author : keryHu keryhu@hotmail.com
 */


import {Component, ChangeDetectionStrategy, OnInit, OnDestroy} from "@angular/core";
import {CORE_DIRECTIVES} from "@angular/common";
import {ROUTER_DIRECTIVES} from "@angular/router";
import {SideMenuService} from "../../services/query/side-menu.service";
import {Menu} from "../../model/Menu";
import {BehaviorSubject} from "rxjs";
import {Observable} from "rxjs";

@Component({
  selector: 'side-nav',
  template: require('./side-menu.component.html'),
  styles: [require('./side-menu.component.css')],
  changeDetection: ChangeDetectionStrategy.OnPush,
  directives: [ROUTER_DIRECTIVES, CORE_DIRECTIVES],
  providers: [SideMenuService]
})

//从后台取出自定义的   菜单  数组。

export class SideMenuComponent implements OnInit,OnDestroy {

  private menus: Observable<Array<Menu>>;

  constructor(private sideMenuService: SideMenuService) {

  }


  ngOnInit() {
    this.getMenus();
  }

  private getMenus() {
    this.menus = this.sideMenuService.getMenus();
  }

  //可以将菜单导航到不同的页面了
  private nav(menu:Menu){
    console.log(menu.url);
  }

  ngOnDestroy() {


  }
}
