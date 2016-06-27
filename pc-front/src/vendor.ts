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



import "rxjs/Rx";

require("expose?$!expose?jQuery!jquery");
require('bootstrap-loader');
require("!style!css!toastr/build/toastr.css");

if ('production' === ENV) {
} else {
}
