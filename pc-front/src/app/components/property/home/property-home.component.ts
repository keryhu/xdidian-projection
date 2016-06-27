/**
 * @Description : 物业公司登录成功后,出现的首页
 * @date : 16/6/21 下午3:57
 * @author : keryHu keryhu@hotmail.com
 */
import { Component,OnInit } from  '@angular/core';
import {PrincipalService} from "../../../shared/services/auth/principal.service";
import {role} from "../../../shared/interface/role";



@Component({
  selector: 'home',
  template: require('./property-home.component.html'),
  styles: [require('./property-home.component.css')],
  providers: [PrincipalService]
})


export class PropertyHomeComponent implements OnInit {
  constructor(private principalService:PrincipalService){}

  ngOnInit(){
    //this.getProfile();
  }

  getProfile(){
    this.principalService.currentUser()
      .subscribe(
        e=>{
          if (!!e&&e.isLogged){
            let roles:role[]=e.authorities;
            roles.forEach(e=>console.log('权限是 : '+e.authority));
            console.log('是否验证'+e.authenticated+' , id is : '+e.name);
            console.log(e);
          }
          },

        error=>console.log('error : '+error),
        ()=>console.log('completed')

      );

  }


}
