import { Component } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router'

import { NavbarComponent } from './components/navbar/navbar.component';


@Component({
  selector: 'mpt-app',
  template: require('./app.html'),
  styles: [require('./app.component.css')],
  directives: [ NavbarComponent , ROUTER_DIRECTIVES ]
})



export class AppComponent {

  public appBrand: string;
  constructor() {
    this.appBrand = "xdidian Brand";
  }


}



