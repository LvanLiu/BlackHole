package com.lvan.blackhoreautoconfigweb;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Lvan
 * @since 2021/7/25
 */
@Configuration
public class RestTemplateConfiguration {

    @LoadBalanced
    @ConditionalOnMissingBean
    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
