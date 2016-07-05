/**
 * @Description : please enter the description
 * @date : 16/6/19 下午7:52
 * @author : keryHu keryhu@hotmail.com
 */

import { RouterConfig, provideRouter } from '@angular/router';
import { HomeRoutes } from './components/home';
import { NotFoundRoutes } from './shared/components/404';
import { PropertySignupRoute } from './components/signup/property';
import { PropertyHomeRoute } from './components/property/home';
import {AUTH_PROVIDERS} from "./shared/services/auth/index";
import {LoginRoute} from "./components/login/login.route";




const APP_ROUTES:RouterConfig = [
  ...HomeRoutes,
  ...PropertySignupRoute,
  ...PropertyHomeRoute,
  ...LoginRoute,
  ...NotFoundRoutes
];

export const APP_ROUTER_PROVIDERS = [
  provideRouter(APP_ROUTES),
  AUTH_PROVIDERS
];
