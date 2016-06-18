package com.xdidian.keryhu.useraccount.stream;

import com.xdidian.keryhu.useraccount.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;


/**
 * @Description : 当email成功被激活后，从email-activated发出的成功激活的email的message，此channel用来
 *              专门接受这样的消息,这个是具体的接受到消息调用起来的执行方法
 * @date : 2016年6月18日 下午9:23:48
 * @author : keryHu keryhu@hotmail.com
 */
@EnableBinding(ActivatedSuccessInputChannel.class)
@Slf4j
public class ActivatedSuccessConsumer {

  @Autowired
  private UserService userService;

  @StreamListener(ActivatedSuccessInputChannel.NAME)
  public void removeUser(final String email) {

    if (!(email == null || email.isEmpty())) {
      userService.findByLoginName(email).ifPresent(e -> {
        e.setEmailStatus(true);
        userService.save(e);
        log.info("user-account 成功删除了此user 的 id is ：{} ", e);
      });

    }
  }

}
