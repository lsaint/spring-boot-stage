package com.ethan.stage.service;

import com.ethan.stage.common.algorithm.SnowFlake;
import com.ethan.stage.config.AppConfig;
import com.ethan.stage.dal.UserRepository;
import com.ethan.stage.dal.Version;
import com.ethan.stage.dal.VersionRepository;
import com.ethan.stage.service.BO.Post;
import com.ethan.stage.service.BO.Quote;
import java.text.MessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EthanService {

    @Autowired private AppConfig config;
    @Autowired private UserRepository userRepository;
    @Autowired private VersionRepository versionRepository;
    @Autowired private RestTemplate restTemplate;

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

    public void queryMySql() {
        System.out.println("mysql简单查询 " + userRepository.count());
    }

    public void getPageQueryVersion() {
        // PageRequest.of(int page, int size, Sort.Direction direction, String... properties)
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Direction.DESC, "id"));
        for (Version version : versionRepository.findAll(pageable)) {
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
}
