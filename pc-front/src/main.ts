import {bootstrap} from '@angular/platform-browser-dynamic';
import {ComponentRef} from '@angular/core';
import {disableDeprecatedForms, provideForms} from '@angular/forms';
import {HTTP_PROVIDERS} from "@angular/http";

import "rxjs/Rx";

import { OAuthService } from 'angular2-oauth2/oauth-service';

import {AppComponent, appInjector} from "./app";
import {APP_ROUTER_PROVIDERS} from './app/app.routes';
import {ENV_PROVIDERS} from './platform/environment';
import {AUTH_PROVIDERS} from "./app/shared/services/auth/index";



function main():Promise<any> {
  return bootstrap(<any>AppComponent, [
    OAuthService,
    disableDeprecatedForms(),
    provideForms(),
    ...HTTP_PROVIDERS,
    ...ENV_PROVIDERS,
    ...APP_ROUTER_PROVIDERS,
    ...AUTH_PROVIDERS
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
