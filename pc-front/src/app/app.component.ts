import {Component, OnInit} from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router'

import { NavbarComponent } from './shared/components/navbar/navbar.component';


@Component({
  selector: 'mpt-app',
  template: require('./app.html'),
  styles: [require('./app.component.css')],
  directives: [ NavbarComponent , ROUTER_DIRECTIVES ]
})



export class AppComponent implements OnInit{

  public appBrand: string;
  constructor() {}

  ngOnInit(){
    this.appBrand = "xdidian Brand";
  }


}



