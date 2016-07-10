/**
 * @Description : 物业公司  权限  拥有 [ROLE_PROPERTY,ROLE_SERVICE,ROLE_ADMIN],任何一个权限的,都可以访问此路由
 * @date : 16/7/10 上午11:54
 * @author : keryHu keryhu@hotmail.com
 */


import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, Router} from '@angular/router';
import {Injectable} from '@angular/core';
import {AuthService} from "../auth/auth.service";


@Injectable()
export class PropertyGuard implements CanActivate {


  constructor( private auth:AuthService, private router:Router) {

  }

  canActivate(next:ActivatedRouteSnapshot, state:RouterStateSnapshot) {
    const roles=['ROLE_PROPERTY','ROLE_SERVICE','ROLE_ADMIN'];
    
    if (this.auth.hasAnyRole(roles)) {
      return true;
    }
    this.router.navigate(['accessDenied']);
    return false;

  }

}
