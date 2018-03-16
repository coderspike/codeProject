<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-03-08
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Git</title>
</head>
<body>

<!-- 常用命令 -->
git add . //将改动添加到暂存区
git commit //将文件提到本地库
git status //查看文件变化
git push //将本地分支的更新推送到远程主机
{
git push -u origin master
}
git clone //从远程主机克隆一个版本库
git fetch //将远程主机版本库的更新取回本地
{
git fetch origin//查看有哪一些改动
}
git pull //取回远程主机的分支
git branch //显示当前分支

<!-- git 公私钥 -->

{
1、ssh-keygen 一路回车
2、id_rsa是私钥文件，id_rsa.pub是公钥文件
3、配置到github中
}

{
git config --global user.name "你的用户名"
git config --global user.email "你的邮箱"
}

</body>
</html>
