package com.lvan.blackholespringcloudserviceprovider.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lvan.blackholespringcloudserviceprovider.entity.User;
import com.lvan.blackholespringcloudserviceprovider.mapper.UserMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Lvan
 * @since 2021/7/25
 */
@RequestMapping("user")
@RestController
public class UserController {

    @Resource
    private UserMapper userMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("listAll")
    public List<User> listAllUsers() {
        List<User> userList = userMapper.selectList(Wrappers.emptyWrapper());
        if (CollUtil.isNotEmpty(userList)) {
            stringRedisTemplate.opsForValue().set("userList", JSONUtil.toJsonStr(userList), 5, TimeUnit.MINUTES);
        }
        return userList;
    }
}
