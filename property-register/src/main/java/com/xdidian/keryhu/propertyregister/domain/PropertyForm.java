package com.xdidian.keryhu.propertyregister.domain;

import java.io.Serializable;

import lombok.Data;

/**
* @ClassName: PropertyForm
* @Description: TODO(物业公司用户注册 web class，此class 提交后会 转换为 PropertyRegisterDto)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月21日 下午6:44:00
 */
@Data
public class PropertyForm  implements Serializable{
	private static final long serialVersionUID = 1027409993570777508L;
	
	private String email;
	private String phone;
	private String password;
	private String companyName;  //公司名字
	private String directName;  //负责人姓名
	
	public PropertyForm(){
		// TODO Auto-generated constructor stub
		this.email=null;
		this.phone=null;
		this.password=null;
		this.companyName=null;
		this.directName=null;
	}

}
