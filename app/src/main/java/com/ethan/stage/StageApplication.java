package com.ethan.stage;

import com.ethan.stage.common.algorithm.SnowFlake;
import com.ethan.stage.config.AppConfig;
import com.ethan.stage.dal.UserRepository;
import com.ethan.stage.dal.Version;
import com.ethan.stage.service.EthanService;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StageApplication implements CommandLineRunner {

    @Autowired private AppConfig config;
    @Autowired private UserRepository userRepository;
    @Autowired private EthanService ethanService;
    private SnowFlake snowFlake = new SnowFlake(1, 2);

    public static void main(String[] args) {
        SpringApplication.run(StageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LocalTime currentTime = new LocalTime();
        System.out.println("引用第三方包读取时间 " + currentTime);

        System.out.println("从配置文件读取信息 " + config.getStrConfig());

        System.out.println("调用common项目snowFlake " + snowFlake.nextId());

        System.out.println("mysql简单查询 " + userRepository.count());

        for (Version version : ethanService.getPageQueryVersion()) {
            System.out.println("分页查询 " + version);
        }

        System.out.println("get第三方url: " + ethanService.getUrl());

        System.out.println("post第三方url: " + ethanService.postUrl());
    }
}
