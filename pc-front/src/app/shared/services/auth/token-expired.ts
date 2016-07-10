/**
 * @Description : please enter the description
 * @date : 16/7/1 下午4:50
 * @author : keryHu keryhu@hotmail.com
 */

import {LocalToken} from "../../model/local-token";

export default function tokenExpired():boolean {


  const tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));

  if(tokenObj&&tokenObj.access_token&&tokenObj.expires_in){


    return +tokenObj.expires_in<=Date.now();
  }

  return true;



}


