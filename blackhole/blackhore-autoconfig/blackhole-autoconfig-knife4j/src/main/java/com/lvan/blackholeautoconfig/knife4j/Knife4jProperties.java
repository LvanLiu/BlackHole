package com.lvan.blackholeautoconfig.knife4j;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lvan
 * @date 2021/9/6
 */
@Data
@ConfigurationProperties(prefix = "knife4j")
public class Knife4jProperties {

    private String groupName = "default";
    private String basePackages;
    private ApiInfo apiInfo;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApiInfo {

        private String title;
        private String description;
        private String termsOfServiceUrl;
        private String version;
        private Contact contact;

        public static ApiInfo acquireDefaultApiInfo() {

            return ApiInfo.builder()
                    .title("black-hole api document")
                    .description("black-hole api document")
                    .termsOfServiceUrl("lvan.com")
                    .version("1.0")
                    .build();
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Contact {

        private String name;
        private String url;
        private String email;

        public static Contact acquireDefaultContact() {

            return Contact.builder()
                    .name("lvan")
                    .url("https://lvanliu.github.io/LvanNote/#/")
                    .email("xxxx@qq.com")
                    .build();
        }
    }
}
