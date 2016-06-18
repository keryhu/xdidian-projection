package com.xdidian.keryhu.useraccount.exception;

/**
 * Description : email找不到的错误
 * Date : 2016年06月18日 上午11:18
 * Author : keryHu keryhu@hotmail.com
 */
public class EmailNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4150301770264026174L;

    public EmailNotFoundException(String message) {
        super(message);
    }

}
