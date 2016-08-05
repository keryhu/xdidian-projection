/**
 * @Description : 聚焦 输入框的指令
 * @date : 16/8/3 下午8:42
 * @author : keryHu keryhu@hotmail.com
 */

import {Directive, OnInit, Renderer, ElementRef} from "@angular/core";
@Directive({
  selector: '[tractorFocusOn]'
})


export class FocusOnDirective implements OnInit{
  constructor (
    private element: ElementRef,
    private renderer: Renderer
  ) { }

  public ngOnInit () {
    this.renderer.invokeElementMethod(this.element.nativeElement, 'focus', null);
  }
}
