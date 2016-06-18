package com.xdidian.keryhu.useraccount.exception;

/**
 * Description : 当根据identity查询数据库是，查询不到对应的user的时候，报此错误
 * Date : 2016年06月18日 上午11:19
 * Author : keryHu keryhu@hotmail.com
 */
public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 8378668714759997552L;

    public UserNotFoundException(String message) {
        super(message);
    }
}
