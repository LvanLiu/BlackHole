package com.lvan.blackhorespringcloudserviceconsumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Lvan
 * @since 2021/7/25
 */
@Component
@FeignClient(name = "service-provider", path = "demo")
public interface HelloWorldFeign {

    @GetMapping("hello-world")
    String helloWorld();
}
