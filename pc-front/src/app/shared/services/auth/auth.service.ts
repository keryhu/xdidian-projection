/**
 * @Description : please enter the description
 * @date : 16/6/22 下午3:16
 * @author : keryHu keryhu@hotmail.com
 */

import {Injectable} from '@angular/core';
import {Observable, AsyncSubject} from "rxjs/Rx";

import {PrincipalService} from './principal.service';
import {ConstantService} from "../constant.service";



@Injectable()
export class AuthService {
  isLogged :boolean=false;

  constructor(private principalService:PrincipalService) {
    this.authenticated();
  }

  authenticated() {
    this.principalService.currentUser().subscribe(e=>{
      if (e){
        console.log('auth service authenticated() e is : '+e);
        this.isLogged=true;
      }
    })
  }


}
