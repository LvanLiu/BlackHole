package com.lvan.blackholeweb.servlet.mvc;

import com.lvan.blackholetest.MinimalWebConfiguration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * @author lvan
 * @date 2021/7/29
 */
@SpringBootTest
@DirtiesContext
class GlobalResponseEntityExceptionHandlerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    void getPersonById_whenMissingPersonId_expectIsBadRequest() throws Exception {

        mockMvc.perform(get("/person/"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @ComponentScan(basePackages = "com.lvan.blackholeweb.servlet")
    @Configuration(proxyBeanMethods = false)
    @MinimalWebConfiguration
    static class TestConfiguration {

        // For manual testing
        static void main(String[] args) {
            SpringApplication.run(TestConfiguration.class, args);
        }

        @RequestMapping("person")
        @RestController
        public static class PersonController {

            @GetMapping("/")
            public Person getPersonById(@RequestParam("id") Integer id) {
                return Person.builder()
                        .id(id)
                        .name("test")
                        .build();
            }
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    static class Person {
        private Integer id;
        private String name;
    }
}