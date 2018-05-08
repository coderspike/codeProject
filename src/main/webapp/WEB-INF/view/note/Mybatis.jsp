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
Mybatis的核心是：sqlsessionfactory

MyBatis系统中默认定义了两级缓存。
一级缓存和二级缓存。
1、默认情况下，只有一级缓存（SqlSession级别的缓存，也称为本地缓存）开启。
2、二级缓存需要手动开启和配置，他是基于namespace级别的缓存。
3、为了提高扩展性。MyBatis定义了缓存接口Cache。我们可以通过实现Cache接口来自定义二级缓存

sqlSession:每次访问数据库时都需要创建它
创建Sqlsession的地方只有一个，那就是SqlsessionFactory的openSession方法
一级缓存(local cache), 即本地缓存, 作用域默认为sqlSession。
当 Session flush 或 close 后, 该Session 中的所有 Cache 将被清空。

二级缓存(second level cache)，全局作用域缓存；二级缓存默认不开启，需要手动配置
MyBatis提供二级缓存的接口以及实现，缓存实现要求 POJO实现Serializable接口
二级缓存在 SqlSession 关闭或提交之后才会生效

Mybatis是如何进行分页的？分页插件的原理是什么？
Mybatis使用RowBounds对象进行分页，它是针对ResultSet结果集执行的内存分页，而非物理分页，
可以在sql内直接书写带有物理分页的参数来完成物理分页功能，也可以使用分页插件来完成物理分页。
(在我们自己的项目中是将分页信息封装到一个BasePageModel类中，包含page和rows属性，通过后台查询来分页)


<!--
<%--#{}和${}的区别是什么？--%>
<%--#{}是预编译处理，${}是字符串替换。--%>[记住这个就行了]
<%--Mybatis在处理#{}时，会将sql中的#{}替换为?号，调用PreparedStatement的set方法来赋值；--%>
<%--Mybatis在处理${}时，就是把${}替换成变量的值。--%>
<%--使用#{}可以有效的防止SQL注入，提高系统安全性。--%>
-->

<!--
当实体类中的属性名和表中的字段名不一样 ，怎么办 ？
第1种： 通过在查询的sql语句中定义字段名的别名，让字段名的别名和实体类的属性名一致
<select id=”selectorder” parametertype=”int” resultetype=”me.gacl.domain.order”>
    select order_id id, order_no orderno ,order_price price form orders where order_id=#{id};
</select>
第2种： 通过
<resultMap>来映射字段名和实体类属性名的一一对应的关系
    <select id="getOrder" parameterType="int" resultMap="orderresultmap">
        select * from orders where order_id=#{id}
    </select>
    <resultMap type=”me.gacl.domain.order” id=”orderresultmap”>
        <!–用id属性来映射主键字段–>
        <id property=”id” column=”order_id”>
            <!–用result属性来映射非主键字段，property为实体类属性名，column为数据表中的属性–>
            <result property=“orderno” column=”order_no”/>
                <result property=”price” column=”order_price”/>
    </reslutMap>
-->

分页：
1、在接口传入RowBounds对象即可
new RowBounds(count,pageNo,pageSize);
count:总记录数
pageNo:当前页
pageSize:每页记录数
mybatis自动在sql中加入分页(在mysql中加入limit)
2、pageHelper

MyBatis 编程步骤：
创建 SqlSessionFactory
通过 SqlSessionFactory 获取 SqlSession
通过 SqlSession 执行数据库操作
提交事务
关闭会话

MyBatis 是如何进行分页的？分页插件的原理？
mybatis 是通过 RowBounds 对象进行分页的，他针对返回结果集 ResultSet 进行内存分页，而非物理分页。
分页插件通过 mybatis 提供的分页插件接口进行实现，通过拦截器拦截需要执行的 sql 并重写 sql，
以此来添加对应的参数，最终实现分页效果

MyBatis 是否支持延迟加载？如果支持，他的实现原理是什么？
mybatis 支持延迟加载，但有限制。他支持一对一、一对多的延迟加载。
要使 mybatis 支持延迟加载，需要在配置文件中配置 lazyLoadingEnabled 值为 true。
mybatis 通过 CGLIB 创建目标对象的代理对象，在调用目标对象的 get
方法时，进行拦截并检查关联对象是否为空，
如果为空则调用 sql 进行相应对象的查询并通过 set 方法进行数据填充，最后在返回对应关联对象。


</body>
</html>
