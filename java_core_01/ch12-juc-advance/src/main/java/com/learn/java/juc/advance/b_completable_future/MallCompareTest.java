package com.learn.java.juc.advance.b_completable_future;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MallCompareTest {
    static List<Mall> malls = Arrays.asList(new Mall("tm"), new Mall("jd"), new Mall("pdd"));
    static public List<String> calcProductsSync(List<Mall> malls, String productName) {
        return malls
            .stream()
            .map((mall) -> String.format("%s in %s price: %f", productName, mall.getName(), mall.calc(productName)))
            .collect(Collectors.toList());
    }

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


    @Test
    public void SynTest() {
        long startTime = System.currentTimeMillis();
        calcProductsSync(malls, "jump").forEach(System.out::println);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    @Test
    public void AsyncTest() {
        long startTime = System.currentTimeMillis();
        calcProductsAsync(malls, "jump").forEach(System.out::println);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

}

class Mall {
    private String name;
    public Mall(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double calc(String productName) {
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ThreadLocalRandom.current().nextDouble(2) + productName.charAt(0);
    }
}
