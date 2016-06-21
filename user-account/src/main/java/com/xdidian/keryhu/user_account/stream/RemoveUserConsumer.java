package com.xdidian.keryhu.user_account.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.xdidian.keryhu.user_account.service.UserService;

/**
 * @Description : 接受到需要删除User消息时，具体的实施方法,传递过来的参数id，可能是uuid，也可能是email， 还可能是phone，需要先判断。然后将他们都转为user 的
 *              id
 * @date : 2016年6月18日 下午9:26:15
 * @author : keryHu keryhu@hotmail.com
 */
@EnableBinding(RemoveUserInputChannel.class)
@Slf4j
public class RemoveUserConsumer {

  @Autowired
  private UserService userService;

  @StreamListener(RemoveUserInputChannel.NAME)
  public void removeUser(String id) {
    log.info("id is : {}", id);
    if (!(id == null || id.isEmpty())) {

      userService.findById(id).ifPresent(e -> userService.deleteUser(e));

      log.info("user-account 成功删除了此user 的 id is ：{} ", id);
    }
  }

}
