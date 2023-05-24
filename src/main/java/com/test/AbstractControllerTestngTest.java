package com.test;

import org.mockito.quality.Strictness;
import org.mockito.testng.MockitoSettings;
import org.mockito.testng.MockitoTestNGListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

/**
 * Abstract controller unit test class for Spring MVC and TestNG.
 *
 * @author Edward Lee
 * @version 2014-8-19
 * @version 2023-5-24
 */
@Listeners(MockitoTestNGListener.class)
@MockitoSettings(strictness = Strictness.WARN)
public abstract class AbstractControllerTestngTest {

    /**
     * Gets the tested controller.
     *
     * @return the controller that is tested
     */
    protected abstract Object testedController();

    /**
     * MVC mock
     */
    private MockMvc mockMvc;

    /**
     * Setups the tested controller in MVC Mock environment.
     */
    @BeforeMethod(alwaysRun = true)
    public void buildMockMvc() {
        mockMvc = MockMvcBuilders.standaloneSetup(testedController()).build();
    }

    /**
     * Mocks the GET request.
     *
     * @param url             请求 URL
     * @param params          请求参数
     * @param expectedContent 期望的返回内容
     * @throws Exception 异常信息
     */
    protected void getMock(String url, Object[] params, String expectedContent)
            throws Exception {
        MockMvcRequestUtils.getMock(mockMvc, url, params, expectedContent);
    }

    /**
     * Mocks the POST request.
     *
     * @param url             请求 URL
     * @param paramsJson      请求参数 JSON 串
     * @param expectedContent 期望的返回内容
     * @throws Exception 异常信息
     */
    protected void postMock(String url, String paramsJson, String expectedContent)
            throws Exception {
        MockMvcRequestUtils.postMock(mockMvc, url, paramsJson, expectedContent);
    }
}
