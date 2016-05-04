/**  
* @Title: StringToBooleanConverter.java
* @Package com.xdidian.keryhu.useraccount.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月4日 上午11:48:45
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.service;

import org.springframework.cloud.stream.converter.AbstractFromMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.MessageConversionException;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;

/**
* @ClassName: StringToBooleanConverter
* @Description: TODO(将message中的String对象转为Boolean对象)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月4日 上午11:48:45
*/
@Component
public class StringToBooleanConverter extends AbstractFromMessageConverter {

	/**
	* <p>Title: </p>
	* <p>Description: </p>
	* @param targetMimeType
	*/
	protected StringToBooleanConverter() {
		super(MimeType.valueOf("string/boolean"));
		// TODO Auto-generated constructor stub
	}

	/**
	* <p>Title: supportedPayloadTypes</p>
	* <p>Description: 自String 转换</p>
	* @return
	* @see org.springframework.cloud.stream.converter.AbstractFromMessageConverter#supportedPayloadTypes()
	*/ 
	@Override
	protected Class<?>[] supportedPayloadTypes() {
		// TODO Auto-generated method stub
		return new Class<?>[] {String.class};
	}

	/**
	* <p>Title: supportedTargetTypes</p>
	* <p>Description: 转换的目标类型 </p>
	* @return
	* @see org.springframework.cloud.stream.converter.AbstractFromMessageConverter#supportedTargetTypes()
	*/ 
	@Override
	protected Class<?>[] supportedTargetTypes() {
		// TODO Auto-generated method stub
		return new Class<?>[] {Boolean.class};
	}
	
	/**
	 * 
	* <p>Title: convertFromInternal</p>
	* <p>Description: 具体的转换逻辑 </p>
	* @param message
	* @param targetClass
	* @param conversionHint
	* @return
	* @see org.springframework.messaging.converter.AbstractMessageConverter#convertFromInternal(org.springframework.messaging.Message, java.lang.Class, java.lang.Object)
	 */
	@Override
	public Object convertFromInternal(Message<?> message, Class<?> targetClass, Object conversionHint){
		
		Object result = null;
		try {
			
			String payload =  message.getPayload().toString();
			
			result=payload.equals("true")?true:false;
			
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MessageConversionException(e.getMessage());
		}
		return result;
	}

}
