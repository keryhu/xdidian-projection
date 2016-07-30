/**
 * @Description : please enter the description
 * @date : 16/7/10 上午11:24
 * @author : keryHu keryhu@hotmail.com
 */

import {UnauthenticatedGuard} from "./unauthenticated.guard";
import {AuthenticatedGuard} from "./authenticated.guard";
import {CompanyAdminGuard} from "./company-admin.guard";
import {DefaultGuard} from "./default.guard";
import {SomeDepartmentGuard} from "./some-department";
import {XdidianAdminGuard} from "./xdidian-admin.guard";
import {XdidianServiceGuard} from "./xdidian-service.guard";


export * from './authenticated.guard';
export * from './unauthenticated.guard';


export const GUARD_PROVIDERS = [AuthenticatedGuard,UnauthenticatedGuard,
  CompanyAdminGuard,DefaultGuard,SomeDepartmentGuard,XdidianAdminGuard,XdidianServiceGuard];
