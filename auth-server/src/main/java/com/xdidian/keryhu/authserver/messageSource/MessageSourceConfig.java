/**  
* @Title: MessageSourceConfig.java
* @Package com.xdidian.keryhu.authserver.client
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月1日 下午2:47:44
* @version V1.0  
*/ 
package com.xdidian.keryhu.authserver.messageSource;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


/**
* @ClassName: MessageSourceConfig
* @Description: TODO(为了配置messageSource)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月1日 下午2:47:44
*/
@Configuration
public class MessageSourceConfig {
    
	
	@Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver slr=new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.CHINA);
		return slr;
	}
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource=new ReloadableResourceBundleMessageSource();
		// 文件就在 src/main/resources/locale/下的message文件
		messageSource.setBasename("classpath:locale/messages");
		messageSource.setDefaultEncoding("UTF-8");
		//每隔小时自动更新文件信息。
		messageSource.setCacheSeconds(3600);
		return messageSource;
	}
	

}
