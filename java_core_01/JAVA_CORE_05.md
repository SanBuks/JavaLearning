

# 1. 类,超类和子类
## 1.1 继承
```java
public class A extends B {
	// 1. super 非对象引用
	// 只是编译器调用父类方法的关键字
	A() {
		// 2. 显示调用父类构造函数
		// 没有显示调用则会默认调用父类默认构造函数
		// 父类若没有默认构造函数则会报错
		super();
        // 3. 调用父类被隐藏掉的方法
		super().xxx();
	}
} 
```

## 1.2 多态
```java
public class Base {
    private String s = "123";
    public void test () {
        System.out.println("123");
    }
}

public class Derived extends Base {
    private String ss = "213";
    public void test () {
        super.test();
        System.out.println(ss);
    }

    public static void main (String [] args) {
        // 允许数组作为一个整体实现多态
        Derived [] deriveds = new Derived[100];
        Base [] bases = deriveds;
        
        // 出现 ArrayStoreException 异常, 实际执行多态发生错误
        bases[0] = new Base();
        deriveds[0].test();  // 实际没有 ss 对象
    }
}
```

## 1.3 函数调用
### 静态绑定
- 静态绑定 : private, static, final 方法 和 构造函数在静态编译时即可确定
- 静态解析 : 
    - 先找候选方法 : 编译器获取对象类型和方法名, 在 对象类型 和 父类型的公有域中 找到所有匹配的函数名
    - 后进行重载解析 : 编译器在候选方法里找到最匹配的方法(完全匹配或最近转换), 如果无法找到最匹配的方法类型则抛出异常

### 动态绑定
- 动态绑定 : 非静态绑定的方法, 以实际绑定的对象类型在函数表中找到对应方法
- 函数表 : 包含了每个类的所有签名, 和实际的调用类型 ( 继承自父类 )
- 动态解析 : 
    - 虚拟机确定实际类型的方法表
    - 在方法表里搜索相关方法并执行


## 1.4 final
- 修饰类 : 类不可被继承(拓展), 类中的所有方法被自动设为 final
- 修饰方法 : 方法不可被覆盖 ( 禁止继承链下级实现多态 )
- 修饰域 : 域不可被更改
> final 方法可以有效减少多态的消耗, JVM 也会检测方法是否实现多态, 同时对短小的方法进行内联优化

## 1.5 类的强制类型转换
- 子类对象赋予给父类变量 : 多态
- 父类对象强制转换为子类对象 : 编译器检测是否能够成功转换, 失败则抛出类型强制转换异常
- 判断上级转下级是否成功 :`if (bases[1] instanceof Derived)`
- null instanceof C : 返回 false

## 1.6 抽象类
- 抽象类不可实例化, 但可定义对象变量
- 抽象类可包含抽象方法, 也可不包含抽象方法
- 抽象类拓展 : 部分定义 -> 抽象类; 全部定义 -> 非抽象类

# 2. Object 超类
- 只有基本类型没有继承Object, 数组等其他类型都继承Object
- 对于判等 : equals, hashCode 和 toString 为 三个法则
- getClass(), getSuperclass() 获取类的运行时描述

## 2.1 equals
### 性质
- 自反性
- 对称性
- 传递性
- 一致性
- 非空性

### 两种写法
- 不同子类之间的比较相等是没有意义的则使用 getClass() 作比较
- 不同子类之间的比较相等是有意义的, 且只在父类上体现则使用 instanceof 作比较, 且一般为 final 不由字类拓展
- 数组的比较一般可以用 `Array.equals(a,b);`
```java
@Override
// 第二种的 equals 重载 一般不由子类拓展设为 final
public boolean equals(Object rhs) {
    // 0. base equal
    super.equals(rhs);
    
    // 1. null == null / same ref
    if (this == rhs) {
        return true;
    }

    // 2. ref != null
    if (rhs == null) {
        return false;
    }

    // 3.1 不同子类之间的比较相等是没有意义
    if (getClass() != rhs.getClass()) {
        return false;
    }
    // 3.2 不同子类之间的比较相等是有意义
    if (!(rhs instanceof Base)) {
        return false;
    }

    // 4. compare all property
    Base baseRhs = (Base) rhs;
    return name.equals(baseRhs.name) &&
           password.equals(baseRhs.password) &&
           money == baseRhs.money;
}
```

