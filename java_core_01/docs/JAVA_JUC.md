# 1. 基本概念
## Thread 状态
- NEW
- RUNNABLE
- BLOCKED
- TIMED_WAITING
- WAITING
- TERMINATED

## wait/sleep 区别
- sleep: Thread 静态方法, 不需要锁
- wait: Object 对象方法, synchronized 区块中调用, 需要锁

## 管程
- monitor: 监视器即锁, 保证同一时间只有一个线程占用竞争资源, 防止死锁或出现脏读等问题

## 用户线程
- 用户线程: 平时用到的普通线程,自定义线程
- 守护线程: 运行在后台,是一种特殊的线程,比如垃圾回收
- 当主线程结束后,用户线程还在运行, JVM 仍然存活
- 如果没有用户线程, 都是守护线程, JVM 会结束 (没有守护的了)
- Thread.isDaemon() 判断是否是守护线程
- Thread.setDaemon() 设置为守护线程

## lock/synchronized 区别
- lock 是接口, synchronized 是关键字
- lock 需要在 finally 手动释放, synchronized 出区域自动释放
- lock 可让等待锁的线程响应中断, synchronized 不行
- lock 可查询锁是否有被申请到, synchronized 不行

# 2. 线程间通信
## 虚假唤醒问题
- 一般的步骤: 
  - 判断(不符合则等待)
  - 操作
  - 通知其他线程
- 需要 if-else 或 while 包裹判断, 防止虚假换新产生错误 
- Condition.await / Condition.signalAll

## 定制化通信 
- 通过标志位通信不同的线程