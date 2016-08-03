/**
 * @Description : please enter the description
 * @date : 16/8/3 上午10:45
 * @author : keryHu keryhu@hotmail.com
 */

import {Directive, ElementRef, HostListener, Input} from "@angular/core";
import {TreeModel} from "../components/tree/tree-model";
import {TreeService} from "../services/tree/tree.service";
import {ConstantService} from "../services/constant.service";

@Directive({
  selector: '[myHover]'
})

export class MouseDirective{

  private el:HTMLElement;

  constructor(el:ElementRef,private treeService:TreeService){

    this.el=el.nativeElement;

  }

  //将前台页面的 当前tree ,参数传递到  此 directive 里面来。
  @Input('myHover') tree:TreeModel;

  @HostListener('mouseenter') onMouseEnter() {
    this.treeService.isSelected(true);
    this.highlight('yellow');
  }
  @HostListener('mouseleave') onMouseLeave() {
    this.treeService.isSelected(false);
    this.highlight(null);
  }
  private highlight(color: string) {
    this.el.style.backgroundColor = color;
  }
}
