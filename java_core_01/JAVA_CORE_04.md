# 1. OOP
- 构造, 实例, 封装, 方法, 状态
- 对象行为, 状态, 标识

![对象关系UML图](D:\Project\LEARNING\JAVA学习\JAVA_CORE_01\image\ch04_对象关系UML图.png)

- 类关系 : 
    - 依赖(关联) : 方法操纵另一个类对象
    - 聚合 : 包含一个类对象的集合
    - 继承(接口继承) : 公有继承 is-a

# 2. 预定义类
## 2.1 对象与对象变量
- 对象变量 是一个指向某个类型对象的引用
- 对象 是 jvm 堆中的分配实体
- 对象变量的赋值本质是共享, 除非`Object obj = other_obj.clone();`

## 2.2 Date 与 LocalDate
- Date 用来保存时间 (与 纪元 1970-01-01 00:00:00 的时间距离) 
- LocalDate 用来保存时间的表示方式 即 日历
```java
// 工厂方法模式生成新的对象
LocalDate now = LocalDate.now();
LocalDate birth = LocalDate.of(1200, 12, 31);
System.out.println(now);
System.out.println(birth.getYear() + " " +
                   birth.getMonth() + " " +
                   birth.getDayOfMonth());

// 修改后生成新的对象
LocalDate preBirth = birth.minusDays(1000);
LocalDate afterBirth = birth.plusDays(1000);
System.out.println(preBirth);
System.out.println(afterBirth);
```

- 创建日历
```java
LocalDate date = LocalDate.now();
int month = date.getMonthValue();
int today = date.getDayOfMonth();

date = date.minusDays(today - 1);
DayOfWeek weekDay = date.getDayOfWeek();
int weekDayNum = weekDay.getValue();
System.out.println("Mon Tue Wed Thu Fri Sat Sun");
for (int i = 0; i < weekDayNum - 1; ++i) {
    System.out.print("    ");
}

while (date.getMonthValue() == month) {
    System.out.printf("%3d", date.getDayOfMonth());
    if (date.getDayOfMonth() == today) {
        System.out.print("*");
    } else {
        System.out.print(" ");
    }
    date = date.plusDays(1);
    if (date.getDayOfWeek().getValue() == 1) {
        System.out.print("\n");
    }
}
```

# 3. 自定义类
## 3.1 基本概念
- 成员函数 -> 方法
- 数据成员 -> 实例域
- 返回实例域 注意是否要深复制还是浅复制
- final 修饰实例域 : 表示存储在变量对象中的引用不可更改, 与对象状态无关
- 参数传递 : 皆为值传递

## 3.2 static 
- 修饰实例域 : 
    - 普通实例域 : 类域, 静态域
    - 常量实例域 : 静态域常量
- 修饰方法 :
    - 不能访问实例域
    - 只能访问静态实例域
- C / C++ 中的语义 : 
    - 一直存在局部变量
    - 编译单元范围内的全局变量和函数
    - 类域

## 3.3 类初始化顺序
- 实例域默认初始化
- 按照初始化块和声明顺序初始化
- 调用构造函数中委托的构造函数
- 调用构造函数
```java
public class Test {
    private static String s = "231";
    static {
        s = "21";
    }

    Test(String ss) {
        this(ss, 2);
    }

    Test(String ss, int a) {
        s = ss;
    }
}
```

## 3.4 对象析构
- 使用完毕后对象需要立即清理 : 手动清理
- 调用 finalizer 方法 : 保证垃圾回收之前调用
- 增加关闭钩

# 4. 包
## 4.1 包名
- 一般包名前缀以网址反序命名 : 如 [com.test].myclass
- 包名对应包目录结构下的相应路径 : 如 com/test/myclass/\*.java, 其中暗含了类的目录结构的根目录
- 未处于默认包名下的编译与运行 : 
    - `javac com/test/myclass.java`
    - `java com/test/myclass`

## 4.2 静态导入类的静态方法和实例域
```java
import static java.lang.System.*;
out.println("test");  // 可以不用
```

## 4.3 包作用域
- 没有声明为 public 和 private 的类 则默认为protect, 对象和方法可以被同一个包下的其他对象使用到

