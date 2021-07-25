package com.lvan.blackhorespringcloudserviceconsumer.controller;

import com.lvan.blackhorespringcloudserviceconsumer.service.HelloWorldService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Lvan
 * @since 2021/7/25
 */
@RequestMapping("demo")
@RestController
public class HelloWorldController {

    @Resource
    private HelloWorldService helloWorldService;

    @GetMapping("hello-world/feign")
    public String restGetHelloWorldWithFeign() {
        return helloWorldService.restGetHelloWorldWithFeign();
    }

    @GetMapping("hello-world/restTemplate")
    public String restGetHelloWorldWithRestTemplate() {
        return helloWorldService.restGetHelloWorldWithRestTemplate();
    }
}
