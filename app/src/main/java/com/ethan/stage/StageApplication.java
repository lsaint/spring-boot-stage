package com.ethan.stage;

import com.ethan.stage.service.EthanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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
        ethanService.postUrl();
    }
}
