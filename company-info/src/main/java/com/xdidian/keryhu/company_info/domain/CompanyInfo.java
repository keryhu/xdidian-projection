package com.xdidian.keryhu.company_info.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.Gson;
import com.xdidian.keryhu.tree.TreeNode;

import lombok.Data;

/**
 * 
* @ClassName: CompanyInfo
* @Description: TODO(员工所在公司的基本信息，包含公司名字，地址，客户平台管理员，组织机构图，营业执照图片)
* @author keryhu  keryhu@hotmail.com
* @date 2016年7月28日 下午1:02:50
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CompanyInfo implements Serializable{

	private static final long serialVersionUID = -1554578596908142213L;
	
	private String id="123";
	private String companyName="上海利好公司";
	private String address="an ting ";
	private String adminId="11";   //平台，客户管理员
	
	@JsonSerialize(using = DepartmentSerializer.class)
	private TreeNode<Department> department;
	
	public CompanyInfo(){
		TreeNode<Department> n1 = new DepartmentTreeNode<>(new Department("111"));
		TreeNode<Department> n2 = new DepartmentTreeNode<>(new Department("222"));
		TreeNode<Department> n3 = new DepartmentTreeNode<>(new Department("333"));
		TreeNode<Department> n4 = new DepartmentTreeNode<>(new Department("444"));
		TreeNode<Department> n5 = new DepartmentTreeNode<>(new Department("555"));
		TreeNode<Department> n6 = new DepartmentTreeNode<>(new Department("666"));
		TreeNode<Department> n7 = new DepartmentTreeNode<>(new Department("777"));

		n1.add(n2);
		n1.add(n3);
		n1.add(n4);
		n2.add(n5);
		n2.add(n6);
		n4.add(n7);
		this.department=n1;
		System.out.println(n1);
		Gson gson=new Gson();
		System.out.println(gson.toJson(n1));
		
	
	}
	
	
	

}
