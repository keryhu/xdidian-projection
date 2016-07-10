/**
 * @Description : please enter the description
 * @date : 16/7/10 上午11:24
 * @author : keryHu keryhu@hotmail.com
 */

import {UnauthenticatedGuard} from "./unauthenticated.guard";
import {AuthenticatedGuard} from "./authenticated.guard";
import {AdminGuard} from "./admin.guard";
import {PropertyGuard} from "./property.guard";
import {ServiceGuard} from "./service.guard";
import {TenantGuard} from "./tenant.guard";

export * from './authenticated.guard';
export * from './unauthenticated.guard';


export const GUARD_PROVIDERS = [AuthenticatedGuard,UnauthenticatedGuard,
  AdminGuard,PropertyGuard,ServiceGuard,TenantGuard];
