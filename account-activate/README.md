帐号激活，包含了email和phone激活，例如系统发送激活码到email邮箱，或者到客户手机，然后用户 前台输入 验证码，后台再进行 对应的
email－token或phone－token，匹配验证，和有效期验证，

email 激活需要验证的逻辑

如果验证过期：

  1 前台web提交－  a： 跳转到注册页面 b： 删除过期的user-account里面的user数据  c： 删除email-activate 里的 email所在的数据， 
  
  2 后台login平台只负责报错－－－email未激活
  
  3  点击email激活URl，所做的3件事情，和上述 1 一样。
  
  
  
物业公司注册完，
  1  前台已经能过实现跳转专用的email激活页面
  2  能够正确收到激活email，token和新的user都能保存数据库
  
如果点击重新注册，则删除所有的与刚刚注册的email有关联的 数据库记录，并且导航到注册页面。


发送次数超过规定的次数后，自动弹出错误，阻止再次发送邮件。

如果点击”再次发送“，且未超过限制，那么就会更新验证码，同时还增加了固定时间内只能 只能进行有限的 “再次发送” 。

对外提供 从emailActivatedToken数据库中查询 email 是否存在的rest 接口

这个接口内部再实现－－－判断email激活是否过期，如果过期执行过期的方法，如果没有过期，那么返回前台对应的refreshToken和resignupToken



