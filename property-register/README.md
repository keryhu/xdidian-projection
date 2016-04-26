第五个启动的service，
物业公司注册后台程序：
链接web 的class  propertyForm 类将用户web注册数据搜集起来，然后传给propertyRegisterDto，

接着propertyRegisterDto 将数据远程 post 给 user－account service 。

这个post 是 permit all ，不需要spring security 但是增加了一个方法拦截（已经登陆的用户无法在此注册，必须先退出登陆）