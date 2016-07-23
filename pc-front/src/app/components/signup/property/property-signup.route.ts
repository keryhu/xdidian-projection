/**
 * @Description : please enter the description
 * @date : 16/6/21 上午11:49
 * @author : keryHu keryhu@hotmail.com
 */
import { PropertySignupComponent } from './property-signup.component';
import {UnauthenticatedGuard} from "../../../shared/services/guard/unauthenticated.guard";

export const PropertySignupRoute=[
  {
    path: 'signup',
    component: 'PropertySignupComponent',
    canActivate:[UnauthenticatedGuard]   //设定了如果已经登录,那么不能访问注册页面  /signup
  }
];
