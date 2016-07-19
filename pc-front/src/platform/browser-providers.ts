/**
 * @Description : 这里专门用来设置全局 service provider
 * @date : 16/7/16 下午1:22
 * @author : keryHu keryhu@hotmail.com
 */


import {HTTP_PROVIDERS} from "@angular/http";
import {disableDeprecatedForms, provideForms} from "@angular/forms";

// AngularClass
import { provideWebpack } from '@angularclass/webpack-toolkit';
import { providePrefetchIdleCallbacks } from '@angularclass/request-idle-callback';

import {Title} from "@angular/platform-browser";
import {asyncRoutes, prefetchRouteCallbacks, APP_ROUTER_PROVIDERS} from "../app/app.routes";



export const APPLICATION_PROVIDERS = [
  disableDeprecatedForms(),
  provideForms(),
  Title,
  provideWebpack(asyncRoutes),
  providePrefetchIdleCallbacks(prefetchRouteCallbacks),
  ...HTTP_PROVIDERS,
  ...APP_ROUTER_PROVIDERS
  //...BOOTSTRAP3_VIEWPROVIDERS
];

export const PROVIDERS = [
  ...APPLICATION_PROVIDERS
];
