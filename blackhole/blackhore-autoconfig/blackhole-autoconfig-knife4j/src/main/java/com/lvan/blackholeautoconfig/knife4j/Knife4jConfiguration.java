package com.lvan.blackholeautoconfig.knife4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author lvan
 * @date 2021/9/6
 */
@Configuration
@EnableConfigurationProperties(Knife4jProperties.class)
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Autowired
    private Knife4jProperties knife4jProperties;

    @Bean
    public Docket defaultApi() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        return docket.apiInfo(apiInfo())
                .groupName(knife4jProperties.getGroupName())
                .select()
                .apis(RequestHandlerSelectors.basePackage(knife4jProperties.getBasePackages()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title(knife4jProperties.getApiInfo().getTitle())
                .description(knife4jProperties.getApiInfo().getDescription())
                .termsOfServiceUrl(knife4jProperties.getApiInfo().getTermsOfServiceUrl())
                .version(knife4jProperties.getApiInfo().getVersion())
                .contact(knife4jProperties.getApiInfo().getContact())
                .build();
    }
}
