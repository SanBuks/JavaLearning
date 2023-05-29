# 配置
## spring-boot 依赖
```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.3.1.tmp</version>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

## 配置数据源
```properties
spring.datasource.username=root
spring.datasource.password=
# 加上时区 serverTimezone=GMT%2B8
spring.datasource.url=jdbc:mysql://localhost:3306/db_xcl?useSSL=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# 配置日志
mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl

# mysql 5 :
# com.mysql.jdbc.Driver
# jdbc:mysql://localhost:3306/db_xcl?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8
```

## mapper 与 实体类
```java
@Repository
public interface ClientMapper extends BaseMapper<Client> {
}

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private Long id;
    private String name;
    private Integer type;
}

//@Test
void searchTest() {
    List<Client> list = clientMapper.selectList(null);
    list.forEach(System.out::println);
}
```

