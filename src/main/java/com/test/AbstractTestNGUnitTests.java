package com.test;

import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeSuite;

/**
 * Abstract base unit test class for TestNG.
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
 * @see org.mockito.MockitoAnnotations
 * @author	Bert Lee
 * @version 2014-8-19
 */
public abstract class AbstractTestNGUnitTests {

	@BeforeSuite(alwaysRun = true) // 集成 TestNG
	public void init() {
		MockitoAnnotations.initMocks(this); // @Mock 注解
	}

}
