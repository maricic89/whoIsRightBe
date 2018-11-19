package com.wir.whoIsRight;

import com.wir.whoIsRight.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class WhoIsRightApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhoIsRightApplication.class, args);
    }
}
