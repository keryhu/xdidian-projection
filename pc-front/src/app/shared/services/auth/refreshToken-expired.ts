import {LocalToken} from "../../model/local-token";
/**
 * @Description : 返回refresh token 的过期时间
 * @date : 16/7/11 下午12:03
 * @author : keryHu keryhu@hotmail.com
 */


export default function RefreshTokenExpired():boolean{
  const tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));
  if(tokenObj){
    return tokenObj.refreshToken_expires_in<= Date.now();
  }
  return true;
}
