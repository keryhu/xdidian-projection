/**
 * @Description : 租户的guard  拥有 ['ROLE_TENANT','ROLE_SERVICE','ROLE_ADMIN'] 任何一种role的人才能访问此页面
 * @date : 16/7/10 下午1:38
 * @author : keryHu keryhu@hotmail.com
 */


import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, Router} from '@angular/router';
import {Injectable} from '@angular/core';
import {AuthService} from "../auth/auth.service";


@Injectable()
export class TenantGuard implements CanActivate {


  constructor( private auth:AuthService, private router:Router) {

  }

  canActivate(next:ActivatedRouteSnapshot, state:RouterStateSnapshot) {

    const roles=['ROLE_TENANT','ROLE_SERVICE','ROLE_ADMIN'];

    if (this.auth.hasAnyRole(roles)) {
      return true;
    }
    this.router.navigate(['accessDenied']);
    return false;

  }

}
