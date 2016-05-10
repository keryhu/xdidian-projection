#OAuth2 -server

**一  、** 第四个启动的service，spring cloud OAuth2 server ,使用jwt 实现了全局 security

二 、 userDetailsService 获取验证用户是从user-account service 通过spring Feign 远程get 获取，同时新建了专门传输user 信息（id,hashPassword,roles)的class AuthUser。

三 、  提供了用户login时，ajax查询email，phone 是否存在于数据库的 rest 接口（也是通过spring Feign从user-account service get ）两个对外查询的非security 接口是：

	/query/isEmailExist?email=xx
	
	/query/isPhoneExist?phone=xx
	
	/query/leftLoginAttemptTimes (no 参数）


> 如果email（phone）已经注册过，则返回json对象，{"isEmailExist":true}，否则false；/query/leftLoginAttemptTimes，用来查询目前还剩几次输错账户登陆的机会

> ***！！不需要增加用户名，密码是否是否正确的Api查询接口，因为这样会容易暴露password***

四 、 增加了点击“登陆”时，用户名，密码输入错误，跳转到 /login?error 页面，并且显示错误信息－－用户名，密码输入错误，重新输入或注册。

五 、 增加了successHandler，就是用户登陆后，如果是admin用户，就会成功跳转admin 首页，目前注释掉了。随时可以开启。

六 、 开启了csrf 验证，在login 登陆页面，也开启了csrf，

七 、 增加了CORS配置，方便pc-gateway，远程注销用户。

八 、 增加了后台，用户名输入错误，系统中不存在的判断。

九 、 为了防止黑客暴力攻击登陆账户数据库，设置了指定时间内，超过xx次登陆失败，就封锁该ip多少个小时的功能。实现的方法，通过AuthenticationFailureListener 和 AuthenticationSuccessListener 两个方法，监听登陆失败和登陆成功，然后纪录失败的次数，保存数据库实现。
 (测试，1 － 如果没达到最大限制，能正常输入 ，2 － 如果大道最大限制，就会被锁定，3 如果清零时间到了，自动清零 4 如果没锁定的情况下登录成功，自动恢复默认，5 如果锁定时间未超时，自动抛出异常， 6 如果锁定超时，自动恢复默认)

十 、 实现了登陆时输错账户，密码，自动跳转/login?error页面，显示错误信息的功能，目前能够显示账户被冻结的信息，和通过messageSource，实现了 badCredentials 错误信息 message.property，自定义的功能。到时候通过js，远程传递当前失败了多少次的 参数，显示该页面。

十一 、 403，非授权和401 页面显示问题，到时候通过前途 httpCode 拦截，设置页面实现。

十二 、 通过监听用户登录成功事件，实现了用户登录成功后，发送spring cloud stream message（userId） 出去，（user——account）接受，并且更新用户登录时间为当时时间的功能。

十四 、 实现了用户登录检测用户的email是否激活的功能，前台负责检测，并且提示信息，ajax获取需要检测的数据，只有用户名，密码检测需要后台提示信息。如果用户的email未激活，且未过期，则前台显示“再次发送激活邮件，或重新注册”，当用户选择这两个按钮的时候，对应的后台是再次发送email激活的message和删除此user 的message，如果用户激活次数过多，则提示“激活多余频繁，请稍后再试，或重新注册”