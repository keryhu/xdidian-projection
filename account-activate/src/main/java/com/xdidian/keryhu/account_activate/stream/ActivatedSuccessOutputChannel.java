package com.xdidian.keryhu.account_activate.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;


/**
 * @Description : 这个只用作email或phone激活成功后，发送出去包含email或phone的message。
 * 发出去后需要坐的3件事情：
 * user-account 接受到消息。查询email或pone所在的user，修改emailStatus 或phoneStatus为 true。
 * user-account所在的系统，删除此user所在的数据库。
 * 
 * @date : 2016年6月18日 下午9:01:03
 * @author : keryHu keryhu@hotmail.com
 */
public interface ActivatedSuccessOutputChannel {

    //此channel的值和 application bindings下面的值一致
    String NAME = "activatedSuccessOutputChannel";

    @Output(NAME)
    MessageChannel success();

}
