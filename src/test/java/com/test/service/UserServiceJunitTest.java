package com.test.service;

import com.test.tutorial.repository.OrgMapper;
import com.test.tutorial.repository.UserMapper;
import com.test.tutorial.repository.entity.Organization;
import com.test.tutorial.repository.entity.User;
import com.test.tutorial.service.UserService;
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
 * <a href="https://blog.csdn.net/ceyowa/article/details/122506061">
 *     SpringBoot中使用Junit5(Jupiter)和Mockito</a>
 * </pre>
 *
 * @author Edward Lee
 * @since 2014-7-25
 * @version 2023-5-24
 */
@ExtendWith(MockitoExtension.class)
class UserServiceJunitTest {

    // tested service
    @InjectMocks
//    private final UserService userService = new UserServiceImpl();
    private UserServiceImpl userService;

    // mocked service (被依赖的服务)
    @Mock
    private UserMapper userMapper;

    // ioc容器管控
//    @Resource
//    private UserMapper userMapper;
//    @Resource
//    private OrgMapper orgMapper;


    /**
     * <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/unit-testing.html#unit-testing-utilities">
     * 14.2.1 General testing utilities</a>
     */
    @BeforeEach
    public void setUp() {
        // if use @InjectMocks, no longer use it
//        ReflectionTestUtils.setField(userService, "userMapper", userMapper);
//        ReflectionTestUtils.setField(userService, "orgMapper", orgMapper);
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
    void getUserName() {
        User user = new User()
                .setId(3L)
                .setUserName("Edward Lee");

        // 1. 定义"被依赖的服务"的方法行为
        // stubbing
        when(userMapper.selectById(anyLong())).thenReturn(user);

        long userId = 0L;
        String userName = userService.getUserName(userId);
        // basic assertions
        assertThat(userName).isEqualTo("");

        /// 重复的测试逻辑代码
        userId = 3L;
        userName = userService.getUserName(userId);
        assertThat(userName).isEqualTo("Edward Lee");
    }

    /**
     * <a href="https://www.baeldung.com/mockito-junit-5-extension">
     *     Mockito and JUnit 5 – Using ExtendWith</a>
     */
    @Test
    void getOrgName(@Mock OrgMapper orgMapper) {
        User user = new User()
                .setId(3L)
                .setUserName("Edward Lee")
                .setOrgId(13L);
        Organization org = new Organization()
                .setId(13L)
                .setOrgName("OpenSource");
        // Given
        when(userMapper.selectById(anyLong())).thenReturn(user);
        when(orgMapper.selectById(anyLong())).thenReturn(org);

        userService = new UserServiceImpl(userMapper, orgMapper);

        // When
        String orgName = userService.getOrgName(3L);
        assertThat(orgName).isEqualTo("OpenSource");

        // Then
        verify(userMapper).selectById(3L);
        verify(orgMapper).selectById(13L);
    }
}
