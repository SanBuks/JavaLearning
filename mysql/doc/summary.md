  1、查看正在进行中的事务
SELECT * FROM information_schema.INNODB_TRX
  2、查看正在锁的事务
SELECT * FROM INFORMATION_SCHEMA.INNODB_LOCKS;
  3、查看等待锁的事务
SELECT * FROM INFORMATION_SCHEMA.INNODB_LOCK_WAITS;
  4、查询是否锁表
SHOW OPEN TABLES where In_use > 0;
在发生死锁时，这几种方式都可以查询到和当前死锁相关的信息。
  5、查看最近死锁的日志
show engine innodb status

- show processlist
- kill id
- SHOW OPEN TABLES where In_use > 0;