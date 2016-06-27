/**
 * @Description : please enter the description
 * @date : 16/6/22 下午3:38
 * @author : keryHu keryhu@hotmail.com
 */

import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

import { AuthService } from './auth.service';
import { ConstantService } from "../constant.service";


@Injectable()
export class UnauthGuard implements CanActivate{
  loginUrl:string;
  constructor(private auth:AuthService,private router: Router){
    this.loginUrl=ConstantService.loginUrl;
  }

  canActivate(next: ActivatedRouteSnapshot,state: RouterStateSnapshot):boolean{
    if (!this.auth.isLogged) return true;

    this.router.navigate([this.loginUrl]);
    return false;
  }
}

