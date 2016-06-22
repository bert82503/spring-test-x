package com.test.service;

import com.test.AbstractJUnitTests;
import com.test.tutorial.bean.User;
import com.test.tutorial.service.UserService;
import com.test.tutorial.dal.UserDao;
import com.test.tutorial.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link UserService}.
 *
 * @author Bert Lee
 * @version 2014-7-25
 */
public class UserServiceJTest extends AbstractJUnitTests {

    // tested service
    private UserService userService = new UserServiceImpl();

    // mocked service (被依赖的服务)
    @Mock
    private UserDao userDao;

    /**
     * <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/unit-testing.html#unit-testing-utilities">
     * 14.2.1 General testing utilities</a>
     */
    @Before
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
    @Test
    public void getUserName() {
        User user = new User(3L, "Edward Lee");

        // 1. 定义"被依赖的服务"的方法行为
        // stubbing
        when(userDao.getUserInfo(anyLong())).thenReturn(user);

        long userId = 0L;
        String userName = userService.getUserName(userId);
        // basic assertions
        assertThat(userName).isEqualTo("");

        userId = 3L;
        userName = userService.getUserName(userId);
        assertThat(userName).isEqualTo("Edward Lee");
    }

}
