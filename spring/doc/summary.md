# 1. 基本概念
## Spring 定义
- 广义: Spring Framework 为核心的 Spring 技术栈, 包括Spring Framework、Spring MVC、SpringBoot、Spring Cloud、Spring Data、Spring Security 等, 其中 Spring Framework 是其他子项目的基础
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
- 字符串数组注入
```xml
<property name="hobbies">
    <array>
        <value>吃饭</value>
        <value>睡觉</value>
        <value>写代码</value>
    </array>
</property>
```

- List 容器注入
```xml
<property name="emp">
    <list>
        <ref bean="user1"/>
        <ref bean="user2"/>
    </list>
</property>
```

- Map 容器注入
```xml
<property name="association">
    <map>
        <entry key-ref="user1" value-ref="user2" />
        <entry>
            <key> <ref bean="user1"/> </key>
            <ref bean="user2"/>
        </entry>
    </map>
</property>
```

- util 集合
```xml
<!-- 需要引入 util 命名空间 -->
<util:list id="list">
    <ref bean="user1"/>
    <ref bean="user2"/>
</util:list>

<util:map id="map">
    <entry key-ref="user2" value-ref="user1"/>
</util:map>

<util:set id="set">
    <ref bean="user1"/>
    <ref bean="user2"/>
</util:set>
```

### P 命名空间
```xml
<!-- 需要引入 p 命名空间 -->
<!-- 格式为 p:attribute_name-ref="container_id" -->
<bean id="association_p" class="com.learn.spring.ioc.di.container.Association"
      p:association-ref="map"     
      p:container-ref="list"/>
```

### 外部引入
```xml
<!-- 需要引入 context 命名空间 -->
<!-- 引入 resource 下的 jdbc.properties 文件 -->
<context:property-placeholder location="classpath:jdbc.properties"/>

<!-- 获取格式为 ${jdbc.user} -->
<bean id="druid_data_source" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="username" value="${jdbc.user}"/>
    <property name="password" value="${jdbc.password}"/>
    <property name="url" value="${jdbc.url}"/>
    <property name="driverClassName" value="${jdbc.driver}"/>
</bean>
```

### bean 作用域
- singleton（默认）: 在 IOC 容器中始终为单实例, IOC容器初始化时初始化
- prototype: 在 IOC 容器中有多个实例, 懒初始化
```xml
<bean class="xxx" scope="prototype"/>
<bean class="xxx" scope="singleton"/>
```

### bean 生命周期
- 实现 BeanPostProcessor 接口
```java
public class UserPostProcess implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("3.初始化之前输出");
        System.out.println(beanName + "" + "初始化之前输出" + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("5.初始化之后输出");
        System.out.println(beanName + "" + "初始化之后输出" + bean);
        return bean;
    }
}
```

- 配置bean
```xml
<!-- 设置初始化调用函数和销毁时调用函数, 必须是成员函数 -->
<bean id="user" class="com.learn.spring.ioc.di.life.User" scope="singleton"
      init-method="init" destroy-method="destroy" p:name="life"/>
<!-- 设置初始化前调用函数和初始化后调用函数 -->
<bean id="process" class="com.learn.spring.ioc.di.life.UserPostProcess"/>
```

- 流程
```java
// 过程
// 1.bean对象创建（调用无参构造器）
// 2.给bean对象设置属性
// 3.bean的后置处理器（初始化之前）
// 4.bean对象初始化（需在配置bean时指定初始化方法）
// 5.bean的后置处理器（初始化之后）
// 6.bean对象就绪可以使用
// 7.bean对象销毁（需在配置bean时指定销毁方法） 只有 singleton 模式会调用
// 8.IOC容器关闭
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("life.xml");
User user = context.getBean("user", User.class);
System.out.println("6. bean 创建完成");
context.close();
System.out.println("8. bean 已销毁");
```

