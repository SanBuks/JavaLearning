# 1. 基本概念

## 1.1 需要解决的问题
- 一个项目只对应一个工程 : 采用一个模块对应一个工程, 利于分工协作
- 每个项目需要额外复制 jar 包 : jar 包保存在统一仓库中, 通过引用避免 jar 包重复
- 手动复制下载 jar 包 : 统一下载 jar 包方式, 去除手动复制到 WEB-INF/lib 下的过程
- jar 包之间存在复杂的依赖 :自动推导依赖

## 1.2 maven 作用
- 定义 : 服务于 java 平台, 项目的自动化构建工具
- 项目构建 : 用 项目工程文件 [源文件, 框架配置文件, 网页静态资源等] 产生一个可在服务器上运行 war 包的过程
- 传统动态 web 工程结构  :
```text
├── src  // 存放源文件
    └── com
├── build  // 编译后的文件
    └── classes
        └── com
└── WebContent  // 站点文件
    ├── index.html  // 主页
    ├── META-INF  // 元信息
    │   ├── context.xml
    │   └── MANIFEST.MF
    ├── resources  // 静态资源
    │   ├── css
    │   └── jsp
    └── WEB-INF  // 内容文件
        ├── spring  // 组件配置信息
        │   ├── app
        │   │   ├── controllers.xml
        │   │   └── servlet-context.xml
        │   ├── db.xml
        │   └── root-context.xml
        ├── views  // 视图
        │   ├── edit.jsp
        │   ├── home.jsp
        │   └── list.jsp
        ├── lib  // 依赖
        └── web.xml  // 站点的配置文件
```

- 部署的网站项目结构  (war 包)  : 
```text
├── index.html  // 主页
├── META-INF  // 元信息
│   ├── context.xml
│   └── MANIFEST.MF
├── resources  // 静态资源
│   ├── css
│   └── jsp
└── WEB-INF  // 内容文件
    ├── spring  // 组件配置信息
    │   ├── app
    │   │   ├── controllers.xml
    │   │   └── servlet-context.xml
    │   ├── db.xml
    │   └── root-context.xml
    ├── views  // 视图
    │   ├── edit.jsp
    │   ├── home.jsp
    │   └── list.jsp
    ├── lib  // 依赖
    ├── classes  // 编译好的类文件
    └── web.xml
```


# maven 构建环节
> 源码生成为可以在服务器上运行的程序

- 清理 : 删除旧的 class 字节码
- 编译 : .java - > .class
- 测试 : 自动测试 junit , 生成测试报告
- 打包 : 动态web工程打包成 war 包 (会改动项目文件结构)
- 安装 : [maven特有] 将工程文件复制到仓库同一的指定位置
- 部署 : war包复制到 Servlet 容器指定目录下
- 搭建 : 搭建服务器必要运行环境

# maven 安装
- M2_HOME :  maven 文件目录
- Path : %M2_HOME%/bin

# maven 工程结构
```
src	- main - java // 源代码
           - resources // 资源文件
    - test - java  // 测试源代码
	- pom.xml  // 配置文件
```

# maven 常用命令
> 进入 pom.xml 目录下运行 

- mvn clean 删除 target
- mvn compile 编译源文件
- mvn install 安装项目工程代码到本地仓库
- mvn test-compile 编译测试源文件
- mvn test 测试
- mvn package 打包

# maven 生命周期
- maven 核心程序仅定义抽象的生命周期， 生命周期中的每个阶段的具体工作由 maven 插件和第三方相关代码完成
- maven 三个基本生命周期
    - Clean Lifecycle 清理工作 （pre-clean, clean, post-clean）
    - Default Lifecycle 核心部分包括构建的大部分环节
    - Site Lifecycle 生成站点 

- maven 下载额外代码过程 ： 
    - 查找 \home\.m2\repository 下是否有相关代码
    - 会从私服、中心仓库、中央仓库镜像下载相关代码到 \home\.m2\repository, 其中repository 存放第三方或自己的代码
    - 在maven settings.xml 文件中修改默认仓库路径为`<localRepository>...\RepMaven</localRepository>`

# maven 项目对象模型
- Project Object Model 项目对象模型 对应于 pom.xml 配置文件
- maven 坐标 [g-a-v] 对应仓库文件目录结构， 从上到下
    - groupid : 公司或组织域名倒序 + 项目名
    - artifactid : 模块名
    - version : 版本 (1.0-SNAPSHOT 快照)
	- scope : 依赖范围
        - compile 对主程序， test 有效， 参与打包
        - test 对主程序无效， test 有效， 不参与打包 （junit）
        - provided  对主程序无效， test 有效， 不参与打包，不参与部署中忽略 （servlet-api.jar）
- 依赖设置
```xml
<dependencies>
    <dependency>
        <groupId>com.learnjava.maven</groupId>
        <artifactId>maven_test1</artifactId>
        <version>1.0-SNAPSHOT</version>
        <scope>compile</scope>
    </dependency>

    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

- 设置相关 jdk 配置 在 maven settings 中设置 
```xml
<profile>
    <id>jdk-1.8</id>
    <activation>
        <jdk>1.8</jdk>
    </activation>
    <!-- properties 也可放在项目 pom.xml 下 -->
    <properties>
         <maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>
         <maven.compiler.compilerVersion>
			1.8
         </maven.compiler.compilerVersion>
    </properties>
</profile>
```

start from 22 

```xml
<build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.1</version>
        <configuration>
            <source>1.8</source>
            <target>1.8</target>
            <encoding>utf8</encoding>
            <compilerArgs>
                <arg>-parameters</arg>
            </compilerArgs>
        </configuration>
      </plugin>
    </plugins>

    <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.xml</include>
                <include>**/*.properties</include>
            </includes>
            <filtering>true</filtering>
        </resource>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.xml</include>
                <include>**/*.properties</include>
            </includes>
            <filtering>true</filtering>
        </resource>
    </resources>
</build>


<properties>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>


```



一、使用Maven命令把Jar包添加到本地仓库

（1）执行maven命令，把Jar添加到本地。

mvn install:install-file -Dfile=/Users/piao/Documents/JavaWeb/Jar/hutool-all-5.5.2.jar  -DgroupId=com.piao -DartifactId=my-hutool -Dversion=1.0 -Dpackaging=jar

    -Dfile  jar的文件路径
    -DgroupId 设置包名，可以自定义
    -DartifactId 设置模块名，可以自定义
    -Dversion 设置版本，可以自定义
    -Dpackaging 设置包的类型

 













看到这个界面我们就成功了，然后只需要我们在项目里引用即可

 

（2）pom文件添加依赖。如下图：

<dependency>
    <groupId>com.piao</groupId>
    <artifactId>my-hutool</artifactId>
    <version>1.0</version>
</dependency>

 

二、把Jar放入项目中添加依赖

（1）在src目录添加一个lib文件夹，把Jar放入进去。

 

（2）配置pom文件添加依赖。

<dependency>
    <groupId>com.piao</groupId>
    <artifactId>my-hutool</artifactId>
    <version>1.0</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/lib/hutool-all-5.5.2.jar</systemPath>
</dependency>

其中 <groupId>、<artifactId>、<version> 结点的内容可以随便填。

 

（3）在spring-boot-maven-plugin部分添加如下配置，否则项目只能运行，如果需要打Jar包就会报错。

<plugins>
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
            <includeSystemScope>true</includeSystemScope>
        </configuration>
    </plugin>
</plugins>

现在我们就可以使用该Jar包了和打包了。

 

三、把Jar上传到私服添加依赖

因为博主目前没有创建私服暂时就不写这块内容，后期有空会更新。
