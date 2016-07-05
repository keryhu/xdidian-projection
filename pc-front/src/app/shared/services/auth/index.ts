/**
 * @Description : please enter the description
 * @date : 16/6/22 下午3:35
 * @author : keryHu keryhu@hotmail.com
 */

import {AuthGuard} from "./auth.guard";
import {AuthService} from "./auth.service";
import {PrincipalService} from "./principal.service";
import {ConstantService} from "../constant.service";
import {TokenRest} from "./token-rest";
import {ConnectionBackend} from "@angular/http";
import {RequestService} from "./request.service";


export * from './auth.guard';
export * from './auth.service';
export * from './principal.service';
export * from './token-expired';
export * from './token-rest';
export * from './request.service';


export const AUTH_PROVIDERS = [AuthGuard, AuthService, PrincipalService, ConstantService,
  TokenRest, ConnectionBackend,RequestService];





