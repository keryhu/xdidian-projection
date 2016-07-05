/**
 * @Description : please enter the description
 * @date : 16/6/22 下午3:28
 * @author : keryHu keryhu@hotmail.com
 */


import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, Router} from '@angular/router';
import {Injectable} from '@angular/core';
import {AuthService} from "./auth.service";
import tokenExpired from "./token-expired";


@Injectable()
export class AuthGuard implements CanActivate {

  constructor( private auth:AuthService, private router:Router) {

  }

  canActivate(next:ActivatedRouteSnapshot, state:RouterStateSnapshot) {

    if (this.auth.isLoggedIn()) {
      console.log('没有过期')
      return true;
    }
    this.router.navigate(['login']);
    return false;

  }

}
