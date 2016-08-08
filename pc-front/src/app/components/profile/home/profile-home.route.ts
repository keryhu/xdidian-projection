/**
 * @Description : 所有关于profile的子路由,都在这里设置。
 * @date : 16/6/19 下午8:57
 * @author : keryHu keryhu@hotmail.com
 */

import {ProfileHomeComponent} from './profile-home.component';
import {AuthenticatedGuard} from "../../../shared/services/guard/authenticated.guard";
import {DefaultGuard} from "../../../shared/services/guard/default.guard";
import {WebpackAsyncRoute} from "@angularclass/webpack-toolkit/dist/index";

export const ProfileHomeRoute = [
  {
    path: 'profile',
    children:[
      {path: ':id',component: 'ProfileHomeComponent'}
    ],
    canActivate: [AuthenticatedGuard, DefaultGuard,WebpackAsyncRoute]
  }
];
