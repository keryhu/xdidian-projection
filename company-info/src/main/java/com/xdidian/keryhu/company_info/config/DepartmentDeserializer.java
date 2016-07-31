package com.xdidian.keryhu.company_info.config;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.xdidian.keryhu.company_info.domain.Department;
import com.xdidian.keryhu.tree.LinkedMultiTreeNode;
import com.xdidian.keryhu.tree.TreeNode;


/**
 * 
 * @ClassName: DepartmentDisserializer
 * @Description: TODO(department tree node disserializer)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年7月31日 下午1:29:17
 */

public class DepartmentDeserializer extends JsonDeserializer<TreeNode<Department>> {

  @Override
  public TreeNode<Department> deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    // TODO Auto-generated method stub
    JsonNode node = jp.getCodec().readTree(jp);
    return parseDepartment(node);
  }


  private TreeNode<Department> parseDepartment(JsonNode node)
      throws JsonProcessingException, IOException {
    if (node.has("name")) {
      String name = node.get("name").asText();
      TreeNode<Department> n1 = new LinkedMultiTreeNode<Department>(new Department(name));
      if (node.has("department") && node.get("department").isArray()) {
        ArrayNode departments = (ArrayNode) node.get("department");

        Collection<TreeNode<Department>> backNodes = parseDepartments(departments);
        if (backNodes != null) {
          backNodes.forEach(e -> {
            n1.add(e);
          });
        }

      }
      return n1;
    } else {
      return null;
    }

  }

  private Collection<TreeNode<Department>> parseDepartments(JsonNode arrayNode)
      throws JsonProcessingException, IOException {
    Collection<TreeNode<Department>> a = new HashSet<>();
    if (arrayNode == null)
      return null;
    arrayNode.forEach(e -> {
      try {
        TreeNode<Department> m = parseDepartment(e);
        if (m != null) {
          a.add(m);
        }

      } catch (Exception e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }

    );
    return a;
  }

}