# 5. 类路径
## 5.1 类路径结构
- jar (Java 归档文件) : 采用zip压缩存放 类的目录结构 (字节码)
- 类路径 : 查找 jar 文件 和 字节码的路径
- 某一个类放入目录 /home/test 中, 则以 /home/test 为基地址, 类路径可设置如下
```text
`/home/test:.:/home/jar/test.jar:/home/'\*'` ( linux以冒号, windows以分号隔开 )
编译器查找顺序 :
1. 运行时库文件 : `rt.jar` 和 `jre/lib 与 jre/lib/ext` 下所有jar包会被自动扫描
2. `/home/test` 文件路径, 一般为绝对路径
3. `.` : 本目录 即  /home/test
4. `/home/jar/test.jar` : jar包
5. `/home/'*'` : 某目录下的所有jar包
```

- 编译器会自动扫描当前目录, 虚拟机需要手动添加 `.`

## 5.2 虚拟机搜索类
- 类路径 : `/home/test:.:/home/jar/test.jar:/home/'\*'`
- 搜索的类 : `com.host.core.Test.class`
1. `rt.jar` 和 `jre/lib` 与 `jre/lib/ext` 下所有 jar 包中找 `com/host/core/Test.class`
2. `/home/test/com/host/core/Test.class`根据类名搜索
3. `com/host/core/Test.class` 本路径 (虚拟机不像编译器会自动搜索本目录)
4. `/home/jar/test.jar` 中的 `com/host/core/Test.class`
5. `/home/'\*'` 中所有 jar 包 (不搜索.class文件)

## 5.3 编译器定位
- 编译的类中用到了 Employee 类
```java
package com.hhh.core;
import java.util.*;
import com.horstmann.core.*;
/*
1. 查找 `java.lang` 默认类
2. 查找 `java.util.Employee`
3. 查找 `com.horstmann.core.Emplouee`
4. 查找 当前包
5. 查找 类路径

查找到一个以上的类则报错, 导入包的顺序不影响
*/
```

## 5.4 设置类路径
- 运行时 通过命令选项 包含类路径 : `java -classpath ...`
- 设置 shell 环境变量自动包含类路径 : `export CLASSPATH=...` 

# 6. 注释
## 6.1 注释格式
- 注释对象 : 包, 共有类, 接口, 域, 方法
- 注释开始 : 以 `/** */` 开始插入注释
- 注释内容 : 标记  [+参数名 / 异常类名] + 自由文本
- 可以使用HTML 修饰符修饰 但是不能使用 `<hl> <hr>`等

## 6.2 类注释
```java
/**
 * {@code Note} is a class 
 * 1. 应该使用 {@code .... } 而非 `<code>` 来标识代码段
 * 2. 使用<img> 和 <a>引用图片和标签
 */
public class Note {
    ...
}
```

## 6.3 方法和域注释
- @param paramName text
- @return text
- @throws Exception text
```java
/**
 * a public static final field
 * 一般只对共有域进行注释
 */
public static final String s = "123";

/**
 * a method
 * @param a {@code a param}  
 * @return {@code return value}
 * @throws IOException {@code a exception}
 */
public int test (int a) throws IOException {
    System.out.println(a);
    return a;
}
```

## 6.4 通用注释
- @author san
- @version 1.6.5
- @since version 1.6.5
- @deprecated Use other instead
```java
/**
 * {@code Note} is a class
 * @author san
 * @version 1.6.5
 * @since version 1.6.5
 * @deprecated User {@code setVisible} instead
 */
public class Note {

}
```

- @see 
```java
/**
 * @since version 1.6.5 {@link note.AnotherNote#test()} 
 * 可以在任何地方注释 指向其他类和其他方法的链接

 * @see note.AnotherNote#test() 其他类 javadoc 文档中的标识
 *  note.AnotherNote 是类 
 *  test() 是类的相关元素

 * @see <a href="https://www.baidu.com">www.baidu.com</a> 链接

 * @see "app" 表示一段文字
*/
```


## 6.5 注释抽取
- 抽取指定多个包 : `javadoc -d xxxdir xxxpackage1 xxxpackage2`
- 抽取默认包 : `javadoc -d xxxdir *.java`
- 抽取选项 : 
    - `-version -author` 增加作者和版本号
    - `-linksource` 增加源文件链接

