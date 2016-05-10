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
import lombok.extern.slf4j.Slf4j;

/**
* @ClassName: UserServiceImpl
* @Description: TODO(实现了UserService 的接口)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月3日 上午11:27:11
*/
@Component("userService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
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
		log.info("需要验证的propertyForm is ： "+propertyForm);
		boolean isEmailCorrect=StringValidate.isEmail(propertyForm.getEmail());
		boolean isPhoneCorrect=StringValidate.isPhone(propertyForm.getPhone());
		boolean isPasswordCorrect=StringValidate.isPassword(propertyForm.getPassword());
		boolean isCompanyName=StringValidate.isCompanyName(propertyForm.getCompanyName());
		boolean isDirectName=StringValidate.isPeopleName(propertyForm.getDirectName());
		
		//所有的输入信息的合法性  boolean，必须确保他们为true，否则报错。
		boolean allCorrect=isEmailCorrect&&isPhoneCorrect&&isPasswordCorrect&&isCompanyName&&isDirectName;
		
		//email，phone，companyName都必须没有注册过，
		boolean noExist=(!userAccountClient.isEmailExist(propertyForm.getEmail()).get("isEmailExist"))&&
				(!userAccountClient.isPhoneExist(propertyForm.getPhone()).get("isPhoneExist"))&&
				(!userAccountClient.isCompanyNameExist(propertyForm.getCompanyName()).get("isCompanyNameExist"));
		log.info("email 是否存在于数据库   "+userAccountClient.isEmailExist(propertyForm.getEmail()));
		log.info("手机是否存在于数据库  "+userAccountClient.isPhoneExist(propertyForm.getPhone()));
		log.info("公司名字是否存在于数据库  "+userAccountClient.isCompanyNameExist(propertyForm.getCompanyName()));
		log.info("是否所有的输入信息都符合规定 ： "+allCorrect+" ， 是否所有输入的信息，在系统中都不存在 ： "+noExist);
		
		if(!(allCorrect&&noExist)){
			throw new PropertySaveException("输入信息不合法，或者提供的手机号，email，公司名字已经注册过!");
		}
		
	}


}
