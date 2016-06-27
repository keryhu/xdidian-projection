/**
 * @Description : please enter the description
 * @date : 16/6/22 下午3:28
 * @author : keryHu keryhu@hotmail.com
 */

import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {Observable} from "rxjs/Rx";

import {ConstantService} from "../constant.service";
import {PrincipalService} from "./principal.service";


@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private principal:PrincipalService) {

  }

  canActivate(next:ActivatedRouteSnapshot, state:RouterStateSnapshot) {

    return this.principal.currentUser().map(e=> {
      if (e) {
        return true;
      }
    }).catch(()=> {
      window.location.href = ConstantService.loginUrl;
      return Observable.of(false)
    })
  }

}
