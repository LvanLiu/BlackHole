package com.lvan.blackhorespringcloudserviceconsumer.controller;

import com.lvan.blackhorespringcloudserviceconsumer.entity.User;
import com.lvan.blackhorespringcloudserviceconsumer.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lvan
 * @since 2021/7/25
 */
@RequestMapping("user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("listAll")
    public List<User> restListAllUsers() {
        return userService.restListAllUsers();
    }
}
