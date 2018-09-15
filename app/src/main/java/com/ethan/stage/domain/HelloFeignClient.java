package com.ethan.stage.domain;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// name为注册到consul中的服务名
// 此时相当于http://consul-service/hello
@FeignClient(name = "consul-service")
public interface HelloFeignClient {

    // 这里是使用feign请求的地址
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    String hello();
}
