package com.lvan.blackholeautoconfigaop;

import com.lvan.blackholeaop.log.aspect.DefaultLogAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lvan
 * @since 2021/8/29
 */
@Configuration
public class LogAspectConfig {

    @ConditionalOnMissingBean
    @Bean
    public DefaultLogAspect logAspect() {
        return new DefaultLogAspect();
    }
}
