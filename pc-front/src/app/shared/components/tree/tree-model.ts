/**
 * @Description : please enter the description
 * @date : 16/8/2 上午9:10
 * @author : keryHu keryhu@hotmail.com
 */


export interface TreeModel{

  value:string|RenamableNode;
  children?:Array<TreeModel>;
  _status?:TreeStatus;
  _treeNodeType?:TreeNodeType;
  _indexInParent?:number;
}


export interface RenamableNode{

  setName(name:string):void;
  toString():string;
}

export enum TreeStatus{
  New,
  Modified,
  EditInProgress
}


export class TreeNodeType{
  public static Expanded:TreeNodeType=new TreeNodeType('node-expanded');  //展开的
  public static Collapsed: TreeNodeType=new TreeNodeType('node-collapsed');  //折叠的
  public static Leaf: TreeNodeType=new TreeNodeType('node-leaf');             // 最底层的枝叶

  constructor(private _cssClass:string){}

  public get cssClass():string{
    return this._cssClass;
  }

}

// node 操作事件,传递的对象是属于TreeModel类型。
export interface NodeEvent{
  node:TreeModel;
}

export interface NodeSelectedEvent extends NodeEvent{

}
