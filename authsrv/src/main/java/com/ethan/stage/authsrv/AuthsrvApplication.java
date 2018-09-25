package com.ethan.stage.authsrv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthsrvApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthsrvApplication.class, args);
    }
}
