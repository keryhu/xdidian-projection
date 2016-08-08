/**
 * @Description : 如果用户已经登录了,在浏览器里面输入 login 路由的话,自动跳转 个人用户首页
 * @date : 16/7/10 上午11:24
 * @author : keryHu keryhu@hotmail.com
 */

import {Injectable} from "@angular/core";
import {CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";
import {AuthService} from "../auth/auth.service";
import {LocalToken} from "../../model/local-token";
@Injectable()
export class UnauthenticatedGuard implements CanActivate{

  constructor(private authService:AuthService,private router:Router){}

  canActivate(next:ActivatedRouteSnapshot, state:RouterStateSnapshot){
    if(!this.authService.isLoggedIn()){
      return true;
    }
    const tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));
    this.router.navigate(['/profile',tokenObj.userId]);
    return false;
  }

}
