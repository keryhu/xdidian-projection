/**
 * @Description : 常量设置
 * @date : 16/6/20 下午5:52
 * @author : keryHu keryhu@hotmail.com
 */
"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
var os_1 = require("os");
var ConstantService = (function () {
    function ConstantService() {
    }
    ConstantService.refreshTokenSaveUrl = '/api/storeRefreshToken';
    ConstantService.refreshTokenGetUrl = '/api/getRefreshToken';
    ConstantService.clientId = 'kksdi2388wmkwe';
    ConstantService.clientSecret = 'kksd23isdmsisdi2';
    ConstantService.authUrl = "http://" + os_1.hostname() + ":9999/uaa/oauth/token";
    //refreshToken 过期时间   单位为 秒   10 days
    ConstantService.refreshToken_expired_in = 864000;
    //最小的刷新access-token的剩余时间, 单位为m,设置这个时间⚠️目的是: 提交更新access-token,还有当浏览器刷新的时候,
    //如果发现剩余时间小于这个数字的时候,自动更新access-token
    ConstantService.minLeftRefreshTokenSeconds = 15;
    //access-token 过期时间为 5分钟
    ConstantService.accessTokenValiditySeconds = 300;
    ConstantService.emailActivate = 'resend-emailActivate';
    ConstantService.emailActivateSuccess = 'emailActivateSuccess'; //email 激活成功
    ConstantService.emailActivateExpired = 'emailActivateExpired'; //email结果过期
    //点击resend一次后的,冷却时间,单位为秒。
    ConstantService.clickCoolingSeconds = 140;
    //鼠标悬浮nodeTree上面
    ConstantService.nodeSelected = 'nodeSelected';
    ConstantService = __decorate([
        core_1.Injectable()
    ], ConstantService);
    return ConstantService;
}());
exports.ConstantService = ConstantService;
//# sourceMappingURL=constant.service.js.map