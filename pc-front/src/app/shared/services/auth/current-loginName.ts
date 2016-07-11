/**
 * @Description : 返回当前用户 的 登录帐号。
 * @date : 16/7/11 下午4:53
 * @author : keryHu keryhu@hotmail.com
 */

import {LocalToken} from "../../model/local-token";

export default function CurrentLoginName():string{

  const tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));

  if(tokenObj){
    return tokenObj.loginName;
  }
  return null;
}
