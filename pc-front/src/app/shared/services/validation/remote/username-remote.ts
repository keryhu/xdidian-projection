import {FormControl} from "@angular/forms";
import {StringFormatValidator} from "../string-format.validator";
import {Observable} from "rxjs/Rx";
import {ReflectiveInjector, Injector} from "@angular/core";
import {UserQueryService} from "../../query/user-query.service";
import {HTTP_PROVIDERS} from "@angular/http";
/**
 * @Description : 用户登陆的时候,验证用户名。这里只有 异步验证,非异步验证不放在这里
 * @date : 16/7/16 上午9:26
 * @author : keryHu keryhu@hotmail.com
 */

export default function UsernameRemoteValidator(control:FormControl) {

  const injector:Injector = ReflectiveInjector.resolveAndCreate([UserQueryService, HTTP_PROVIDERS]);
  const http = injector.get(UserQueryService);

  //这个用于登录的 时候,验证用户输入的email是否已经注册。如果未注册,则报错。
  if (StringFormatValidator.emailOfBoolean(control.value)) {
    return new Observable((obs:any) => {
      control.valueChanges.debounceTime(300)
        .distinctUntilChanged()
        .switchMap(e=>http.emailExist(e))
        .subscribe(e=> {
          if (!e) {
            obs.next({'emailNotExist': true});
          }
          else {
            obs.next(null);
          }
          obs.complete();
        })

    })
  }
  //用于登录的时候,验证手机号是否已经注册,如果未注册,则报错。
  else if (StringFormatValidator.phoneOfBoolean(control.value)) {
    return new Observable((obs:any)=> {
      control.valueChanges.debounceTime(300)
        .distinctUntilChanged()
        .switchMap(e=>http.phoneExist(e))
        .subscribe(e=> {
          if (!e) {
            obs.next({'phoneNotExist': true});
          }
          else {
            obs.next(null);
          }
          obs.complete();
        })
    })

  }
}

