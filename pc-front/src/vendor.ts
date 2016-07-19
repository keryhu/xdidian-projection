import '@angular/platform-browser';
import '@angular/platform-browser-dynamic';
import '@angular/core';
import '@angular/common';
import '@angular/forms';
import '@angular/http';
import '@angular/router';

// AngularClass
import '@angularclass/webpack-toolkit';
import '@angularclass/request-idle-callback';

// RxJS
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/mergeMap';

import 'rxjs/Rx';

//因为引用的原生的bootstrap,所以必须引入jquery
require("expose?$!expose?jQuery!jquery");
//require("!style!css!toastr/build/toastr.css");

import 'bootstrap-loader';
import 'ng2-bootstrap';

if ('production' === ENV) {
  // Production


} else {
  // Development
  require('angular2-hmr');

}
