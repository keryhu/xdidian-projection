# 物业公司注册（property－register）

一 、 第五个启动的service，

二 、 物业公司注册后台程序流程：

  1 、链接web 的class  propertyForm 类将用户web注册数据搜集起来
    
   2 、然后传给propertyRegisterDto，
   
   3 、 验证体检的数据的合法性，是否符合email，phone，password规范，查询是否已经注册过，如果满足条件，执行下一步
   
   4 、 将注册的信息打包成message dto 利用spring cloud stream 发送给user-account 
   
   5 、 user－account 接受到注册信息的message，执行dto converter 转换成user 的操作，然后保存数据库，并发送email出去激活帐号。
   
   6 、 然后页面跳转到 信息提示页面，“查看邮件，或再次发送。。”，这些是 pc-gateway和 user-account 来处理。－－－完成
   
   三 、 
