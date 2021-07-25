package com.lvan.blackhorespringcloudserviceconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BlackhoreSpringCloudServiceConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlackhoreSpringCloudServiceConsumerApplication.class, args);
    }

}
