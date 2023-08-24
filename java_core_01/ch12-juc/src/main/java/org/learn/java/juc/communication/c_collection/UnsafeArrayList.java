package org.learn.java.juc.communication.c_collection;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class UnsafeArrayList {
    public static void main(String [] args) {
        // 多次运行出现错误
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            Thread t = new Thread(()-> {
                Random random = new Random();
                // 当一个线程正在迭代集合的同时，其他线程可能在集合中进行修改操作，这会导致迭代器的迭代状态与实际集合的结构不一致。
                list.add(random.nextInt(100));
                // 为了确保迭代的安全性，Java 集合框架在迭代期间会进行快速失败（fail-fast）机制的检查，
                // 一旦检测到结构性修改，就会抛出 ConcurrentModificationException 异常，以避免潜在的数据不一致问题。
                System.out.println(Thread.currentThread().getName() + " : " + list);
            }, Integer.toString(i));
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void Vector() {
        List<Integer> list = new Vector<>();
        for (int i = 0; i < 10; ++i) {
            Thread t = new Thread(()-> {
                Random random = new Random();
                list.add(random.nextInt(100));
                System.out.println(list);
            }, Integer.toString(i));
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void SynchronizedList() {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 10; ++i) {
            Thread t = new Thread(()-> {
                Random random = new Random();
                list.add(random.nextInt(100));
                System.out.println(list);
            }, Integer.toString(i));
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void CopyOnWrite() {
        List<Integer> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; ++i) {
            Thread t = new Thread(()-> {
                Random random = new Random();
                list.add(random.nextInt(100));
                System.out.println(list);
            }, Integer.toString(i));
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
