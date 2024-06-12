# 01-demo
- SpringBoot 帮我们简单、快速地创建一个独立的、生产级别的 Spring 应用
  - 快速创建独立 Spring 应用: 免去导包、写配置、启动运行
  - 直接嵌入Tomcat, 无需部署 war 包(servlet 容器: tomcat/webapps)
  - 提供可选的 starter，简化应用整合
  - 按需自动配置 Spring 以及 第三方库, 约定大于配置
  - 提供生产级特性：如 监控指标、健康检查、外部化配置等
  - 无代码生成、无xml
- SpringBoot 创建项目流程 
  - 目录结构, 主程序(@SpringBootApplication)
  - mvn: 父项目spring-boot-starter-parent, 启动器(xxx-starter), 插件
  - 集中配置: resources/application.properties
- 依赖管理
  - parent: 管理版本号
  - starter: 递归导入依赖 
  - 自定义: properties/version 标签
- 扫描机制
  - @SpringBootApplication(scanBasePackages = "org.learn.java") 等价于下面三个类
    - @SpringBootConfiguration: 等价 @Configuration 
    - @EnableAutoConfiguration: 开启自动化配置, 按需加载
      - @Import({AutoConfigurationImportSelector.class}): 导入配置选择器
        - AutoConfigurationEntry getAutoConfigurationEntry(AnnotationMetadata annotationMetadata) 获取自动化配置列表
        - List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) 获取默认可选项
          - ImportCandidates.load(AutoConfiguration.class, getBeanClassLoader()).getCandidates(): 获取`META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` 中指定的 configuration 类路径
        - 比如 DataSourceAutoConfiguration 配置类根据条件注解来实现按需导入 bean
          - @Import 引入bean
          - @EnableConfigurationProperties(DataSourceProperties.class) 引入绑定对象
          - @ConfigurationProperties(prefix = "spring.datasource") 绑定属性
    - @ComponentScan("org.learn.java") 添加扫描位置(默认为主类所在包路径)
- 自动配置机制
  - ServerProperties 与 server 配置一一对应 
  - 加载顺序: 
    - spring-boot-starter-web (限定场景): 其中依赖 核心场景启动器
    - spring-boot-starter: 其中依赖 自动配置类 
    - spring-boot-autoconfigure: 存放所有场景的配置类: 比如 jdbc 的 DataSourceAutoConfiguration 
    - org.springframework.boot.autoconfigure.AutoConfiguration.imports 生效
    - org.springframework.boot.autoconfigure.xxx.XxxConfiguration 生效 
    - xxxProperties 绑定配置项, 后被组件提取生效
    - @ConditionalOn 条件注解 按场景加载配置
- 组件注册
  - @SpringBootConfiguration 
  - @Bean(@Scope) @Service ... 
  - @Import
- 条件注册(方法或配置类上) 
  - @ConditionalOnClass：如果类路径中存在这个类，则触发指定行为
  - @ConditionalOnMissingClass：如果类路径中不存在这个类，则触发指定行为
  - @ConditionalOnBean：如果容器中存在这个Bean（组件），则触发指定行为
  - @ConditionalOnMissingBean：如果容器中不存在这个Bean（组件），则触发指定行为 
- 属性绑定 
  - @Component + @ConfigurationProperties(prefix = "xxx")
  - @EnableConfigurationProperties(Xxx.class) + @ConfigurationProperties(prefix = "xxx")
- YAML
  - application.properties 一般不用缩进, 复杂配置一般采用 application.yaml, 额外配置需要添加为项目配置文件 
  - properties 语法, yaml 语法, | 与 >用法, --- 分离文档 

# 02-log
- 日志框架
  - 接口层: JCL(默认), SLF4J, Jboss-logging 
  - 实现: Log4j, JUL, Logback(默认)
  - 利用监听器机制初始化配置 
- 日志配置: 
  - 格式, 日期格式, @slf4
  - 日志级别, 指定默认级别, 某个包级别, 分组级别
- 日志文件: 
  - 指定文件名 
  - 指定路径
- 滚动切割:
  - file-name-pattern 日志存档的文件名格式（默认值：${LOG_FILE}.%d{yyyy-MM-dd}.%i.gz）
  - clean-history-on-start 应用启动时是否清除以前存档（默认值：false）
  - max-file-size 存档前，每个日志文件的最大大小（默认值：10MB）
  - total-size-cap 日志文件被删除之前，可以容纳的最大大小（默认值：0B）。
  - max-history 日志文件保存的最大天数(默认值：7).
- 自定义日志框架配置 
  - Logback: logback-spring.xml, logback-spring.groovy, logback.xml, or logback.groovy
  - Log4j2: log4j2-spring.xml, log4j2.xml
  - JDK: logging.properties
  - exclusions 排除包依赖后添加自定义包

# 03-web
- 相关包
  - DispatcherServletAutoConfiguration
  - ServletWebServerFactoryAutoConfiguration
  - ErrorMvcAutoConfiguration
  - HttpEncodingAutoConfiguration
  - MultipartAutoConfiguration
  - WebMvcAutoConfiguration
