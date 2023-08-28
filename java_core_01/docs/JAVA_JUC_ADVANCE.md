# 2. CompletableFuture
## 2.1 Future
- Future 接口定义了操作异步任务执行一些方法，如获取结果、取消执行、判断是否被取消、判断是否完毕等, 表示对异步处理的操作
- FutureTask 与 Future 的关系: 
  - `public class FutureTask<V> implements RunnableFuture<V>` 
  - `public FutureTask(Callable<V> callable)` 返回值特性
  - `public interface RunnableFuture<V> extends Runnable, Future<V>` 线程特性 + 异步特性
 
![](image/JUC_FutureTask.png)

- 一般用线程池 + Future 节省线程创建与销毁资源
- 缺点(获取结果的方法不是最优的): 
  - get() 容易出现阻塞问题, 一般放在最后 或者使用 get(3, TimeUnit.SECONDS) 过时不候, 抛出异常
  - isDone() 轮询耗费 CPU 时间
  - 有用接口较少
- 需要改进的地方:
  - 回调通知
  - 结合线程池
  - 任务阶段组合
  - 最快结果筛选

## 2.2 CompletableFuture 基本概念
- 设计原理: 观察者模式, 任务完成后通知监听一方
- 类架构: 
  - `public class CompletableFuture<T> implements Future<T>, CompletionStage<T>` 
  - CompletionStage: 代表异步任务中的一个步骤, 类似 Linux 操作系统中的管道符  
- 创建方法:  
  - `public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)`
  - `public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)` 
- 组合方法:
  - `public CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action)`
  - `public CompletableFuture<T> exceptionally(Function<Throwable, ? extends T> fn)`
 
## 2.3 流式调用
- 函数式接口:

![](image/JUC_function.png)

- join 和 get 区别:
  - join 不抛异常, get 抛异常
  - join 不可中断, get 可中断

- 使用案例
```java
class Foo {
    static public List<String> calcProductsAsync(List<Mall> malls, String productName) {
        return malls
                .stream()
                .map((mall) -> CompletableFuture.supplyAsync(
                        () -> String.format("%s in %s price: %f", productName, mall.getName(), mall.calc(productName))))
                .toList()
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
```

## 2.4 步骤接口分类
- 获取结果:
  - `public T get() throws` 阻塞获取结果
  - `public T get(long timeout, TimeUnit unit) throws` 预计时间阻塞, 会报超时异常
  - `public T join()` 抛出的是 uncheck 异常
  - `public T getNow(T valueIfAbsent)` 不阻塞, 如果未完成则返回 valueIfAbsent 
  - `public boolean complete(T value)` 直接完成, 如果源未完成返回 value
- 结果处理: 
  - `public <U> CompletableFuture<U> thenApply( Function<? super T,? extends U> fn)` 串行化, 会报错并终止
  - `public <U> CompletableFuture<U> handle( BiFunction<? super T, Throwable, ? extends U> fn)` 串行化, 附带异常传递
  - `public CompletableFuture<T> exceptionally( Function<Throwable, ? extends T> fn)` 异常处理, 与 handle 类似
- 结果消费:  
  - `public CompletableFuture<Void> thenAccept(Consumer<? super T> action)` 无返回值, null
  - `public CompletableFuture<Void> thenRun(Runnable action)` 无返回值, null
- 线程池选择:     
  - 默认线程池: thenRun 和 thenRunAsync 都用默认
  - 自定义: thenRun 共用自定义线程池, thenRunAsync 调用自定义线程池, 某种情况下会出现优化为 main 线程
- 速度选择:  
  - `public <U> CompletableFuture<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn)` 采用最先算出的 stage
- 结果合并:
  - `public <U,V> CompletableFuture<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)`  合并计算结果

    
