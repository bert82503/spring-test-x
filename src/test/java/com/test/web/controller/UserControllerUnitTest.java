package com.test.web.controller;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.AbstractControllerTestNGUnitTests;
import com.test.tutorial.service.UserService;
import com.test.tutorial.web.controller.UserController;

/**
 * Unit test for {@link UserController}.
 * <p/>
 * <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/unit-testing.html#unit-testing-spring-mvc">
 * 14.2.2 Spring MVC - Unit Testing</a>
 *
 * @author Bert Lee
 * @version 2014-8-19
 */
public class UserControllerUnitTest extends AbstractControllerTestNGUnitTests {

    // tested controller
//    private UserController userController = new UserController();
    // 21. New annotations: @Captor, @Spy, @InjectMocks (Since 1.8.3)
    // instantiates object from class and injects mock or spy fields into tested object automatically
    // no longer have to use ReflectionTestUtils.setField(...)
    @InjectMocks
    private UserController userController;

    // mocked service (被依赖的服务)
    @Mock
    private UserService userService;


    @Override
    protected Object testedController() {
        return userController;
    }

    /**
     * <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/unit-testing.html#unit-testing-utilities">
     * 14.2.1 General testing utilities</a>
     */
    @BeforeClass(alwaysRun = true)
    public void setUp() {
        // if use @InjectMocks, no longer use it
//        ReflectionTestUtils.setField(userController, "userService", userService);
    }


    @Test(dataProvider = "getUserNameTestData")
    public void getUserName(Object[] params, String userName, String expectedContent)
            throws Exception {
        // 1. 定义"被依赖的服务"的方法行为
        when(userService.getUserName(anyLong())).thenReturn(userName);

        // 2. testing
        this.getMock("/user/getUserName?userId={userId}&ptp={ptp}", params, expectedContent);
    }

    @DataProvider(name = "getUserNameTestData")
    private static Object[][] getUserNameTestData() {
        return new Object[][]{
                // invalid param - Bean Validation 注解未起作用
//                {new Object[]{"0", null}, null, "{\"code\":1000401,\"message\":\"User not exists\",\"name\":null}"},
                //
                {new Object[]{"23", null}, null, "{\"code\":1000401,\"message\":\"User not exists\",\"name\":null}"},
                {new Object[]{"24", ""}, "", "{\"code\":1000401,\"message\":\"User not exists\",\"name\":null}"},
                //
                {new Object[]{"3", "1.PC.1AUqk"}, "Bert Lee", "{\"code\":0,\"message\":\"OK\",\"name\":\"Bert Lee\"}"},
        };
    }

    @Test(dataProvider = "updateUserNameTestData")
    public void updateUserName(String paramsJson, boolean result, String expectedContent)
            throws Exception {
        // 1. 定义"被依赖的服务"的方法行为
        when(userService.updateUserName(anyLong(), anyString())).thenReturn(result);

        // 2. testing
        this.postMock("/user/updateUserName", paramsJson, expectedContent);
    }

    @DataProvider(name = "updateUserNameTestData")
    private static Object[][] updateUserNameTestData() {
        return new Object[][]{
                {"{\"userId\":23,\"userName\":\"Bert Lee\"}", true,
                        "{\"code\":0,\"message\":\"OK\"}"},
                {"{\"userId\":24,\"userName\":\"Bert Lee\"}", false,
                        "{\"code\":1000402,\"message\":\"Operation is fail\"}"},
        };
    }
}
