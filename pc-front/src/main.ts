import {bootstrap} from '@angular/platform-browser-dynamic';
import {ComponentRef} from '@angular/core';
import {disableDeprecatedForms, provideForms} from '@angular/forms';
import {HTTP_PROVIDERS} from "@angular/http";

import "rxjs/Rx";

// AngularClass
import { provideWebpack } from '@angularclass/webpack-toolkit';
import { providePrefetchIdleCallbacks } from '@angularclass/request-idle-callback';

import {AppComponent, appInjector} from "./app";
import {APP_ROUTER_PROVIDERS,asyncRoutes, prefetchRouteCallbacks} from './app/app.routes';
import {ENV_PROVIDERS} from './platform/environment';
import {AUTH_PROVIDERS} from "./app/shared/services/auth/index";
import {Title} from "@angular/platform-browser";
import {GUARD_PROVIDERS} from "./app/shared/services/guard/index";



function main():Promise<any> {
  return bootstrap(<any>AppComponent, [
    disableDeprecatedForms(),
    provideForms(),
    Title,
    provideWebpack(asyncRoutes),
    providePrefetchIdleCallbacks(prefetchRouteCallbacks),
    ...HTTP_PROVIDERS,
    ...ENV_PROVIDERS,
    ...APP_ROUTER_PROVIDERS,
    ...AUTH_PROVIDERS,
    ...GUARD_PROVIDERS
  ]).then((appRef:ComponentRef<any>) => {
    appInjector(appRef.injector);
  }).catch(e => console.error(e));
}

if ('development' === ENV && HMR === true) {
  let ngHmr = require('angular2-hmr');
  ngHmr.hotModuleReplacement(main, module);
} else {
  document.addEventListener('DOMContentLoaded', () => main());
}
