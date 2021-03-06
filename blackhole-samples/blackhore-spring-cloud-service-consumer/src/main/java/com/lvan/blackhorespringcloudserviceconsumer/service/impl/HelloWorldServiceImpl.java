package com.lvan.blackhorespringcloudserviceconsumer.service.impl;

import com.lvan.blackhorespringcloudserviceconsumer.feign.ServiceProviderFeign;
import com.lvan.blackhorespringcloudserviceconsumer.service.HelloWorldService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Lvan
 * @since 2021/7/25
 */
@Service("helloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService {

    @Resource
    private ServiceProviderFeign serviceProviderFeign;
    @Resource
    private RestTemplate restTemplate;

    @Override
    public String restGetHelloWorldWithFeign() {
        return serviceProviderFeign.helloWorld();
    }

    @Override
    public String restGetHelloWorldWithRestTemplate() {

        return restTemplate.getForObject("http://service-provider/demo/hello-world", String.class);
    }
}
