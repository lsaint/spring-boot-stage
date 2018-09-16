package com.ethan.stage.domain;

import org.springframework.stereotype.Component;

// hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds
@Component
public class HelloFeignFallback implements HelloFeignClient {
    public String hello() {
        return "hello fallback";
    }
}
