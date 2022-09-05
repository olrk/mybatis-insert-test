# test-mybatis-insert
测试大数据量下MyBatis不同插入方式的性能

## 环境说明

* **MySQL**
  5.7.38，max_allowed_packet：16777216
* **SpringBoot**
  2.6.11
* **数据源**
  Hikari
* **mysql-connector-java**
  8.0.30

## 库表准备

```mysql
create database study;

use study;

create table test_mybatis_insert
(
    id       int(11) unsigned auto_increment,
    date     char(10)       not null,
    month    char(7)        not null,
    name     varchar(100)   not null,
    domain   varchar(100)   not null,
    code     varchar(50)    not null,
    number   varchar(50)    not null,
    type     tinyint(2)     not null,
    province varchar(50)    not null,
    count    bigint(20)     not null,
    level    tinyint(2)     not null,
    model    tinyint(2)     not null,
    address  varchar(150)   not null,
    price    decimal(14, 4) not null,
    remark   varchar(200)   not null,
    primary key (id),
    key idx_date_name_domain (date, name, domain),
    key idx_month (month, number, code)
) engine = InnoDB
  charset = utf8mb4;
```

## 测试说明

* JMeter
  * 程序提供 RestFul 接口供 JMeter 调用
  * JMeter 有两个线程组，一是【预热组】，二是【测试组】
  * 预热组：单线程，循环5次，每次启动 JVM 后启动，用来预热 JVM
  * 测试组：单线程，循环20次，用来执行测试
* MyBatis 插入方式
  * 单条插入：遍历列表单条插入，每插入一条自动提交事务
  * foreach 插入：使用 MyBatis 的 foreach 标签拼接列表元素，插入后自动提交事务
  * batch 单条插入：遍历列表单条插入，全部插入完成后手动一次性提交全部事务，sqlSession 为 ExecutorType.BATCH
  * 每 xxx 条 foreach 插入：对列表进行分批，xxx 条为一批，分批进行 foreach 插入，每一批插入后自动提交事务
  * 每 xxx 条 batch foreach 插入：对列表进行分批，xxx 条为一批，分批进行 foreach 插入，全部批次插入完成后手动一次性提交全部事务，sqlSession 为 ExecutorType.BATCH

## 测试结果

### 5000条数据

| 插入方式       | 平均耗时（ms） |
| -------------- | -------------- |
| 单条插入       | 146407         |
| foreach 插入   | 476            |
| batch 单条插入 | 343            |

batch 方式需要在数据源 url 配置中新增一个连接参数：rewriteBatchedStatements=true，加上改参数后，再对前两种方式进行一次测试：

| 插入方式     | 平均耗时（ms） |
| ------------ | -------------- |
| 单条插入     | 156567         |
| foreach 插入 | 463            |

鉴于上面的测试结果，下面的测试均在添加了 rewriteBatchedStatements=true 这个参数下进行，同时由于单条插入性能毫无疑问垫底，不再进行单条插入的测试。

### 20000条数据

| 插入方式                      | 平均耗时（ms） |
| ----------------------------- | -------------- |
| foreach 插入                  | 1759           |
| batch 单条插入                | 1211           |
| 每 1000 条 foreach 插入       | 2353           |
| 每 1000 条 batch foreach 插入 | 2384           |

### 50000条数据

由于 foreach 拼接 50000 条数据后的大小超过了我们设置的 max_allowed_packet，所以只能进行分批插入

![图片1](https://user-images.githubusercontent.com/53005753/188395340-4a8905e0-3cba-442e-9a4d-0ffc245937ce.png)

| 插入方式                       | 平均耗时（ms） |
| ------------------------------ | -------------- |
| batch 单条插入                 | 3022           |
| 每 1000 条 foreach 插入        | 6176           |
| 每 1000 条 batch foreach 插入  | 6120           |
| 每 5000 条 foreach 插入        | 5098           |
| 每 5000 条 batch foreach 插入  | 5110           |
| 每 20000 条 foreach 插入       | 4394           |
| 每 20000 条 batch foreach 插入 | 4429           |

## 结论

1. 循环单条插入的效率极低，在实际开发中要极力避免这种情况
2. foreah 插入在数据量不是很大的情况下效率尚可，数据量越大越比不过 batch 单条插入
3. 在大数据量下 batch 单条插入的效率极高，推荐使用，不过对事物提交的时机需要仔细斟酌
4. 分批插入的效率比全部插入的效率低，但在实际开发中，由于需要考虑到 MySQL 服务器内存、I/O 带宽等因素，分批还是比较常见的。具体的每批次数量需要根据具体业务、具体服务器配置来考虑
