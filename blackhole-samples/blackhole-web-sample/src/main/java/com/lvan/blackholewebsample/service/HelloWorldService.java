package com.lvan.blackholewebsample.service;

import cn.hutool.core.map.MapUtil;
import com.lvan.blackholeaop.log.LogAop;
import org.springframework.stereotype.Service;

/**
 * @author Lvan
 * @since 2021/8/30
 */
@Service
public class HelloWorldService {

//    @LogAop
    public Object helloWorld(Integer id) {
        return MapUtil.of("id", 1);
    }
}
