# 1. 基础项目配置
## 配置项目依赖
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.46</version>
</dependency>

<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.7</version>
</dependency>
```

## 配置 Mybatis 和 注册相关映射
```xml
<!-- resource 下配置 -->
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC"/>
      <dataSource type="POOLED">
        <property name="driver" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/db_xcl?useSSL=true&amp;
                                                                       useUnicode=true&amp;
                                                                       characterEncoding=UTF-8"/>
        <property name="username" value="root"/>
        <property name="password" value=""/>
      </dataSource>
    </environment>
  </environments>
  <mappers>
      <mapper resource="com/learn_java/dao/HelloMapper.xml"/>
  </mappers>
</configuration>
```

## DAO 层
```xml
<mapper namespace="com.learn_java.dao.HelloDao"> 
<!-- 命名空间与DAO一致 -->
  <select id="getList" resultType="com.learn_java.pojo.Hello">  
  <!-- id-> 方法容器元素类型 -->
    select * from client
  </select>
</mapper>
```

```java
package com.learnjava.dao;

import com.learnjava.pojo.Client;
import java.util.List;

public interface ClientDao {
  List<Client> getClientList();
}

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.mybatis.example.BlogMapper">
  <select id="selectBlog" resultType="Blog">
    select * from Blog where id = #{id}
  </select>
</mapper>
```

## Util 工具类
```java
package com.learn_java.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;

