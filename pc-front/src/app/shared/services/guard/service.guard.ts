/**
 * @Description : 拥有客服权限的guard,拥有['ROLE_ADMIN','ROLE_SERVICE'] 的人员才能访问此页面
 * @date : 16/7/10 上午11:24
 * @author : keryHu keryhu@hotmail.com
 */


import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, Router} from '@angular/router';
import {Injectable} from '@angular/core';
import {AuthService} from "../auth/auth.service";


@Injectable()
export class ServiceGuard implements CanActivate {


  constructor( private auth:AuthService, private router:Router) {

  }

  canActivate(next:ActivatedRouteSnapshot, state:RouterStateSnapshot) {

    const roles=['ROLE_ADMIN','ROLE_SERVICE'];

    if (this.auth.hasAnyRole(roles)) {
      return true;
    }
    this.router.navigate(['accessDenied']);
    return false;

  }

}
