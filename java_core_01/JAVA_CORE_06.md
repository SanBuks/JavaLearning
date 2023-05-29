# 1. 接口
## 1.1 接口概念
- 可定义常量域, 不可定义实例域, 定义的实例域会被默认当成 static final
- SE8 后可定义方法, 但是不能引用实例域, 同时也可以定义静态方法
- 方法自动 public, 类继承时需要显示声明为public, 否则为protected
- compareTo 接口遵循 equals 相同的准则 : 对称性, 非空性
- compareTo 接口对于基类的转化而言有 2 + 1 种写法 :
    - 只在父类上体现不同字类之间的比较意义
    - 不同子类之间的比较的无意义
    - 用统一的 rank 表示
```java
public class InterfaceTest implements Comparable<InterfaceTest>{
    private int s;

    public InterfaceTest(int s) {
        this.s = s;
    }

    @Override
    public int compareTo(InterfaceTest obj) {
        /*
        if (s < obj.s) {
            return -1;
        } else if (s == obj.s) {
            return 0;
        } else {
            return 1;
        }
        */
        return Integer.compare(s, obj.s);
    }

    @Override
    public String toString() {
        return "InterfaceTest{" +
            "s=" + s +
            '}';
    }

    public static void main(String... args) {
        InterfaceTest a [] = new InterfaceTest [] {
            new InterfaceTest(3),
            new InterfaceTest(1),
            new InterfaceTest(7),
            new InterfaceTest(-22),
            new InterfaceTest(23),
        };

        for (InterfaceTest item : a) {
            System.out.println(item);
        }
 
        // 对于对象 InterfaceTest 而言
        // 泛型算法 sort 对于类的compareTo实现有着编译期检测的严格的要求
        // 必须实现 Comparable 接口
        Arrays.sort(a);
        System.out.println("----------------------------------");

        for (InterfaceTest item : a) {
            System.out.println(item);
        }
    }
}
```

## 1.2 接口拓展
- 可用接口声明对象变量, 但是无法定义变量
- 可用 instanceof 判断对象是否实现了接口
- 接口可被继承
- 类可以实现多个接口但是只能继承一个类
- 一般用