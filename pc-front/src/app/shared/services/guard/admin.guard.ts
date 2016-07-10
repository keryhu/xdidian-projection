/**
 * @Description : admin guard , 拥有权限  ROLE_ADMIN 可以访问任何 路由
 * @date : 16/7/10 上午11:24
 * @author : keryHu keryhu@hotmail.com
 */
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, Router} from '@angular/router';
import {Injectable} from '@angular/core';
import {AuthService} from "../auth/auth.service";


@Injectable()
export class AdminGuard implements CanActivate {


  constructor( private auth:AuthService, private router:Router) {

  }

  canActivate(next:ActivatedRouteSnapshot, state:RouterStateSnapshot) {

    if (this.auth.hasRole('ROLE_ADMIN')) {
      return true;
    }
    this.router.navigate(['accessDenied']);
    return false;

  }

}
