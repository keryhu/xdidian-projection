#OAuth2 -server

**一  、** 第四个启动的service，spring cloud OAuth2 server ,使用jwt 实现了全局 security

二 、 userDetailsService 获取验证用户是从user-account service 通过spring Feign 远程get 获取，同时新建了专门传输user 信息（id,hashPassword,roles)的class AuthUser。

> ***！！不需要增加用户名，密码是否是否正确的Api查询接口，因为这样会容易暴露password***

三 、 后台验证用户不存在，密码错误，ip冻结，email未激活

四 、 为了防止黑客暴力攻击登陆账户数据库，设置了指定时间内，超过xx次登陆失败，就封锁该ip多少个小时的功能。实现的方法，通过AuthenticationFailureListener 和 AuthenticationSuccessListener 两个方法，监听登陆失败和登陆成功，然后纪录失败的次数，保存数据库实现。
 (测试，1 － 如果没达到最大限制，能正常输入 ，2 － 如果大道最大限制，就会被锁定，3 如果清零时间到了，自动清零 4 如果没锁定的情况下登录成功，自动恢复默认，5 如果锁定时间未超时，自动抛出异常， 6 如果锁定超时，自动恢复默认)


五 、 通过监听用户登录成功事件，实现了用户登录成功后，发送spring cloud stream message（userId） 出去，（user——account）接受，并且更新用户登录时间为当时时间的功能。

六 、 实现了用户登录检测用户的email是否激活的功能，如果未激活，则不能登录，后台做了未激活，自动拦截的功能，前台能过正确显示“email未激活的提示信息，不做任何页面跳转，让客户选择是自己查看邮箱还是重新注册，是否跳转，目前未定，