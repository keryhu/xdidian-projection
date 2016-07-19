import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {RequestService} from "../../../shared/services/auth/request.service";

/**
 * @Description : 各个角色,注册的service
 * @date : 16/7/17 上午7:53
 * @author : keryHu keryhu@hotmail.com
 */

@Injectable()
export class SignupService {

  private propertyUrl = '/property-signup/signup';

  constructor(private http:Http, private request:RequestService) {
  }

  //物业公司注册具体实现方法
  create(data) {
    return this.http.post(this.propertyUrl, JSON.stringify(data),
      {headers: this.request.getJsonHeaders()})
      .map(res=>res.json())
     ;
  }
}
