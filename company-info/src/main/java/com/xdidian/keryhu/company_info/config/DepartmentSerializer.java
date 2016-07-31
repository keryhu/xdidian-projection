package com.xdidian.keryhu.company_info.config;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.xdidian.keryhu.company_info.domain.Department;
import com.xdidian.keryhu.tree.TreeNode;


/**
 * @Description : TODO(Department的json自定义序列化)
 * @date : 2016年7月28日 下午8:38:43
 * @author : keryHu keryhu@hotmail.com
 */


public class DepartmentSerializer extends JsonSerializer<TreeNode<Department>> {

	@Override
	public void serialize(TreeNode<Department> value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub

	  writeDepartment(value, jgen);

	}
	
	private void writeDepartment(TreeNode<Department> node,JsonGenerator jgen)
			throws IOException{
		jgen.writeStartObject();
		Department department = node.data();
		if (department == null) {
			jgen.writeNullField("name");
		} else {
			jgen.writeStringField("name", department.getName());
		}
		// 写他的下代，

		writeDepartments(node.subtrees(),jgen);
		
		jgen.writeEndObject();
	}

	private void writeDepartments(Collection<? extends TreeNode<Department>> nodes, JsonGenerator jgen)
			throws IOException {

		if(nodes.isEmpty()) return;
		jgen.writeArrayFieldStart("department");
		
		for(TreeNode<Department> node:nodes){
			writeDepartment(node,jgen);
		}
		jgen.writeEndArray();
	}

}
