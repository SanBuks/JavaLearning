package com.learn.spring.tx.service;

import com.learn.spring.tx.dao.IBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class BookService implements IBookService {

    private IBookDao bookDao;

    @Autowired
    public void setBookDao(IBookDao bookDao) {
        this.bookDao = bookDao;
    }
    //    @Transactional(readOnly = true) // 只读操作
//    @Transactional(timeout = 3)     // 超时回滚
//    @Transactional(noRollbackFor = ArithmeticException.class) // 指定 runtime 异常不回滚
//    @Transactional(isolation = Isolation.DEFAULT) // 隔离性
    // 问题:
    //  - READ UNCOMMITTED
    // - 脏读: 读未提交
    //  - READ COMMITTED
    // - 不可重复读: 单行修改
    //  - REPEATABLE READ (DEFAULT)
    // - 幻读: 多出一行
    //  - SERIALIZABLE

//    @Transactional(propagation = Propagation.REQUIRED)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    // 传播行为
    // REQUIRED：支持当前事务，如果不存在就新建一个(默认)【没有就新建，有就加入】
    // REQUIRES_NEW：开启一个新的事务，如果一个事务已经存在，则将这个存在的事务挂起
    //              【不管有没有，直接开启一个新事务， 开启的新事务和之前的事务不存在嵌套关系，之前事务被挂起】
    // NESTED: 如果当前正有一个事务在进行中，则该方法应当运行在一个嵌套式事务中。
    //         被嵌套的事务可以独立于外层事务进行提交或回滚。如果外层事务不存在，行为就像REQUIRED一样。
    //         【有事务的话，就在这个事务里再嵌套一个完全独立的事务，嵌套的事务可以独立的提交和回滚。没有事务就和REQUIRED一样。
    public void buyBook(Integer bookId, Integer userId) {
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException ex) {
//            throw new RuntimeException("超时");
//        }
//        int a = 1 / 0;

        Integer price = bookDao.getBookPriceById(bookId);
        bookDao.updateStock(bookId);
        bookDao.updateUserBalance(userId, price);
    }

//    @Transactional // 1. 调用同类函数, 同类函数的 transaction 不奏效, 调用函数的 transaction 奏效
//                   // 2. 一般通过新的类(如 BooksService), 调用相关函数可使函数的 transaction 奏效
    @Override
    public void buyBooks(Integer bookId, Integer userId, int times) {
//        for (int i = 0; i < times; ++i) {
//            buyBook(bookId, userId);
//        }
    }
}
