/**
 * @Description : 左边菜单栏
 * @date : 16/8/5 上午9:07
 * @author : keryHu keryhu@hotmail.com
 */


import {Component, ChangeDetectionStrategy, OnInit, OnDestroy} from "@angular/core";
import {CORE_DIRECTIVES} from "@angular/common";
import {ROUTER_DIRECTIVES} from "@angular/router";
import {TreeModel} from "../tree/tree-model";
import {SideMenuComponent} from "./sideNav-tree/side-menu.component";
@Component({
  selector: 'side-nav',
  template: require('./side-nav.component.html'),
  styles: [require('./side-nav.component.css')],
  changeDetection: ChangeDetectionStrategy.OnPush,
  directives: [ROUTER_DIRECTIVES, CORE_DIRECTIVES,SideMenuComponent]
})


export class SideNavComponent implements OnInit,OnDestroy{
  constructor(){

  }

  sideMenus:Array<TreeModel>=[{
    value: '公司信息',
    url: '/lo',
    children:[
      {
        value:'基本信息',
        url: '/l',
        children:[
          {
            value:'aaa',
            url: '/login',
          },
          {
            value:'bbb',
            url: '/login',
          }
        ]
      }
    ]
  },
    {
      value: '培训',
      url: '/lo',
      children:[
        {
          value:'ddd',
          url: '/l',
          children:[
            {
              value:'aaa',
              url: '/login',},
            {
              value:'bbb',
              url: '/login',
            }
          ]
        }
      ]
    }];

  ngOnInit(){
    console.log('side nav');
  }

  ngOnDestroy(){

  }
}
