/**
 * @Description : please enter the description
 * @date : 16/6/22 下午3:35
 * @author : keryHu keryhu@hotmail.com
 */

import {AuthService} from "./auth.service";
import {ConstantService} from "../constant.service";
import {TokenRest} from "./token-rest";
import {ConnectionBackend} from "@angular/http";
import {RequestService} from "./request.service";


export * from './auth.service';
export * from './token-expired';
export * from './token-rest';
export * from './request.service';
export * from './dynamic-token-refresh-interval';
export * from './token-expiredAt';
export * from './current-loginName';


export const AUTH_PROVIDERS = [ AuthService, ConstantService,
  TokenRest, ConnectionBackend,RequestService];






