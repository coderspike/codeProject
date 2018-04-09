<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-03-21
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dubbo</title>
</head>
<body>
<%--简介--%>
Dubbo是阿里巴巴提供的开源的SOA服务化治理的技术框架。

它最大的特点是按照分层的方式来架构，使用这种方式可以使各个层之间解耦合（或者最大限度地松耦合）。
从服务模型的角度来看，Dubbo采用的是一种非常简单的模型，要么是提供方提供服务，
要么是消费方消费服务，所以基于这一点可以抽象出服务提供方（Provider）和服务消费方（Consumer）两个角色。

<%--Dubbo--%>
Provider: 暴露服务的服务提供方。
Consumer: 调用远程服务的服务消费方。
Registry: 服务注册与发现的注册中心(ZooKeeper)。
Monitor: 统计服务的调用次调和调用时间的监控中心。
Container: 服务运行容器。
</body>
</html>
