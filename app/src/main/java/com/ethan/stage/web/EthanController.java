package com.ethan.stage.web;

import com.ethan.stage.domain.EthanService;
import com.ethan.stage.domain.HelloFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EthanController {

    @Autowired private HelloFeignClient helloFeignClient;
    @Autowired EthanService ethanService;

    @RequestMapping("/ethan/hystrix/")
    public String hystrix() {
        return ethanService.testHystrix();
    }

    @RequestMapping("/feign/call/")
    public String feignCall() {
        return helloFeignClient.hello();
    }
}
