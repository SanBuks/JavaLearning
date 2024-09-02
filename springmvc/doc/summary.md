## 0. basic
- MVC是一种软件架构的思想
  - View(视图层): html或jsp等, 与用户进行交互, 展示数据
  - Controller(控制层):  servlet, 接收请求和响应浏览器
  - Model(模型层): JavaBean, 处理数据
- 工作流程:
  - 用户通过视图层发送请求到服务器, 在服务器中请求被Controller接收 
  - Controller调用相应的Model层处理请求
  - Model(Service-DAO) 处理完毕将结果返回到Controller
  - Controller再根据请求处理的结果找到相应的View视图
  - 渲染数据后最终响应给浏览器
- 三层架构分为表述层, 业务逻辑层, 数据访问层, 表述层表示前台页面和后台servlet
- 优点:
  - Spring 家族原生产品, 与 IOC 容器等基础设施无缝对接
  - 基于原生的Servlet, 通过了功能强大的前端控制器DispatcherServlet, 对请求和响应进行统一处理
  - 表述层各细分领域需要解决的问题全方位覆盖, 提供全面解决方案

## 1. hello-world
```text
src
  ├─main
  │  ├─java
  │  ├─resources
  │  │          mvc.xml  (SpringMVC Config)
  │  └─webapp            (Web Resource Directory)
  │      └─WEB-INF
  │              web.xml (Web Module Deployment Descriptor)
  └─test
      ├─java
      └─resources
```
- 创建 Web 工程(两种配置 web.xml 方式)
- 配置 mvc.xml
  - 自动扫包
  - 配置Thymeleaf视图解析器
- 配置 Tomcat(7)
  - Application Server: Tomcat
  - On Update Action: Redeploy
  - VM options: -Dfile.encoding=UTF-8
  - On Frame deactivation: Update classes and resource
  - Deployment: Application Context & xxx:war exploded
- th:href="@{/target}" 指定跳转路径
- 过程: 
  - 浏览器发送请求, 若符合前端控制器的 url-pattern, 就会被 前端控制器 DispatcherServlet 处理
  - 前端控制器会读取 SpringMVC 的核心配置文件, 通过扫描组件找到控制器, 通过 @RequestMapping进行匹配, 若匹配成功, 该注解所标识的控制器方法就是处理请求的方法
  - 处理请求的方法需要返回一个视图名称, 该视图名称会被视图解析器解析, 加上前缀和后缀组成视图的路径, 通过Thymeleaf对视图进行渲染, 最终转发到视图所对应页面

## 2. request-mapping
- 嵌套路由, 路由组
- request-mapping 派生类
- 参数(判断)匹配, 请求头匹配
- ant风格: 
  - ?: 表示任意的单个字符
  - *: 表示任意的0个或多个字符
  - **: 表示任意的一层或多层目录
  - 注意: 在使用时，只能使用//xxx的方式
- 占位参数匹配

## 3. get-parameter
- ServletApi 获取参数
- @RequestParam
- @PathVariable: 占位符正则匹配
- @RequestHeader
- @CookieValue
- 实体类 Get
- 实体类 Post
- 解决中文乱码: 
  - Get 乱码: tomcat/conf/server.xml 配置解码方式: `<Connector ... URIEncoding="UTF-8" />`
  - Post 乱码: web.xml 配置 CharacterEncodingFilter 过滤器处理 

## 4. domain-obj
- Request 域对象方法
  - ServletAPI 
  - ModelAndView 
  - Model(Interface)
  - Map
  - ModelMap(extends LinkedHashMap<String, Object>)
- Model, Map, ModelMap 由 BindingAwareModelMap 实现 (extends extends ModelMap & Implements Model)
- Session 域对象方法: HttpSession
- Context 域对象方法: session.getServletContext

## 5. view
- ThymeleafView: "path" 通过视图解析器拼接成页面文件路径后转发访问
- InternalResourceView: "forward:path" 转发相应路由
- RedirectView: "redirect:path" 重定向相应路由
- `<mvc:view-controller path="/" view-name="index"/>`: 视图控制器, 会默认屏蔽 Controller
- `<mvc:annotation-driven />`: 开启注解驱动, 使得 Controller 生效

## 6. RESTful
- (Resource) Representational State Transfer: (资源) 通过表现形式体现状态转移
  - 资源: 可以由一个或多个 URI 来标识
  - 表现形式: 如 HTML/XML/JSON/纯文本/图片/视频/音频等等
  - 状态转移(变化): 资源变化, 通过 HTTP 协议动词实现状态变化
- put/delete 模拟: 
  - 配置 HiddenHttpMethodFilter
  - 底层 _method 替换 
- CRUD RESTful 风格 Controller
- th:each, th:text, th:value, th:field 
- 前端处理 put/delete:
  - form.action = event.target.href 获取事件 dom 对象
  - form.submit() 表单提交
  - form.preventDefault() 阻止默认行为
