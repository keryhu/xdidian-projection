import {bootstrap} from '@angular/platform-browser-dynamic';
import {ComponentRef} from '@angular/core';
import {FORM_PROVIDERS} from "@angular/common";
import {HTTP_PROVIDERS} from "@angular/http";

import { AppComponent,appInjector} from "./app";
import { APP_ROUTER_PROVIDERS } from './app/app.routes';
import {ENV_PROVIDERS} from './platform/environment';

function main():Promise<any> {
  return bootstrap(<any>AppComponent, [
    ...FORM_PROVIDERS,
    ...HTTP_PROVIDERS,
    ...ENV_PROVIDERS,
      ...APP_ROUTER_PROVIDERS
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
