/**
 * @Description : please enter the description
 * @date : 16/7/10 下午1:19
 * @author : keryHu keryhu@hotmail.com
 */

import {Component,OnInit} from '@angular/core';
import {CORE_DIRECTIVES} from "@angular/common";
import {Title} from "@angular/platform-browser";

@Component({
  selector:'access-denied',
  template:require('./access-denied.component.html'),
  styles:[require('./access-denied.component.css')],
  directives: [CORE_DIRECTIVES]
})

export class AccessDeniedComponent implements OnInit{
  constructor(private titleService:Title){}

  ngOnInit(){

  }

  public setTitle(){
    this.titleService.setTitle('新地点无权访问页面')
  }
}
