/**
 * @Description : please enter the description
 * @date : 16/6/19 上午10:19
 * @author : keryHu keryhu@hotmail.com
 */
import {Component, OnInit} from  '@angular/core';
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'home',
  template: require('./home.component.html'),
  styles: [require('./home.component.css')]
})


export class HomeComponent implements OnInit{

  constructor(private titleService:Title){}

  ngOnInit(){
    this.setTitle();
  }

  public setTitle(){
    this.titleService.setTitle('新地点首页');
  }



}
