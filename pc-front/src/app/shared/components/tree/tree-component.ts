/**
 * @Description : please enter the description
 * @date : 16/8/2 上午10:33
 * @author : keryHu keryhu@hotmail.com
 */

import {Component, OnInit, Input} from "@angular/core";
import {TreeView} from "./tree-view";
import {TreeModel} from "./tree-model";
@Component({
  selector:'tree',
  template: `<tree-view  [tree]="tree"> </tree-view>`,

  directives: [TreeView]
})

//前台tree 首先导向这里,将数据传递个 tree-view,而tree-view 是一个递归操作的component,可以无限极的展现
//tree的读写操作。
export class TreeComponent implements OnInit{
  @Input()
  private tree:TreeModel;

 constructor(){}

 public ngOnInit(){
   console.log(this.tree);

 }

}
