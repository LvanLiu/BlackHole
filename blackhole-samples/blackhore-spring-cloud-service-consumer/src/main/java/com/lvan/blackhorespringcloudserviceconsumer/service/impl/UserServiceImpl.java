package com.lvan.blackhorespringcloudserviceconsumer.service.impl;

import com.lvan.blackhorespringcloudserviceconsumer.entity.User;
import com.lvan.blackhorespringcloudserviceconsumer.feign.ServiceProviderFeign;
import com.lvan.blackhorespringcloudserviceconsumer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lvan
 * @since 2021/7/25
 */
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private ServiceProviderFeign userFeign;

    @Override
    public List<User> restListAllUsers() {
        log.info("restListAllUsers start");
        return userFeign.listAllUsers();
    }
}
