import {bootstrap} from "@angular/platform-browser-dynamic";
import {ComponentRef} from "@angular/core";
import {ENV_PROVIDERS} from "./platform/environment";
import {AppComponent, appInjector} from "./app";


function main():Promise<any> {
  return bootstrap(<any>AppComponent, [
    ...ENV_PROVIDERS,
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
