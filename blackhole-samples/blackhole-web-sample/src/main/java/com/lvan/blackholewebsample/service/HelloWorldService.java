package com.lvan.blackholewebsample.service;

import cn.hutool.core.map.MapUtil;
import com.lvan.blackholeaop.log.EnableLogAop;
import org.springframework.stereotype.Service;

/**
 * @author Lvan
 * @since 2021/8/30
 */
@Service
public class HelloWorldService {

    @EnableLogAop
    public Object helloWorld(Integer id) {
        return MapUtil.of("id", 1);
    }
}
