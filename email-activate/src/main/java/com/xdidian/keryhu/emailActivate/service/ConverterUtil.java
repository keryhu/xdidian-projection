/**  
* @Title: ConverterUtil.java
* @Package com.xdidian.keryhu.emailActivate.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月11日 下午9:16:46
* @version V1.0  
*/ 
package com.xdidian.keryhu.emailActivate.service;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.xdidian.keryhu.domain.EmailActivatedDto;
import com.xdidian.keryhu.emailActivate.domain.ActivatedToken;

/**
* @ClassName: ConverterUtil
* @Description: TODO(用于email激活的时候，类型的转换)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月11日 下午9:16:46
*/
@Component
public class ConverterUtil {
	
	/**
	 * 将email激活对象的Dto，转为本地的ActivatedToken，用户本地保存数据库
	 */
	public Function<EmailActivatedDto,ActivatedToken> EmailActivatedDtoToActivatedToken=
			x->{
				 ActivatedToken activatedToken=new ActivatedToken();
				 activatedToken.setEmail(x.getEmail());
				 activatedToken.setExpiryDate(x.getExpireDate());
				 activatedToken.setSentTimes(0);
				 activatedToken.setEmailToken(x.getEmailToken());
				 activatedToken.setReregisterToken(x.getReregisterToken());
				 activatedToken.setResendToken(x.getResendToken());
				 return activatedToken;
			};
			
			/**
			 * 将本地的对象ActivatedToken，转为EmailActivatedDto；这个用户重新发送的时候。
			 */
	public Function<ActivatedToken,EmailActivatedDto> activatedTokenToEmailActivatedDto=
			x->{
				EmailActivatedDto dto=new EmailActivatedDto();
				dto.setEmail(x.getEmail());
				dto.setEmailToken(x.getEmailToken());
				dto.setReregisterToken(x.getReregisterToken());
				dto.setResendToken(x.getResendToken());
				dto.setExpireDate(x.getExpiryDate());
				return dto;
			};

}
