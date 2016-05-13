package com.xdidian.keryhu.propertyregister.client;



import org.springframework.stereotype.Component;


/**
* @ClassName: CreateUserClientFallback
* @Description: TODO(userAccount rest service 方法。)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月26日 上午9:25:12
 */
@Component
public class UserAccountClientFallback implements UserAccountClient {



	/**
	* <p>Title: isEmailExist</p>
	* <p>Description: </p>
	* @param email
	* @return
	* @see com.xdidian.keryhu.propertyregister.client.UserAccountClient#isEmailExist(java.lang.String)
	*/ 
	@Override
	public Boolean isEmailExist(String email) {
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
	public Boolean isPhoneExist(String phone) {
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
	public Boolean isCompanyNameExist(String companyName) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
