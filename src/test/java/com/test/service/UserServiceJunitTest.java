package com.test.service;

import com.test.tutorial.repository.OrgMapper;
import com.test.tutorial.repository.UserMapper;
import com.test.tutorial.repository.entity.Organization;
import com.test.tutorial.repository.entity.User;
import com.test.tutorial.service.UserService;
import com.test.tutorial.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;
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
//@Execution(ExecutionMode.CONCURRENT)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceJunitTest {

    // tested service
    @InjectMocks
//    private final UserService userService = new UserServiceImpl();
    private UserServiceImpl userService;

    // mocked service (被依赖的服务)
    @Mock
    private UserMapper userMapper;
    @Mock
    private OrgMapper orgMapper;

    // 组件自动装配
//    @Resource
//    private UserMapper userMapper;
//    @Resource
//    private OrgMapper orgMapper;


    /**
     * <a href="https://docs.spring.io/spring-framework/reference/testing/unit.html#unit-testing-utilities">
     *     General Testing Utilities</a>
     */
    @BeforeEach
    public void setUp() {
        // if use @InjectMocks, no longer use it
        // 组件自动装配
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
    @ParameterizedTest
    @MethodSource("getUserNameTestData")
    void getUserName(User user) {
        // 1. 定义"被依赖的服务"的方法行为
        // stubbing
        when(userMapper.selectById(anyLong())).thenReturn(user);

        long userId = 0L;
        String userName = userService.getUserName(userId);
        // basic assertions
        assertThat(userName).isEqualTo("");

        // 重复的测试逻辑代码
        userId = 3L;
        userName = userService.getUserName(userId);
        assertThat(userName).isEqualTo("Edward Lee");
    }

    static Stream<Arguments> getUserNameTestData() {
        // 构造数据
        return Stream.of(
                arguments(new User().setId(3L).setUserName("Edward Lee"),
                        "OpenSource"
                )
        );
    }

    /**
     * <a href="https://www.baeldung.com/mockito-junit-5-extension">
     *     Mockito and JUnit 5 – Using ExtendWith</a>
     */
    @ParameterizedTest
    @MethodSource("getOrgNameTestData")
    void getOrgName(User user, Organization org, String expectedOrgName) {
        // 准备-Given
        when(userMapper.selectById(anyLong())).thenReturn(user);
        when(orgMapper.selectById(anyLong())).thenReturn(org);

        userService = new UserServiceImpl(userMapper, orgMapper);

        // 执行-When
        String orgName = userService.getOrgName(user.getId());

        // 验证-Then
        assertThat(orgName).isEqualTo(expectedOrgName);
        verify(userMapper, times(1)).selectById(user.getId());
        verify(orgMapper).selectById(org.getId());
    }

    static Stream<Arguments> getOrgNameTestData() {
        // 构造数据
        return Stream.of(
                arguments(new User().setId(3L).setUserName("Edward Lee").setOrgId(13L),
                        new Organization().setId(13L).setOrgName("OpenSource"),
                        "OpenSource"
                )
        );
    }
}
