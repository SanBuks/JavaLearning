package com.learn.spring.tx.controller;

import com.learn.spring.tx.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    @Autowired
    private IBookService bookService;

    public void buyBook(Integer bookId, Integer userId) {

        System.out.println("buy book");
    }
}