### FactoryBean
```java
// 主要用于整合第三方框架, 隐藏配置过程
// 配置工厂 bean 后获取 bean 实质获取的是 User 本身
public class MFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return new User();
    }
    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
```

### 自动装配
```xml
<!-- 对应注入的对象实例只能有一个 -->
<bean id="user_controller" class="com.learn.spring.ioc.di.auto.controller.UserController" autowire="byType"/>
<bean id="user_service" class="com.learn.spring.ioc.di.auto.service.UserServiceImpl" autowire="byType"/>

<!-- 对应注入的对象名称对应 -->
<bean id="user_controller" class="com.learn.spring.ioc.di.auto.controller.UserController" autowire="byName"/>
<bean id="userService" class="com.learn.spring.ioc.di.auto.service.UserServiceImpl" autowire="byName"/>
<bean id="userDao" class="com.learn.spring.ioc.di.auto.dao.UserDaoImpl"/>
```

## 3.3 注解管理 Bean
### 开启组件扫描
```xml
<!-- 需要引入 context 命名空间 -->

<!-- 指定排除方式 -->
<context:component-scan base-package="com.learn.spring.ioc.annotation.bean" use-default-filters="true">
    <!-- type="annotation"，根据注解排除，expression中设置要排除的注解的全类名 -->
    <!-- type="assignable"，根据类型排除，expression中设置要排除的类型的全类名 -->
    <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    <!-- <context:exclude-filter type="assignable" expression="com.atguigu.spring6.controller.UserController"/> -->
</context:component-scan>

<!-- 指定引入方式 -->
<context:component-scan base-package="com.learn.spring.ioc.annotation.bean" use-default-filters="false">
    <!-- type="annotation"，根据注解只选定，expression中设置要只选定的注解的全类名 -->
    <!-- type="assignable"，根据类型只选定，expression中设置要只选定的类型的全类名 -->
    <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/> 
    <!-- <context:include-filter type="assignable" expression="com.atguigu.spring6.controller.UserController"/> -->
</context:component-scan>
```

### 三种 bean 注解
```java
// 1. 默认 value(id) 为 类名小写
// 2. 默认 required  为 true 表示需要注入的对象必须存在
@Component(value="user") 
//@Controller   controller 层
//@Service      service 层
//@Repository   dao 层
public class User {
    public void print() {
        System.out.println("user");
    }
}
```

### @Autowired 多种注入方式
```java
// @Autowired 默认根据类型进行匹配, 需要通过 @Qualifier 进行过滤名称

// set 方法注入
@Autowired
public void setUserDao();

// 字段注入
@Autowired
private UserService userService;

// 构造方法注入
@Autowired
public UserController(UserService userService) {
    this.userService = userService;
}

// 形参注入/单参构造可无注解
public UserController(/*@Autowired*/ UserService userService) {
    this.userService = userService;
}

// 通过限定 bean 名称解决多个类形注入问题
@Autowired
@Qualifier(value="anotherUserDaoImpl")
public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
}
```

### @Resource 注入
```java
// 1. @Resource 只能注解在 field 和 set 方法上
// 2. @Resource 默认以 byName 方式查找 bean, 找不到则通过 byType 查找
// 3. @Resource 通过 value 属性指定 bean 名称, 如果省略则以字段名作为名称查找
```

### 全注解开发
```java
@Configuration // 配置类
//@ComponentScan({"com.xxx.spring.controller", "com.xxx.spring6.service","com.xxx.spring6.dao"})
@ComponentScan("com.xxx.spring6") // 扫描包路径
public class SpringConfig {}
```

