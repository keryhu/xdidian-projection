import {FormControl} from "@angular/forms";
import {UserQueryService} from "../../query/user-query.service";
import {HTTP_PROVIDERS} from "@angular/http";
import {ReflectiveInjector, Injector} from "@angular/core";
import {Observable} from "rxjs/Rx";
import {StringFormatValidator} from "../string-format.validator";
/**
 * @Description : 远程查询公司名字是否存在。
 * @date : 16/7/19 下午3:10
 * @author : keryHu keryhu@hotmail.com
 */

export default function CompanyNameRemote(control:FormControl) {
  const injector:Injector = ReflectiveInjector.resolveAndCreate([UserQueryService, HTTP_PROVIDERS]);
  const http = injector.get(UserQueryService);

  //这个用于注册的 时候,验证用户输入的公司名字是否已经注册。如果已经注册,则报错。

  if (StringFormatValidator.companyNameOfBoolean(control.value)) {
    return new Observable((obs:any) => {
      control.valueChanges.debounceTime(300)
        .distinctUntilChanged()
        .switchMap(e=>http.companyNameExist(e))
        .subscribe(e=> {
          if (e) {
            obs.next({'companyNameExist': true});
          }
          else {
            obs.next(null);
          }
          obs.complete();
        })

    })
  }


}
