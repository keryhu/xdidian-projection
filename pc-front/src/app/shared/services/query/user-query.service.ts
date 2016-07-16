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



  emailExist(email:string):Observable<boolean> {
    const url = '/user-account/users/query/isEmailExist';
    const params = new URLSearchParams();
    params.set('email', email);

    return this.http.get(url, {search: params})
      .map((res:Response)=>{
         return res.json();
      } )
      .catch(this.handleError);

  }

  phoneExist(phone:string):Observable<boolean>{
    const url='/user-account/users/query/isPhoneExist';
    const params = new URLSearchParams();
    params.set('phone', phone);

    return this.http.get(url,{search:params})
      .map((res:Response)=>{
        return res.json();
      } )
      .catch(this.handleError);

  }



  private handleError(error:Response) {
    console.error(error);
    return Observable.throw(error || 'Server error');
  }

}
