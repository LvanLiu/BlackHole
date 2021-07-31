package com.lvan.blackhoreautoconfigweb;

import com.lvan.blackholeweb.servlet.mvc.GlobalResponseEntityExceptionHandler;
import com.lvan.blackholeweb.servlet.mvc.WrapperResponseBodyAdvice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author Lvan
 * @since 2021/7/31
 */
@Configuration
public class ControllerAdviceConfig {

    @ConditionalOnMissingBean
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @ConditionalOnClass(DispatcherServlet.class)
    @Bean
    public GlobalResponseEntityExceptionHandler globalResponseEntityExceptionHandler() {
        return new GlobalResponseEntityExceptionHandler();
    }

    @ConditionalOnMissingBean
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @ConditionalOnClass(DispatcherServlet.class)
    @Bean
    public WrapperResponseBodyAdvice wrapperResponseBodyAdvice() {
        return new WrapperResponseBodyAdvice();
    }

}