# 4. AOP
## 4.1 代理模式 
### 静态代理 与 AOP 术语
```java
// [横切关注点]： 同一类分散的业务问题, 现在关注的是 业务前后的日志打印

// [切面]: 封装通知方法的类
public class CalculatorStaticProxy implements ICalculator {
    // [代理对象]
    private final Calculator calculator;

    public CalculatorStaticProxy(Calculator calculator) {
        this.calculator = calculator;
    }

    // [环绕通知]
    @Override
    public int add(int lhs, int rhs) {
        // [前置通知] [连接点]处于业务之前
        PreBusiness(lhs, rhs);
        // 核心业务
        int ans = -1;
        try{
            ans = calculator.add(lhs, rhs);
            // [返回通知] [连接点]处于业务结束
            System.out.println("success!");
        } catch (Exception exception) {
            // [异常通知] [连接点]处于业务异常
            System.out.println("error");
        } finally {
            // [后置通知] [连接点]处于业务之后
            System.out.println("lhs + rhs = " + ans);
        }
        return ans;
    }

    // 通过找到函数即可找到连接点则 可以访问获取这个函数的对象可称为[切入点]
    // [切入点] 即是 寻找连接点的方式
    void PreBusiness(int lhs, int rhs) {
        System.out.println("(lhs, rhs): " + lhs + ", " + rhs);
    }

    @Override
    public int sub(int lhs, int rhs) {
        System.out.println("(lhs, rhs): " + lhs + ", " + rhs);
        int ans = calculator.sub(lhs, rhs);
        System.out.println("lhs - rhs = " + ans);
        return ans;
    }
}
```

### 动态代理
```java
// JDK 动态代理处理有接口的目标类, 动态代理动态生成的代理类会在com.sun.proxy包下, 类名为$proxy1, 和目标类实现相同的接口
public class CalculatorDynamicProxyFactory {
    // 需要代理的实体类
    private final Object target;
    public CalculatorDynamicProxyFactory(Object target) {
        this.target = target;
    }

    Object getProxy() {
        ClassLoader classLoader = target.getClass().getClassLoader();  // 获取类加载器
        Class<?>[] classInterface = target.getClass().getInterfaces(); // 获取类接口
        // 设置代理接口类
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("[dynamic proxy]: method name = " + method.getName());
                Object result = method.invoke(target, args);
                System.out.println("[dynamic proxy]: result = " + result);
                return result;
            }
        };
        // 返回代理实体的对象
        return Proxy.newProxyInstance(classLoader, classInterface, invocationHandler);
    }
}

public class TestCalculator {
    @Test
    void testDynamicProxyCalculator(){
        CalculatorDynamicProxyFactory dynamicProxyFactory = new CalculatorDynamicProxyFactory(new Calculator());
        ICalculator calc = (ICalculator) dynamicProxyFactory.getProxy();
        calc.add(1, 2);
    }
}

// cglib 动态代理通过继承处理没有接口的目标类 生成的代理类会和目标在在相同的包下, 会继承目标类
// AspectJ 本质上是静态代理, 将代理逻辑“织入”被代理的目标类编译得到的字节码文件, 所以最终效果是动态的, weaver 就是织入器
```

## 4.2 注解 AOP
### 引入依赖
```xml
<!--spring aop依赖-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aop</artifactId>
    <version>6.0.2</version>
</dependency>
<!--spring aspects依赖-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aspects</artifactId>
    <version>6.0.2</version>
</dependency>
```

### 配置
```java
@Configuration
@ComponentScan(value="com.learn.spring.aop.annotation")
@EnableAspectJAutoProxy  // 配置自动扫描切面类
public class AopConfig {
}
```

### 切面表达式, 切入点与优先级
```java
@Aspect
@Component
public class LogAspect {
    // 标准格式: execution ([类成员访问修饰符] [包路径].[类名].[方法名]([形参类型])
    // 通配符格式: execution(* com.learn.spring.aop.annotation.*..*(..))") 
    
    // 实例
    @Before(value = "execution(public int com.learn.spring.aop.annotation.Calculator.add(..))")
    @Order(value = 1) // 设定优先级, 越小, 越外层, 越优先
    void beforeBusiness(JoinPoint joinPoint) {
        // 根据切入点获取代理方法的签名信息
        String methodName = joinPoint.getSignature().getName();
        Object [] args = joinPoint.getArgs();
        
        System.out.println("前置通知: 方法名 " + methodName + Arrays.toString(args));
    }
}
```

