package com.lvan.blackholewebsample.controller;

import cn.hutool.core.map.MapUtil;
import com.lvan.blackholeweb.servlet.mvc.WrapperResult;
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

    @WrapperResult
    @GetMapping("hello-world")
    public Object helloWorld(@RequestParam("id") Integer id) {
        return MapUtil.of("id", 1);
    }
}
