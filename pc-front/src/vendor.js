"use strict";
require('@angular/platform-browser');
require('@angular/platform-browser-dynamic');
require('@angular/core');
require('@angular/common');
require('@angular/forms');
require('@angular/http');
require('@angular/router');
// AngularClass
require('@angularclass/webpack-toolkit');
require('@angularclass/request-idle-callback');
// RxJS
require('rxjs/add/operator/map');
require('rxjs/add/operator/mergeMap');
require('rxjs/Rx');
require('@angular2-material/button');
require('@angular2-material/card');
require('@angular2-material/checkbox');
require('@angular2-material/grid-list');
require('@angular2-material/input');
require('@angular2-material/list');
require('@angular2-material/menu');
require('@angular2-material/progress-circle');
require('@angular2-material/progress-bar');
require('@angular2-material/radio');
require('@angular2-material/sidenav');
require('@angular2-material/slide-toggle');
require('@angular2-material/tabs');
require('@angular2-material/toolbar');
//因为引用的原生的bootstrap,所以必须引入jquery
require("expose?$!expose?jQuery!jquery");
require('bootstrap-loader');
require("!style!css!toastr/build/toastr.css");
require('bootstrap-loader');
require('ng2-bootstrap');
if ('production' === ENV) {
}
else {
    // Development
    require('angular2-hmr');
}
