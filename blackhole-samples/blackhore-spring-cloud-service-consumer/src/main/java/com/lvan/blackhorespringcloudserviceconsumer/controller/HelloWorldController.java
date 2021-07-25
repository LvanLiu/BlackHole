package com.lvan.blackhorespringcloudserviceconsumer.controller;

import com.lvan.blackhorespringcloudserviceconsumer.feign.HelloWorldFeign;
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
    private HelloWorldFeign helloWorldFeign;

    @GetMapping("hello-world")
    public String helloWorld() {

        return helloWorldFeign.helloWorld();
    }
}
