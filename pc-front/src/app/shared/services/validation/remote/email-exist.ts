import {UserQueryService} from "../../query/user-query.service";
import {HTTP_PROVIDERS} from "@angular/http";
import {ReflectiveInjector, Injector} from "@angular/core";
import {StringFormatValidator} from "../string-format.validator";
import {Observable} from "rxjs/Rx";
import {FormControl} from "@angular/forms";
/**
 * @Description : 用于注册的时候,如果email已经存在了,则报错
 * @date : 16/7/16 上午9:21
 * @author : keryHu keryhu@hotmail.com
 */


export default function EmailExist(control:FormControl) {

  const injector: Injector = ReflectiveInjector.resolveAndCreate([UserQueryService,HTTP_PROVIDERS]);
  const http = injector.get(UserQueryService);

  //这个用于注册的时候,验证email是否已经存在于数据库

    return new Observable((obs:any) => {
      control.valueChanges.debounceTime(300)
        .distinctUntilChanged()
        .filter(e=>StringFormatValidator.emailOfBoolean(e))
        .switchMap(e=>http.emailExist(e))
        .subscribe(e=> {
          if(e){
            obs.next({'emailExist': true});
          }
          else {
            obs.next(null);
          }
          obs.complete();
        })

    })

}
