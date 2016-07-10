/**
 * @Description : please enter the description
 * @date : 16/6/19 下午8:57
 * @author : keryHu keryhu@hotmail.com
 */

import { PropertyHomeComponent } from './property-home.component';
import {AuthenticatedGuard} from "../../../shared/services/guard/authenticated.guard";
import {PropertyGuard} from "../../../shared/services/guard/property.guard";

export const PropertyHomeRoute=[
    {
      path: 'property',
      component: PropertyHomeComponent,
      canActivate: [AuthenticatedGuard,PropertyGuard]
    }
];
