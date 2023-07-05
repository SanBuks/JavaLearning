## 概念
- 程序(program): 为完成特定任务而编写的一组指令的集合(一段静态的代码)
- 进程(process): 程序的一次执行过程, 或是正在内存中运行的应用程序
  - 独立内存空间, 存在生命周期
  - 操作系统调度和分配资源的最小单位, 进程间通信成本高
- 线程(thread): 进程中的一段执行路径, 一个进程中至少有一个线程
  - 并行执行, 是 CPU 调度 和 执行的最小单位
  - 共享堆和方法区, 隔离 PC, VMS, NMS, 线程间通信成本低
- 线程调度策略: 分时调度, 抢占式调度
- 并发与并行的概念
- 多核的成本: 共用资源限制, 调度损耗

## 创建
- 继承 Thread 类, 覆盖 run 方法, 注意 start 和 run 的区别
- 实现 Runnable 接口, runnable 注入到 thread 对象中运行
- 二者区别: 
  - 相同: 都使用 Thread 的 start 方法, 都需要创建 Thread 类或子类的对象
  - 接口优点: 规避单继承问题, 更好处理数据是共享还是分离, 更好处理数据与代码分离
  - 两者联系: 本质代理, public class Thread implements Runnable
 
## 常用方法: 
### 一般方法
- Thread.currentThread() 获取当前线程
- Thread.sleep() 当前线程阻塞指定的毫秒数
- Thread.yield() 一旦执行此方法, 就释放CPU的执行权
- b.join() 在线程a中通过线程b调用join(), 意味着线程a进入阻塞状态, 直到线程b执行结束, 线程a才结束阻塞状态, 继续执行
- b.isAlive() 判断线程是否存活
- stop, suspend, resume 不建议使用因为会存在资源未清理, 死锁问题 
### 优先级
- a.getPriority()
- a.setPriority();
- Thread.MAX_PRIORITY
### 生命周期
- 新建, 就绪, 运行, 阻塞, 死亡(1.5 之前)
- 新建, Runnable, 死亡, 计时等待, 无限等待, 阻塞锁
- 其他方法: 同步锁, wait/notify/notifyAll

## 线程安全
### 同步代码块 + 锁
- 原理: 同步锁, Java对象在堆中的数据分为分为对象头、实例变量、空白的填充, 而对象头中包含：
  - Mark Word(记录了和当前对象有关的GC、锁标记等信息), 指向类的指针, 数组长度
  - 哪个线程获得了同步锁对象之后, 同步锁对象就会记录这个线程的ID
### 同部方法
- 注意 Runnable 和 Thread 使用的区别
### 缺点
- 代码块中实际是串行执行, 性能低

## 死锁