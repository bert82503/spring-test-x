package com.test;

import org.junit.Rule;

import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * Abstract unit test class for JUnit.
 *
 * <p>References:
 * <ul>
 *   <li>Spring Framework:
 *       <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/unit-testing.html">
 *           14. Unit Testing</a>
 *   </li>
 *   <li>Mockito:
 *       <a href="http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#9">
 *           9. Shorthand for mocks creation - @Mock annotation</a>
 *   </li>
 *   <li>
 *       <a href="http://junit.org/junit4/cookbook.html">
 *           JUnit Cookbook</a>
 *   </li>
 * </ul>
 *
 * @see org.mockito.junit.MockitoJUnit#rule()
 * @see org.mockito.junit.MockitoRule
 * @see org.mockito.MockitoAnnotations#openMocks(Object)
 * @author Bert Lee
 * @since 2014-8-19
 */
//@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractJUnitUnitTests {

    @Rule // 集成 JUnit
    public MockitoRule rule = MockitoJUnit.rule(); // @Mock 注解

//	@Before // 集成 JUnit
//	public void initMocks() {
//		MockitoAnnotations.openMocks(this); // @Mock 注解
//	}

}
