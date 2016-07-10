/**
 * @Description : 物业公司登录成功后,出现的首页
 * @date : 16/6/21 下午3:57
 * @author : keryHu keryhu@hotmail.com
 */
import {Component, OnInit} from  '@angular/core';
import {Router} from "@angular/router";
import {Title} from "@angular/platform-browser";


@Component({
  selector: 'home',
  template: require('./property-home.component.html'),
  styles: [require('./property-home.component.css')],
  providers: []
})


export class PropertyHomeComponent implements OnInit {

  constructor(private router:Router, private titleService:Title) {
  }

  ngOnInit() {
    this.setTitle();
  }

  public setTitle() {
    this.titleService.setTitle('新地点物业公司主页');
  }


}
