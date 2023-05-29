# 1. 数据类型
## 1.1 基础数据类型
| Data Type  | Default Value  | Default size |
|------------|----------------|--------------|
| boolean    | false          | 1 bit        |
| char       | '\u0000'       | 2 byte       |
| byte       | 0              | 1 byte       |
| short      | 0              | 2 byte       |
| int        | 0              | 4 byte       |
| long       | 0L             | 8 byte       |
| float      | 0.0F           | 4 byte       |
| double     | 0.0D           | 8 byte       |

- Java 没有任何无符号形式的 int、long、short 或 byte 类型
- 可以通过 Byte.toUnsignedInt(b) 来拓展计算无符号类型数
- 整数和布尔类型之间不能进行相互转换, 用 b ? 1 : 0 转换 boolean 为整数
- Double 的特殊值
```java
public class Test {
    public static void main(String [] args) {
        System.out.println(Double.isFinite(-1/0.0));// false
        System.out.println(Double.isInfinite(1/0.0));// true
        System.out.println(Double.isNaN(0/0.0));// true
    }
}
```

## 1.2 特殊形式
- 十六进制 : 0XAA 
- 八进制 : 017
- 二进制 :  0B1001
- 分隔下划线 : 100_000_000
- 十六进制 基数为 2 的计数法 0x1.0p-2

## 1.3 编码与字符
### Unicode
- 字符集 : 能表示字符的集合 
- 字符编码 : 字符的实际二进制编码
- Unicode Character Set (UCS) 万国码 : 国际组织制定的可以容纳世界上所有文字和符号的字符编码方案
- 码点(码位) :  映射字符 现在所使用的 Unicode 范围为 \u0-\u10FFFF
- 代码级别(代码平面) : 0x10 (17个平面) \* 65536(2^8) = 1114112个码点 (字符)

### UTF
- UCS Transformation Format (UTF) Unicode字符集转换格式
- UTF-8 : 以 一字节 为单位 (代码单元/编码单元) 的编码, 其中单个码位的最大编码长度是4个代码单元,  UCS2 时期为最大编码长度为 2
- UTF-16 以 双字节 为单位 (代码单元/编码单元) 的编码, 其中单个码位的最大编码长度是2个代码单元
- UTF-8 为变长编码算法, UTF-16 为代码单元对的编码算法 (详见Wiki)
- 为了编码的唯一性, 编码较码位会膨胀

### char
- Unicode 字符 的 UTF-16 编码的代码单元, 字面值常量必加单引号, 有时一个字符需要用 2个 char 描述
- char 的字面值常量为 16 进制 unicode 编码 '\uxxxx' 四位, 可以转换为 int
- 在单引号中只能用 \u 表示字符, 在源代码中可以直接 \u1000 直接编码转义, 注意不要使用源码的转义, 因为在注释中可能会出现 bug

# 2. 变量
- 不区分声明和定义, 但是必须初始化, 否则会报错, 可用 var i = 1; 自动推导类型
- 对于变量的命名, 可以通过 isJavaidentifierStart 和 isJavaidentifierPart 判断, 下划线不可为变量名
- final 表示常量, public static final 表示类常量
- strictfp 关键字标记的方法必须使用严格的浮点计算（中间值截断）

# 3. 运算符
## 3.1 常用数学方法
```java
// 正常的模运算
floorMod(x, n);  // -2 % 3 = -2 , floorMod(-2, 3) = 1
// 常见方法
Math.round(x);  // 就近取整, 五则向上取整
Math.sqrt(x); Math.pow(x, a);
Math.exp(n); Math.log(n); Math.log1O(n);
// 预设常量
Math.PI;
Math.E;
// 报错提醒的计算方法
Math.addExact(100, 1);
Math.negateExact(100, 1);
```

## 3.2 数值类型转换
![整型转换图.png](D:\Project\LEARNING\JAVA学习\JAVA_CORE_01\image\ch03_整型转换图.png)

