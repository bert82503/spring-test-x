package com.test.service;

import com.test.tutorial.repository.UserMapper;
import com.test.tutorial.repository.entity.User;
import com.test.tutorial.service.UserService;
import com.test.tutorial.service.impl.UserServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.quality.Strictness;
import org.mockito.testng.MockitoSettings;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test of {@link UserService}.
 *
 * @author Edward Lee
 * @version 2014-7-25
 * @version 2023-5-24
 */
@SuppressWarnings("unused")
@Listeners(MockitoTestNGListener.class)
@MockitoSettings(strictness = Strictness.WARN)
public class UserServiceTestngTest {

    // tested service
    @InjectMocks
    private final UserService userService = new UserServiceImpl();
    // 注意：服务实现类声明！(不建议这样使用！)
//    @InjectMocks
//    private UserServiceImpl userService;

    // mocked service (被依赖的服务)
    // 9. Shorthand for mocks creation - @Mock annotation
    @Mock
    private UserMapper userMapper;


    /**
     * <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/unit-testing.html#unit-testing-utilities">
     *     14.2.1 General testing utilities</a>
     */
    @BeforeClass(alwaysRun = true)
    public void setUp() {
        // if use @InjectMocks, no longer use it
//        ReflectionTestUtils.setField(userService, "userMapper", userMapper);
    }


    /**
     * <pre>
     * Mockito:
     * <a href="http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#2">
     *     2. How about some stubbing?</a>
     *
     * <a href="http://joel-costigliola.github.io/assertj/">
     *     AssertJ - Fluent assertions for java</a>
     * </pre>
     */
    @Test(dataProvider = "getUserNameTestData")
    public void getUserName(long userId, User user, String expected) {
        // 1. 定义"被依赖的服务"的方法行为
        // How about some stubbing?
        when(userMapper.selectById(userId))
                .thenReturn(user);

        // testing
        String userName = userService.getUserName(userId);
        // basic assertions
        assertThat(userName).isEqualTo(expected);
    }

    @DataProvider(name = "getUserNameTestData")
    private static Object[][] getUserNameTestData() {
        return new Object[][]{
                // userId invalid
                {-1L, null, ""},
                {0L, null, ""},
                //
                {24L, null, ""},
                {3L, new User().setId(3L).setUserName(""), ""},
                {10L, new User().setId(10L).setUserName("Edward Lee"), "Edward Lee"},
                {23L, new User().setId(23L).setUserName("广益@!~#$%^&"), "广益@!~#$%^&"},
        };
    }

    @Test(dataProvider = "updateUserNameTestData")
    public void updateUserName(long userId, String userName, int updateResult, boolean expected) {
        // 1. 定义"被依赖的服务"的方法行为
        // Argument matchers
        when(userMapper.updateById(any(User.class)))
                .thenReturn(updateResult);

        // testing
        boolean result = userService.updateUserName(userId, userName);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "updateUserNameTestData")
    private static Object[][] updateUserNameTestData() {
        return new Object[][]{
                // param invalid
                // userId
                {-1L, "Edward Lee", 0, false},
                {0L, "广益@!~", 1, false},
                // userName
                {123L, null, 1, false},
                {456L, "", 0, false},
                //
                {23L, "Edward Lee", 0, false},
                {24L, "广益@!~", 1, true},
        };
    }
}
