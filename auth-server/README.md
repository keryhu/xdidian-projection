第四个启动的service，spring cloud OAuth2 server ,使用jwt 实现了全局 security

验证的用户从user account 远程get 。

提供了一个所有用户的登陆授权页面，／login，现在需要设置web 前端提交验证，
例如验证email 是否存在在数据库，如果email不在数据库，那么前台报错，“您的email未注册”，相应的还有phone 判断，


实现了2个对外查询接口，用于前台web登陆 ajax 查询。

/query/isEmailExist?email=xx   如果此email已经注册过，则返回true，否则false

/query/isPhoneExist?phone=xx   如果此phone已经注册过，则返回true，否则false


不需要增加 用户名，密码是否是否正确的pai查询接口，因为这样会容易暴露password

增加了点击“登陆”时，用户名，密码输入错误，跳转到 /login?error 页面，并且显示错误信息－－用户名，密码输入错误，重新输入或注册。

增加了successHandler，就是用户登陆后，如果是admin用户，就会成功跳转admin 首页，目前注释掉了。随时可以开启。