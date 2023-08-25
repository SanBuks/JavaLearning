package org.learn.java.juc.readwrite;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Cache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    private final int putWait;
    private final int getWait;

    public Cache(int putWait, int getWait) {
        this.putWait = putWait;
        this.getWait = getWait;
    }

    public void put(String key, Object obj) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": 正在写!");
            TimeUnit.SECONDS.sleep(putWait);
            map.put(key, obj);
            System.out.println(Thread.currentThread().getName() + ": 写完了!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Object get(String key) {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": 正在读!");
            TimeUnit.SECONDS.sleep(getWait);
            Object obj = map.get(key);
            System.out.println(Thread.currentThread().getName() + ": 读完了!");
            return obj;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
        }
    }
}

public class ReadWriteLockTest {

    @Test
    public void test() {
        Cache cache = new Cache(3, 3);
        List<Thread> executors1 = new ArrayList<>();
        List<Thread> executors2 = new ArrayList<>();

        for (int i = 1; i <= 3; ++i) {
            int finalI = i;
            Thread t = new Thread(()->{
                cache.put("" + finalI, "" + finalI);
            }, Integer.toString(i));
            t.start();
            executors1.add(t);
        }

        for (int i = 1; i <= 3; ++i) {
            int finalI = i;
            Thread t = new Thread(()->{
                cache.get("" + finalI);
            }, Integer.toString(i));
            t.start();
            executors2.add(t);
        }

        ThreadsJoin(executors1);
        ThreadsJoin(executors2);
    }

    @Test
    public void firstWriteThenRead() {
        Cache cache = new Cache(10, 1);
        Thread tread = new Thread(() -> {
            cache.put("123", "123");
        });
        Thread twrite= new Thread(() -> {
            cache.get("123");
        });

        tread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        twrite.start();

        try {
            tread.join();
            twrite.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void ThreadsJoin(List<Thread> executors) {
        for (Thread t : executors) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
