package com.learn.java.juc.advance.b_completable_future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class StageTest {

    @Test
    public void getTest() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "success";
        }, pool);

        // System.out.println(result.get(4, TimeUnit.SECONDS));
        System.out.println(result.join());
    }

    @Test
    public void completeTest() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "success";
        }, pool);

        System.out.println(result.complete("test") + "\t" + result.get());
    }

    @Test
    public void thenApplyTest() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(() -> {
            System.out.println("111");
            return 1;
        }, pool).thenApply((r) -> {
            System.out.println("222");
            return r + 1;
        }).thenApply((r) -> {
            System.out.println("333");
            return r + 1;
        }).whenComplete((r, e) -> {
            if (e == null) {
                System.out.println(r);
            }
        }).exceptionally((e) -> {
            System.out.println("@" + e.getMessage());
            return null;
        });
        pool.shutdown();
    }

    @Test
    public void handlerTest() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture.supplyAsync(() -> {
                System.out.println("111");
                int a_ = 1 / 0;
                return 1;
            }, pool).handle((r, e) -> {
                if (e == null) {
                    System.out.println("222");
                    return r + 1;
                } else {
                    System.out.println(e.getMessage());
                    throw new RuntimeException((e.getMessage()));
                }
            }).handle((r, e) -> {
                if (e == null) {
                    System.out.println("333");
                    return r + 1;
                } else {
                    System.out.println(e.getMessage());
                    throw new RuntimeException((e.getMessage()));
                }
            }).whenComplete((r, e) -> {
                if (e == null) {
                    System.out.println(r);
                }
            }).exceptionally((e) -> {
                System.out.println(e.getMessage());
                return null;
            });
        } finally {
            pool.shutdown();
        }
    }

    @Test
    public void consumeTest() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture.supplyAsync(() -> {
                System.out.println("111");
                return 1;
            }, pool).thenAccept((r) -> {
                System.out.println(r);
            }).thenRun(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("thenRun");
            }).exceptionally((e) -> {
                e.printStackTrace();
                System.out.println(e.getMessage());
                return null;
            });
        } finally {
            pool.shutdown();
        }
    }

    @Test
    public void threadPoolThenRunTest() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(3);
        try {
            CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
                latch.countDown();
                return "";
            }, pool).thenRun(() -> {
                System.out.println(Thread.currentThread().getName());
                latch.countDown();
            }).thenRun(() -> {
                System.out.println(Thread.currentThread().getName());
                latch.countDown();
            });
        } finally {
            pool.shutdown();
        }
        latch.await();
    }

    @Test
    public void threadPoolThenRunAsyncTest() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(3);
        try {
            CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
                latch.countDown();
                return "";
            }, pool).thenRunAsync(() -> {
                System.out.println(Thread.currentThread().getName());
                latch.countDown();
            }).thenRunAsync(() -> {
                System.out.println(Thread.currentThread().getName());
                latch.countDown();
            });
        } finally {
            pool.shutdown();
        }
        latch.await();
    }

    @Test
    public void applyToEitherTest() throws InterruptedException, ExecutionException {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName());
            return "aaa";
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName());
            return "bbb";
        });

        CompletableFuture<String> cf3 = cf1.applyToEither(cf2, r -> {
            return r + " is Winner!";
        });

        System.out.println(cf3.get());
    }

    @Test
    public void combineTest() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(()->{
            return 10;
        }).thenCombine(CompletableFuture.supplyAsync(()->{
            return 20;
        }), (x, y) -> {
            return x + y;
        });
        System.out.println(cf.get());
    }

}
