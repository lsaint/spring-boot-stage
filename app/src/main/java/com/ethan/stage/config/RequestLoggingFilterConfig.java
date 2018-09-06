package com.ethan.stage.config;

import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingFilterConfig {

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
