# user-account用户账户系统
一 、 这是整个App项目的user的数据库查询，编辑，保存service，提供了查询，编辑，保存的rest 接口。其它程序如果需要调用
user的数据库，必须和这些接口对接，不能直接操作数据库。
目前实现了下面几个功能：

 AuthUser 接口、 email 接口、 phone 接口、 companyName 接口 、 Property 注册接口 、 user 删除接口。

二 、 它本身是一个spring OAuth2 Resource Service，实现了根据权限大小，分配不同的功能，也就是权限的层级关系。


三 、 AuthUser接口： 方便Auth－service 查询该用户的id、password、roles从而验证用户登陆信息是否正确（实现了Dto）

> /users/query/findByIdentity get 方法  @RequestParam("identity") ， 无security

四 、 email 接口： 远程查询该email是否存在于数据库
> /users/query/isEmailExist  get 方法 @RequestParam("email") ， 无security

五 、 phone 接口： 远程查询phone是否存在于数据库，
> /users/query/isPhoneExist get 方法，@RequestParam("phone") ， 无security

六 、 companyName 接口： 远程查询companyName是否存在于数据库，

> /users/query/isComponyNameExist get 方法 @RequestParam("phone") ， 无security

七 、 property 注册接口： 用户propertyRegitser 服务器，将用户注册数据，远程post 给该接口，该接口实现了保存数据库的功能。
> /users/property/save post 方法，保存，无 security，但是保存之前，必须是非登陆用户，做了相应的拦截。

八： user 删除接口： 用户注销账户，管理员清理账户，

> /users/{id} delete 方法  有security，目前还未设置。

九 、 实现了两个Dto（AuthUser 和 propertyRegister ）


