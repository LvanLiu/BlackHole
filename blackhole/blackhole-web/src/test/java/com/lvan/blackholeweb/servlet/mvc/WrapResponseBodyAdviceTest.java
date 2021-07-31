package com.lvan.blackholeweb.servlet.mvc;

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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author Lvan
 * @since 2021/7/31
 */
@SpringBootTest
@DirtiesContext
class WrapResponseBodyAdviceTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private static Person mockPerson;

    static {
        mockPerson = mockPerson.builder()
                .id(1)
                .name("test")
                .build();
    }

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcEasyBuilder.buildMockMvc(wac);
    }

    @Test
    void getPerson_whenWrapperResultOnClass_expectedWrapperApiResult() throws Exception {

        ResultActions resultActions = mockMvc.perform(get("/wrapper/person"));
        resultActions.andDo(MockMvcResultHandlers.print());

        String body = resultActions.andReturn().getResponse().getContentAsString();
        ApiResult<Person> apiResult = new ApiResult<>();
        apiResult = JSONUtil.toBean(body, apiResult.getClass());

        assertEquals(ApiCode.BizHandleCode.SUCCESS.getCode(), apiResult.getCode());
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getPerson_whenWrapperResultOnMethods_expectedWrapperApiResult() throws Exception {

        ResultActions resultActions = mockMvc.perform(get("/wrapperOnMethods/person"));
        resultActions.andDo(MockMvcResultHandlers.print());

        String body = resultActions.andReturn().getResponse().getContentAsString();
        ApiResult<Person> apiResult = new ApiResult<>();
        apiResult = JSONUtil.toBean(body, apiResult.getClass());

        assertEquals(ApiCode.BizHandleCode.SUCCESS.getCode(), apiResult.getCode());
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getPerson_whenUsingWrongHttpMethod_expectedDontWrapperApiResult() throws Exception {

        ResultActions resultActions = mockMvc.perform(post("/wrapper/person"));
        resultActions.andDo(MockMvcResultHandlers.print());

        String body = resultActions.andReturn().getResponse().getContentAsString();
        ErrorDetail errorDetail = JSONUtil.toBean(body, ErrorDetail.class);

        assertEquals(errorDetail.getCode(), ApiCode.ApiCheckResultCode.HTTP_REQUEST_NOT_SUPPORTED.getCode());
        resultActions.andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
    }

    @Test
    void getPerson_whenNoWrapperResultAnnotation_expectedResponseBodyIsPerson() throws Exception {

        ResultActions resultActions = mockMvc.perform(get("/dontWrapper/person"));
        resultActions.andDo(MockMvcResultHandlers.print());

        String body = resultActions.andReturn().getResponse().getContentAsString();
        Person person = JSONUtil.toBean(body, Person.class);

        assertThat(person, equalTo(mockPerson));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @ComponentScan(basePackages = "com.lvan.blackholeweb.servlet")
    @Configuration(proxyBeanMethods = false)
    @MinimalWebConfiguration
    static class MiniApplication {

        // For manual testing
        static void main(String[] args) {
            SpringApplication.run(GlobalResponseEntityExceptionHandlerTest.MiniApplication.class, args);
        }

        @WrapperResult
        @RequestMapping("wrapper")
        @RestController
        public static class UsingWrapperResultAnnotationController {

            @GetMapping(value = "person")
            public Person getPerson() {
                return mockPerson;
            }
        }

        @RequestMapping("wrapperOnMethods")
        @RestController
        public static class UsingWrapperResultAnnotationOnMethodsController {

            @WrapperResult
            @GetMapping(value = "person")
            public Person getPerson() {
                return mockPerson;
            }
        }

        @RequestMapping("dontWrapper")
        @RestController
        public static class dontWrapperPerson {

            @GetMapping(value = "person")
            public Person getPerson() {
                return mockPerson;
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
    }
}