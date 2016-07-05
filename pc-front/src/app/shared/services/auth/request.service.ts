/**
 * @Description : 设置全局的header
 * @date : 16/7/4 上午9:58
 * @author : keryHu keryhu@hotmail.com
 */

import {Headers} from "@angular/http";
import {Injectable} from "@angular/core";
import {ConstantService} from "../constant.service";
import {LocalToken} from "../../model/local-token";

@Injectable()
export class RequestService {
  private tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));
  private clientId:string = ConstantService.clientId;
  private clientSecret:string = ConstantService.clientSecret;
  private basicSecret:string;

  constructor() {
    this.basicSecret = btoa(`${this.clientId}:${this.clientSecret}`);
  }

  getJsonHeaders() {
    let headers = new Headers();

    headers.append('Content-Type', 'application/json');
    return headers;
  }

  getAuthHeaders() {
    let headers = this.getJsonHeaders();
    let token=this.tokenObj.access_token;
    headers.append('Authorization', `Bearer ${token}`);
    return headers;
  }


  getLoginHeaders() {

    let headers = new Headers();

    headers.append('Content-Type', 'application/x-www-form-urlencoded');
    headers.append('Authorization', 'Basic ' + this.basicSecret);
    return headers;
  }
}
