/**
 * @Description : 聚焦 输入框的指令
 * @date : 16/8/3 下午8:42
 * @author : keryHu keryhu@hotmail.com
 */
"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require("@angular/core");
var FocusOnDirective = (function () {
    function FocusOnDirective(element, renderer) {
        this.element = element;
        this.renderer = renderer;
    }
    FocusOnDirective.prototype.ngOnInit = function () {
        this.renderer.invokeElementMethod(this.element.nativeElement, 'focus', null);
    };
    FocusOnDirective = __decorate([
        core_1.Directive({
            selector: '[tractorFocusOn]'
        })
    ], FocusOnDirective);
    return FocusOnDirective;
}());
exports.FocusOnDirective = FocusOnDirective;
//# sourceMappingURL=focus-on.directive.js.map