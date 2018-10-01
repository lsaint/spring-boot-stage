package com.ethan.stage.authsrv;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthsrvApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthsrvApplication.class, args);
    }

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter =
                new CommonsRequestLoggingFilter() {
                    protected void beforeRequest(HttpServletRequest request, String message) {}
                };
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setIncludeHeaders(false);
        // filter.setAfterMessagePrefix("REQUEST DATA : ");
        return filter;
    }
}
