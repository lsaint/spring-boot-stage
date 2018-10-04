package com.ethan.stage.authsrv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    // AuthorizationServerConfigurerAdapter is used to configure
    // how the OAuth authorization server works

    @Autowired AuthenticationManager authenticationManager;
    @Autowired RedisConnectionFactory redisConnectionFactory;

    /*
     * > tokenKeyAccess("permitAll()")
     * > /oauth/token_key, exposes public key for token verification  if using JWT tokens
     *
     * > checkTokenAccess("isAuthenticated()")
     * > /oauth/check_token, allow check token
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                // .checkTokenAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
        // curl -X POST http://localhost:8888/oauth/token
        // -d 'grant_type=client_credentials&client_id=client2&client_secret=123456&scope=11'
        // OAuth标准不支持json格式的提交
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client1")
                .secret("{noop}client1secret")
                .authorizedGrantTypes("password", "refresh_token")
                .authorities("admin", "user")
                .and()
                .withClient("client2")
                .secret("{noop}123456")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .authorities("admin", "user");
        /*
         * 'grant_type=client_credentials&client_id=client2&client_secret=123456&scope=11'
         *  and
         * 'grant_type=password&username=Ethan&password=aaaaaa&client_id=client1&client_secret=client1secret&scope=22'
         */
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(new RedisTokenStore(redisConnectionFactory))
                .authenticationManager(authenticationManager);
    }
}
