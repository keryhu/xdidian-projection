package com.xdidian.keryhu.authserver.domain;

import java.io.Serializable;
import java.util.List;
import com.xdidian.keryhu.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 
* @ClassName: AuthUserDto
* @Description: TODO(user 登录的时候，需要验证的选项，除了密码，password，还需要验证 
* emailActivatedSendTimes有没有超过规定的次数，不过这个次数可以通过远程rest获取， emailActivatedStatus 
* 是否已经email激活验证了)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月6日 上午9:54:51
 */

@AllArgsConstructor
@Data
public class AuthUserDto implements Serializable {
	
	private static final long serialVersionUID = -8674622062902968568L;
	private String id;
	private String password;
	private List<Role> roles;
	private String email;
	private boolean EmailActivatedStatus;
	
	public AuthUserDto() {
		// TODO Auto-generated constructor stub
		this.id=null;
		this.password=null;
		this.roles=null;
		this.email=null;
		this.EmailActivatedStatus=false;
	}
}
