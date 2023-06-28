# 1. JAVA程序开发平台
## 1.1 特性
- 是一个完整的开发平台而非仅仅只是语言
- 有一个庞大的类库
- 提供自动垃圾回收服务
- 提供跨操作系统可移植性
- 提供安全性

## 1.2 相关术语
- Applet : 在网页中运行的程序 (现已被前端技术所取代)
- Servlet : 在服务器中运行的程序 (实质为处理协议请求, 生成数据, 发送协议响应的标准接口)
---
- JDK(Java Development Kit) : 与 Java 配套的开发工具包
- JRE(Java Runtime Enviornment) : Java 运行时环境
---
- SE(Standard Edition) : 用于桌面或简单服务器应用的开发平台
- EE(Enterprise Edition) : 用于复杂服务器应用的开发平台
- ME(Micro Edition) : 用于手机和其他小型设备的开发平台
---
- OpenJDK : 开源的 SE JDK实现, 不包括浏览器和 Java FX
- Java FX : 图形化用户界面的替代工具包
---
- SDK(Standard Development Kit) : 1998 ~ 2006 Java 开发运行工具包
- Java 2 : 1998 - 2006 的 Java 语言版本


# 2. 白皮书特性
- 简单性 : 无头文件, 无指针运算, 无结构联合, 无重载, 无虚基类
- 面对对象 : 用接口取代多重继承, 提供更多运行时自省功能
- 分布式 : 提供 HTTP 和 FTP 相关类库
- 健壮性 : 指针模型可以消除内存重写和数据损坏的可能性
- 安全性 : 提供沙箱等安全机制
- 体系结构中立 : JVM 解释字节码提供可移植性, 同时提供即时编译功能
- 可移植性 : 数据类型大小固定, 字符采用 Unicode 格式, 固定字节顺序
- 解释性 : 解释器可以执行任意字节码
- 多线程 : 提供多线程类库
- 动态性 : 运行时特性比C++更强大

# 3. 发展史
- 1991 年 Sun 公司 Patrick Naughton 和 James Gosling 开发Green项目 (有线电视转换盒设备), 需要设计开发平台无关可移植语言, 生成中间代码通过虚拟机转换为机器代码执行
- 1994 年 Green项目解散, Patrick Naughton 和 Jonathan Payne 开发当时火热的浏览器并命名为 HotJava, 并开发出了 Java Applet 雏形
- 1996 发布 1.0 版本, 随后 1.1 版本增强反射能力, 增加 GUI 事件处理模型和内部类
- 1998 发布 1.2 版本, 增加 strictfp 修饰符, 随后发布 JAVA2SDK, 同时退出了企业版 SDK
- 2004 发布 5.0 版本, 增加了泛型编程, for each 循环, 自动装箱, 注解, 静态导入, 枚举等
- 2011 发布 7.0 版本, 增加基于字符串的switch, 二进制字面量, 钻石操作符
- 2014 发布 8.0 版本, 增加函数式编程, 含有默认方法的接口, 流, 日期/时间库