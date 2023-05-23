package com.test;

import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Abstract unit test class for TestNG.
 *
 * <ul>
 *   <li>
 *       Spring Framework:
 *       <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/unit-testing.html">
 *           14. Unit Testing</a>
 *   </li>
 *   <li>Mockito:
 *       <a href="http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#9">
 *           9. Shorthand for mocks creation - @Mock annotation</a>
 *   </li>
 *   <li>
 *       TestNG:
 *       <a href="http://testng.org/doc/documentation-main.html#annotations">
 *           2 - Annotations</a>
 *   </li>
 * </ul>
 *
 * @see org.mockito.MockitoAnnotations#openMocks(Object)
 * @author Bert Lee
 * @since 2014-8-19
 */
// [FAQ] Is Mockito thread-safe?
//@Test(singleThreaded = true)
public abstract class AbstractTestNGUnitTests {

	@BeforeSuite(alwaysRun = true)
	public void initMocks() {
		// 9. Shorthand for mocks creation - @Mock annotation
		// 模拟对象创建的简写——@Mock注解
		MockitoAnnotations.openMocks(this);
	}

}
