/**
 * @Title: Constants.java
 * @Package com.xdidian.keryhu.emailActivate.domain
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu  keryhu@hotmail.com
 * @date 2016年5月17日 下午4:31:20
 * @version V1.0
 */
package com.xdidian.keryhu.emailActivate.domain;

/**
 * Description : 设定 常量
 * Date : 2016年06月18日 上午8:35
 * Author : keryHu keryhu@hotmail.com
 */
public final class Constants {

    private Constants() {
    }

    //此常量表示： email 未激活，token存在于数据库，且激活时间未过期。
    public static final String EMAIL_NOT_ACTIVATED_AND_TOKEN_EXIST_AND_NOT_EXPIRED =
            "emailNotActivatedAndTokenExistAndNotExpired";

}
