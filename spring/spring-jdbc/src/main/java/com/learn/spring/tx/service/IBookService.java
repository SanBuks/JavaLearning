package com.learn.spring.tx.service;

public interface IBookService {

    public void buyBook(Integer bookId, Integer userId);
    public void buyBooks(Integer bookId, Integer userId, int times);
}
