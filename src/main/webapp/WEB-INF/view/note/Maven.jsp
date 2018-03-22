<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>常用的maven命令</title>
</head>
<body>
<!-- 常用命令 -->
{
mvn install //在本地Repository中安装jar
mvn clean //清除产生的项目
mvn test //运行测试
mvn package //打包，根据pom.xml打成war或jar
mvn clean install -Dmaven.test.skip=true -Dmaven.javadoc.skip=true //打包
}
<!-- 环境隔离 -->
<profiles>
    <profile>
        <id>dev</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <deploy.type>dev</deploy.type>
        </properties>
    </profile>
    <profile>
        <id>beta</id>
        <properties>
            <deploy.type>beta</deploy.type>
        </properties>
    </profile>
</profiles>
</body>
</html>
