/**
 * @Title: EmailNotFoundException.java
 * @Package com.xdidian.keryhu.emailActivate.exception
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu  keryhu@hotmail.com
 * @date 2016年5月14日 下午9:59:52
 * @version V1.0
 */
package com.xdidian.keryhu.emailActivate.exception;

/**
 * Description : email找不到发生的错误
 * Date : 2016年06月18日 上午8:36
 * Author : keryHu keryhu@hotmail.com
 */
public class EmailNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2407783389846201037L;

    public EmailNotFoundException(String message) {
        super(message);
    }

}
