package com.ethan.stage.web;

import com.ethan.stage.domain.EthanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EthanController {
    @Autowired EthanService ethanService;

    @RequestMapping("/ethan/hystrix/")
    public String hystrix() {
        return ethanService.testHystrix();
    }
}
