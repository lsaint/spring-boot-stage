package com.ethan.stage;

import com.ethan.stage.domain.EthanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableAutoConfiguration // 从consul中读配置
@SpringCloudApplication // = SpringBootApplication + EnableDiscoveryClient + EnableCircuitBreaker
public class StageApplication implements CommandLineRunner {

    @Autowired private EthanService ethanService;

    public static void main(String[] args) {
        SpringApplication.run(StageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ethanService.invokeCommon();
        ethanService.readConfig();
        ethanService.queryMySql();
        ethanService.getPageQueryVersion();
        ethanService.getUrl();
        // ethanService.postUrl();
    }
}
