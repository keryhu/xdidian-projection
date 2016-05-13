/**  
* @Title: EmailActivatedToken.java
* @Package com.xdidian.keryhu.emailActivate.domain
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月11日 上午10:56:32
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

/**
* @ClassName: EmailActivatedToken
* @Description: TODO(用于保存用户email激活账户的token生成，到期时间，userId，激活次数于数据库的 实体类)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月11日 上午10:56:32
*/
@Data
public class ActivatedToken implements Serializable {

	private static final long serialVersionUID = 425412958779020624L;
	
	
	@Id
	private String id;
	
	private String email; //从user的 中取来，或者从注册对象中取来。
	
	private String token;  //email 激活产生的 随机码
	
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime expiryDate; //token 过期时间
	
	//邮件激活，已经重发的次数（包含当前userId下更换email的次数）
	private int sentTimes;
		
		
	/**
	 * 
	* <p>Title: </p>
	* <p>Description: 传递userId，初始化EmailActivatedToken</p>
	* @param userId
	 */
	public ActivatedToken(){
		this.id=UUID.randomUUID().toString();
		this.expiryDate=null;
		this.email=null;
		this.token=null;
		this.sentTimes=0;
	}
	
	
	

}
