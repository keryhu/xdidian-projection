/**
 * @Description : 如果需要客户登录的路有,但是客户没有登录,就自动跳转到 login页面
 * @date : 16/7/10 上午11:24
 * @author : keryHu keryhu@hotmail.com
 */
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, Router} from '@angular/router';
import {Injectable} from '@angular/core';
import {AuthService} from "../auth/auth.service";


@Injectable()
export class AuthenticatedGuard implements CanActivate {


  constructor( private auth:AuthService, private router:Router) {

  }

  canActivate(next:ActivatedRouteSnapshot, state:RouterStateSnapshot) {

    if (this.auth.isLoggedIn()) {
      return true;
    }
    this.router.navigate(['login']);
    return false;

  }

}
