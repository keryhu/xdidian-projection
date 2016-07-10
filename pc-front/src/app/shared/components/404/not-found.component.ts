/**
 * @Description : please enter the description
 * @date : 16/6/21 上午9:59
 * @author : keryHu keryhu@hotmail.com
 */

import {Component, OnInit} from '@angular/core';
import { CORE_DIRECTIVES } from '@angular/common';
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'not-found',
  template: require('./not-found.component.html'),
  styles: [require('./not-found.component.css')],
  directives: [CORE_DIRECTIVES]

})
export class NotFoundComponent implements OnInit{
  constructor(private titleService:Title){}

  ngOnInit(){
    this.setTitle();
  }

  public setTitle(){
    this.titleService.setTitle('新地点404页面')
  }

}
