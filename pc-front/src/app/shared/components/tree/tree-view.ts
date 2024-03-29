/**
 * @Description : 使用parentTree和children,支持无限层级
 * @date : 16/8/1 下午6:57
 * @author : keryHu keryhu@hotmail.com
 */
import {Component, Input, OnInit, OnDestroy} from '@angular/core';
import {TreeModel, TreeNodeType, TreeStatus} from "./tree-model";
import {CORE_DIRECTIVES, NgClass} from "@angular/common";
import {TreeService} from "../../services/tree/tree.service";
import {MouseDirective} from "../../directive/mouse.directive";
import {BehaviorSubject} from "rxjs/Rx";
import {FocusOnDirective} from "../../directive/focus-on.directive";

@Component({
  selector: 'tree-view',
  template: require('./tree-view.html'),
  styles: [require('./tree-view.css')],
  providers:[TreeService],
  directives: [TreeView,CORE_DIRECTIVES,NgClass,MouseDirective,FocusOnDirective]
  //这里之所以要引用本身的 treeView directives,因为tree是一个递归算法展示,无限级的
})

export class TreeView implements OnInit,OnDestroy{

  private isLeaf: boolean;
  //表示当前的node有没有被选中,这个是一个异步操作事件
  private _isSelected=new BehaviorSubject(false);

  private _isEditMode=new BehaviorSubject(false);

  private nodeSelectedSub:any;


  @Input()
  private tree:TreeModel;

  @Input()
  private parentTree :TreeModel;

  @Input()
  private indexInParent:number;

  private isNodeExpanded(): boolean {
    return this.tree._treeNodeType === TreeNodeType.Expanded;
  }


  constructor(private treeService:TreeService){

  }

  ngOnInit(){
    this.indexInParent = 0;

    this.setDefaultTreeNodeType(this.tree);

    this.isLeaf = !Array.isArray(this.tree.children);
    this.tree._indexInParent = this.indexInParent;

    //监听 node selected 事件
    this.listenNodeSelected();

  }

  // node expanded collepsed-----------------------------------------------------------

  //如果未设置treeModel是展开,还是折叠,还是leaf,那么就判断该treeNode是否有children,如果有那么
  //就将children展开,否则,就设置他们为最终的枝叶。---这个方法,也就是默认的展示方法。,尽量展开children
  private setDefaultTreeNodeType(node: TreeModel) {
    if (!node._treeNodeType) {
      if (node.children) {
        node._treeNodeType = TreeNodeType.Expanded;
      } else {
        node._treeNodeType = TreeNodeType.Leaf;
      }
    }

  }

  private getEditMode(){
    return this._isEditMode;
  }

  private isEditInProgress() {
    return this.tree._status === TreeStatus.EditInProgress
      || this.tree._status === TreeStatus.New;
  }
  /**
   *
   * 点击treeModel,使得tree展开或者折叠。
   *
     */

  switchTreeNodeType(node:TreeModel){
    if (node._treeNodeType === TreeNodeType.Leaf) {
      return;
    }

    node._treeNodeType=this.getNextFoldingType(node);

  }


  private getNextFoldingType(node: TreeModel): TreeNodeType {
    if (node._treeNodeType === TreeNodeType.Expanded) {
      return TreeNodeType.Collapsed;
    }

    return TreeNodeType.Expanded;
  }

  private  getIcon(node: TreeModel):string{

  const expanded='treeNode glyphicon glyphicon-expand';

  const collapsed='treeNode glyphicon glyphicon-collapse-down';

  const leaf='leafNode glyphicon glyphicon-collapse-up';   //这里可以设置不同css

    if(node._treeNodeType===TreeNodeType.Collapsed){
      return expanded;
    }
    else if(node._treeNodeType===TreeNodeType.Expanded){
      return collapsed;
    }
    return leaf;
  }

// node selected------------------------
  listenNodeSelected(){

    this.nodeSelectedSub=this.treeService.nodeSelected$
      .subscribe(e=>{

        if (Object.is(e, true)){
          this._isSelected.next(true);
        }
        else {
          this._isSelected.next(false);
        }

      })
  }

  isSelected(){
    return this._isSelected;
  }

  // 先选择,后编辑/新建/移除,操作--------------------------------------
  externalEdit(node: TreeModel){

    this._isEditMode.next(true);
    this.tree._status=TreeStatus.EditInProgress;

    console.log(node);
  }

  externalNew(node: TreeModel){
    if(node.children){
      this._isEditMode.next(true);
      //到时候这里通过下拉菜单,选择下,是要建立部门,还是要建立最终的部门
      const newNode:TreeModel={
        value:'新部门',
        _status:TreeStatus.New,
        children:[]

      }
      node.children.push(newNode);
      console.log(this.tree);
    }
    console.log(node);
  }


  externalRemove(node: TreeModel){
    //不能删除root
    if(!Object.is(this.parentTree,undefined)){

      //找出满足条件的,index,删除他
      const myIndex:number=this.parentTree.children.findIndex(n=>n===node);
      this.parentTree.children.splice(myIndex,1);

    }

  }

  //当鼠标离开的时候,切换当时的treeNode,为非编辑状态。
  saveTree(node: TreeModel){
    //表示修改好了
    this._isEditMode.next(false);
    let result=false;
    if(!this.parentTree.children){
      result=true;
    }
    else if(this.parentTree.children&&this.parentTree.children.includes(node)){
      result=false;
    }
    else {
      result=true;
    }

    if(!result){
      console.log('不能保存相同的名字 ');
    }

  }

  ngOnDestroy(){
    if(!Object.is(this.nodeSelectedSub,undefined)){
      this.nodeSelectedSub.unsubscribe();
    }
  }

}
