<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-03-14
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tomcat</title>
</head>
<body>
<%--Tomcat插件--%>
<plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <version>2.2</version>
    <configuration>
        <path>/taobao</path>
        <port>9090</port>
        <uriEncoding>UTF-8</uriEncoding>
    </configuration>
</plugin>
执行 mvn tomcat7:run
访问 http://127.0.0.1:9090/taobao

</body>
</html>
