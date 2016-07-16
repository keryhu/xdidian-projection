/**
 * @Description : 查询本地ip,有没有被服务器冻结,返回true,表示冻结,否则返回fasle
 * @date : 16/7/14 下午4:58
 * @author : keryHu keryhu@hotmail.com
 */

import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {RequestService} from "../auth/request.service";

@Injectable()
export class IpBlockStatus{
  private url='/auth-server/uaa/query/blockStatus';
  constructor(private http:Http,private request:RequestService){}

  query():Observable<any>{
    return this.http.get(this.url,{headers:this.request.getJsonHeaders()})
      .map(e=>e.json())
      ;
  }

}
