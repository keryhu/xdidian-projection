/**
 * @Description : 物业公司注册提交的model class
 * @date : 16/6/22 上午10:55
 * @author : keryHu keryhu@hotmail.com
 */
"use strict";
var PropertySignup = (function () {
    function PropertySignup(companyName, //公司名字
        email, phone, password, directName //负责人名字
        ) {
        this.companyName = companyName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.directName = directName;
    }
    return PropertySignup;
}());
exports.PropertySignup = PropertySignup;
//# sourceMappingURL=property-signup.js.map