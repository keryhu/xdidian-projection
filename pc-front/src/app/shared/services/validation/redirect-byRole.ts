/**
 * @Description : 根据用户之前的role分配不同的页面
 * 目前仅仅实现了分配到 property,物业公司的注册页面。
 * @date : 16/7/20 下午6:54
 * @author : keryHu keryhu@hotmail.com
 */

import {Injectable} from "@angular/core";
import {Router} from "@angular/router";

@Injectable()
export class RedirectByRole{

  constructor(private router:Router){}



  //当用户的激活码激活过期,数据库删除了原来未激活的user数据,需要根据user之前的role,分配到此role所在
  //的注册页面
  toSignup(m:String[]){

    m.filter(e=>e.startsWith('ROLE_'))
      .map(e=>{
        if(Object.is(e,'ROLE_PROPERTY')){
          this.router.navigate(['/signup']);
        }
        else if(Object.is(e,'ROLE_ADMIN')){
          //导航到admin页面
        }
        else if(Object.is(e,'ROLE_TENANT')){
          //导航到租户的注册页面
        }
      });

  }
}
