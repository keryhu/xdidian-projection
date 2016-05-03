/**  
* @Title: PropertySave.java
* @Package com.xdidian.keryhu.useraccount.exception
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月3日 上午10:41:36
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.exception;

/**
* @ClassName: PropertySave
* @Description: TODO(物业公司注册信息保存发生的错误，指输入信息不合法，或者提供的手机号，email，公司名字已经注册过)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月3日 上午10:41:36
*/
public class PropertySaveException extends RuntimeException {

	
	private static final long serialVersionUID = 2621101793033056674L;
	
	public PropertySaveException(String message){
		super(message);
	}

}
