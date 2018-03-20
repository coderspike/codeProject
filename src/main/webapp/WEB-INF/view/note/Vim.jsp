<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-03-13
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vim</title>
</head>
<body>
<%--常用命令--%>
:w #保存
:w! #强制保存
:q #推出
:q! #强制退出
:wq! #强制保存退出
:w[finame] #另存为
:!Command #临时执行命令
:set nu #显示行号
nG(光标移动到第几行) #移动到第几行，如2G移动到第2行
G #移动到最后一行
:/word #下搜索word的字符串
:?word #上搜索word的字符串
:n1,n2s/word1/word2/g #从第n1行到第n2行搜索word1替换为word2
:n1,$s/word1/word2/gc #与上相同，只是在替换时询问
dd #剪切当前行
ndd #剪切向下n行
yy #复制当前行
nyy #复制向下n行
p,P #p粘贴到下一行，P粘贴到上一行t
u #撤销前一次操作
ctrl+r #重做前一个操作
</body>
</html>
