/**
 * @Title: TokenNotFoundException.java
 * @Package com.xdidian.keryhu.emailActivate.exception
 * @Description: TODO(用一句话描述该文件做什么)
 * @author keryhu  keryhu@hotmail.com
 * @date 2016年5月17日 下午2:53:02
 * @version V1.0
 */
package com.xdidian.keryhu.emailActivate.exception;


/**
 * Description : 当token不存在的时候，报告的错误
 * Date : 2016年06月18日 上午8:38
 * Author : keryHu keryhu@hotmail.com
 */
public class TokenNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -3243973140485053254L;

    public TokenNotFoundException(String message) {
        super(message);
    }

}
