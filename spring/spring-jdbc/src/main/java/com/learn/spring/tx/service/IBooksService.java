package com.learn.spring.tx.service;

public interface IBooksService {
    public void buyBooks(Integer bookId, Integer userId, int times);
}
