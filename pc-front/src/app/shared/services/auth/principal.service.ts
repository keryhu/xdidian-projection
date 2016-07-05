/**
 * @Description : 获取当前用户的service
 * @date : 16/6/21 下午3:40
 * @author : keryHu keryhu@hotmail.com
 */

import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Rx';


@Injectable()
export class PrincipalService {
  constructor(private http:Http) {
  }

  //因为有多个地方,需要用到 查看是否可以取得当前登录用户,所以这里使用了 share()
  currentUser():Observable<any> {
    return this.http.get("/user")
      .map((res:Response)=> res.json())
      .catch(this.handleError)
      ;

  }


  private handleError(error:Response) {
    return Observable.throw(error.json() || 'Server error');
  }

}
