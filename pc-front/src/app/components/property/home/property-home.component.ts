/**
 * @Description : 物业公司登录成功后,出现的首页
 * @date : 16/6/21 下午3:57
 * @author : keryHu keryhu@hotmail.com
 */
import { Component,OnInit } from  '@angular/core';
import {PrincipalService} from "../../../shared/services/auth/principal.service";
import {Router} from "@angular/router";



@Component({
  selector: 'home',
  template: require('./property-home.component.html'),
  styles: [require('./property-home.component.css')],
  providers: [PrincipalService]
})


export class PropertyHomeComponent implements OnInit {

  constructor(private principalService:PrincipalService,private router:Router){}

  ngOnInit(){

    }





}
