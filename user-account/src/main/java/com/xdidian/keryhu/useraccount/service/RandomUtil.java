/**  
* @Title: RandomUtil.java
* @Package com.xdidian.keryhu.useraccount.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月7日 下午3:32:30
* @version V1.0  
*/ 
package com.xdidian.keryhu.useraccount.service;

import java.util.Random;

import org.springframework.stereotype.Component;

/**
* @ClassName: RandomUtil
* @Description: TODO(产生随机字符串的 Bean)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月7日 下午3:32:30
*/
@Component
public class RandomUtil {
	
	public final String ALLCHAR="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public String create( int length){
		
		StringBuffer sb = new StringBuffer();  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {  
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));  
        }  
        return sb.toString();  
		
	}

}
