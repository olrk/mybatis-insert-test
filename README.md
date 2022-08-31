# test-mybatis-insert
测试大数据量下MyBatis不同插入方式的性能

库表准备：

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
