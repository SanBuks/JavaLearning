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
  │
  └─test
      ├─java
      └─resources
```
- 创建 Web 工程(两种配置 web.xml 方式)
- 配置 mvc.xml
  - 自动扫包
  - 配置Thymeleaf视图解析器
- 配置 Tomcat
  - Application Server: Tomcat
  - On Update Action: Redeploy
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
  - ？：表示任意的单个字符
  - *：表示任意的0个或多个字符
  - **：表示任意的一层或多层目录
  - 注意：在使用时，只能使用//xxx的方式
- 占位参数匹配

## 3. get-parameter
- 
