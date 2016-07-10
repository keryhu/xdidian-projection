/**
 * @Description : please enter the description
 * @date : 16/6/23 下午7:50
 * @author : keryHu keryhu@hotmail.com
 */

import {ReflectiveInjector, Injectable} from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
import {FormControl} from '@angular/forms';
import {UserQueryService} from "../query/user-query.service";
import {StringFormatValidator} from "./string-format.validator";

@Injectable()
export  class EmailRemoteValidate {

  constructor(private userQueryService:UserQueryService){}

    email(control:FormControl){

      return new Observable((obs:any) => {

        control.valueChanges.debounceTime(300)
          .filter(e=>StringFormatValidator.emailOfBoolean(e))
          .switchMap(e=>this.userQueryService.emailExist(e))
          .subscribe(e=> {
            console.log('正在输入的e is : ' + e);
            if(e){
              obs.next({emailExist: true});
            }
            else {
              obs.next(null);
            }
            obs.complete();
          })

      })

    }
}
