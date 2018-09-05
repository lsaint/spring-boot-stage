package com.ethan.stage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties // tells Spring to treat this class as a consumer of application.yml
@ConfigurationProperties // Annotation for externalized configuration
public class YmlConfig {

    private String strConfig;

    public String getStrConfig() {
        return strConfig;
    }

    public void setStrConfig(String strConfig) {
        this.strConfig = strConfig;
    }
}
