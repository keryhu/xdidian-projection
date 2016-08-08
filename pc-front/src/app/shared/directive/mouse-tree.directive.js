/**
 * @Description : please enter the description
 * @date : 16/8/3 上午10:45
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
var MouseDirective = (function () {
    function MouseDirective(el, treeService) {
        this.treeService = treeService;
        this.el = el.nativeElement;
    }
    MouseDirective.prototype.onMouseEnter = function () {
        this.treeService.isSelected(true);
        this.highlight('yellow');
    };
    MouseDirective.prototype.onMouseLeave = function () {
        this.treeService.isSelected(false);
        this.highlight(null);
    };
    MouseDirective.prototype.highlight = function (color) {
        this.el.style.backgroundColor = color;
    };
    __decorate([
        core_1.Input('myHover')
    ], MouseDirective.prototype, "tree");
    __decorate([
        core_1.HostListener('mouseenter')
    ], MouseDirective.prototype, "onMouseEnter");
    __decorate([
        core_1.HostListener('mouseleave')
    ], MouseDirective.prototype, "onMouseLeave");
    MouseDirective = __decorate([
        core_1.Directive({
            selector: '[myHover]'
        })
    ], MouseDirective);
    return MouseDirective;
}());
exports.MouseDirective = MouseDirective;
//# sourceMappingURL=mouse-tree.directive.js.map