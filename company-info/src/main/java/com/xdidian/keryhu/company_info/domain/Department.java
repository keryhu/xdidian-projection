package com.xdidian.keryhu.company_info.domain;

import java.io.Serializable;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
* @ClassName: Department
* @Description: TODO(企业部门的pojo，最底层是具体的职位名字。例如：实习生，人事专员，销售经理等)
* @author keryhu  keryhu@hotmail.com
* @date 2016年7月27日 下午6:10:04
 */
@ToString
public class Department implements Serializable{

	private static final long serialVersionUID = 7229242816438511357L;
	
	@Setter
    @Getter
	private String id;   //department id ,用来区分不同的部门或者官职，例如市场部，销售经理，有着不同的id
	
	@Setter
	@Getter
	private String name;   //部门的名字
	 
	
	public Department(){
		super();
	}
	
	public Department(String name){
		this();
		setName(name);
		this.id=UUID.randomUUID().toString();
	}
	
	

}
