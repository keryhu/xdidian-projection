/**
 * @Description : 左边菜单栏
 * @date : 16/8/5 上午9:07
 * @author : keryHu keryhu@hotmail.com
 */


import {Component, ChangeDetectionStrategy, OnInit} from "@angular/core";
import {CORE_DIRECTIVES} from "@angular/common";
import {ROUTER_DIRECTIVES} from "@angular/router";
@Component({
  selector: 'side-nav',
  template: require('./side-nav.component.html'),
  styles: [require('./side-nav.component.css')],
  changeDetection: ChangeDetectionStrategy.OnPush,
  directives: [ROUTER_DIRECTIVES, CORE_DIRECTIVES]
})


export class SideNavComponent implements OnInit{
  constructor(){

  }

  ngOnInit(){
    console.log('side nav');
  }
}