- 不同操作数的优先转换等级 : int -> long -> float -> double 
- 小整型提升 : byte / short / char -> int
- 逻辑右移 : `>>>` 算术右移 : `>>` 
- (n & 0b1000) / 0b1000 与 (n & (1 << 3)) >> 3 如果第四位为 1 返回 1 否则返回 0

# 4. 字符串
## 4.1 不可变字符串
- String 是不可变字符串可共享, 理念是字符串的共享 优于 字符串的操作
- String 变量被替换后的内存会由内存回收机制回收
- String 共享机制实际由虚拟机决定, 只有字面值 String 保证共享, 其他未作保证

## 4.2 字符串拼接
```java
// 1.连接,本质是在堆上创建一个新的字符串
s1 = s2 + s3;  
// 2.代码单元从下标 0 开始, 总长最多为 3 的子串
s.substring(0, 3);  
// 3.用分隔符 / 拼接 后面的字符串
s = String.join(" / ", "S", "M", "L", "XL");  
```

## 4.3 判等
```java
// 1. == 只判断二者是否指向同一个内存位置
if (s1 == "123") { 
    // 可能为真, 虚拟机有可能将二者共享
} 
if (s1.substring(0, 2) == "12"){
    // 可能为假, 虚拟机有可能不将二者共享
    // 只有字符串常量始终共享
}
// 2. 判断是否相等
if(s1.equals(s2))
if(s1.compareTo(s2) == 0)
// 3. 忽略大小写判断是否相等
s1.equalsIgnoreCase(s2);
```
## 4.4 判空
```java
// 1. 判断空串
if (str.length() ==0) // 或 
if (str.equals(""))

// 2. 判断对象是否为null然后再判断是否为空串
if (str != null && str.lengthO != 0);  
```

## 4.5 码点和代码单元
```java
public static void main(String... args) throws Exception {
    // https://unicode-table.com/en/xxxxx/
    // 1. 将 UTF-16LE 编码 的字节数组转换为相应字符转
    byte [] starBytes = {(byte) 0x3C, (byte) 0xD8, (byte) 0x1F, (byte) 0xDF};
    byte [] fireBytes = {(byte) 0x3D, (byte) 0xD8, (byte) 0x25, (byte) 0xDD};
    byte [] moonBytes = {(byte) 0x3C, (byte) 0xD8, (byte) 0x11, (byte) 0xDF};
    String s = new String(starBytes, StandardCharsets.UTF_16LE);
    s += new String(fireBytes, StandardCharsets.UTF_16LE);
    s += new String(moonBytes, StandardCharsets.UTF_16LE);

    // 2. 打印每个代码单元
    for (int i = 0; i < s.length(); ++i) {
        System.out.print(s.charAt(i));
        System.out.print(" ");
    }
    System.out.println("");

    // 3. 遍历码点
    // 3.1 根据 codePointAt 遍历
    int codePointSize = s.codePointCount(0, s.length());
    for (int i = 0; i < codePointSize; ++i) {
        // 获取第 i + 1 个码点的代码单元的下标
        int index = s.offsetByCodePoints(0, i);
        int cp = s.codePointAt(index);
        System.out.print(cp);
        System.out.print(" ");
    }
    System.out.println("");

    // 3.2 根据 isSupplementaryCodePoint 遍历
    for (int i = 0; i < s.length(); ++i) {
        int cp = s.codePointAt(i);
        System.out.print(cp);
        System.out.print(" ");
        if (Character.isSupplementaryCodePoint(cp)) {
            ++i;
        }
    }
    System.out.println("");

    // 3.3 根据 isSurrogate 遍历
    for (int i = 0; i < s.length(); ++i) {
        int cp = s.codePointAt(i);
        System.out.print(cp);
        System.out.print(" ");
        if (Character.isSurrogate(s.charAt(i))) {
            ++i;
        }
    }
    System.out.println("");

    // 3.4 根据 codePoints [] 遍历
    int [] codePoints = s.codePoints().toArray();
    for (final int cp : codePoints) {
        System.out.print(cp);
        System.out.print(" ");
    }
    System.out.println("");

    // 4. codePoints 转换为 String
    int [] myCodePoints = {0x1F602, 0x2764, 0x1F932};
    String r = new String(myCodePoints, 0, myCodePoints.length);
    System.out.println(r);
}
```

