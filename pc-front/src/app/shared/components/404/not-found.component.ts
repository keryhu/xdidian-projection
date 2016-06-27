/**
 * @Description : please enter the description
 * @date : 16/6/21 上午9:59
 * @author : keryHu keryhu@hotmail.com
 */

import { Component } from '@angular/core';
import { CORE_DIRECTIVES } from '@angular/common';

@Component({
  selector: 'not-found',
  template: require('./not-found.component.html'),
  styles: [require('./not-found.component.css')],
  directives: [CORE_DIRECTIVES]

})
export class NotFoundComponent {
  constructor(){}
}
