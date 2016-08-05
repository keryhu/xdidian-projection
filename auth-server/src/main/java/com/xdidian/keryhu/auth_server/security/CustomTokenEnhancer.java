package com.xdidian.keryhu.auth_server.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

/**
 * 
* @ClassName: CustomTokenEnhancer
* @Description: TODO(设定自定义的 access token jwt，此jwt，除了加载一般的信息，还加载了 userId)
* @author keryhu  keryhu@hotmail.com
* @date 2016年8月5日 下午8:18:35
 */
@Component("customTokenEnhancer")
public class CustomTokenEnhancer  extends JwtAccessTokenConverter{

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    // TODO Auto-generated method stub
    String userId=authentication.getUserAuthentication().getName();
    final Map<String, Object> additionalInfo = new HashMap<>();
    additionalInfo.put("userId", userId);
    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
    accessToken = super.enhance(accessToken, authentication);
    return accessToken;
  }

}
