import {bootstrap} from '@angular/platform-browser-dynamic';

import "rxjs/Rx";

import {AppComponent} from "./app";
import {ENV_PROVIDERS,decorateComponentRef} from './platform/environment';
import {PLATFORM_PROVIDERS} from "./platform/browser";
import {AUTH_PROVIDERS} from "./app/shared/services/auth/index";
import {GUARD_PROVIDERS} from "./app/shared/services/guard/index";



function main():Promise<any> {
  return bootstrap(<any>AppComponent, [
    ...PLATFORM_PROVIDERS,
    ...ENV_PROVIDERS,
    ...AUTH_PROVIDERS,
    ...GUARD_PROVIDERS
  ]).then(decorateComponentRef)
    .catch(e => console.error(e));
}

if ('development' === ENV && HMR === true) {
  let ngHmr = require('angular2-hmr');
  ngHmr.hotModuleReplacement(main, module);
} else {
  document.addEventListener('DOMContentLoaded', () => main());
}