### StringBuilder 
- String构建器 效率高于 String
```java
StringBuilder builder = new StringBuilder();
builder.append(ch);
builder.append(str);
String result builder.toString();
```

# 5. 输入输出
## 5.1 输入方法
### 读取数据
```java
Scanner scanner = new Scanner(System.in);
String str = scanner.next(); // 读取 字符串, 以空白符分隔
String str1 = scanner.nextLine(); // 读取 一行字符串, 以回车分隔
String str3 = scanner.nextInt(); // 读取 一个int, 以空白符分隔
System.out.println(str);
```

### 控制台读取
```java
// 是否能获取 console / null 因平台而异
Console cons = System.console();
if (cons != null) {
    String userName = cons.readLine("User name: ");
    // 只能读取一行, 读取之前会按照 fmt 打印提示信息
    char [] passwd = cons.readPassword("Password: is %1$2.3d", 1);
    System.out.println(userName);
    System.out.println(new String(passwd));
}
```

## 5.2 输出格式
![](D:\Project\LEARNING\JAVA学习\JAVA_CORE_01\image\ch03_格式说明符语法.png)

### 相关方法
- 可以使用 s 转换符格式化任意的对象, 调用 formatTo 方法 或 toString 方法
- `String str = String.format("%s", name);` 创建一个格式化字符串

### 索引值
```java
// 1$, 2$ 索引值从 1 开始
System.out.printf("%1$f, %1$f", 20F);
// 20.000000, 20.000000

// %< 标志引用前一个变量
System.out.printf("%1$f, %<#f\n", 20F);
// 20.000000, 20.000000
```

### 标志
```java
// + : 正数之前加 '+'   
// 空格 : 正数之前加 ' '
// 0 : 数字前补0 
System.out.printf("%1$+d, %2$+d, %1$ d, %2$ d, %1$03d, %2$03d\n", -20, 20);
// -20, +20, -20,  20

// - : 左对齐
// ( : 将负数扩在括号内
// , : 用 ',' 分位
System.out.printf("%1$-3d, %1$(3d, %2$(3d, %3$,4f\n", 2, -2, 44444444.22222222222);
//2  ,   2, (2), 44,444,444.222222

// %#f : 对于f转换符的整数增加小数点  
// %#x : 对于 x 转换符增加 0x 前缀
// %#o : 对于o转换符增加0前缀
System.out.printf("%#f, %#x, %#o", 20.33, 16, 20);
//20.330000, 0x10, 024
```

### 最小宽度与精度
```java
// . 占宽度
System.out.printf("%10.2d", 44444.2);
//    44444.20
```

### 转换字符
```java
// 十进制整数, 十六进制整数, 八进制整数
System.out.printf("%d, %x, %o, \n", 20, 20, 20);
// 20, 14, 24, 

// 浮点数小数点后保留六位, 十进制十为基数科学计数法的浮点数
// 浮点数小数点后保留两位, 十六进制二为基数科学计数法
System.out.printf("%f, %e, %g, %a\n", 20F, 2000D, 2000F, 2000D);
// 20.000000, 2.000000e+03, 2000.00, 0x1.f4p10

// 字符串(可序列化任意对象formatTo/StringTo 方法), 字符, 布尔值, 散列码
System.out.printf("%s, %c, %b, %h\n", "test", 'c', 20, 23);
// test, c, true, 17
```

### 日期转换符
```java
// 日期转换符        
Date d = new Date();
System.out.printf("%tB", d);
```

