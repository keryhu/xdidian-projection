/**
 * @Description : please enter the description
 * @date : 16/8/3 上午10:45
 * @author : keryHu keryhu@hotmail.com
 */

import {Directive, ElementRef, HostListener, Input} from "@angular/core";

@Directive({
  selector: '[myHover]'
})

export class MouseDirective{

  private el:HTMLElement;

  constructor(el:ElementRef){

    this.el=el.nativeElement;

  }


  @HostListener('mouseenter') onMouseEnter() {
    this.highlight('black');
  }
  @HostListener('mouseleave') onMouseLeave() {
    this.highlight(null);
  }
  private highlight(color: string) {
    this.el.style.color= color;
    this.el.style.cursor='pointer'

  }
}
