<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-03-12
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Redis</title>
</head>
<body>
<%--简介--%>
Redis，一个开源的 key-value，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件。
<%--常用操作--%>
启动
/usr/local/redis/bin/redis-server /etc/redis.conf
------------------------------------------------------------------------
关闭
/usr/local/redis/bin/redis-cli -h 127.0.0.1 -p 6379 shutdown
------------------------------------------------------------------------
查看是否启动
ps -ef | grep redis
------------------------------------------------------------------------
进入客户端
redis-cli
------------------------------------------------------------------------
关闭客户端
redis-cli shutdown
------------------------------------------------------------------------
设置开机自动启动配置
echo "/usr/local/redis/bin/redis-server /etc/redis.conf" >> /etc/rc.local
------------------------------------------------------------------------
开放防火墙端口
添加规则：iptables -I INPUT -p tcp -m tcp --dport 6379 -j ACCEPT
保存规则：service iptables save
重启 iptables：service iptables restart
------------------------------------------------------------------------
常用命令
keys * ： 查看所有keys
ttl：获得一个key的活动时间
flushall：删除所有数据库中的所有key
set(key, value)：给数据库中名称为key的string赋予值value
get(key)：返回数据库中名称为key的string的value
setex(key, time, value)：向库中添加string，设定过期时间time
</body>
</html>
