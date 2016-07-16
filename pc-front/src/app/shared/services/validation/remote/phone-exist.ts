import {UserQueryService} from "../../query/user-query.service";
import {HTTP_PROVIDERS} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {ReflectiveInjector, Injector} from "@angular/core";
import {FormControl} from "@angular/forms";
import {StringFormatValidator} from "../string-format.validator";
/**
 * @Description : 用于用户注册的时候,如果手机号已经存在则报错。
 * @date : 16/7/16 上午9:40
 * @author : keryHu keryhu@hotmail.com
 */

export default function PhoneExist(control:FormControl) {

  const injector:Injector = ReflectiveInjector.resolveAndCreate([UserQueryService, HTTP_PROVIDERS]);
  const http = injector.get(UserQueryService);

  return new Observable((obs:any)=> {
    control.valueChanges.debounceTime(300)
      .distinctUntilChanged()
      .filter(e=>StringFormatValidator.phoneOfBoolean(e))
      .switchMap(e=>http.phoneExist(e))
      .subscribe(e=> {
        if (e) {
          obs.next({'phoneExist': true});
        }
        else {
          obs.next(null);
        }
        obs.complete();
      })

}
