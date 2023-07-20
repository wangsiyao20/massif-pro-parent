package com.massif.oauth2.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer  // @EnableAuthorizationServer注解表示启用了授权服务器的功能。
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {    // AuthorizationServerConfigurerAdapter用于配置授权服务器的具体行为

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 这个Bean对象在massif-main-system-core模块的SecurityConfig类中创建出来了
     * 我觉得大概是拿这个AuthenticationManager对象去管理上面那个UserDetailsService去走哪个实现类去认证
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public TokenStore tokenStore() {
        // 通过jdbc，即mysql存储token， todo  以后要换成redis存
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    @Primary
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        // 设置token存储方式
        services.setTokenStore(tokenStore());
        // token有效时间（秒）  30*24*3600秒 即为30天
        services.setAccessTokenValiditySeconds(30 * 24 * 3600);
        return services;
    }

    @Bean(name = "clientDetailsService2")
    public ClientDetailsService clientDetailsService() {
        // clientId + clientSecret相关的 oauth_client_details表的汇报工作
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()    // 允许客户端通过表单认证
                .checkTokenAccess("permitAll()");       // 允许所有人访问验证令牌
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .userDetailsService(userDetailsService)
                .tokenServices(defaultTokenServices())
                .authenticationManager(authenticationManager);
    }

}
