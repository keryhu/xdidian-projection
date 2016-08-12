/**
 * @Description : 从后台去除side-menu 的 菜单名字, url等数组。
 * @date : 16/8/11 下午3:53
 * @author : keryHu keryhu@hotmail.com
 */


import {Injectable} from "@angular/core";
import {URLSearchParams} from "@angular/http";
import {RequestService} from "../auth/request.service";
import {Http} from "@angular/http";
import {LocalToken} from "../../model/local-token";
import {Response} from "@angular/http";
import {Headers} from "@angular/http";

@Injectable()
export class SideMenuService{
  private headers = new Headers();

  constructor(private http:Http,private request:RequestService){

  }

  public getMenus(){
    const tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));
    const  url='/api-personal-menu/query/menus';
    const params = new URLSearchParams();
    params.set('id', tokenObj.userId);
    this.headers.append('Content-Type', 'application/json');
    this.headers.append('Authorization', `Bearer ${tokenObj.access_token}`);

    return this.http.get(url,{search:params,headers:this.headers})
      .map((res:Response)=>{
        return res.json()
      })
      .catch(this.request.defaultHandlerError);
  }


}
