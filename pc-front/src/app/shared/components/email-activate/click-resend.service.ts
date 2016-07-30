/**
 * @Description : 在email激活,和 倒计时之间 传递消息的service。
 * @date : 16/7/18 下午9:06
 * @author : keryHu keryhu@hotmail.com
 */


import {Injectable} from "@angular/core";
import {Subject} from "rxjs/Rx";


@Injectable()
export class ClickResendService{

  //默认是用户未点击,'重新发送' 按钮。
  private clickResendSource=new Subject<String>();

  clickResend$=this.clickResendSource.asObservable();

  constructor(){}

  click(message:string){
    console.log('clickResendService click method is running ...');
    this.clickResendSource.next(message);
  }


}
