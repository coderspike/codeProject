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
<%--乱码问题--%>
如何解决POST请求中文乱码问题，GET的又如何处理呢？
<%--在web.xml中加入：--%>
<%--<filter>--%>
<%--<filter-name>CharacterEncodingFilter</filter-name>--%>
<%--<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>--%>
<%--<init-param>--%>
<%--<param-name>encoding</param-name>--%>
<%--<param-value>utf-8</param-value>--%>
<%--</init-param>--%>
<%--</filter>--%>
<%--<filter-mapping>--%>
<%--<filter-name>CharacterEncodingFilter</filter-name>--%>
<%--<url-pattern>/*</url-pattern>--%>
<%--</filter-mapping>--%>
<%--以上可以解决post请求乱码问题。对于get请求中文参数出现乱码解决方法有两个：--%>
<%--修改tomcat配置文件添加编码与工程编码一致，如下：--%>
<%--<ConnectorURIEncoding="utf-8" connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443"/>--%>
<%--另外一种方法对参数进行重新编码：--%>
<%--String userName = new String(request.getParamter("userName").getBytes("ISO8859-1"),"utf-8")--%>

</body>
</html>
