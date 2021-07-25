package com.lvan.blackhorespringcloudserviceconsumer.feign;

import com.lvan.blackhorespringcloudserviceconsumer.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Lvan
 * @since 2021/7/25
 */
@Component
@FeignClient(name = "service-provider", path = "/")
public interface ServiceProviderFeign {

    @GetMapping("user/listAll")
    List<User> listAllUsers();


    @GetMapping("demo/hello-world")
    String helloWorld();
}
