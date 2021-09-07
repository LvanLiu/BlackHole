package com.lvan.blackholeautoconfig.knife4j;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
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

        Knife4jProperties.ApiInfo apiInfo =
                ObjectUtil.defaultIfNull(knife4jProperties.getApiInfo(), Knife4jProperties.ApiInfo.acquireDefaultApiInfo());
        Knife4jProperties.Contact myContact =
                ObjectUtil.defaultIfNull(apiInfo.getContact(), Knife4jProperties.Contact.acquireDefaultContact());

        Contact contact = new Contact(myContact.getName(), myContact.getUrl(), myContact.getEmail());
        return new ApiInfoBuilder()
                .title(apiInfo.getTitle())
                .description(apiInfo.getDescription())
                .termsOfServiceUrl(apiInfo.getTermsOfServiceUrl())
                .version(apiInfo.getVersion())
                .contact(contact)
                .build();
    }
}
