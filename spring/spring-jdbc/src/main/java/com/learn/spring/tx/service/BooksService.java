package com.learn.spring.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksService implements IBooksService{

    private IBookService bookService;

    @Autowired
    public void setBookService(IBookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void buyBooks(Integer bookId, Integer userId, int times) {
        for (int i = 0; i < times; ++i) {
            bookService.buyBook(bookId, userId);
        }
    }
}
