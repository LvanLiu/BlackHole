package com.lvan.blackholewebsample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lvan
 * @since 2021/7/25
 */
@RequestMapping("demo")
@RestController
public class HelloWorldController {

    @GetMapping("hello-world")
    public String helloWorld(@RequestParam("id") Integer id) {
        return "Hello World";
    }
}
