/**
 * @Description : 设置整个系统的导航
 * @date : 16/6/20 上午10:42
 * @author : keryHu keryhu@hotmail.com
 */

import {Component, Input, ChangeDetectionStrategy} from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {ROUTER_DIRECTIVES} from '@angular/router';
import {hostname} from 'os'; //use core-js

@Component({
  selector: 'my-navbar',
  template: require('./navbar.component.html'),
  styles: [require('./navbar.component.css')],
  changeDetection: ChangeDetectionStrategy.OnPush,
  directives: [ROUTER_DIRECTIVES, CORE_DIRECTIVES]
})

export class NavbarComponent {
  @Input() brand:string;
  loginUrl = `http://${hostname()}:9999/uaa/login`;
}
