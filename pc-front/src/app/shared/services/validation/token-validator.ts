/**
 * @Description : post 前台页面输入的token 进行后台验证,包含email,phone token验证。
 * 这个是email,phone 激活验证
 * @date : 16/7/20 下午12:10
 * @author : keryHu keryhu@hotmail.com
 */

import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {RequestService} from "../auth/request.service";
import {ConstantService} from "../constant.service";


@Injectable()
export class TokenValidator {


  constructor(private http:Http, private request:RequestService) {
  }

  email(data) {

    const url:string = '/api-account-activate/accountActivate/emailOrPhone';
    return this.http.post(url, JSON.stringify(data),
      {headers: this.request.getJsonHeaders()})
      .map((res:Response)=> {
        console.log(res);
        //如果激活成功,处理办法。
        if (res.status==200) {
          return res['_body'];
        }
        else{
          return res.json()
        }

      })
      .catch(this.request.defaultHandlerError)
  }

  emailResend(data) {
    const url = '/api-account-activate/email/resend';
    return this.http.post(url,JSON.stringify(data),
      {headers: this.request.getJsonHeaders()})
      .map(res=>{

        if (res.status==200) {
          return res['_body'];
        }
        else{
          return res.json()
        }
      })
      .catch(this.request.defaultHandlerError)
  }

  emailResignup(data){
    const url='/api-account-activate/email/resignup';

    return this.http.post(url,JSON.stringify(data),
      {headers: this.request.getJsonHeaders()})
      .map(res=>{

        if (res.status==200) {
          return res['_body'];
        }
        else{
          return res.json()
        }
      })
      .catch(this.request.defaultHandlerError)
  }


}
