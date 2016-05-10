/**  
* @Title: EmailStatus.java
* @Package com.xdidian.keryhu.mailServer.mail
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月8日 下午9:57:36
* @version V1.0  
*/ 
package com.xdidian.keryhu.mailServer.mail;

/**
* @ClassName: EmailStatus
* @Description: TODO(自定义的spring mail 的基本参数属性)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月8日 下午9:57:36
*/
public class EmailStatus {
	
	public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";
 
    private final String to;
    private final String subject;
    private final String body;
 
    private String status;
    private String errorMessage;
 
    public EmailStatus(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
 
    public EmailStatus success() {
        this.status = SUCCESS;
        return this;
    }
 
    public EmailStatus error(String errorMessage) {
        this.status = ERROR;
        this.errorMessage = errorMessage;
        return this;
    }
 
    public boolean isSuccess() {
        return SUCCESS.equals(this.status);
    }
 
    public boolean isError() {
        return ERROR.equals(this.status);
    }
 
    public String getTo() {
        return to;
    }
 
    public String getSubject() {
        return subject;
    }
 
    public String getBody() {
        return body;
    }
 
    public String getStatus() {
        return status;
    }
 
    public String getErrorMessage() {
        return errorMessage;
    }

}