- 功能 
  - 特点视图解析: ContentNegotiatingViewResolver 和 BeanNameViewResolver Bean
  - 支持为静态资源提供服务: static 文件下 index.html 等
  - 格式与转换: Converter、GenericConverter 和 Formatter Bean
  - 返前端结果: HttpMessageConverters
  - 接前端传参: ConfigurableWebBindingInitializer bean
  - 国际: MessageCodesResolver
- 最佳实践:
  - 全自动 直接编写控制器逻辑
  - 手自一体 @Configuration + WebMvcConfigurer + WebMvcRegistrations (不要标注 @EnableWebMvc)
  - 全手动 @Configuration + 配置WebMvcConfigurer (标注 @EnableWebMvc)
- WebMvcAutoConfiguration: 
 ```java
@AutoConfiguration(after = { DispatcherServletAutoConfiguration.class, ... }) // XXX 自动配置之后
@ConditionalOnWebApplication(type = Type.SERVLET)                             // 类型 SERVLET、REACTIVE
@ConditionalOnClass({ Servlet.class, ...})                                    // XXX.class 前提
@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)                   // xxx.bean 前提
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)                          // 优先级
@ImportRuntimeHints(WebResourcesRuntimeHints.class)
public class WebMvcAutoConfiguration { 
  ... 
}

// 大致三件事:
public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter();                                       // 支持 rest
public OrderedFormContentFilter formContentFilter();                                                 // 支持 解析 rest 数据 
// 添加配置 WebMvcConfigurer                                                                           // 添加 配置
@Import(EnableWebMvcConfiguration.class)
@EnableConfigurationProperties({ WebMvcProperties.class, WebProperties.class })
public static class WebMvcAutoConfigurationAdapter implements WebMvcConfigurer, ServletContextAware; 
// 添加配置 EnableWebMvcConfiguration, 其中 DelegatingWebMvcConfiguration 继承 WebMvcConfigurationSupport
// 注意: 按 SpirngBoot 顺序 不会使得 WebMvcAutoConfiguration 失效, 自己配置会使得 WebMvcAutoConfiguration 失效
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(WebProperties.class)
public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration implements ResourceLoaderAware;  
	
//  配置 WebMvcConfigurer 相关 bean 大致如下
addFormatters          // 格式器
addInterceptors        // 拦截器
addResourceHandlers    // 资源处理
addCorsMappings        // 跨域
addViewControllers     // 视图控制器: 跳转
addArgumentResolvers   // Controller 参数解析
addReturnValueHandlers // Controlelr 返回值处理

configureViewResolvers              // 配置 视图解析器
configurePathMatch                  // 配置 路由匹配
configureContentNegotiation         // 配置 内容协商
configureAsyncSupport               // 配置 异步支持
configureDefaultServletHandling     // 配置 解析
configureMessageConverters          // 配置 消息转化
configureHandlerExceptionResolvers  // 配置 异常处理
 ```
  - 绑定配置属性: WebMvcProperties.class, WebProperties.class 
  - 静态资源处理:
```java
// 拿 WebMvcConfigurer::addResourceHandlers 举例
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (!this.resourceProperties.isAddMappings()) {
        logger.debug("Default resource handling disabled");
        return;
    }
    // 1. 获取 webjar 包下静态资源
    addResourceHandler(registry, this.mvcProperties.getWebjarsPathPattern(),
            "classpath:/META-INF/resources/webjars/");
    // 2. 默认四个静态资源路径位置
    //    a. classpath:/META-INF/resources/
    //    b. classpath:/resources/
    //    c. classpath:/static/
    //    d. classpath:/public/
    addResourceHandler(registry, this.mvcProperties.getStaticPathPattern(), (registration) -> {
        registration.addResourceLocations(this.resourceProperties.getStaticLocations());
        if (this.servletContext != null) {
            ServletContextResource resource = new ServletContextResource(this.servletContext, SERVLET_LOCATION);
            registration.addResourceLocations(resource);
        }
    });
}

private void addResourceHandler(ResourceHandlerRegistry registry, String pattern,
        Consumer<ResourceHandlerRegistration> customizer) {
    if (registry.hasMappingForPattern(pattern)) {
        return;
    }
    ResourceHandlerRegistration registration = registry.addResourceHandler(pattern);
    customizer.accept(registration);
    // 3. 缓存处理
    //    a. cachePeriod: 缓存周期
    //    b. cacheControl: HTTP缓存控制
    //    c. useLastModified: 使用最后一次修改
    registration.setCachePeriod(getSeconds(this.resourceProperties.getCache().getPeriod()));
    registration.setCacheControl(this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl());
    registration.setUseLastModified(this.resourceProperties.getCache().isUseLastModified());
    customizeResourceHandlerRegistration(registration);
}
```
  - 欢迎页处理
```java

```