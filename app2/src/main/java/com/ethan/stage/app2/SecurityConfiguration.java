package com.ethan.stage.app2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // WebSecurityConfigurerAdapter is used to configure
    // how the this(app2) server is secured.

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
         * for httpBasic auth:
         * curl http://kane:123123@localhost:8501/consul/invoke/
         * or
         * curl -u kane:123123 http://localhost:8501/consul/invoke/
         */
        http.authorizeRequests().antMatchers("/consul/invoke/").hasRole("USER").and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
         * In spring-security-core:5.0.0.RC1, the default PasswordEncoder is built as a DelegatingPasswordEncoder.
         * When you store the users in memory, you are providing the passwords in plain text and
         * when trying to retrieve the encoder from the DelegatingPasswordEncoder to validate the password
         * it can't find one that matches the way in which these passwords were stored.
         * You can also simply prefix {noop} to your passwords in order for the DelegatingPasswordEncoder
         * use the NoOpPasswordEncoder to validate these passwords.
         */
        auth.inMemoryAuthentication().withUser("kane").password("{noop}123123").roles("USER");
    }
}
