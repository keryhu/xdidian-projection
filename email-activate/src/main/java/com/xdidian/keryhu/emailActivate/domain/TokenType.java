/**  
* @Title: TokenType.java
* @Package com.xdidian.keryhu.emailActivate.domain
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月17日 上午11:17:11
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
* @ClassName: TokenType
* @Description: TODO(email激活时，共有3个token类型，需要在url验证时，验证其真伪
* confirm 用户点击激活url中含有的token
* resend 用于 用户点击 “重新发送”url中含有的需要被验证的token
* reregister 用户点击“重新注册”url中含有的需要被验证的token)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月17日 上午11:17:11
*/
public enum TokenType {

	CONFIRM,           //用户点击激活url中含有的token
	RESEND,           //用于 用户点击 “重新发送”url中含有的需要被验证的token
	REREGISTER;       //用户点击“重新注册”url中含有的需要被验证的token
	
	private static Map<String,TokenType> tokenMap=new HashMap<String,TokenType>(3);
	
	static{
		tokenMap.put("CONFIRM", CONFIRM);
		tokenMap.put("RESEND", RESEND);
		tokenMap.put("REREGISTER", REREGISTER);
	}
	
	@JsonCreator
	public static TokenType forValue(String value){
		return tokenMap.get(value);
	}
	
	@JsonValue
	public String toValue(){
		for(Entry<String,TokenType> token:tokenMap.entrySet()){
			if(token.getValue()==this)
				return token.getKey();
		 }
		return null;
	}	
}
