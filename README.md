# spring-test-x
The extension of [Spring TestContext Framework](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/testing.html).

### 核心组件
#### 单元测试（[Unit testing - Wikipedia](https://en.wikipedia.org/wiki/Unit_testing)）
* [AbstractTestNGUnitTests](/src/main/java/com/test/AbstractTestNGUnitTests.java)（强烈推荐使用！！！）
* [AbstractJUnitTests](/src/main/java/com/test/AbstractJUnitTests.java)（不建议使用！）

#### 集成测试（[Integration testing - Wikipedia](https://en.wikipedia.org/wiki/Integration_testing)）

### 代码示例
#### 单元测试
* [UserServiceTest](/src/test/java/com/test/service/UserServiceTest.java)（强烈推荐使用！！！）
* [UserServiceJTest](/src/test/java/com/test/service/UserServiceJTest.java)

从单元测试代码中可以清晰地看出，基于 TestNG 的参数化数据驱动测试明显要比基于 JUnit 的简单清晰。

### 参考资料
* [14. Unit Testing of Spring Framework](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/unit-testing.html)
* [16. Further Resources of Spring Framework](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/testing-resources.html)
* [Test Overview - SpringSide4](https://github.com/springside/springside4/wiki/Test-Overview)
* [TestNG documentation](http://testng.org/doc/documentation-main.html)
* [Mockito documentation](http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html)
* [Mockito FAQ](https://github.com/mockito/mockito/wiki/FAQ)
* [AssertJ](http://joel-costigliola.github.io/assertj/)
* [TestNG - Wikipedia](https://en.wikipedia.org/wiki/TestNG)
* [Mockito - Wikipedia](https://en.wikipedia.org/wiki/Mockito)
* JUnit: "A programmer-oriented testing framework for Java" (面向程序员)
