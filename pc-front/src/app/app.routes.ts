/**
 * @Description : please enter the description
 * @date : 16/6/19 下午7:52
 * @author : keryHu keryhu@hotmail.com
 */

import {RouterConfig, provideRouter} from '@angular/router';
import {HomeRoutes} from './components/home';
import {NotFoundRoutes} from './shared/components/404';
import {LoginRoute} from "./components/login/login.route";
import {AccessDeniedRoutes} from "./shared/components/access-denied/access-denied.route";
import {EmailActivaRoute} from "./shared/components/email-activate/email-activate.route";
import {SignupRoute} from "./components/signup/signup.route";
import {ProfileHomeRoute} from "./components/profile/home/profile-home.route";


const APP_ROUTES:RouterConfig = [
  ...HomeRoutes,
  ...SignupRoute,
  ...ProfileHomeRoute,
  ...LoginRoute,
  ...AccessDeniedRoutes,
  ...EmailActivaRoute,
  ...NotFoundRoutes
];

export const APP_ROUTER_PROVIDERS = [
  provideRouter(APP_ROUTES),


];

//使用异步路由加载
export const asyncRoutes:AsyncRoutes = {

  'SignupComponent': require('es6-promise-loader!./components/signup'),
  'ProfileHomeComponent': require('es6-promise-loader!./components/profile/home')
};

export const prefetchRouteCallbacks:Array<IdleCallbacks> = [

  asyncRoutes['SignupComponent'],
  asyncRoutes['ProfileHomeComponent']
];


