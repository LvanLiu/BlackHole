package com.lvan.blackholewebsample.controller;

import com.lvan.blackholeweb.log.LogAop;
import com.lvan.blackholeweb.servlet.mvc.WrapperResult;
import com.lvan.blackholewebsample.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private HelloWorldService helloWorldService;

    @LogAop
    @WrapperResult
    @GetMapping("hello-world")
    public Object helloWorld(@RequestParam("id") Integer id) {
        return helloWorldService.helloWorld(id);
    }
}
