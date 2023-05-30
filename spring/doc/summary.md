# 1. 基本概念
## Spring 定义
- 广义：Spring Framework 为核心的 Spring 技术栈，Spring Framework、Spring MVC、SpringBoot、Spring Cloud、Spring Data、Spring Security 等，其中 Spring Framework 是其他子项目的基础
- 狭义：Spring Framework，是一个分层的、面向切面的 Java 应用程序的一站式轻量级解决方案

## Spring 核心
- IOC：控制反转，指把创建对象过程交给 Spring 进行管理
- AOP：面向切面编程，封装多个类的公共行为，将那些与业务无关，却为业务模块所共同调用的逻辑封装起来，减少系统的重复代码，降低模块间的耦合度。另外，AOP 还解决一些系统层面上的问题，比如日志、事务、权限等

## Spring 特点
- 非侵入式：使用 Spring Framework 开发应用程序时，Spring 对应用程序本身的结构影响非常小。对领域模型可以做到零污染；对功能性组件也只需要使用几个简单的注解进行标记，完全不会破坏原有结构，反而能将组件结构进一步简化。
- 控制反转
- 面向切面编程
- 容器：Spring IoC 是一个容器，因为它包含并且管理组件对象的生命周期。组件享受到了容器化的管理，替程序员屏蔽了组件创建过程中的大量细节，极大的降低了使用门槛，大幅度提高了开发效率
- 组件化：Spring 实现了使用简单的组件配置组合成一个复杂的应用。在 Spring 中可以使用 XML 和 Java 注解组合这些对象。这使得我们可以基于一个个功能明确、边界清晰的组件有条不紊的搭建超大型复杂应用系统
- 一站式：在 IoC 和 AOP 的基础上可以整合各种企业应用的开源框架和优秀的第三方类库。

## Spring 模块
**①Spring Core（核心容器）**
spring core提供了IOC,DI,Bean配置装载创建的核心实现。核心概念： Beans、BeanFactory、BeanDefinitions、ApplicationContext。
- spring-core ：IOC和DI的基本实现
- spring-beans：BeanFactory和Bean的装配管理(BeanFactory)
- spring-context：Spring context上下文，即IOC容器(AppliactionContext)
- spring-expression：spring表达式语言

**②Spring AOP**
- spring-aop：面向切面编程的应用模块，整合ASM，CGLib，JDK Proxy
- spring-aspects：集成AspectJ，AOP应用框架
- spring-instrument：动态Class Loading模块

**③Spring Data Access**
- spring-jdbc：spring对JDBC的封装，用于简化jdbc操作
- spring-orm：java对象与数据库数据的映射框架
- spring-oxm：对象与xml文件的映射框架
- spring-jms： Spring对Java Message Service(java消息服务)的封装，用于服务之间相互通信
- spring-tx：spring jdbc事务管理

**④Spring Web**
- spring-web：最基础的web支持，建立于spring-context之上，通过servlet或listener来初始化IOC容器
- spring-webmvc：实现web mvc
- spring-websocket：与前端的全双工通信协议
- spring-webflux：Spring 5.0提供的，用于取代传统java servlet，非阻塞式Reactive Web框架，异步，非阻塞，事件驱动的服务

**⑤Spring Message**
- Spring-messaging：spring 4.0提供的，为Spring集成一些基础的报文传送服务

**⑥Spring test**
- spring-test：集成测试支持，主要是对junit的封装

# 2. 入门
## 引入依赖
```xml
<dependencies>
    <!--spring context依赖-->
    <!--当你引入Spring Context依赖之后，表示将Spring的基础依赖引入了-->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>6.0.2</version>
    </dependency>
    <!--junit5测试-->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.3.1</version>
    </dependency>
</dependencies>
```
## 配置 bean
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="user" class="com.learn.spring.User"> </bean>
</beans>
```
## 获取 bean 过程
```java
public class TestUser {
    @Test
    public void testUser() {
        // 1. doc4j 加载解析 xml, 获取 id, class 属性值, 生成 Map<String, BeanDefinition> beanDefinitionMap;
        // 2. 通过 BeanDefinitionReader 将 BeanDefinition 加载到 IOC 容器, 可以进行额外修改
        // 3. IOC 中: BeanFactory + Reflection 实例化对象
        // 4. IOC 中: 初始化后生成对象
        ApplicationContext app = new ClassPathXmlApplicationContext("bean.xml");
        User user = (User) app.getBean("user");
        System.out.println(user);
        user.print();
    }

    // 反射过程
    @Test
    public void testReflection() throws Exception {
        // 加载解析 xml, 获取 id, class 属性值
        Class<?> clazz = Class.forName("com.learn.spring.User");
        User user = (User) clazz.getDeclaredConstructor().newInstance();
        user.print();
    }
}
```

## 整合日志
- 引入依赖
```xml
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.19.0</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-slf4j2-impl</artifactId>
    <version>2.19.0</version>
