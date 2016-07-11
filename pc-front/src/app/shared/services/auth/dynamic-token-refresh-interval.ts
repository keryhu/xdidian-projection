/**
 * @Description : please enter the description
 * @date : 16/7/11 上午11:35
 * @author : keryHu keryhu@hotmail.com
 */

import TokenExpiredAt from "./token-expiredAt";
import {ConstantService} from "../constant.service";


export default function DynamicTokenRefreshInterval():number{

  const  leftRefreshTokenSeconds:number = ConstantService.minLeftRefreshTokenSeconds;

  return TokenExpiredAt()- Date.now() - 1000 * leftRefreshTokenSeconds;

}
