package com.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.AbstractTestNGUnitTests;
import com.test.tutorial.bean.User;
import com.test.tutorial.dal.UserDao;
import com.test.tutorial.service.UserService;
import com.test.tutorial.service.impl.UserServiceImpl;

/**
 * Unit test for {@link UserService}.
 *
 * @author Bert Lee
 * @version 2014-7-25
 */
public class UserServiceUnitTest extends AbstractTestNGUnitTests {

    // tested service
    private UserService userService = new UserServiceImpl();

    // mocked service (被依赖的服务)
    // 9. Shorthand for mocks creation - @Mock annotation
    @Mock
    private UserDao userDao;

    /**
     * <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/unit-testing.html#unit-testing-utilities">
     * 14.2.1 General testing utilities</a>
     */
    @BeforeClass(alwaysRun = true)
    public void setUp() {
        ReflectionTestUtils.setField(userService, "userDao", userDao);
    }

    /**
     * <ul>
     *   <li>
     *       Mockito:
     *       <a href="http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#2">
     *           2. How about some stubbing?</a>
     *   </li>
     *   <li>
     *       <a href="http://joel-costigliola.github.io/assertj/">
     *           AssertJ - Fluent assertions for java</a>
     *   </li>
     * </ul>
     */
    @Test(dataProvider = "getUserNameTestData")
    public void getUserName(long userId, User user, String expected) {
        // 1. 定义"被依赖的服务"的方法行为
        // stubbing
        when(userDao.getUserInfo(userId))
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
                {3L, new User(3L, ""), ""},
                {10L, new User(10L, "Edward Lee"), "Edward Lee"},
                {23L, new User(23L, "行乐@!~#$%^&"), "行乐@!~#$%^&"},
        };
    }

    @Test(dataProvider = "updateUserNameTestData")
    public void updateUserName(long userId, String userName, int updateResult, boolean expected) {
        // 1. 定义"被依赖的服务"的方法行为
        // stubbing using built-in any() argument matcher
        when(userDao.updateUserInfo(any(User.class)))
                .thenReturn(updateResult);

        // testing
        boolean result = userService.updateUserName(userId, userName);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "updateUserNameTestData")
    private static Object[][] updateUserNameTestData() {
        return new Object[][]{
                // param invalid
                {-1L, "Edward Lee", 0, false}, // userId
                {0L, "行乐@!~", 1, false},
                {123L, null, 1, false}, // userName
                {456L, "", 0, false},
                //
                {23L, "Edward Lee", 0, false},
                {24L, "行乐@!~", 1, true},
        };
    }
}
