package com.xdidian.keryhu.useraccount.domain;

import com.xdidian.keryhu.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description : user 登录的时候，需要验证的选项，出了密码，password，还需要验证
 * emailActivatedSendTimes有没有超过规定的次数，不过这个次数可以通过远程rest获取， emailActivatedStatus
 * 是否已经email激活验证了
 * Date : 2016年06月18日 上午11:17
 * Author : keryHu keryhu@hotmail.com
 */
@AllArgsConstructor
@Data
public class AuthUserDto implements Serializable {
	
	private static final long serialVersionUID = -4128086432158731873L;
	
	private String id;
	private String password;
	private List<Role> roles;
	private boolean emailStatus;
	
	public AuthUserDto() {
		// TODO Auto-generated constructor stub
		this.id=null;
		this.password=null;
		this.roles=null;
		this.emailStatus=false;
	}
		
}
