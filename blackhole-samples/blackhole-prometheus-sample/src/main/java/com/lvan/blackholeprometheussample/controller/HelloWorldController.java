package com.lvan.blackholeprometheussample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lvan
 * @since 2021/8/29
 */
@RequestMapping
@RestController
public class HelloWorldController {

    @GetMapping("helloWorld")
    public String helloWorld() {
        return "hello world";
    }
}
