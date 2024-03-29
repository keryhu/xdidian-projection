package com.xdidian.keryhu.auth_server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.xdidian.keryhu.auth_server.domain.JwtOfReadAndWrite;

import java.security.KeyPair;


/**
 * 
 * @Description : spring oauth2 授权验证class 注意不要使用 @RequiredArgsConstructor(onConstructor
 *              = @__(@Autowired))
 * @date : 2016年6月18日 下午8:03:16
 * @author : keryHu keryhu@hotmail.com
 */
@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(JwtOfReadAndWrite.class)
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired // 自定义的jwt属性变量
  private JwtOfReadAndWrite jwtOfReadAndWrite;
  @Autowired
  private UserDetailsService userDetailsService;
  
  @Autowired
  private CustomTokenEnhancer customTokenEnhancer;

  @Autowired
  private CustomAuthenticationProvider customAuthenticationProvider;

  /**
   * 针对angular2等其它前台 登录访问 的时候，设计的 访问控制。
   * 
   * @param authenticationManager
   */
  private void insertHack(AuthenticationManager authenticationManager) {
    if (authenticationManager instanceof ProviderManager) {
      ProviderManager pm = (ProviderManager) authenticationManager;
      pm.getProviders().add(customAuthenticationProvider);
    }
  }

  @Bean
  public JwtAccessTokenConverter tokenEnhancer() {
    KeyPair keyPair = new KeyStoreKeyFactory(
        // Storepass jsk8iiu2e
        new ClassPathResource("microserver.jks"), "jsk8iiu2e".toCharArray())
            // keypass jsdk88sk
            .getKeyPair("serverconfig", "jsk8iiu2e".toCharArray());

    //使用自定义的accessToken converter
    customTokenEnhancer.setKeyPair(keyPair);
    return customTokenEnhancer;
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory().withClient(jwtOfReadAndWrite.getClientId())
        .secret(jwtOfReadAndWrite.getClientSecret()).autoApprove(jwtOfReadAndWrite.isAutoApproval())
        .resourceIds(jwtOfReadAndWrite.getResourceIds())
        .authorizedGrantTypes(jwtOfReadAndWrite.getGrantTypes())
        .scopes(jwtOfReadAndWrite.getScopes())
        .accessTokenValiditySeconds(jwtOfReadAndWrite.getAccessTokenValiditySeconds())
        .refreshTokenValiditySeconds(jwtOfReadAndWrite.getRefreshTokenValiditySeconds());
  }


  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    insertHack(authenticationManager);
    endpoints.authenticationManager(authenticationManager).tokenStore(this.tokenStore())
        .accessTokenConverter(tokenEnhancer())
        //就算自定义的 authenticationManager，也要加上下面这个 userDetailsService，否则无法 更新 refreshToken
        .userDetailsService(userDetailsService)
        
    ;
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
    // .allowFormAuthenticationForClients() 客户端提交验证，不能加这个。
    ;
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(tokenEnhancer());
  }


  @Bean
  @Primary
  public DefaultTokenServices tokenServices() {
    DefaultTokenServices tokenServices = new DefaultTokenServices();
    tokenServices.setSupportRefreshToken(true);
    tokenServices.setTokenStore(this.tokenStore());
    tokenServices.setTokenEnhancer(tokenEnhancer());
    return tokenServices;
  }


}
