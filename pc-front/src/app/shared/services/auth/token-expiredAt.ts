import {LocalToken} from "../../model/local-token";
/**
 * @Description : access-token 过期的时间点。
 * @date : 16/7/11 上午11:38
 * @author : keryHu keryhu@hotmail.com
 */

//如果tokenObj 不存在,那么就返回 当前时间,表示立马就过期。
export default function TokenExpiredAt():number{
  const tokenObj:LocalToken = JSON.parse(localStorage.getItem('token'));

  if (tokenObj) {
    const token = tokenObj.access_token;
    if (token) {
      const userData = JSON.parse(window.atob(token.split('.')[1]));
      return userData.exp * 1000;

    }

  }

  return Date.now();

}