public class MybaitsUtil {
  private static SqlSessionFactory sqlSessionFactory;
  static {
    try {
      String resource = "mybatis-config.xml";
      //  resource 文件会部署在根路径
      InputStream inputStream = Resources.getResourceAsStream(resource);
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static SqlSession getSqlsession() {
    return sqlSessionFactory.openSession();
  }
}
```

## 简单使用
```java
try (SqlSession session = MybaitsUtil.getSqlsession()) {
  HelloDao HelloMapper = session.getMapper(HelloDao.class);
  List<Hello> list = HelloMapper.getList();
  for (Hello h : list) {
    System.out.println(h.toString());
  }
} catch (Exception e) {
  e.printStackTrace();
}
```

# 2. CRUD
- 增删改需要额外增加 commit 操作
- `<properties resource="db.properties"/>` 属性优先走外部文件
```xml
  <select id="getHelloById" parameterType="int" resultType="com.learn_java.pojo.Hello">
    SELECT * FROM client WHERE id = #{id}
  </select>

  <insert id="addHello" parameterType="com.learn_java.pojo.Hello">
    INSERT INTO client (`id`, `name`, `type`) VALUES(#{id}, #{name}, #{type})
  </insert>

  <update id="updateHelloById" parameterType="map">
    UPDATE client SET `name` = #{name}, `type` = #{type}
    WHERE id = #{id}
  </update>

  <delete id="deleteHelloById" parameterType="int">
    DELETE FROM client WHERE id = #{id}
  </delete>
```

# 3. 映射
```xml
<!-- mybatis-config.xml 类型别名映射 -->
<!-- typeAliases 于 properties 后 -->
<!-- package 于 typeAlias 后 -->
<configuration>
  <properties resource="db.properties"/>
  <typeAliases>
    <!-- 1. 实体类的 @Alias("HH") 注解会覆盖 typeAlias -->	    
    <typeAlias type="com.learn_java.pojo.Hello" alias="Client"/>

    <!-- 2. 后面对应的类型一定首字母小写 -->    
    <package name="com.learn_java.pojo"/> 
  </typeAliases>
</configuration>
```

# 4. 设置
```
<settings>
    <setting name="useGeneratedKeys" value="true"/>
    <setting name="mapUnderscoreToCamelCase" value ="true">
    <!-- LOG4J -->
    <setting name="logImpl" value="STDOUT_LOGGING"/>
<settings/>
```

# 5. 插件
- mybatis-plus

# 6. 映射
```xml
<mappers>
    <mapper resource="com/learn_java/dao/HelloMapper.xml"/>

    <!-- class 和 mapper 必须放在一起且同名 -->
    <mapper class="com.learn_java.dao.HelloMapper"/>
    <package name="com.learn_java.dao"/>
</mappers>
```

# 7. 生命周期
- SqlSessionFactoryBuilder : 可被实例化、使用和丢弃，一旦创建了 SqlSessionFactory
- SqlSessionFactory : 一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例
- SqlSession : 每个线程都应该有它自己的 SqlSession 实例, 对象不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域

# 8. resultMap
- 解决字段不一问题
- select 别名 或者 resultMap (属性名来映射列到 JavaBean 的属性上)
```xml
<resultMap id="resultMapId" type="another_class" >
    <result column="column_name" property="field_name" javaType="_int"/>
</resultMap>
<!-- 如果映射到一个 JavaBean，MyBatis 通常可以推断类型。然而，如果你映射到的是 HashMap，那么你应该明确地指定 javaType 来保证行为与期望的相一致 -->
```

# 9. Log4j
- propertity配置
```property
log4j.rootLogger=DEBUG,console,file

#控制台输出的相关设置
log4j.appender.console = org.apache.log4j.ConsoleAppender
log4j.appender.console.Target = System.out
log4j.appender.console.Threshold=DEBUG
log4j.appender.console.layout = org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=(%c)-%m%n

#文件输出的相关设置
log4j.appender.file = org.apache.log4j.RollingFileAppender
log4j.appender.file.File=./log/record.log
log4j.appender.file.MaxFileSize=10mb
log4j.appender.file.Threshold=DEBUG
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=(%p)(%d{yy-MM-dd})(%c)%m%n

#日志输出级别
log4j.logger.org.mybatis=DEBUG
log4j.logger.java.sql=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sql.ResultSet=DEBUG
log4j.logger.java.sql.PreparedStatement=DEBUG
```

- 一般用法
```java
public class TestCompany {
  static Logger logger = Logger.getLogger(TestCompany.class);  

  //@Test
  public void getOne() {
      logger.info("info");
      dosomething...
  }

  @Test
  public void TestLog4j() {
    logger.info("stest");
    logger.debug("testse");
    logger.error("tetee");
  }
}
```

# 10. 注解
- 复杂情况下不要用注解

```java
@Select("SELECT * FROM company WHERE id = #{pid}")
Company getOneById(@Param("pid") int id);
```

# 11. 多对一 / 一对多
```xml
<!-- 1. 多对一 -->
<!-- 嵌套查询 -->
<select id="getAll" resultMap="stu">
    SELECT * FROM student
</select>
<resultMap id="stu" type="Student" >
    <association column="tid" property="teacher" javaType="Teacher" select="getTeacher" />
</resultMap>
<select id="getTeacher" resultType="Teacher">
    SELECT * FROM teacher
</select>
<!-- 链表查询 -->
<select id="getAll" resultMap="stu">
    SELECT s.id, s.name, t.name AS tname
    FROM student s LEFT OUTER JOIN teacher t
    ON (s.tid = t.id)
</select>
<resultMap id="stu" type="Student" >
    <association column="tid" property="teacher" javaType="Teacher" select="getTeacher" />
</resultMap>
<select id="getTeacher" resultType="Teacher">
    SELECT * FROM teacher
</select>

<!-- 2. 一对多 -->
<!-- 嵌套查询 -->
<select id="getAll" resultMap="tea">
    SELECT id, `name` FROM teacher
</select>
<resultMap id="tea" type="Teacher">
    <result property="id" column="id"/>
    <collection property="studentsList" ofType="Student" javaType="ArrayList" select="getStu" column="id"/>
</resultMap>
<select id="getStu" resultType="Student">
    SELECT id, `name`, tid FROM student
    WHERE id = #{tid}
</select>

<!-- 链表查询 -->
<select id="getAll" parameterType="long" resultMap="getTeacher">
    SELECT t.id AS tid, t.name AS tname,
           s.id AS sid, s.name AS sname
    FROM student AS s LEFT OUTER JOIN teacher AS t
    ON (s.tid = t.id)
    WHERE t.id = #{param_tid}
</select>
<resultMap id="getTeacher" type="Teacher">
    <result column="tid" property="id" />
    <result column="tname" property="name"/>
    <collection property="studentsList" ofType="Student">
        <result column="sid" property="id"/>
        <result column="sname" property="name"/>
        <result column="tid" property="tid"/>
    </collection>
</resultMap>
```

# 12. 动态SQL
- 随机数类
```java
public class IDutils {
  public static String getId() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }
}
```

```xml
<!-- 1. if, where 语句 -->
<select id="selectBlog" parameterType="map" resultType="blog">
    SELECT * FROM blog
	<where>
        <if test="title != null">
            title like #{title}
        </if>
        <if test="name != null">
            AND name like #{name}
        </if>
    </where>
</select>

<!-- 2. choose, when, otherwise -->
<select id="findActiveBlogLike" resultType="Blog">
    SELECT * FROM BLOG WHERE state = 'ACTIVE'
    <choose>
        <when test="title != null">
            AND title like #{title}
        </when>
        <!-- 对象字段 与 条件查询 -->
        <when test="author != null and author.name != null">
            AND author_name like #{author.name}
        </when>
        <otherwise>
            AND featured = 1
        </otherwise>
    </choose>
</select>

<!-- 3. set 动态更新 -->
<update id="updateAuthorIfNecessary">
  update Author
    <set>
      <if test="username != null">username=#{username},</if>
      <if test="password != null">password=#{password},</if>
      <if test="email != null">email=#{email},</if>
      <if test="bio != null">bio=#{bio}</if>
    </set>
  where id=#{id}
</update>


<!-- 4. trim -->
<!-- 动态地在行首插入 SET 关键字，并会删掉额外的逗号（这些逗号是在使用条件语句给列赋值时引入的） -->
<trim prefix="SET" suffixOverrides=",">
  ...
</trim>
<!-- prefixOverrides 属性会忽略通过管道符分隔的文本序列（注意此例中的空格是必要的）。上述例子会移除所有 prefixOverrides 属性中指定的内容，并且插入 prefix 属性中指定的内容-->
<trim prefix="WHERE" prefixOverrides="AND |OR ">
  ...
</trim>

<!-- 5. foreach -->
<select id="selectPostIn" resultType="domain.blog.Post">
    SELECT *
    FROM POST P
    WHERE ID in
    <foreach item="item" index="index" collection="list"
      open="(" separator="," close=")">
        #{item}
    </foreach>
</select>
```

# 13. sql 片段
```
<sql id="userColumns"> 
	${alias}.id,${alias}.username,${alias}.password 
</sql>

这个 SQL 片段可以在其它语句中使用，例如：

<select id="selectUsers" resultType="map">
  select
    <include refid="userColumns">
    	<property name="alias" value="t1"/>
    </include>,
    <include refid="userColumns">
    	<property name="alias" value="t2"/>		
    </include>
  from some_table t1
    cross join some_table t2
</select>
```


# mybaits-plus
- 
```java
@MapperScan("com.learn_java.mp")
@SpringBootApplication
public class MpApplication {
    ...
}
```



Redis 6.2+
MongoDB 4+
AnotherRedisDesktopManager
Docker 19.+

SSH MobaXTerm
SCP WinScp

- 规范 AliJava

- Bladex-Tool
- mvn install

- IO 异常正常扔
- @DS("slave")  application-pro 配数据源
- service exception 抛出逻辑异常
- rpc 问题 openfeign 