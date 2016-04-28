#OAuth2 -server

**一  、** 第四个启动的service，spring cloud OAuth2 server ,使用jwt 实现了全局 security

二 、 userDetailsService 获取验证用户是从user-account service 通过spring Feign 远程get 获取，同时新建了专门传输user 信息（id,hashPassword,roles)的class AuthUser。

三 、  提供了用户login时，ajax查询email，phone 是否存在于数据库的 rest 接口（也是通过spring Feign从user-account service get ）两个对外查询的非security 接口是：

	/query/isEmailExist?email=xx
	
	/query/isPhoneExist?phone=xx


> 如果email（phone）已经注册过，则返回true，否则false

> ***！！不需要增加用户名，密码是否是否正确的Api查询接口，因为这样会容易暴露password***

四 、 增加了点击“登陆”时，用户名，密码输入错误，跳转到 /login?error 页面，并且显示错误信息－－用户名，密码输入错误，重新输入或注册。

五 、 增加了successHandler，就是用户登陆后，如果是admin用户，就会成功跳转admin 首页，目前注释掉了。随时可以开启。

六 、 开启了csrf 验证，在login 登陆页面，也开启了csrf，
七、  增加了CORS配置，方便pc-gateway，远程注销用户。