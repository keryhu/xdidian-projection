/**
 * @Description : please enter the description
 * @date : 16/8/2 上午9:10
 * @author : keryHu keryhu@hotmail.com
 */


export interface TreeModel{

  value:string;
  url?:string;          //之所以要加上url,方便侧边栏的导航使用
  children?:Array<TreeModel>;
  expanded?:boolean;
  _status?:TreeStatus;
  _treeNodeType?:TreeNodeType;
  _indexInParent?:number;
}




export enum TreeStatus{
  New,
  EditInProgress
}


export class TreeNodeType{
  public static Expanded:TreeNodeType=new TreeNodeType('node-expanded');  //展开的
  public static Collapsed: TreeNodeType=new TreeNodeType('node-collapsed');  //折叠的
  public static Leaf: TreeNodeType=new TreeNodeType('node-leaf');             // 最底层的枝叶

  constructor(private _cssClass:string){}



}



