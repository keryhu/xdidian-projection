/**
 * @Description : 单独设定的倒计时 程序,应用的 几分钟倒计时的前台程序,例如防止用户重复点击 发送某个链接。
 * @date : 16/7/18 下午3:59
 * @author : keryHu keryhu@hotmail.com
 */


import {Component, OnInit, Input, OnDestroy} from "@angular/core";
import {Observable, BehaviorSubject, Subscription} from "rxjs/Rx";
import {ClickResendService} from "../email-activate/click-resend.service";
import ClickNotExpired from "../email-activate/click-expired";
import {ConstantService} from "../../services/constant.service";


@Component({
  selector: 'my-countdown',
  template: require('./countdown.component.html'),
  styles: [require('./countdown.component.css')]
})


export class CountdownComponent implements OnInit,OnDestroy {

  private listenClickSub:Subscription;
  private countdownSub:Subscription;
  private num:number;


  //显示倒计时 时间
  timeMsg:string;
  private _clickStatus = new BehaviorSubject(false);

  constructor(private clickResendService:ClickResendService) {
  }

  ngOnInit() {


    const n = localStorage.getItem('num');
    //如果click 倒计时未过期,则执行 Observable 刷新。
    if (ClickNotExpired()) {
      this.num = +n;
      this._clickStatus.next(true);
      this.countdown();

    }
    else {
      this._clickStatus.next(false);
    }


    //如果页面,刷新不管倒计时有没有到期,都需要一直监听click事件。
    this.listenClick();

  }


  //倒计时转换程序  不会大于1个小时,3600秒
  private ms(t):string {
    //当显示的数字小于10的时候,自动前面补齐0
    const pad = n=>n <= 9 ? '0' + n : n;
    const minutes = Math.floor(t / 60) % 60;
    t -= minutes * 60;
    const seconds = t % 60;
    return [
      pad(minutes) + ' 分',
      pad(seconds) + ' 秒'
    ].join('')
  }


  //监听 email-activate click message .
  listenClick() {
    this.listenClickSub = this.clickResendService.clickResend$.subscribe(
      e=> {
        console.log('has listenClick running ? ');
        if (Object.is(e, ConstantService.emailActivate)) {
          console.log('listenClickSub is running ..');
          this._clickStatus.next(true);
          localStorage.setItem('num', ConstantService.clickCoolingSeconds.toString());
          this.num = ConstantService.clickCoolingSeconds;
          this.countdown();
        }

        else {
          this.countdownSub.unsubscribe();
        }
      }
    )

  }

  getResendStatus() {
    return this._clickStatus;
  }


  countdown() {

    const source = Observable.interval(1000)
      .map(e=> {
        console.log('2')
        this.num--;
        if (this.num >= 0) {
          localStorage.setItem('num', this.num.toString());
        }
        else {
          localStorage.setItem('num', '-1');
          this._clickStatus.next(false);


          //当倒计时结束后,刷新下页面, 让手工停止 这个倒计时运行
          if (!Object.is(this.countdownSub, undefined)) {
            this.countdownSub.unsubscribe();
          }
        }
        return this.num;
      })
      .share()
      .filter(x=>x >= 0);


    this.countdownSub = source
      .subscribe(()=> {
        this.timeMsg = this.ms(this.num);
      })
  }


  ngOnDestroy() {

    //当click时间过期的时候,执行:
    if (!ClickNotExpired()) {

      //当注销,或者页面跳转的时候,自动将倒计时清零。
      localStorage.setItem('num', '-1');
      if (!Object.is(this.countdownSub, undefined)) {
        console.log('停止 countdown ')
        this.countdownSub.unsubscribe();
      }

    }

    if (!Object.is(this.listenClickSub, undefined)) {
      this.listenClickSub.unsubscribe();
    }


  }
}
