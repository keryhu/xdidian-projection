/**
 * @Description : 左边侧导航,设定的tree view
 * @date : 16/8/6 上午10:52
 * @author : keryHu keryhu@hotmail.com
 */


import {Component, OnInit, OnDestroy, Input} from "@angular/core";
import {CORE_DIRECTIVES, NgClass} from "@angular/common";
import {TreeNodeType, TreeModel} from "../../tree/tree-model";
@Component({
  selector: 'side-menu',
  template: require('./side-menu.component.html'),
  styles: [require('./side-menu.component.css')],
  providers: [],
  directives: [SideMenuComponent, CORE_DIRECTIVES, NgClass]
})


export class SideMenuComponent implements OnInit,OnDestroy {

  @Input()
  private tree: TreeModel;

  @Input()
  private parentTree: TreeModel;

  @Input()
  private indexInParent: number;

  private isNodeExpanded(): boolean {
    return this.tree._treeNodeType === TreeNodeType.Expanded;
  }


  constructor() {
  }

  ngOnInit() {
    this.setDefaultTreeNodeType(this.tree);
    this.tree.expanded = false;

  }

  setBackground(node: TreeModel) {
    console.log('222')
    const collapsed = 'menu-icon glyphicon glyphicon-collapse-down';
    const expanded = 'menu-icon glyphicon glyphicon-expand';
    let isLeaf:boolean=false;
    let isExpanded:boolean=false;
    let collapsed:boolean=false;
    let color;
    if(this.tree&&!this.parentTree){
       return;
    }
    else if(node.children&&node.children.length>0){
      isExpanded=true;
    }
    else {
      isLeaf=true;
    }

    if(this.getIcon(node)===collapsed||this.getIcon(node)===expanded){
      isLeaf=true;
    }
    return {
      leaf:isLeaf,

    };



  }

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

  private  getIcon(node: TreeModel): string {

    const expanded = 'menu-icon glyphicon glyphicon-expand';

    const collapsed = 'menu-icon glyphicon glyphicon-collapse-down';

    const leaf = 'menu-icon glyphicon glyphicon-collapse-up';   //这里可以设置不同css

    if (node._treeNodeType === TreeNodeType.Collapsed) {
      return expanded;
    }
    else if (node._treeNodeType === TreeNodeType.Expanded) {
      return collapsed;
    }
    return leaf;
  }


  switchTreeNodeType(node: TreeModel) {
    if (node._treeNodeType === TreeNodeType.Leaf) {
      return;
    }

    if (this.hasChildMenu()) {
      this.tree.expanded = !this.tree.expanded;
    }
    node._treeNodeType = this.getNextFoldingType(node);

  }


  private getNextFoldingType(node: TreeModel): TreeNodeType {
    if (node._treeNodeType === TreeNodeType.Expanded) {
      return TreeNodeType.Collapsed;
    }

    return TreeNodeType.Expanded;
  }


  //当刷新浏览器的时候,或者第一次打开的时候,默认,不显示子菜单,下面这个方法,判断当前的tree,有没有子菜单
  private hasChildMenu(): boolean {
    if (this.tree && this.tree.children && this.tree.children.length > 0) {
      return true;
    }
    return false;
  }


  private showChildMenu(node: TreeModel) {
    return node.expanded;
  }


  ngOnDestroy() {

  }

}
