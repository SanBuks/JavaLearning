package com.learn.spring.tx.dao;

public interface IBookDao {
    public Integer getBookPriceById(int bookId);
    public Integer updateStock(int bookId);
    public Integer updateUserBalance(int userId, int price);
}
