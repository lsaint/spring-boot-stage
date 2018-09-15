package com.ethan.stage.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsulController {

    @Autowired private DiscoveryClient discoveryClient;
    @Autowired private LoadBalancerClient loadBalancer;

    @RequestMapping("/hello")
    public String hello() {
        return "reply from app";
    }

    @RequestMapping("/consul/services/")
    public Object getConsulServices() {
        return discoveryClient.getInstances("consul-service");
    }

    @RequestMapping("/consul/invoke/")
    public String invokeConsul() {
        // 显式使用客户端负载均衡, 隐式见 app2 的 ConsulController
        ServiceInstance serviceInstance = loadBalancer.choose("consul-service");
        System.out.println("consul-service地址：" + serviceInstance.getUri());

        String callServiceResult =
                new RestTemplate()
                        .getForObject(serviceInstance.getUri().toString() + "/hello", String.class);
        System.out.println("consul-service调用结果: " + callServiceResult);
        return callServiceResult;
    }
}
