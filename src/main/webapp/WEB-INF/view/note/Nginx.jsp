<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-03-15
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nginx</title>
</head>
<body>
<%--常用命令--%>
启动服务：
要启动nginx,只需执行安装时自带的可执行文件就行了。
/usr/sbin/nginx
/usr/local/nginx/sbin/nginx -c /usr/local/nginx/conf/nginx.conf 用配置文件启动
关闭服务：
/usr/sbin/nginx -s signal
signal参数有：
stop 快速停止
quit 优雅（从容）地停止
重启服务：
进入nginx安装目录sbin下，输入命令./nginx -t 用来验证配置文件是否正确
进入nginx可执行目录sbin下，输入命令./nginx -s reload 即可


</body>
</html>
