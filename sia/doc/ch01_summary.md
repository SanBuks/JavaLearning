# Ch01 简介
- Java Community Process(JCP): 由成员制定标准
- Java Specification Request(JSR): 提案通过后成为标准
- Specification: 包括接口定义，抽象类和关系，状态和生命周期等
- JavaEE: 是一组被通过的JSR的合集, 建立在JavaSE之上的标准, 解决企业级开发中的一系列问题
  - Servlet：定义了如何处理Web请求
  - Java Server Faces：定义了如何使编写Web界面
  - JAX-RS：定义了如何编写RESTFul的接口
  - EJB：定义了如何编写“企业Bean”
  - JPA：定义了如何编写ORM和数据存取
  - JTA：定义了如何编写事务相关的代码
  - JMS：定义了如何编写消息队列程序
  - CDI：定义了如何编写依赖注入
  - JAX：定义了如何编写XML程序
  - JAX-WS: 定义了如何编写基于XML的网络服务，即SOAP
- 为了简化 JavaEE 标准开发流程孕育出 Spring 框架 (Rob Johnson)
  - POJO based 简化 EJB
  - DI & Interface 实现松耦合 
  - AOP & Template 减少模板代码
- IOC 容器: 
  - org.springframework.beans.factory.BeanFactory
  - org.springframework.context.ApplicationContext: 5 种类型
    - AnnotationConfigApplicationContext 
    - ClassPathXmlConfigApplicationContext 
- Bean 生命周期: 
- 技术栈:

# Ch02 装配
- 常用注解:
  - 装配 @Configuration @ComponentScan @Autowired
  - 测试 @RunWith(SpringJUnit4ClassRunner.class) @ContextConfiguration(classes= XXXXConfig.class)
- JavaConfig:
  - @Component @Bean 拦截创建对象函数调用, 自动装配参数
- XML:
  - bean, constructor-arg(ref/value), c标签, properties(ref/value), p标签, util标签
- 混合: 
  - JavaConfig 混合 XML: @Import(class), @ImportResource(xml-path) 
  - XML 混合 JavaConfig: <bean class="sia.config.CDConfig" />

# Ch03 高级装配
- JavaConfig 配置 Profile: @Profile("dev"), 测试 @ActiveProfiles("dev")
- XML 配置 Profile: <beans profile="dev"> ... </beans>
- 条件初始化Bean: @Conditional(XXXCondition.class), 实现 XXXCondition implements Condition 
- 处理歧义: 