## 2.2 hashCode
- 对象的散列码 : 辨别对象是否相等的无规律整型值, o.hashCode()
- Object 的 hashCode 根据对象的存储地址导出
- 数组使用 Array.hashCode()
```java
@Override
public int hashCode() {
    // 1. 用Objects的静态方法不用创建对象
    // 2. 如果对象是 null 则 返回 0
    return  3 * Objects.hashCode(name) +
            5 * Objects.hashCode(password) +
            11 * Objects.hashCode(money);
    // 3. 快速组合多个对象的 hashCode
    // return Objects.hash(name, password, money);
}
```

# 3. 泛型容器与对象包装器

## 3.1 泛型容器
- 默认泛型容器的类型 Object : `ArrayList arrayList = new ArrayList<>()` 
- 将默认类型容器赋予给特化的容器会发生警告错误, 强制类型转换也会, 此时用@SuppressWarnings("unchecked") 忽略警告

## 3.2 泛型容器的基础操作
- 创建预留空间的容器 : `ArrayList<ClassName> arrayList = new ArrayList<>(100)`
- 确保分配的空间 : `staff.ensureCapacity(100)`
- 减少预留的空间 : `staff.trimToSize()`
- 增删改查 : `staff.add(0,object), staff.remove(0), staff.set(1, object) / staff.get(0)`
- 转换为数组 : `staff.toArray()`

## 3.3 对象包装器类
- 包装器类 : Character, Boolean, Byte, Short, Integer, Long, Float 和 Void
- 对于基础类型的泛型容器需要使用包装器类, 编译器在 add 和 set 时会自动拆箱和装箱
- 包装器类不可改变, 需要使用 equals 判等
- 包装器类遵循基础类型的类型自动转换
```java
// 自动拆箱
int n = staff.get(i); // 等价
int n = staff.get(i).intValue();

// 自动装箱
staff.add(i);  // 等价
staff.add(Integer.valueOf(3));
```

# 4. 可变参数
- 参数列表中的可变参数 : `public static void main(String... args)`
- 可变参数等价为 : `public static void main(String [] args)`
- 参数列表中只要最后一个参数是可变参数就可以进行上述的等价转换
- 对于基本类型会自动装箱 : `public static void test(Object [] args)`
```java
public void test(String... args) {
    for(String s : args) {
        System.out.println(s);
    }
}

public static void main (String [] args) {
    Derived d = new Derived("", "",  90);
    d.test(new String[] {"123", "231"});
    d.test("123", "231");
}
```

# 5. 枚举类
- 反射为对象 : `Size ss = Enum.valueOf(Size.class, "SMALL")`
- 获取枚举值的名称 : `Size.SMALL.toString()`
- 获取枚举值的序号下标 : `ss.ordinal()`
```java
enum Size {
    // 1. 定义多个枚举值 和 枚举值的域
    SMALL("S"), MEDIUM("M"), LARGE("L");

    // 2. 枚举值的域和构造函数
    private String abbreviation;
    Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }
    public String getAbbreviation() {
        return abbreviation;
    }
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}

public static void main (String [] args) {
    Size s = Size.SMALL;
    Size ss = Enum.valueOf(Size.class, "SMALL");
    System.out.println(s.getAbbreviation());
    System.out.println(s.toString());
    System.out.println(Size.SMALL.toString());
}
```


# 6. 反射
> 能够分析类能力的程序称为反射

