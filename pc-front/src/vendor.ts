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


import '@angular2-material/button';
import '@angular2-material/card';
import '@angular2-material/checkbox';
import '@angular2-material/grid-list';
import '@angular2-material/input';
import '@angular2-material/list';
import '@angular2-material/menu';
import '@angular2-material/progress-circle';
import '@angular2-material/progress-bar';
import '@angular2-material/radio';
import '@angular2-material/sidenav';
import '@angular2-material/slide-toggle';
import '@angular2-material/tabs';
import '@angular2-material/toolbar';




//因为引用的原生的bootstrap,所以必须引入jquery
require("expose?$!expose?jQuery!jquery");
require('bootstrap-loader');
require("!style!css!toastr/build/toastr.css");

import 'bootstrap-loader';
import 'ng2-bootstrap';

if ('production' === ENV) {
  // Production


} else {
  // Development
  require('angular2-hmr');

}
