/**
 * @Description : 常量设置
 * @date : 16/6/20 下午5:52
 * @author : keryHu keryhu@hotmail.com
 */

import { Injectable } from '@angular/core';
import {hostname} from "os";

@Injectable()
export class ConstantService{

  constructor(){

  }


  public static refreshTokenSaveUrl:string='/api/storeRefreshToken';
  public static refreshTokenGetUrl:string='/api/getRefreshToken';
  public static clientId:string='kksdi2388wmkwe';
  public static clientSecret:string='kksd23isdmsisdi2';
  public static authUrl: string=`http://${hostname()}:9999/uaa/oauth/token`;
  //refreshToken 过期时间   单位为 秒   10 days
  public static refreshToken_expired_in:number=864000;

  //最小的刷新access-token的剩余时间, 单位为m,设置这个时间⚠️目的是: 提交更新access-token,还有当浏览器刷新的时候,
  //如果发现剩余时间小于这个数字的时候,自动更新access-token
  public static minLeftRefreshTokenSeconds:number=15;
  //access-token 过期时间为 5分钟
  public static accessTokenValiditySeconds:number=300;

  public static emailActivate:string='resend-emailActivate';
  public static emailActivateSuccess:string='emailActivateSuccess';    //email 激活成功
  public static emailActivateExpired:string='emailActivateExpired';   //email结果过期

  //点击resend一次后的,冷却时间,单位为秒。
  public static clickCoolingSeconds:number=140;


  //鼠标悬浮nodeTree上面
  public static nodeSelected:string='nodeSelected';


}