## 6.1 Class类
- 运行时 Class 类 保存每个对象所属类的信息 （每个类一个）, 获取 Class 类对象的方法 : 
```java
Class c1 = random.getClass();
// 需要异常处理
Class c2 = Class.forName("java.util.Random"); 
Class c3 = int.class;
Class c4 = Derived.class;
```

- 根据 Class 类对象定义对象 : c4.newInstance() ( 调用默认构造函数, 没有则报错 )

## 6.2 反射分析构造函数, 方法, 域 
```java
// 1. 打印构造函数
public static void printConstructors(Class<?> cl) {
    Constructor<?> [] constructors = cl.getConstructors();
    for (Constructor<?> c : constructors) {
        System.out.print("    ");
        // 打印修饰符和名字
        String modifiers = Modifier.toString(c.getModifiers());
        String name = c.getName();
        if (!modifiers.isEmpty()) {
            System.out.print(modifiers + " ");
        }
        System.out.print(name + "(");
        // 打印参数列表
        Class<?> [] params = c.getParameterTypes();
        for(int i = 0; i < params.length; ++i) {
            System.out.print(params[i]);
            if( i < params.length - 1) {
                System.out.print(",");
            }
        }
        System.out.print(");");
        System.out.println();
    }
}

// 2. 打印方法 
public static void printMethods(Class<?> cl) {
    Method [] methods = cl.getDeclaredMethods();
    for (Method method : methods) {
       System.out.print("    ");

       String modifiers = Modifier.toString(method.getModifiers());
       Class<?> returnType = method.getReturnType();
       String name = method.getName();

        if (!modifiers.isEmpty()) {
            System.out.print(modifiers + " ");
        }
        // 打印返回类型
        System.out.print(returnType.getName() + " ");
        System.out.print(name + "(");

        Class<?> [] params = method.getParameterTypes();
        for(int i = 0; i < params.length; ++i) {
            System.out.print(params[i]);
            if( i < params.length - 1) {
                System.out.print(",");
            }
        }
        System.out.print(");");
        System.out.println();
    }
}

// 3. 打印数据域
public static void printFields(Class<?> cl) {
    Field [] fields = cl.getDeclaredFields();
    for (Field field : fields) {
        System.out.print("    ");
        String fieldName = field.getName();
        Class<?> type = field.getType();
        System.out.print(type.getName() + " ");
        System.out.print(fieldName);
        System.out.println();
    }
}
```

## 6.3 反射分析获取对象
```java
Class<?> super_cl = cl.getSuperclass();
System.out.println(super_cl.getName());
// getDeclaredField 获取 类中声明的所有类型域, 不包括继承来的域
Field f = super_cl.getDeclaredField("name");
// 设置可访问性
f.setAccessible(true);
// 反射获取域的对象
String s = (String)f.get(object);
System.out.println(s);
// 反射设置对象中的域
f.set(object, "test");
```

