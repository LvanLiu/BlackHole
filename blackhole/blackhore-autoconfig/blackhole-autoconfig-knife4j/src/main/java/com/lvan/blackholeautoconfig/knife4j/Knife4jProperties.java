package com.lvan.blackholeautoconfig.knife4j;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.service.Contact;

/**
 * @author lvan
 * @date 2021/9/6
 */
@Data
@ConfigurationProperties(prefix = "knife4j")
public class Knife4jProperties {

    private String groupName;
    private String basePackages;
    private ApiInfo apiInfo;

    @Data
    public static class ApiInfo {

        private String title;
        private String description;
        private String termsOfServiceUrl;
        private Contact contact;
        private String version;
    }
}
