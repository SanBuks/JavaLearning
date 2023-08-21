# 1. 基础
## 1.1 概念
- MyBatis-Plus 是一个 MyBatis 的增强工具, 在 MyBatis 的基础上只做增强不做改变, 为简化开发, 提高效率而生
- 特点: 
  - 无侵入：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
  - 损耗小：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作
  - 强大的 CRUD 操作：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求
  - 支持 Lambda 形式调用：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
  - 支持主键自动生成：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题
  - 支持 ActiveRecord 模式：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作
  - 支持自定义全局通用操作：支持全局通用方法注入（ Write once, use anywhere ）
  - 内置代码生成器：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用
  - 内置分页插件：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询
  - 分页插件支持多种数据库：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库
  - 内置性能分析插件：可输出 SQL 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询
  - 内置全局拦截插件：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作
- 构建工程:
  - 构建 Spring-Boot 工程
  - 引入依赖
  - 配置 Mysql 连接
  - 配置 MP 日志
  - 加入 @MapperScan 注解

## 1.2 基础 CRUD
- BaseMapper 中的 CRUD
- IService, ServiceImpl 实现

## 1.3 注解
- @TableName / global-config.db-config.table-prefix (默认前缀)
- @TableId(value="id", type= IdType.ASSIGN_ID) 设定实体类与字段映射, 主键生成策略
- @TableField 设定实体类属性和字段映射
- global-config.db-config.id-type (默认主键生成策略)
- @TableLogic 设定逻辑删除字段

## 1.4 雪花算法
- 应对数据库分表, 垂直分表/水平分表
- 对于水平分表
  - 自增: 需要选取分表数量, 扩充平滑, 分布不均
  - 取模: 需要选取表数量, 均匀, 扩充需要重新分布
  - 雪花: 时间自增排序, ID 不重复性, 效率高
- 数据分布: 长度共64bit, 
  - 首先是一个符号位, 一般是0
  - 41bit时间截(毫秒级)，存储的是时间截的差值（当前时间截 - 开始时间截)，结果约等于69.73年
  - 10bit作为机器的ID（5个bit是数据中心，5个bit的机器ID，可以部署在1024个节点）
  - 12bit作为毫秒内的流水号（意味着每个节点在每毫秒可以产生 4096 个 ID）
 
# 2. 条件构造器
## 2.1 基础类
- Wrapper ： 条件构造抽象类，最顶端父类
  - AbstractWrapper ： 用于查询条件封装，生成 sql 的 where 条件
    - QueryWrapper ： 查询条件封装
    - UpdateWrapper ： Update 条件封装
    - AbstractLambdaWrapper ： 使用Lambda 语法
      - LambdaQueryWrapper ：用于Lambda语法使用的查询Wrapper
      - LambdaUpdateWrapper ： Lambda 更新封装Wrapper
- QueryWrapper 
  - 组合查询条件, 删除条件, 修改条件 
  - 逻辑组合 or(i->i.ge().lt())
  - 限定字段 select
  - 子查询