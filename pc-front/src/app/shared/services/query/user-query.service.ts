/**
 * @Description : please enter the description
 * @date : 16/6/23 下午4:07
 * @author : keryHu keryhu@hotmail.com
 */

import {Http, Response, URLSearchParams} from '@angular/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class UserQueryService {

  constructor(private http:Http) {
  }

  private baseUrl = `/user-account/users/query/isEmailExist`;

  emailExist(email:string):Observable<string> {
    let params = new URLSearchParams();
    params.set('email', email);

    //noinspection TypeScriptUnresolvedFunction
    return this.http.get(this.baseUrl, {search: params})
      .map((res:Response)=>{
        console.log('被查询的email is : - '+email)
        console.log(`user-query-service is running ..被查询的email 是: ${email}。。. 结果是: ${res.json()}`);
        return res.json();
      } )
      .catch(this.handleError);

  }


  private handleError(error:Response) {
    console.error(error);
    return Observable.throw(error || 'Server error');
  }

}
