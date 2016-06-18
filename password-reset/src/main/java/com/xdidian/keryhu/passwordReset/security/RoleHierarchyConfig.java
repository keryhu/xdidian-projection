package com.xdidian.keryhu.passwordReset.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

@Configuration
public class RoleHierarchyConfig {

    /**
     * 设定管理员>客服人员> 物业公司注册人员 或 物业租户
     *
     * @return RoleHierarchyImpl    返回类型
     */
    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
        //(设定管理员>客服人员> 物业公司注册人员 或 物业租户)
        //new StringBuffer("")
        roleHierarchyImpl.setHierarchy("ROLE_ADMIN>ROLE_SERVICE  ROLE_SERVICE>ROLE_PROPERTY  "
                + "  ROLE_SERVICE>ROLE_TENANT  ROLE_SERVICE>ROLE_INTENTION ");
        return roleHierarchyImpl;
    }


}
