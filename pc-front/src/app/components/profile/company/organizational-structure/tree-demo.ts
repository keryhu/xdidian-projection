/**
 * @Description : please enter the description
 * @date : 16/8/1 下午6:59
 * @author : keryHu keryhu@hotmail.com
 */


import {Component} from '@angular/core';

import {TreeModel} from "../../../../shared/components/tree/tree-model";
import {TreeComponent} from "../../../../shared/components/tree/tree-component";

@Component({
  template: `<h1> TreeView</h1>
            
             <hr>
             <h4>右键编辑/新建/删除</h4>
             <tree  class="structure-tree" [tree]="f"></tree>`
  ,
  styles:[require('./tree-demo.css')],
  directives: [TreeComponent]
})

export class TreeDemo {
  f:TreeModel={
    value: '总经办',
    children:[
      {
        value:'ddd',
        children:[
          {value:'aaa'},
          {value:'bbb'}
        ]
      }
    ]
  }


  constructor(){

  }

  loadDirectories(){


  }
}
