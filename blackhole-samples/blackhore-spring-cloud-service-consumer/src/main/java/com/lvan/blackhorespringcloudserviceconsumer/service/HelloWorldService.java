package com.lvan.blackhorespringcloudserviceconsumer.service;

/**
 * @author Lvan
 * @since 2021/7/25
 */
public interface HelloWorldService {

    String restGetHelloWorldWithFeign();

    String restGetHelloWorldWithRestTemplate();
}
