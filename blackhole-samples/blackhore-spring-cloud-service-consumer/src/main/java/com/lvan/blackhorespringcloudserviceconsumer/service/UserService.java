package com.lvan.blackhorespringcloudserviceconsumer.service;

import com.lvan.blackhorespringcloudserviceconsumer.entity.User;

import java.util.List;

/**
 * @author Lvan
 * @since 2021/7/25
 */
public interface UserService {

    List<User> restListAllUsers();
}
