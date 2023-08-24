package org.learn.java.juc.communication.c_collection;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class OtherTest {
    @Test
    public void SetTest() {
        Set<Integer> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; ++i) {
            Thread t = new Thread(()-> {
                Random random = new Random();
                set.add(random.nextInt(100));
                System.out.println(Thread.currentThread().getName() + " : " + set);
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
    public void MapTest() {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; ++i) {
            Thread t = new Thread(()-> {
                Random random = new Random();
                map.put(UUID.randomUUID().toString(), random.nextInt(100));
                System.out.println(Thread.currentThread().getName() + " : " + map);
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
