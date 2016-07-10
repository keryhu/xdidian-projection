/**
 * @Description : please enter the description
 * @date : 16/6/29 上午10:07
 * @author : keryHu keryhu@hotmail.com
 */

import {LoginComponent} from "./login.component";
import {UnauthenticatedGuard} from "../../shared/services/guard/unauthenticated.guard";

export const LoginRoute=[
  {
    path: 'login',
    component: LoginComponent,
    canActivate:[UnauthenticatedGuard]
  }
]
