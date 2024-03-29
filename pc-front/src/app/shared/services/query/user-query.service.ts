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
    const url = '/api-user-account/users/query/isEmailExist';
    const params = new URLSearchParams();
    params.set('email', email);

    return this.http.get(url, {search: params})
      .map((res:Response)=>{
         return res.json();
      } )
      .catch(this.handleError);

  }

  phoneExist(phone:string):Observable<boolean>{
    const url='/api-user-account/users/query/isPhoneExist';
    const params = new URLSearchParams();
    params.set('phone', phone);

    return this.http.get(url,{search:params})
      .map((res:Response)=>{
        return res.json();
      } )
      .catch(this.handleError);

  }

  companyNameExist(name:string):Observable<boolean>{

    const url='/api-user-account/users/query/isCompanyNameExist';
    const params = new URLSearchParams();
    params.set('companyName', name);

    return this.http.get(url,{search:params})
      .map((res:Response)=>{
        return res.json();
      })
      .catch(this.handleError);
  }


  //查询email所在的状态有没有激活,如果已经激活,直接跳转login页面。
  emailStatus(id:string):Observable<boolean>{
    const url='/api-user-account/users/query/emailStatus';
    const params = new URLSearchParams();
    params.set('loginName', id);

    return this.http.get(url,{search:params})
      .map((res:Response)=>{
        return res.json();
      })
      .catch(this.handleError);
  }




  private handleError(error:Response) {
    console.error(error);
    return Observable.throw(error || 'Server error');
  }

}
