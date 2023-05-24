package com.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

/**
 * Utility methods of mock MVC request.
 *
 * @author Edward Lee
 * @version 2023-5-24
 * @since 2016-06-29 12:23
 */
class MockMvcRequestUtils {

    /**
     * Mocks the GET request.
     *
     * @param mockMvc         模拟的 MVC 测试上下文
     * @param url             请求 URL
     * @param params          请求参数
     * @param expectedContent 期望的返回内容
     * @throws Exception 异常信息
     */
    public static void getMock(
            final MockMvc mockMvc, final String url, final Object[] params,
            String expectedContent)
            throws Exception {
        // 2.1 构造 GET 请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(url, params);

        jsonRequestMock(mockMvc, requestBuilder, expectedContent);
    }

    /**
     * Mocks the POST request.
     *
     * @param mockMvc         模拟的 MVC 测试上下文
     * @param url             请求 URL
     * @param paramsJson      请求参数 JSON 串
     * @param expectedContent 期望的返回内容
     * @throws Exception 异常信息
     */
    public static void postMock(
            final MockMvc mockMvc, final String url, final String paramsJson,
            String expectedContent)
            throws Exception {
        // 2.1 构造 POST 请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(url)
                // 设置请求体，服务于 "@RequestBody"
                .content(paramsJson);

        jsonRequestMock(mockMvc, requestBuilder, expectedContent);
    }

    /**
     * Mocks the request for "application/json;charset=UTF-8" Content-Type.
     *
     * @param mockMvc         模拟的 MVC 测试上下文
     * @param requestBuilder  请求构建者
     * @param expectedContent 期望的返回内容
     * @throws Exception 异常信息
     */
    private static void jsonRequestMock(
            MockMvc mockMvc, MockHttpServletRequestBuilder requestBuilder,
            String expectedContent)
            throws Exception {
        // 2.2 设置 HTTP 请求属性
        requestBuilder
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
        ;

        // 3. 定义期望的响应行为
        mockMvc.perform(requestBuilder)
                // 打印整个请求与响应细节
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // 校验是否是期望的返回结果
                .andExpect(content().string(expectedContent))
        ;
    }
}
