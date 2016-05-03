package com.xdidian.keryhu.authserver.domain;

import java.io.Serializable;
import java.util.List;
import com.xdidian.keryhu.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 这个是提供给OAuth2 service 验证的DTO，
 * @author hushuming
 *
 */



@AllArgsConstructor
@Data
public class AuthUserDto implements Serializable {
	
	private static final long serialVersionUID = -8674622062902968568L;
	private String id;
	private String password;
	private List<Role> roles;
	
	public AuthUserDto() {
		// TODO Auto-generated constructor stub
		this.id=null;
		this.password=null;
		this.roles=null;
	}
		
}