### 5 种通知
```java
// @Before 前置通知, 在被代理的目标方法前执行
// @AfterReturning 返回通知, 在被代理的目标方法成功结束后执行
// @After 后置通知, 在被代理的目标方法最终结束后执行 (5.3版本前有变化)
// @AfterThrowing 异常通知, 在被代理的目标方法异常结束后执行
// @Around 环绕通知, 使用try...catch...finally 结构围绕整个被代理的目标方法, 包括上面四种通知对应的所有位置

@Aspect
@Component
public class LogAspect {
    // 返回通知, 获取结果
    @AfterReturning(value="com.learn.spring.aop.annotation.LogAspect.PointCut()", returning = "result")
    void afterReturningBusiness(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("返回通知: 方法名 " + methodName + " 返回对象" + result.toString());
    }

    // 异常通知, 返回异常
    @AfterThrowing(value="execution(public int com.learn.spring.aop.annotation.Calculator.add(..))", throwing = "ex")
    void afterThrowingBusiness(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("异常通知: 方法名 " + methodName + " 返回对象" + ex.toString());
    }
    
    // 环绕通知
    @Around(value = "execution(public int com.learn.spring.aop.annotation.Calculator.sub(..))")
    Object aroundBusiness(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String argsString = Arrays.toString(args);
        Object result = null;
        try {
            System.out.println("前置通知: 方法名 " + methodName + argsString);
            result = joinPoint.proceed(args);  // 直接执行代理方法, 获取返回对象
            System.out.println("返回通知: 方法名 " + methodName + " 返回对象 " + result.toString());
        } catch (Throwable throwable) {  // 获取异常
            System.out.println("异常通知: 方法名 " + methodName + " 异常对象 " + throwable);
        } finally {
            System.out.println("后置通知: 方法名 " + methodName);
        }
        return result;
    }
}
```

### 6. 复用切面表达式
```java
@Aspect
@Component
public class LogAspect {
    // 重用切入点表达式
    @Pointcut(value = "execution(public int com.learn.spring.aop.annotation.Calculator.add(..))")
    public void PointCut() { }
    @After(value = "PointCut()")
    void afterBusiness(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("后置通知: 方法名 " + methodName);
    }
}
```

## 4.3 xml AOP
```xml
<aop:config>
    <!-- 指定 切面bean 对象 -->
    <aop:aspect ref="logAspect">
        <aop:pointcut id="pointcut" expression="execution(* com.learn.spring.aop.xml.Calculator.add(int, int))"/>
        <aop:before method="beforeBusiness" pointcut-ref="pointcut"/>
        <aop:after method="afterBusiness" pointcut-ref="pointcut"/>
        <aop:after-returning method="afterReturningBusiness" returning="result" pointcut-ref="pointcut"/>
        <aop:after-throwing method="afterThrowingBusiness" throwing="ex" pointcut-ref="pointcut"/>
    </aop:aspect>
    <!-- 指定 切面bean 对象 -->
    <aop:aspect ref="logAspect">
        <aop:pointcut id="pointcut-sub" expression="execution(* com.learn.spring.aop.xml.Calculator.sub(int, int))"/>
        <aop:around method="aroundBusiness" pointcut-ref="pointcut-sub"/>
    </aop:aspect>
</aop:config>
```

# 5. 整合 JUnit
- 引入依赖
```xml
<!--spring对junit的支持相关依赖-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>6.0.2</version>
</dependency>
<!--junit5测试-->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.3.1</version>
</dependency>
```

- 单元测试类
```java
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= Config.class)
public class SpringJunitTest {

    @Autowired
    private User user;

    @Test
    public void test() {
        user.print();
    }
}
```