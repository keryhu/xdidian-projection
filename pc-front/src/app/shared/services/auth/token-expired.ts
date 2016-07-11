/**
 * @Description : please enter the description
 * @date : 16/7/1 下午4:50
 * @author : keryHu keryhu@hotmail.com
 */

import {LocalToken} from "../../model/local-token";

export default function tokenExpired():boolean {


  const tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));


  if (tokenObj) {
    const token = tokenObj.access_token;
    if (token) {
      const userData = JSON.parse(window.atob(token.split('.')[1]));
      const ex = userData.exp * 1000;
      return ex <= Date.now();
    }
    
  }


  return true;


}



