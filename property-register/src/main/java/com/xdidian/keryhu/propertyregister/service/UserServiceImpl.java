/**  
* @Title: UserServiceImpl.java
* @Package com.xdidian.keryhu.propertyregister.service
* @Description: TODO(用一句话描述该文件做什么)
* @author keryhu  keryhu@hotmail.com  
* @date 2016年5月3日 上午11:27:11
* @version V1.0  
*/ 
package com.xdidian.keryhu.propertyregister.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xdidian.keryhu.propertyregister.client.UserAccountClient;
import com.xdidian.keryhu.propertyregister.domain.PropertyForm;
import com.xdidian.keryhu.propertyregister.exception.PropertySaveException;
import com.xdidian.keryhu.util.StringValidate;

import lombok.RequiredArgsConstructor;

/**
* @ClassName: UserServiceImpl
* @Description: TODO(实现了UserService 的接口)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月3日 上午11:27:11
*/
@Component("userService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
	
	private final UserAccountClient userAccountClient;

	/**
	* <p>Title: vlidateBeforSave</p>
	* <p>Description: 验证前台输入信息的合法性</p>
	* @param propertyForm  
	* @see com.xdidian.keryhu.propertyregister.service.UserService#vlidateBeforSave(com.xdidian.keryhu.propertyregister.domain.PropertyForm)
	*/ 
	@Override
	public void vlidateBeforSave(PropertyForm propertyForm) {
		// TODO Auto-generated method stub
		
		boolean isEmailCorrect=StringValidate.IsEmail(propertyForm.getEmail());
		boolean isPhoneCorrect=StringValidate.IsPhone(propertyForm.getPhone());
		boolean isPasswordCorrect=StringValidate.IsPassword(propertyForm.getPassword());
		boolean isCompanyName=StringValidate.IsCompanyName(propertyForm.getCompanyName());
		boolean isDirectName=StringValidate.IsPeopleName(propertyForm.getDirectName());
		
		//所有的输入信息的合法性  boolean，必须确保他们为true，否则报错。
		boolean allCorrect=isEmailCorrect&&isPhoneCorrect&&isPasswordCorrect&&isCompanyName&&isDirectName;
		
		//email，phone，companyName都必须没有注册过，
		boolean allExist=(!userAccountClient.isEmailExist(propertyForm.getEmail()))&&
				(!userAccountClient.isPhoneExist(propertyForm.getPhone()))&&
				(!userAccountClient.isCompanyNameExist(propertyForm.getCompanyName()));
		
		if(!(allCorrect&&allExist)){
			throw new PropertySaveException("输入信息不合法，或者提供的手机号，email，公司名字已经注册过!");
		}
		
	}

}