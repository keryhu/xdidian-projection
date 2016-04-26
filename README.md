# xdidian-micro-docker
xdidian  micro server docker 
这是自行开发企业项目的的github代码库
spring micro－service cloud docker domo

目前启动顺序是： eureka －> config server ->user account-> auth server
-> property-register -> pc gateway 等其他的

目前是每一个service单独的数据库，相互之间没有实现message broker。



2016-3-2: 目前进展－本地和docker均能正常跑起来。

实现的功能：
 1 auth－server 实现了oauth2 功能。全局的oauth2 SSO功能
 2 config-server 实现了单独的spring-security功能。
 3 实现了固定IP 192.168.1.110
 4 实现了encry编码加密和解密功能。
 5 实现了spring－cloud service 注册和被发现功能。
 6 spring－service连接mongo本地数据库功能
 7 mongo数据库可便携移动迁移的功能，包含了docker－data－volume
 8  升级到了docker－compose 1.6
 9  增加了mail－server 发送邮件服务，目前已成功，产品上线时，修改成正确的邮件服务器
 10  增加了rabbitmq 的docker compose，并且配置比较智能化
      

 
 

 
   