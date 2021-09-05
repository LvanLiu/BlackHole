package com.lvan.blackhoreautoconfigweb;

import com.lvan.blackholeaop.strategy.AspectHandleStrategy;
import com.lvan.blackholeweb.log.aspect.LogAspect;
import com.lvan.blackholeweb.log.aspect.LogRecordStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lvan
 * @since 2021/8/29
 */
@Configuration
public class WebLogAspectConfig {

    @Bean
    public LogAspect logAspect() {

        AspectHandleStrategy logRecordStrategy = new LogRecordStrategy();
        return new LogAspect(logRecordStrategy);
    }
}