## 6.4 反射递归分析对象
```java
public class ObjectAnalyzer {
    private ArrayList<Object> visited = new ArrayList<>();

    public String toString(Object object) {
        if (object == null) {
            return "null";
        }

        // 防止循环引用导致的无限递归
        if (visited.contains(object)) {
            return "...";
        }
        visited.add(object);

        StringBuffer r = null;
        Class<?> cl = object.getClass();

        if (cl.isArray()) {  // 如果是数组则遍历返回对象
            r = new StringBuffer(cl.getComponentType() + "[]{");
            for (int i = 0; i < Array.getLength(object); ++i) {
                if (i > 0) {
                    r.append(",");
                }
                Object val = Array.get(object, i);
                if (cl.getComponentType().isPrimitive()) {
                    r.append(val);
                } else {
                    // 递归调用 toString
                    r.append(toString(val));
                }
            }
            return r.append("}").toString();

        } else { // 不是数组是对象则递归遍历类和父类所有域

            r = new StringBuffer(cl.getName() + "[");
            do {
                Field[] fields = cl.getDeclaredFields();
                AccessibleObject.setAccessible(fields, true);
                for (int i = 0; i < fields.length; ++i) {
                    if (i > 0) {
                        r.append(",");
                    }
                    r.append(fields[i].getName());
                    try {
                        Class<?> t = fields[i].getType();
                        Object val = fields[i].get(object);
                        if (t.isPrimitive()) {
                            r.append(val);
                        } else {
                            r.append(toString(val));
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                r.append("]");
                cl = cl.getSuperclass();
            } while (cl != null);
            return r.toString();
        }
    }

    public static void main(String... args) {

/* 对于 核心 java.lang 包的递归反射会受到限制
// Unable to make {member} accessible: module {A} does not 'opens {package}' to {B}
// # --add-opens has the following syntax: 
// {A}/{package}={B} 
// java --add-opens java.base/java.lang=ALL-UNNAMED

ArrayList<Integer> squares = new ArrayList<>();
for (int i = 0; i < 5; ++i) {
    squares.add(i*i);
}
*/
        Derived d = new Derived("test", "sd", 123);
        System.out.println(new ObjectAnalyzer().toString(d));
    }
}
```

## 6.5 反射分析数组
- 使用 `System.arraycopy(a, 0, newArray, 0, Math.min(a.length, newLength));` 出现错误
```java
public static Object[] badCopyOf(Object[] a, int newLength) {
    // 创建了一个新的 Object[] 数组, 其原始类型为 Object
    Object[] newArray = new Object[newLength];
    System.arraycopy(a, 0, newArray, 0, Math.min(a.length, newLength));
    // 缺点 : 只能以 Object 接受, 无法向下特化
    return newArray;
}

public static void main(String... args) {
    Integer [] ints = new Integer [] {1, 2, 3, 4, 5};
    // java: 不兼容的类型: java.lang.Object[]无法转换为java.lang.Integer[]
    Integer [] a = ArrayReflection.badCopyOf(ints, 6);
    if (a != null) {
        for (int i : a) {
            System.out.println(i);
        }
    }
}
```

- 使用 `Array.newInstance(componentType, newLength);` 替换原始定义
```java
public static Object copyOfArray(Object a, int newLength) {
    Class<?> cl = a.getClass();
    if (!cl.isArray()) {
        return null;
    }

    Class<?> componentType = cl.getComponentType();
    // 替换原始定义
    Object newArray = Array.newInstance(componentType, newLength);

    // 获取数组中的元素
    int value = Array.getInt(a, 0);
    System.out.println(value);
    // 设置数组中的元素
    Array.setInt(a, 0, 2);

    int length = Array.getLength(a);
    System.arraycopy(a, 0, newArray,0, Math.min(length, newLength));
    return newArray;
}

public static void main(String... args) {
    int [] ints = new int [] {1, 2, 3, 4, 5};
    int [] a = (int [])ArrayReflection.copyOfArray(ints, 6);
    if (a != null) {
        for (int i : a) {
            System.out.println(i);
        }
    }
}
```

## 6.6 反射分析方法
- 通过 className.class.getMethod("funcName", Class... parameterTypes) 获取方法
- 通过 method.invoke(object, Object... args) 调用方法,  如果是静态方法则, object 为 null
```java
public class InvokeTest {
    public static double square(double x) {
        return x * x;
    }

    public static void printTable(double from, double to, int n, Method f) {
        System.out.println(f);
        double dx = (to - from) / (n - 1);
        for (double x = from; x <= to; x += dx) {
            try {
                double y = (Double) f.invoke(null, x);
                System.out.printf("%10.4f | %10.4f\n", x, y);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String... args) throws Exception {
        Method square = InvokeTest.class.getMethod("square", double.class);
        Method sqrt = Math.class.getMethod("sqrt", double.class);

        printTable(1, 10, 10, square);
        System.out.println("----------------------------------");
        printTable(1, 10, 10, sqrt);
    }
}

```