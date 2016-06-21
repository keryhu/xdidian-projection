/**
 * @Description : 获取当前用户的service
 * @date : 16/6/21 下午3:40
 * @author : keryHu keryhu@hotmail.com
 */

import { Injectable } from '@angular/core';

interface user{
  username: string;
  password: string;
}


@Injectable()
export class ProfileService{
  currentUser(){

  }
}
