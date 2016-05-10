# user-account用户账户系统
一 、 这是整个App项目的user的数据库查询，编辑，保存service，提供了查询，编辑，保存的rest 接口。其它程序如果需要调用
user的数据库，必须和这些接口对接，不能直接操作数据库。
目前实现了下面几个功能：

 AuthUser 接口、 email 接口、 phone 接口、 companyName 接口 、  user 删除接口。

二 、 它本身是一个spring OAuth2 Resource Service，实现了根据权限大小，分配不同的功能，也就是权限的层级关系。


三 、 AuthUser接口： 方便Auth－service 查询该用户的id、password、roles从而验证用户登陆信息是否正确（实现了Dto）

> /users/query/findByIdentity get 方法  @RequestParam("identity") ， 无security

四 、 email 接口： 远程查询该email是否存在于数据库，返回的是json－map
> /users/query/isEmailExist  get 方法 @RequestParam("email") ， 无security

五 、 phone 接口： 远程查询phone是否存在于数据库，返回的是json－map
> /users/query/isPhoneExist get 方法，@RequestParam("phone") ， 无security

六 、 companyName 接口： 远程查询companyName是否存在于数据库，返回的是json－map

> /users/query/isComponyNameExist get 方法 @RequestParam("phone") ， 无security


七 、 user 删除接口： 用户注销账户，管理员清理账户，

> /users/{id} delete 方法  有security，目前还未设置。

八 、 实现了两个Dto（AuthUser 和 propertyRegister ）

九 、 更新用户登录时间－－－通过spring cloud stream 监听message（用户注册成功），接受到userId后，自动更新到该user 的 上次登录成功的时间为当时时间。

十 、 通过spring cloud stream 监听 物业公司注册信息的message，当收到此信息后，converter 为user ，增加email激活码和设置email激活状态为false，后然后保存数据库。

十一 、 取出刚刚注册的user信息，转为EmailActivatedDto对象，然后发送message ，要求mail-server 发送注册帐号激活的email

十二 、 当用户注册完后，前台页面跳转到 显示消息提示“ 请查收邮箱，如未收到，请查看垃圾邮件，或重新发送邮件，或重新注册”，pc-gateway，通过spring zuul获取到后台的rest接口，来显示上面的信息，和完成ajax提交。

十三 、 当用户点击 激活email的链接时，后台有一个对应url的get方法，来判断url是否正确，是否过时，如果一起都是预期的，则将数据保存到数据库，并且更新用户的email激活状态为true，跳转login页面，并显示 激活成功的 提示信息，
