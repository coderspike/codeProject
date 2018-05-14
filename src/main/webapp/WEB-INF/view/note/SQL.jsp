<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-04-23
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据库相关</title>
</head>
<body>
数据库分表：
横向切分是指一个表数据量很大，分成非常多的小表进行存入等操作，比如股票行情数据，可以按照id取余进行分表存储
活跃度进行纵向分表比如博客系统中 标题等是冷数据，访问量等是热数据

索引的分类
聚集索引
非聚集索引
哈希索引
B+-树索引（二分查找）

哈希索引：
哈希索引查询效率O(1)
不能实现范围查找以及> < <> 等这些符号的相关查找
不需要频繁的调整数据分布

B-Tree索引：
支持范围查找
效率O(logN)
需要频繁的合并和分裂。

索引有什么作用
在数据库系统中建立索引主要有以下作用：
快速取数据；
保证数据记录的唯一性；
实现表与表之间的参照完整性；
在使用 ORDER BY、GROUP BY 子句进行数据检索时，利用索引可以减少排序和分组的时间。
</body>
</html>
