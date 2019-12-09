package com.voda.springbootapicaching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootApiCachingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApiCachingApplication.class, args);
    }

}
