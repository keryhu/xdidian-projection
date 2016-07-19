import {UserQueryService} from "../../query/user-query.service";
import {HTTP_PROVIDERS} from "@angular/http";
import {ReflectiveInjector, Injector} from "@angular/core";
import {Observable} from "rxjs/Rx";
import {StringFormatValidator} from "../string-format.validator";
import {FormControl} from "@angular/forms";
/**
 * @Description : 用户注册的时候,如果手机号存在,则报错。
 * @date : 16/7/19 下午3:53
 * @author : keryHu keryhu@hotmail.com
 */

export default function PhoneRemote(control:FormControl) {

  const injector:Injector = ReflectiveInjector.resolveAndCreate([UserQueryService, HTTP_PROVIDERS]);
  const http = injector.get(UserQueryService);

  //这个用于注册的 时候,验证用户输入的手机号是否已经注册。如果已经注册,则报错。

  if (StringFormatValidator.phoneOfBoolean(control.value)) {
    return new Observable((obs:any) => {
      control.valueChanges.debounceTime(300)
        .distinctUntilChanged()
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

    })
  }



}