</dependency>
```

- 配置文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <loggers>
        <!--
            文件名固定为：log4j2.xml，文件必须放到类根路径下
            level指定日志级别，从低到高的优先级：
                TRACE < DEBUG < INFO < WARN < ERROR < FATAL
                trace：追踪，是最低的日志级别，相当于追踪程序的执行
                debug：调试，一般在开发中，都将其设置为最低的日志级别
                info：信息，输出重要的信息，使用较多
                warn：警告，输出警告的信息
                error：错误，输出错误信息
                fatal：严重错误
        -->
        <root level="DEBUG">
            <appender-ref ref="spring6log"/>
            <appender-ref ref="RollingFile"/>
            <appender-ref ref="log"/>
        </root>
    </loggers>

    <appenders>
        <!--输出日志信息到控制台-->
        <console name="spring6log" target="SYSTEM_OUT">
            <!--控制日志输出的格式-->
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss SSS} [%t] %-3level %logger{1024} - %msg%n"/>
        </console>

        <!--文件会打印出所有信息，这个log每次运行程序会自动清空，由append属性决定，适合临时测试用-->
        <File name="log" fileName="D:/Project/LEARNING/JavaLearning/spring/log/test.log" append="false">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} %-5level %class{36} %L %M - %msg%xEx%n"/>
        </File>

        <!-- 这个会打印出所有的信息，
            每次大小超过size，
            则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，
            作为存档-->
        <RollingFile name="RollingFile" fileName="D:/Project/LEARNING/JavaLearning/spring/log/app.log"
                     filePattern="log/$${date:yyyy-MM}/app-%d{MM-dd-yyyy}-%i.log.gz">
            <PatternLayout pattern="%d{yyyy-MM-dd 'at' HH:mm:ss z} %-5level %class{36} %L %M - %msg%xEx%n"/>
            <SizeBasedTriggeringPolicy size="50MB"/>
            <!-- DefaultRolloverStrategy属性如不设置，
            则默认为最多同一文件夹下7个文件，这里设置了20 -->
            <DefaultRolloverStrategy max="20"/>
        </RollingFile>
    </appenders>
</configuration>
```

- 使用 logger
```java
private final Logger logger = LoggerFactory.getLogger(User.class);

@Test
public void testLog4j() {
    logger.debug("hello log4j!");
}
```

# 3. IOC 容器
## 3.1 概念
- IOC 容器：管理所有 Java 对象（Spring Bean）的实例化和初始化，控制对象与对象之间的依赖关系，降低耦合
- DI：实现了控制反转的思想， 指的是将对象的属性和依赖通过配置方式进行注入， 有 构造注入 和 set 注入
- DI 实现: ApplicationContext, BeanFactory(内部使用)
- ApplicationContext 主要通过 ConfigurableApplicationContext 接口实现功能，有多个实现如下

| 类型名                          | 简介                                                         |
| ------------------------------- | ------------------------------------------------------------ |
| ClassPathXmlApplicationContext  | 通过读取类路径下的 XML 格式的配置文件创建 IOC 容器对象       |
| FileSystemXmlApplicationContext | 通过文件系统路径读取 XML 格式的配置文件创建 IOC 容器对象     |
| ConfigurableApplicationContext  | ApplicationContext 的子接口，包含一些扩展方法 refresh() 和 close() ，让 ApplicationContext 具有启动、关闭和刷新上下文的能力。 |
| WebApplicationContext           | 专门为 Web 应用准备，基于 Web 环境创建 IOC 容器对象，并将对象引入存入 ServletContext 域中。 |

## 3.2 XML 管理 Bean
### 三种方式获取 bean
```java
// 1. id 获取 bean
User user = (User) context.getBean("user");
user.print();

// 2. class 获取 bean (只能有一个该类型 bean)
User user2 = (User) context.getBean(User.class);
user2.print();

// 如果组件类实现了接口，根据接口类型可以获取 bean 吗？
// 可以，前提是bean唯一
// 如果一个接口有多个实现类，这些实现类都配置了 bean，根据接口类型可以获取 bean 吗？
// 不行，因为bean不唯一
        
// 3. 两种结合获取 bean
User user3 = (User) context.getBean("user", User.class);
user3.print();
```

### 两种注入的配置
```xml
<!-- set 注入，注意加上默认构造函数-->
<bean id="user_set" class="User">
    <!-- value 值默认为字面量 -->
    <property name="id" value="123"/>
    <property name="name" value="user_name"/>
</bean>

<!-- ctor 注入，注意加上有参构造函数-->
<bean id="user_ctor" class="User">
    <constructor-arg name="id" value="321"/>             <!-- 属性名方式 -->
    <constructor-arg index="1" value="user_ctor_name"/>  <!-- 属性下标方式 -->
</bean>
```

### 特殊值处理
- null 值 `<property name="name"> <null /> </property>`
- xml 实体 `<property name="expression" value="a &lt; b"/>`
- CDATA 节 `<property name="expression"> <value><![CDATA[a < b]]></value> </property>`

### 自定义类型注入
- 外部 bean 注入
```xml
<bean id="user" class="com.learn.spring.ioc.di.clazz.User">
    <property name="id" value="123"/>
    <property name="name" value="user_name"/>
    <property name="department" ref="clazz" />
</bean>
```

- 内部 bean 注入
```xml
<bean id="user_in" class="com.learn.spring.ioc.di.clazz.User">
    <property name="id" value="123"/>
    <property name="name" value="user_name"/>
    <property name="department">
        <bean class="com.learn.spring.ioc.di.clazz.Department">
            <property name="name" value="DepartmentInner"/>
        </bean>
    </property>
</bean>
```

- 级联 bean 注入
```xml
<bean id="user_cascade" class="com.learn.spring.ioc.di.clazz.User">
    <property name="id" value="123"/>
    <property name="name" value="user_name"/>
    <property name="department" ref="clazz" />  <!-- 注意 user 中需要 get 方法-->
    <property name="department.name" value="DepartmentCascade"/>
</bean>
```

### 容器属性注入
- 