package com.lvan.blackholespringcloudserviceprovider.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lvan.blackholespringcloudserviceprovider.entity.User;
import com.lvan.blackholespringcloudserviceprovider.mapper.UserMapper;
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
    private UserMapper userMapper;

    @GetMapping("listAll")
    public List<User> listAllUsers() {

        return userMapper.selectList(Wrappers.emptyWrapper());
    }
}
