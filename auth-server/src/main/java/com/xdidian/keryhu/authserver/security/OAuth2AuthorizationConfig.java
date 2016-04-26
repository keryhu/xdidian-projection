package com.xdidian.keryhu.authserver.security;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
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



/**
* @ClassName: OAuth2Config
* @Description: TODO(spring oauth2 授权验证class)
* 注意不要使用 @RequiredArgsConstructor(onConstructor = @__(@Autowired))
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月25日 下午9:26:48
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
    @Qualifier("authenticationManagerBean")
	private  AuthenticationManager authenticationManager;
	
	@Autowired//自定义的jwt属性变量 
	private   jwtOfReadAndWrite jwtOfReadAndWrite;
	
	 @Bean
		public JwtAccessTokenConverter jwtAccessTokenConverter() {
			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
			KeyPair keyPair = new KeyStoreKeyFactory(
					//-storepass  jsk8iiu2e
					new ClassPathResource("microserver.jks"), "jsk8iiu2e".toCharArray())
					//keypass   jsdk88sk
					    .getKeyPair("serverconfig","jsk8iiu2e".toCharArray());
			
			converter.setKeyPair(keyPair);
			return converter;
		}

	 @Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory()
					.withClient(jwtOfReadAndWrite.getClientId())
					.secret(jwtOfReadAndWrite.getClientSecret())
					.autoApprove(jwtOfReadAndWrite.isAutoApproval())
				    .resourceIds(jwtOfReadAndWrite.getResourceIds())
					.authorizedGrantTypes(jwtOfReadAndWrite.getGrantTypes())
					.scopes(jwtOfReadAndWrite.getScopes());
		}

	

	

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
			throws Exception {
		endpoints.authenticationManager(authenticationManager).accessTokenConverter(
				jwtAccessTokenConverter());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer)
			throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess(
				"isAuthenticated()");
	}
	
	 @Bean
	    public TokenStore tokenStore() {
	        return new JwtTokenStore(jwtAccessTokenConverter());
	    }
	 
	 
	 
	    @Bean
		@Primary
		public DefaultTokenServices tokenServices() {
			DefaultTokenServices tokenServices = new DefaultTokenServices();
			tokenServices.setSupportRefreshToken(true);
			tokenServices.setTokenStore(tokenStore());
			return tokenServices;
		}


}
