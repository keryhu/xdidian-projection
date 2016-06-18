package com.xdidian.keryhu.useraccount.rest;

import com.xdidian.keryhu.domain.Role;
import com.xdidian.keryhu.useraccount.domain.AuthUserDto;
import com.xdidian.keryhu.useraccount.domain.User;
import com.xdidian.keryhu.useraccount.exception.UserNotFoundException;
import com.xdidian.keryhu.useraccount.service.ConverterUtil;
import com.xdidian.keryhu.useraccount.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserController {

    private final UserService userService;

    private final ConverterUtil converterUtil;


    /**
     * 根据用户输入的登录帐号loginName，email或者phone格式 查询数据库中的user
     * 对象,未注册用户和已经注册的用户都可以使用)
     * 返回的是spring data HAL rest 带有herf的链接，这个是 auth－service 需要调用的rest get接口
     * 必须使用@RequestParam，如果使用PathVariable，则查询email会有bug
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/query/findByLoginName")
    public ResponseEntity<?> findByLoginName(@RequestParam("loginName") String loginName) {

        //如果用户不存在，则抛出错误,返回json {"code":404,"message":"您查询的用户不存在！！"}
        User user = userService.findByLoginName(loginName)
                .orElseThrow(() -> new UserNotFoundException("您输入的identity，数据库中不存在！！"));
        //将User 转为 AuthUser对象
        AuthUserDto au = converterUtil.userToAuthUser.apply(user);
        return ResponseEntity.ok(au);
    }


    /**
     * 对外提供查询email是否存在数据库的api接口，不需要增加spring security验证
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/query/isEmailExist")
    public Boolean isEmailExist(@RequestParam("email") String email) {

        return userService.emailIsExist(email);
    }


    /**
     * 对外提供查询phone是否存在与数据库，不需要增加spring security验证
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/query/isPhoneExist")
    public Boolean isPhoneExist(@RequestParam("phone") String phone) {
        log.info("需要被查询的phone是： {} , phone  是否存在于数据库 ：{} ", phone, userService.phoneIsExist(phone));
        return userService.phoneIsExist(phone);
    }


    /**
     * 查询数据库中是否存在此公司名字
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/query/isCompanyNameExist")
    public Boolean isCompanyNameExist(@RequestParam("companyName") String companyName) {
        log.info("查询公司名字是否存在，公司名字是 ：{} , 查到的 结果 为 ： {}", companyName, userService.companyNameIsExist(companyName));
        return userService.companyNameIsExist(companyName);
    }

    /**
     * rest 接口查询当前loginName所在的用户，邮箱是否已经激活，如果激活，返回ture，默认是false
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/query/emailStatus")
    public Boolean emailStatus(@RequestParam("loginName") String loginName) {
        return userService.emailStatus(loginName);
    }

    /**
     * 根据提供的loginName，查询到数据库中此用户拥有的roles
     * 如果email不存在于数据库，返回空的roles，否则返回roles 的String 类型的List
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/query/roles")
    public List<Role> findRolesByLoginName(@RequestParam("loginName") String loginName) {


        return userService.findByLoginName(loginName)
                .filter(e -> e.getRoles() != null)
                .map(e -> e.getRoles())
                .orElse(new ArrayList<Role>());


    }

    /**
     * 提供rest接口，供login服务器，根据loginName查询对应的email
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/query/loginNameToEmail")
    public String loginNameToEmail(@RequestParam("loginName") String loginName) {
        return userService.loginNameToEmail(loginName);
    }


}
