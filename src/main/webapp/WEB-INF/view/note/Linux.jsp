<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-03-15
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Linux</title>
</head>
<body>
<%--常用命令--%>
------------------------------------------------------------------------
查看端口号
ps -ax | grep nginx
------------------------------------------------------------------------
查看Linux内核版本命令：
1、cat /proc/version
2、uname -a
查看操作系统相关信息uname -a
查看内核版本 cat /proc/version
查看发行版本信息 cat /etc/issue
查看磁盘使用和挂载信息 df -h
查看网络配置信息 ifconfig
------------------------------------------------------------------------
终止程序
kill -9 1292　 终止线程号为1292的进程
------------------------------------------------------------------------
重命名
mv oldFileName newFileName
------------------------------------------------------------------------
移动文件
mv 文件 目录
------------------------------------------------------------------------
远程复制
#scp /home/administrator/news.txt root@192.168.6.129:/etc/squid
------------------------------------------------------------------------
查看端口占用情况
netstat -tln |grep 8080
------------------------------------------------------------------------
修改文件权限
chmod 777 file　 file的权限 -rwxrwxrwx, r表示读、w表示写、x表示可执行
------------------------------------------------------------------------
寻找文件
1、whereis ifconfig
2、locate ifconfig
</body>
</html>
