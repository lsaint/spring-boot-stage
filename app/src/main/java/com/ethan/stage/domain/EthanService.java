package com.ethan.stage.domain;

import com.ethan.stage.common.algorithm.SnowFlake;
import com.ethan.stage.config.AppConfig;
import com.ethan.stage.dal.UserRepository;
import com.ethan.stage.dal.VersionDO;
import com.ethan.stage.dal.VersionRepository;
import com.ethan.stage.domain.BO.Post;
import com.ethan.stage.domain.BO.Quote;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.text.MessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EthanService {

    @Autowired private AppConfig config;
    @Autowired private UserRepository userRepository;
    @Autowired private VersionRepository versionRepository;
    @Autowired private RestTemplate restTemplate;
    @Autowired private StringRedisTemplate stringRedisTemplate;

    // 当调用远程配置时build的时候也必须启动config server
    // 或者使用 gradle build -x test
    @Value("${my.val}")
    private String myconfig;

    public void invokeCommon() {
        SnowFlake snowFlake = new SnowFlake(1, 2);
        System.out.println("调用common项目snowFlake " + snowFlake.nextId());
    }

    public void readConfig() {
        System.out.println(
                MessageFormat.format("从本地配置文件读取信息 {0} {1}", config.getStrConfig(), config.getA()));
        System.out.println("从配置文件服务器读取信息 " + myconfig);
    }

    public void redisOps() {
        stringRedisTemplate.opsForValue().set("a", "ABcd");
        String va = stringRedisTemplate.opsForValue().get("a");
        System.out.println("从redis读取key a  " + va);
    }

    public void queryMySql() {
        System.out.println("mysql简单查询 " + userRepository.count());
    }

    public void getPageQueryVersion() {
        // PageRequest.of(int page, int size, Sort.Direction direction, String... properties)
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Direction.DESC, "id"));
        for (VersionDO version : versionRepository.findAll(pageable)) {
            System.out.println("分页查询 " + version);
        }
    }

    public String getUrl() {
        Quote quote =
                restTemplate.getForObject(
                        "http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        System.out.println("get第三方url: " + quote.toString());
        return quote.toString();
    }

    public String postUrl() {
        HttpEntity<Post> request = new HttpEntity<>(new Post("ti", "bo", 1));
        Post postReply =
                restTemplate.postForObject(
                        "https://jsonplaceholder.typicode.com/posts", request, Post.class);
        System.out.println("post第三方url: " + postReply.toString());
        return postReply.toString();
    }

    @HystrixCommand(fallbackMethod = "hystrixFallback")
    public String testHystrix() {
        return restTemplate.getForObject("http://localhost:5000/hello", String.class);
    }

    public String hystrixFallback() {
        return "hystrixFallback";
    }
}
