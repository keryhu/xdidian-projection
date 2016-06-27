/**
 * @Description : 常量设置
 * @date : 16/6/20 下午5:52
 * @author : keryHu keryhu@hotmail.com
 */

import { Injectable } from '@angular/core';
import {hostname} from "os";

@Injectable()
export class ConstantService{

  public static loginUrl:string=`http://${hostname()}:9999/uaa/login`;

}
