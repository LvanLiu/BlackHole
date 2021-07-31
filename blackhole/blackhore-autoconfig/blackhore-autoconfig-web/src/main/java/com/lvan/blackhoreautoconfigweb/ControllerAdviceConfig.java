package com.lvan.blackhoreautoconfigweb;

import com.lvan.blackholeweb.servlet.mvc.GlobalResponseEntityExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lvan
 * @since 2021/7/31
 */
@Configuration
public class ControllerAdviceConfig {

    @Bean
    public GlobalResponseEntityExceptionHandler globalResponseEntityExceptionHandler() {
        return new GlobalResponseEntityExceptionHandler();
    }
}
