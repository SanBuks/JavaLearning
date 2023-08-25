package org.learn.java.juc.readwrite;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockDowngradeTest {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private String data = "Shared data";

    @Test
    public void processData() {
        lock.writeLock().lock(); // 获取写锁
        try {
            // 执行写操作
            data = "Modified data";
            // 临时释放写锁并获取读锁（锁降级）
            lock.readLock().lock();
            try {
                // 执行读操作
                System.out.println("Read data: " + data);
            } finally {
                lock.readLock().unlock(); // 释放读锁
            }
        } finally {
            lock.writeLock().unlock(); // 释放写锁
        }
    }
}
