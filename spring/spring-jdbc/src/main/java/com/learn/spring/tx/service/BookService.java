package com.learn.spring.tx.service;

import com.learn.spring.tx.dao.IBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookDao bookDao;
}
