package com.learn.spring.txXml.service;

import com.learn.spring.txXml.dao.IBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService {

    private IBookDao bookDao;

    @Autowired
    public void setBookDao(IBookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void buyBook(Integer bookId, Integer userId) {
        Integer price = bookDao.getBookPriceById(bookId);
        bookDao.updateStock(bookId);
        bookDao.updateUserBalance(userId, price);
    }
}