- 处理静态资源:
  - webapp/static/js/xxx.js -> 配置打包插件 maven-war-plugin
  - `<mvc:default-servlet-handler/>` 配置默认 Servlet 处理静态资源, 优先 DispatcherServlet 为主, 剩余走 DefaultServlet

## 7. request-body
- 请求获取
  - @RequestBody param 参数获取请求体
  - RequestEntity<String> param 参数获取请求实体(包含多个部分)
- 返回获取
  - HttpServletResponse response 输出返回体
  - @ResponseBody 自动输出返回体
  - @RestController 给所有方法自动加上 @ResponseBody 
  - HttpMessageConverter: 报文信息 <=> POJO
  - 引入 HttpMessageConverter POM
  ```pom
  <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.12.1</version>
  </dependency>
  ```
- 下载功能
  - 获取文件绝对路径 session.getServletContext(), context.getRealPath("/static/image/" + filename)
  - 构建返回报文
  ```java
  MultiValueMap<String, String> headers = new HttpHeaders();
  headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
  HttpStatus status = HttpStatus.OK;
  ResponseEntity<byte[]> entity = new ResponseEntity<>(data, headers, status);
  ```
- 上传功能
  - `<form th:action="@{/upload}" method="post" enctype="multipart/form-data">` 前端获取文件
  - 增加 上传解析器 POM
  ```pom
  <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>1.3.1</version>
  </dependency>
  ```
  - `<bean id="multipartResolver" class="...CommonsMultipartResolver"/>` 增加上传解析器
  - MultipartFile photo 参数接收, 上传文件夹创建, 文件名重复处理

## 8. interceptor & ex-handler
### 拦截器
- 顺序: Filter -> Dispatcher -> preHandle -> Controller -> postHandle -> afterComplation(渲染视图完毕之后)
- 源码相关位置
```java
// DispatcherServlet 
// -- doDispatch(): 
if (!mappedHandler.applyPreHandle(processedRequest, response)) { return; }
mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
mappedHandler.applyPostHandle(processedRequest, response, mv);
// -- -- processDispatchResult():
mappedHandler.triggerAfterCompletion(request, response, null);
```
- 配置方式: 
  - bean 全局配置 
  - ref + @Component 全局配置 
  - mvc:interceptors + @Component 指定路径拦截
- 拦截顺序: 
  - preHandle 都返回 true: preHandle 按配置顺序执行, postHandle 和 afterCompletion 按反序执行
  - preHandle 某个返 false: preHandle 返回 false 前都执行, postHandle 一个不执行, afterCompletion 按反序从 false 向前执行
### 异常处理
- HandlerExceptionResolver 接口
  - DefaultHandlerExceptionResolver (默认异常处理)
  - SimpleMappingExceptionResolver (自定义)
- 配置方法: 
  - xml: SimpleMappingExceptionResolver 视图 & 请求域对象 
  - annotation: @ControllerAdvice 与 @ExceptionHandler(value= RuntimeException.class)

## 9. annotation
- 配置流程:
  - 容器会在类路径中查找实现 ServletContainerInitializer 接口的类来配置 Servlet容器
  - SpringServletContainerInitializer 实现接口, 反过来又会查找 WebApplicationInitializer 接口类并委托配置任务
  - AbstractAnnotationConfigDispatcherServletInitializer 作为实父类继承该接口来简化实现
- 配置方式 WebInit
```java
// #1. Web 工程初始化类 替代 web.xml 
@Configuration
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {
  // 1. 指定 Spring 配置整合第三方框架
  getRootConfigClasses;  
  // 2. 指定 SpringMVC 配置
  getServletConfigClasses;
  // 3. 指定 DispatcherServlet url-pattern
  getServletMappings;
  // 4. 指定 过滤器
  getServletFilters
}
```

- 配置 WebConfig
```java
@Configuration
// 1. 配置扫描组件
@ComponentScan(value="org.learn.java.springmvc.*")
// 2. 注解驱动
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    // 模板解析器
    @Bean public ITemplateResolver templateResolver();
    // 模板引擎
    @Bean public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver);
    // 视图解析
    @Bean public ViewResolver viewResolver(SpringTemplateEngine springTemplateEngine);
    
    // 视图控制器
    @Bean public void addViewControllers(ViewControllerRegistry registry);
    // 默认 Servlet 处理静态资源
    @Bean public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer);
    // 上传解析
    @Bean public MultipartResolver multipartResolver();
    // 异常处理
    @Bean public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers);
    // 拦截器
    @Bean public void addInterceptors(InterceptorRegistry registry);
}
```

## 10. summary
### 常用组件 
- DispatcherServlet(前端控制器): 统一处理请求和响应, 整个流程控制的中心, 由它调用其它组件处理用户的请求
- HandlerMapping(处理器映射器): 根据请求的 url, method 等信息查找 Handler
- Handler(处理器): Controller, 对具体的用户请求进行处理
- HandlerAdapter(处理器适配器): 对处理器进行适配执行
- ViewResolver(视图解析器): 解析得到相应的视图, 如: ThymeleafView, InternalResourceView, RedirectView
- View(视图): 将模型数据通过页面展示给用户