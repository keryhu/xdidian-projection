/**
 * @Description : please enter the description
 * @date : 16/6/19 下午7:52
 * @author : keryHu keryhu@hotmail.com
 */

import { RouterConfig, provideRouter } from '@angular/router';
import { HomeRoutes } from './components/home/home.route';
import { NotFoundRoutes } from './components/404/not-found.route';
import { PropertySignupRoute } from './components/signup/property/property-signup.route';

const APP_ROUTES:RouterConfig = [
  ...HomeRoutes,
  ...PropertySignupRoute,
  ...NotFoundRoutes
];

export const APP_ROUTER_PROVIDERS = [
  provideRouter(APP_ROUTES)
];
