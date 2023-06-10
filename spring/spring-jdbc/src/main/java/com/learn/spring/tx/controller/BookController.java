package com.learn.spring.tx.controller;

import com.learn.spring.tx.service.IBookService;
import com.learn.spring.tx.service.IBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    private IBookService bookService;
    private IBooksService booksService;

    @Autowired
    public void setBookService(IBookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setBooksService(IBooksService booksService) {
        this.booksService = booksService;
    }

    public void buyBook(Integer bookId, Integer userId) {
        bookService.buyBook(bookId, userId);
    }

    public void buyBooks(Integer bookId, Integer userId, int times) {
        booksService.buyBooks(bookId, userId, times);
    }
}
