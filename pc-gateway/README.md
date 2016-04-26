最后一个启动的服务。（目前是7）

spring cloud xdidian pc html5 gateway 实现。

使用spring proxy zuul 将后台所有的接口，自动路由到各个micro－service ，

实现了spring OAuth2 SSo 登陆。

因为有了spring proxy zuul，前台调用其它服务器service的方法，都必须加上对应的 路由前置。

例如物业公司注册的时候，前台web 页面post url，需要加上 /property-register/ ，最好结果是： /property-register/＋property-register 对应的 post 路由。


其它各个需要被导航到的service ，的一些逻辑判断，不在 pc－gateway里面判断，判断都移植到各自的 service执行。