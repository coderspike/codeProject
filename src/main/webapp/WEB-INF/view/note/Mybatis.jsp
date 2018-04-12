<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-04-10
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mybatis</title>
</head>
<body>
MyBatis 包含一个非常强大的查询缓存特性,它可以非常方便地配置和定制。缓存可以极大的提升查询效率。

MyBatis系统中默认定义了两级缓存。
一级缓存和二级缓存。
1、默认情况下，只有一级缓存（SqlSession级别的缓存，也称为本地缓存）开启。
2、二级缓存需要手动开启和配置，他是基于namespace级别的缓存。
3、为了提高扩展性。MyBatis定义了缓存接口Cache。我们可以通过实现Cache接口来自定义二级缓存

sqlSession:每次访问数据库时都需要创建它
创建Sqlsession的地方只有一个，那就是SqlsessionFactory的openSession方法
一级缓存(local cache), 即本地缓存, 作用域默认为sqlSession。当 Session flush 或 close 后, 该Session 中的所有 Cache 将被清空。

二级缓存(second level cache)，全局作用域缓存；二级缓存默认不开启，需要手动配置
MyBatis提供二级缓存的接口以及实现，缓存实现要求 POJO实现Serializable接口
二级缓存在 SqlSession 关闭或提交之后才会生效
</body>
</html>
