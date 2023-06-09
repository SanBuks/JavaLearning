package com.learn.spring.tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao implements IBookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer getBookPriceById(int bookId) {
        String sql = "SELECT price FROM t_book WHERE book_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, bookId);
    }

    @Override
    public Integer updateStock(int bookId) {
        String sql = "UPDATE t_book SET stock = stock - 1 WHERE book_id = ?";
        return jdbcTemplate.update(sql, bookId);
    }

    @Override
    public Integer updateUserBalance(int userId, int price) {

        String sql = "UPDATE t_user SET balance = balance - ? where user_id = ?";
        return jdbcTemplate.update(sql, price, userId);
    }
}
