package com.xdidian.keryhu.user_account.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.xdidian.keryhu.user_account.service.UserService;
import static com.xdidian.keryhu.util.StringValidate.isEmail;
import static com.xdidian.keryhu.util.StringValidate.isPhone;



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
  public void activateSuccess(final String id) {

    if (!(id == null || id.isEmpty())) {
      userService.findByLoginName(id).ifPresent(e -> {
        if(isEmail(id)){
          e.setEmailStatus(true);
        }
        if(isPhone(id)){
          e.setPhoneStatus(true);
        }
        
        userService.save(e);
        log.info("user-account 成功激活 {} 的user  ", id);
      });

    }
  }

}
