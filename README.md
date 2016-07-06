# xdidian-micro-docker
一 、 xdidian  micro server docker 
这是自行开发企业项目的的github代码库
spring micro－service cloud docker domo

二 、 目前启动顺序是： eureka －> config server ->user account-> auth server
-> property-register -> mail-activation-> pc gateway 等其他的

三 、 目前是每一个service单独的数据库，相互之间没有实现message broker。

四 、 增加了在指定限制时间内email激活账户的功能，如果未激活不能登录账户，超时了未激活，自动删除账户。


四 、 更新于 2016-7-5: 接下来需要实现的功能： 
     实现了前台oauth2，前台 password grant功能。
     短暂的access－token 和 长时间的 refreshtoken功能（并且refreshtoken保存在后台数据库）
     忘记密码，重设密码功能
     
     在下一步是，编辑用户资料的功能
  
 

 
   