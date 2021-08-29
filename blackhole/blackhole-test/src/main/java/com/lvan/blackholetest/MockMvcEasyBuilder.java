package com.lvan.blackholetest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * it is easy to build mock mvc
 *
 * @author Lvan
 * @since 2021/7/30
 */
public class MockMvcEasyBuilder {

    /**
     * 由于Springboot官方得MockMvc不在支持utf8编码了，会造成单元测试打印响应报文时存在乱码，因此这里手动加上UTF-8编码以解决中文乱码问题。
     */
    public static MockMvc buildMockMvc(WebApplicationContext wac) {

        return MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }))
                .build();
    }
}
