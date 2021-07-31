package com.lvan.blackholeweb.servlet.mvc;

import cn.hutool.json.JSONUtil;
import com.lvan.blackholetest.MinimalWebConfiguration;
import com.lvan.blackholetest.MockMvcEasyBuilder;
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
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

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
    void globalExceptionHandler_whenMissingServletRequestParameter_expectHttpStatusIsBadRequest() throws Exception {

        ResultActions resultActions = mockMvc.perform(get("/ex/missingServletRequestParameter"));
        resultActions.andDo(MockMvcResultHandlers.print());

        ErrorDetail errorDetail = parseContentToErrorDetail(resultActions);

        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
        assertEquals(errorDetail.getCode(), ApiCode.ApiCheckResultCode.HTTP_PARAMS_ERROR.getCode());
    }

    @Test
    void globalExceptionHandler_whenMissingServletRequestPart_expectHttpStatusIsBadRequest() throws Exception {

        ResultActions resultActions = mockMvc.perform(multipart("/ex/missingServletRequestPart"));
        resultActions.andDo(MockMvcResultHandlers.print());

        ErrorDetail errorDetail = parseContentToErrorDetail(resultActions);

        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
        assertEquals(errorDetail.getCode(), ApiCode.ApiCheckResultCode.HTTP_PARAMS_ERROR.getCode());
    }

    @Test
    void globalExceptionHandler_whenHttpMessageNotReadable_expectHttpStatusIsBadRequest() throws Exception {

        ResultActions resultActions = mockMvc.perform(post("/ex/httpMessageNotReadable")
                .contentType(MediaType.APPLICATION_JSON));
        resultActions.andDo(MockMvcResultHandlers.print());

        ErrorDetail errorDetail = parseContentToErrorDetail(resultActions);

        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
        assertEquals(errorDetail.getCode(), ApiCode.ApiCheckResultCode.HTTP_PARAMS_ERROR.getCode());
    }

    @Test
    void globalExceptionHandler_whenMethodArgumentNotValid_expectHttpStatusIsBadRequest() throws Exception {

        ResultActions resultActions = mockMvc.perform(post("/ex/methodArgumentNotValid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.toJsonStr(new Person())));
        resultActions.andDo(MockMvcResultHandlers.print());

        ErrorDetail errorDetail = parseContentToErrorDetail(resultActions);

        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
        assertEquals(errorDetail.getCode(), ApiCode.ApiCheckResultCode.HTTP_PARAMS_ERROR.getCode());
    }

    @Test
    void globalExceptionHandler_whenHttpRequestMethodNotSupported_expectHttpStatusIsMethodNotAllowed() throws Exception {

        ResultActions resultActions = mockMvc.perform(post("/ex/httpRequestMethodNotSupported")
                .contentType(MediaType.APPLICATION_JSON));
        resultActions.andDo(MockMvcResultHandlers.print());

        ErrorDetail errorDetail = parseContentToErrorDetail(resultActions);

        resultActions.andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
        assertEquals(errorDetail.getCode(), ApiCode.ApiCheckResultCode.HTTP_REQUEST_NOT_SUPPORTED.getCode());
    }

    @Test
    void globalExceptionHandler_whenHttpMediaTypeNotSupported_expectHttpStatusIsUnsupportedMediaType() throws Exception {

        ResultActions resultActions = mockMvc.perform(post("/ex/httpMediaType")
                .contentType(MediaType.APPLICATION_XML));
        resultActions.andDo(MockMvcResultHandlers.print());

        ErrorDetail errorDetail = parseContentToErrorDetail(resultActions);

        resultActions.andExpect(MockMvcResultMatchers.status().isUnsupportedMediaType());
        assertEquals(errorDetail.getCode(), ApiCode.ApiCheckResultCode.HTTP_REQUEST_NOT_SUPPORTED.getCode());
    }

    @Test
    void globalExceptionHandler_whenHttpMediaTypeNotAcceptable_expectHttpStatusIsMediaTypeNotAcceptable() throws Exception {

        ResultActions resultActions = mockMvc.perform(post("/ex/httpMediaType")
                .accept(MediaType.APPLICATION_XML)
                .content(JSONUtil.toJsonStr(mockPerson))
                .contentType(MediaType.APPLICATION_XML));
        resultActions.andDo(MockMvcResultHandlers.print());
        resultActions.andExpect(MockMvcResultMatchers.status().isNotAcceptable());
    }

    @Test
    void globalExceptionHandler_whenThrowRuntimeException_expectHttpStatusIsSystemError() throws Exception {

        ResultActions resultActions = mockMvc.perform(get("/ex/runtimeException"));
        resultActions.andDo(MockMvcResultHandlers.print());

        ErrorDetail errorDetail = parseContentToErrorDetail(resultActions);

        resultActions.andExpect(MockMvcResultMatchers.status().isInternalServerError());
        assertEquals(errorDetail.getCode(), ApiCode.SystemErrorCode.SYSTEM_ERROR.getCode());
    }

    private ErrorDetail parseContentToErrorDetail(ResultActions resultActions) throws UnsupportedEncodingException {
        String body = resultActions.andReturn().getResponse().getContentAsString();
        return JSONUtil.toBean(body, ErrorDetail.class);
    }

    @ComponentScan(basePackages = "com.lvan.blackholeweb.servlet")
    @Configuration(proxyBeanMethods = false)
    @MinimalWebConfiguration
    static class TestConfiguration {

        // For manual testing
        static void main(String[] args) {
            SpringApplication.run(TestConfiguration.class, args);
        }

        @RequestMapping("ex")
        @RestController
        public static class GlobalExceptionTestController {

            @GetMapping(value = "/missingServletRequestParameter")
            public void testMissingServletRequestParameter(@RequestParam("id") Integer id) {
            }

            @PostMapping(value = "/missingServletRequestPart")
            public void testMissingServletRequestPart(@RequestPart("file") MultipartFile file) {
            }

            @PostMapping(value = "/httpMessageNotReadable")
            public void httpMessageNotReadable(@RequestBody Person person) {
            }

            @PostMapping(value = "/methodArgumentNotValid")
            public void methodArgumentNotValid(@RequestBody @Validated Person person) {
            }

            @GetMapping(value = "/httpRequestMethodNotSupported")
            public void httpRequestMethodNotSupported() {
            }

            @PostMapping(value = "/httpMediaType", produces = MediaType.APPLICATION_JSON_VALUE)
            public void httpMediaType(@RequestBody Person person) {
            }

            @GetMapping(value = "/runtimeException")
            public void throwsRuntimeException() {
                throw new RuntimeException("error");
            }
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    static class Person {
        @NotNull(message = "id必填")
        private Integer id;
        @NotBlank(message = "name必填")
        private String name;
    }
}