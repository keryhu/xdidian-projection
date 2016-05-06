package com.xdidian.keryhu.useraccount.domain;

import java.io.Serializable;
import java.util.List;
import com.xdidian.keryhu.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 
* @ClassName: AuthUserDto
* @Description: TODO(user 登录的时候，需要验证的选项，出了密码，password，还需要验证 
* emailActivatedSendTimes有没有超过规定的次数，所以必须提供次参数， emailActivatedStatus 
* 是否已经email激活验证了)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月6日 上午9:47:42
 */

@AllArgsConstructor
@Data
public class AuthUserDto implements Serializable {
	
	private static final long serialVersionUID = -4128086432158731873L;
	
	private String id;
	private String password;
	private List<Role> roles;
	private boolean emailActivatedStatus;    //当前用户，邮件是否已经被激活。
	private int emailActivatedSendTimes;    //当前用户已经发送激活邮件的次数
	
	public AuthUserDto() {
		// TODO Auto-generated constructor stub
		this.id=null;
		this.password=null;
		this.roles=null;
		this.emailActivatedStatus=false;
		this.emailActivatedSendTimes=0;
	}
		
}
