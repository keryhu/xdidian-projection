/**
 * @Description : please enter the description
 * @date : 16/6/19 下午8:57
 * @author : keryHu keryhu@hotmail.com
 */

import { PropertyHomeComponent } from './property-home.component';
import {AuthGuard} from "../../../shared/services/auth/auth.guard";

export const PropertyHomeRoute=[
    {
      path: 'property',
      component: PropertyHomeComponent,
      canActivate: [AuthGuard]
    }
];
