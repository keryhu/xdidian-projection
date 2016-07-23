/**
 * @Description : 设置全局的header
 * @date : 16/7/4 上午9:58
 * @author : keryHu keryhu@hotmail.com
 */

import {Headers, Response} from "@angular/http";
import {Injectable} from "@angular/core";
import {ConstantService} from "../constant.service";
import {LocalToken} from "../../model/local-token";
import {Observable} from "rxjs/Rx";

@Injectable()
export class RequestService  {
  private tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));
  private clientId:string = ConstantService.clientId;
  private clientSecret:string = ConstantService.clientSecret;
  private basicSecret:string;

  //service , contstrctor 中的方法,不能放到 ngOnInit 中
  constructor() {
    this.basicSecret = btoa(`${this.clientId}:${this.clientSecret}`);
  }



  getJsonHeaders() {
    const headers = new Headers();

    headers.append('Content-Type', 'application/json');
    return headers;
  }

  getAuthHeaders() {
    const headers = this.getJsonHeaders();
    const token = this.tokenObj.access_token;
    headers.append('Authorization', `Bearer ${token}`);
    return headers;
  }


  getLoginHeaders() {

    const headers = new Headers();

    headers.append('Content-Type', 'application/x-www-form-urlencoded');
    headers.append('Authorization', 'Basic ' + this.basicSecret);
    return headers;
  }

  //默认的错误处理,这个指spring 后台报的 RuntimeException
  //首先从2个eror 对象中取出,自己需要的 error 对象 {"code":404,"message":"sss"}
  defaultHandlerError(error:Response){
    console.log(error);

    //将错误信息,转为数组,找到'}"开始之后的内容,就是自定义的的内容。
    const x=error['_body'];
    //寻找到第一个对象结尾的下标,
    if(x.startsWith('{')){

      const index:number=x.indexOf('}');

      //如果返回的对象只有一个{},
      if(index===x.length-1){
        const m = JSON.parse(x);
        return Observable.throw(m.message || 'Server error');
      }
      //将后面的剩余对象取出来。
      const newN=x.substring(index+1);
      const m = JSON.parse(newN);
      console.log(m);
      return Observable.throw(m.message || 'Server error');
    }

    //如果返回的不是 {} 的类型,那么直接返回
    return Observable.throw(x || 'Server error');

  }
}
