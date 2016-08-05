/**
 * @Description : please enter the description
 * @date : 16/6/19 下午8:57
 * @author : keryHu keryhu@hotmail.com
 */

import {ProfileHomeComponent} from './profile-home.component';
import {AuthenticatedGuard} from "../../../shared/services/guard/authenticated.guard";
import {DefaultGuard} from "../../../shared/services/guard/default.guard";
import {ProfileDetailComponent} from "../details/profile-detail.component";

export const ProfileHomeRoute = [
  {
    path: 'profile',
    component: 'ProfileHomeComponent',
    canActivate: [AuthenticatedGuard, DefaultGuard]
  },
  {
    path:'profile/:id',
    component: ProfileDetailComponent,
    canActivate: [AuthenticatedGuard,DefaultGuard]
  }
];