## 5.3 文件输入与输出
### 读文件
```java
// 1. 获取 Path
Path p = Paths.get("D:\\test.txt");
// Path p = Paht.of("D:\\test.txt");

// 2. 打开 Scanner
Scanner sc = new Scanner(p, "UTF-8");
// Scanner sc = new Scanner(p, StandardCharsets.UTF_8);

while (sc.hasNextLine()) {
    System.out.println(sc.nextLine());
}

// 3. 关闭 Scanner
sc.close();
```

### 写文件
```java
// 1. 打开 PrintWriter
PrintWriter pw = new PrintWriter("D:\\test.txt", "UTF-8");
// PrintWriter pw = new PrintWriter("D:\\test.txt", StandardCharsets.UTF_8);

// 覆盖
pw.print("test!!!");
// 追加写
pw.append("test!!!");
// 追加写 & 追加偏移写
pw.write('s');
pw.write("s2", 1, 1);

// 2. 刷新缓冲区
pw.flush();

// 3. 关闭 PrintWriter
pw.close();
```

# 6. 流程控制
- javac Xlint:fallthrough xxx.java 会提示 switch 没有 break
- @SuppressWarnings("fallthrough") 抑制无 break 提示
- case 标签可以是 整型, 枚举常量 和 字符串字面值
- break / continue 标签 与 goto 一样效果

# 7. 大数值
```java
// 1. 读取大整数
Scanner sc = new Scanner(System.in);
BigInteger n = sc.nextBigInteger();
BigInteger k = sc.nextBigInteger();
sc.close();

// 2. 大整数 一
BigInteger odds = BigInteger.ONE;
// 通过 compareTo 比较
for (BigInteger i = BigInteger.ONE; i.compareTo(k) <= 0; i = i.add(BigInteger.ONE)) {
    // odds * (n - i + 1) / i
    odds = odds.multiply((n.subtract(i).add(BigInteger.ONE))).divide(i);
}
System.out.println(odds);

// 3. 大浮点数
BigDecimal m = BigDecimal.valueOf(30.2D);
// 向绝对值较大方向取整
m = m.divide(BigDecimal.valueOf(29.3), RoundingMode.HALF_UP);
System.out.println(m);
```

# 8. 数组
## 8.1 定义
-　int a [] = {1, 2, 3};
-　int a [] = new int [3] {1, 2, 3}; 
-　int b [] = new int[10]; 对于数字数组默认初始化为零, boolean 默认初始化为 false, 对象默认初始化为 null
-　int b[] = new int[0]; 可以创建个数为零的数组

## 8.2 数组拷贝
- int newArray [] = oldArray; 浅拷贝
- newArray = Arrays.copyOf(oldArray, oldArray.length \* 2); 深拷贝
- args[0] 是 options

## 8.3 数组排序
```java
int a [] = new int [10];

for (int i = 0; i < 10; ++i) {
    // 随机化 Math.random() 0.0 - 1.0
    a[i] = (int)(Math.random() * 10);
    System.out.print(a[i] + " ");
}

// 排序
Arrays.sort(a);
System.out.print("\n");

for (int i : a) {
    System.out.print(i + " ");
}
for (int i = 0; i < a.length; ++i) {
    System.out.print(i + " ");
}
```

## 8.4 多维数组
- 规则多维数组
```java
// 先定义后赋值
int a [][] = new int [10][10];

for (int i = 0; i < 10; ++i) {
    for (int j = 0; j < 10; ++j) {
        a[i][j] = (int) (Math.random() * 10);
    }
}

for (int row[] : a) {
    for (int i : row) {
        System.out.print(i + " ");
    }
    System.out.print("\n");
}
```

- 不规则多维数组
```java
// 直接定义
int b [][] = { 
    {1,2,3,4,5,6},
    {1,2,3,5,4,6,7}
};
System.out.print(Arrays.deepToString(b));

// 先定义引用的数组 再定义每个不同引用的数组
int b [][] = new int [10][];
for (int i = 0; i < 10; ++i) {
    b[i] = new int [i];
    for (int j = 0; j < i; ++j) {
        b[i][j] = i;
    }
}

System.out.print(Arrays.deepToString(b));
```