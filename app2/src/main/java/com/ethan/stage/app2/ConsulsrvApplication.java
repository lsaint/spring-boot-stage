package com.ethan.stage.app2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsulsrvApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulsrvApplication.class, args);
    }
}
