这是整个App项目的user的数据库查询，编辑，保存service，提供了查询，编辑，保存的rest 接口。其它程序如果需要调用
user的数据库，必须和这些接口对接，不能直接操作数据库。
目前实现了：

这是一个spring oauth2 Resouce security，如果要访问此端口，必须登录拥有相应的权限才可以。并且根据权限大小实现了权限排序验证。即
admin拥有其它所有权限。就是权限的层级关系。

此是spring cloud 的用户查询的service

提供user的两种DTO 类，方便auth service 验证的 AuthUserDto ， 方便物业公司注册的 propertyregisterDto，方便其它接口无缝调用user服务。