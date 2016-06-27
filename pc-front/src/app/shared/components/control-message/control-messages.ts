/**
 * @Description : please enter the description
 * @date : 16/6/23 上午7:08
 * @author : keryHu keryhu@hotmail.com
 */

import { Component,Host } from '@angular/core';
import { Control, NgFormModel } from '@angular/common';

@Component({
  selector: 'control-messages',
  inputs: ['controlName: control'],
  //template: require('./control-message.html'),
  template: `<div *ngIf="errorMessage !== null">{{errorMessage}}</div>`,
  styles: [require('./control-message.css')]
})

export class ControlMessages{
  controlName: string;
  constructor(@Host()private _formDir: NgFormModel){}

  get errorMessage(){
    let c:any=null;
    let ctrl=this.controlName.split('.');
    let grep=this._formDir.form;

    for(let i=0; i<ctrl.length;i++){
      c=grep.find(ctrl[i]);
      grep=c;
    }

    for (let propertyName in c.errors){
      if(c.errors.hasOwnProperty(propertyName)&&c.touched){

      }

    }

    return null;

  }


}
