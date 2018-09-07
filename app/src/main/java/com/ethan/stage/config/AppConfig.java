package com.ethan.stage.config;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Data
@Validated // 检测配置
@Configuration
@EnableConfigurationProperties // tells Spring to treat this class as a consumer of application.yml
@ConfigurationProperties("app") // Annotation for externalized configuration
public class AppConfig {

    @Size(min = 1, max = 5, message = "长度不符")
    private String strConfig;

    @Min(5)
    private int a;
}
