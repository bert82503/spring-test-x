package com.test.service;

import com.test.tutorial.bean.User;
import com.test.tutorial.service.UserService;
import com.test.tutorial.dal.UserDao;
import com.test.tutorial.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test for {@link UserService}.
 * <pre>
 * <a href="https://junit.org/junit5/docs/current/user-guide/">
 *     JUnit 5 User Guide</a>
 * </pre>
 *
 * @author Edward Lee
 * @since 2014-7-25
 * @version 2023-5-24
 */
@ExtendWith(MockitoExtension.class)
class UserServiceJUnitTest {

    // tested service
    @InjectMocks
    private final UserService userService = new UserServiceImpl();

    // mocked service (被依赖的服务)
    @Mock
    private UserDao userDao;


    /**
     * <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/unit-testing.html#unit-testing-utilities">
     * 14.2.1 General testing utilities</a>
     */
    @BeforeEach
    public void setUp() {
        // if use @InjectMocks, no longer use it
//        ReflectionTestUtils.setField(userService, "userDao", userDao);
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

        /// 重复的测试逻辑代码
        userId = 3L;
        userName = userService.getUserName(userId);
        assertThat(userName).isEqualTo("Edward Lee");
    }
}
