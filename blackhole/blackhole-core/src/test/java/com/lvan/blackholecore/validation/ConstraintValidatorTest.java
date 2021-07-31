package com.lvan.blackholecore.validation;

import cn.hutool.json.JSONUtil;
import com.lvan.blackholetest.MinimalWebConfiguration;
import com.lvan.blackholetest.MockMvcEasyBuilder;
import lombok.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author Lvan
 * @since 2021/7/31
 */
@SpringBootTest
@DirtiesContext
class ConstraintValidatorTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Test
    void enumValidator_default_success() throws Exception {

        Person person = Person.builder()
                .id(1)
                .name("name")
                .sex(1)
                .build();

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(person)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void enumValidator_whenEnumIsEmpty_validFailed() throws Exception {

        Student student = Student.builder()
                .id(1)
                .name("name")
                .sex(Sex.MALE.getCode())
                .build();

        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(student)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void enumValidator_whenMethodIsBlank_validFailed() throws Exception {

        User user = User.builder()
                .id(1)
                .name("name")
                .sex(1)
                .build();

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void enumValidator_whenNestedObject_success() throws Exception {

        Student student = Student.builder()
                .id(1)
                .name("name")
                .sex(Sex.MALE.getCode())
                .build();

        Person person = Person.builder()
                .id(1)
                .name("name")
                .sex(1)
                .student(student)
                .build();

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(person)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcEasyBuilder.buildMockMvc(wac);
    }

    @ComponentScan(basePackages = "com.lvan.blackholecore.validation")
    @Configuration(proxyBeanMethods = false)
    @MinimalWebConfiguration
    static class MiniApplication {

        // For manual testing
        static void main(String[] args) {
            SpringApplication.run(ConstraintValidatorTest.MiniApplication.class, args);
        }

        @RequestMapping
        @RestController
        public static class GlobalExceptionTestController {

            @PostMapping(value = "person")
            public void addPerson(@RequestBody @Validated Person person) {
            }

            @PostMapping(value = "student")
            public void addStudent(@RequestBody @Validated Student student) {
            }

            @PostMapping(value = "user")
            public void addUser(@RequestBody @Validated User user) {
            }
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @EqualsAndHashCode
    static class Person {
        @NotNull(message = "id必填")
        private Integer id;
        @NotBlank(message = "name必填")
        private String name;
        @Enum(target = Sex.class, method = "getCode")
        private Integer sex;

        @Valid
        private Student student;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @EqualsAndHashCode
    static class Student {
        @NotNull(message = "id必填")
        private Integer id;
        @NotBlank(message = "name必填")
        private String name;
        @Enum(target = Empty.class, method = "")
        private Integer sex;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @EqualsAndHashCode
    static class User {
        @NotNull(message = "id必填")
        private Integer id;
        @NotBlank(message = "name必填")
        private String name;
        @Enum(target = Sex.class, method = "")
        private Integer sex;
    }

    @Getter
    @AllArgsConstructor
    enum Sex {

        MALE(1),
        FEMALE(2);

        private final int code;
    }

    enum Empty {
    }
}