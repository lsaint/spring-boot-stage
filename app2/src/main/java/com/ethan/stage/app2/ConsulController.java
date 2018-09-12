package com.ethan.stage.app2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsulController {

    // 这个RestTemplate由注解了LoadBalanced的bean创建
    @Autowired RestTemplate loadBalanceRestTemplate;

    @RequestMapping("/hello")
    public String hello() {
        return "reply from app---2";
    }

    @RequestMapping("/consul/invoke/")
    public String invokeConsul() {
        // 隐式调用客户端负载均衡
        String result =
                loadBalanceRestTemplate.getForObject("http://consul-service/hello", String.class);
        return result;
    }
}
