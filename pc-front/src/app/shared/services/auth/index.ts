/**
 * @Description : please enter the description
 * @date : 16/6/22 下午3:35
 * @author : keryHu keryhu@hotmail.com
 */

import {AuthGuard} from "./auth.guard";
import {AuthService} from "./auth.service";
import {PrincipalService} from "./principal.service";
import {ConstantService} from "../constant.service";


export * from './auth.guard';
export * from './auth.service';
export * from './principal.service';
export * from './unauth-guard.service';



export const AUTH_PROVIDERS=[AuthGuard, AuthService, PrincipalService,ConstantService]


