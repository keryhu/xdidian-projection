/**
 * @Description : please enter the description
 * @date : 16/8/3 上午8:49
 * @author : keryHu keryhu@hotmail.com
 */


import {Injectable} from "@angular/core";
import {Subject} from "rxjs/Rx";


@Injectable()
export class TreeService{


  //用户鼠标悬浮  node 上面的 订阅。
  private nodeSelectedSource=new Subject<boolean>();

  nodeSelected$=this.nodeSelectedSource.asObservable();

  constructor(){}

  //如果选中了,则返回true,否则返回false。
  isSelected(message:boolean){
    this.nodeSelectedSource.next(message);
  }







}


