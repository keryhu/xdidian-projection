package com.xdidian.keryhu.propertyregister.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.xdidian.keryhu.propertyregister.domain.PropertyRegisterDto;



/**
* @ClassName: CreateUserClientFallback
* @Description: TODO(userAccount rest service 方法。)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月26日 上午9:25:12
 */
@Component
public class UserAccountClientFallback implements UserAccountClient {

	@Override
	public ResponseEntity<PropertyRegisterDto> createUser(PropertyRegisterDto dto) {
		// TODO Auto-generated method stub
		return new ResponseEntity<PropertyRegisterDto>(new PropertyRegisterDto(),HttpStatus.BAD_GATEWAY);
	}

	@Override
	public ResponseEntity<?> del(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* <p>Title: isEmailExist</p>
	* <p>Description: </p>
	* @param email
	* @return
	* @see com.xdidian.keryhu.propertyregister.client.UserAccountClient#isEmailExist(java.lang.String)
	*/ 
	@Override
	public boolean isEmailExist(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	* <p>Title: isPhoneExist</p>
	* <p>Description: </p>
	* @param phone
	* @return
	* @see com.xdidian.keryhu.propertyregister.client.UserAccountClient#isPhoneExist(java.lang.String)
	*/ 
	@Override
	public boolean isPhoneExist(String phone) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	* <p>Title: isCompanyNameExist</p>
	* <p>Description: </p>
	* @param companyName
	* @return
	* @see com.xdidian.keryhu.propertyregister.client.UserAccountClient#isCompanyNameExist(java.lang.String)
	*/ 
	@Override
	public boolean isCompanyNameExist(String companyName) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
