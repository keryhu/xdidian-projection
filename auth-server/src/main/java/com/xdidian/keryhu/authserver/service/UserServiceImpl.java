package com.xdidian.keryhu.authserver.service;


import com.xdidian.keryhu.authserver.client.UserClient;
import com.xdidian.keryhu.authserver.domain.AuthUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


/**
 * Description : Auth-server的service
 * Date : 2016年06月17日 下午10:30
 * Author : keryHu keryhu@hotmail.com
 */
@Service("userService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserClient userClient;

    /**
     * 根据User 的 唯一标志符 用户登录的帐号。例如 email ，phone 等查询唯一的数据库用户
     */
    @Override
    public Optional<AuthUserDto> findByLoginName(String loginName) {

        return Optional.ofNullable(userClient.ByLoginName(loginName));
    }

    /**
     * 获取当前ip地址
     */
    @Override
    public String getIP(HttpServletRequest request) {
        // TODO Auto-generated method stub
        String xfHeader = request.getHeader("X-Forwarded-For");
        return (xfHeader == null || xfHeader.isEmpty()) ? request.getRemoteAddr() : xfHeader.split(",")[0];

    }


}
