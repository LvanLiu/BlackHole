package com.lvan.blackholeeurekaserversample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BlackholeEurekaServerSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlackholeEurekaServerSampleApplication.class, args);
    }

}
