<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
username: <input type="text" id="username"><br><br>
password: <input type="password" id="password"><br><br>
<button id="loginbtn">登录页-登录</button>
</body>
<script type="text/javascript">
    $('#loginbtn').click(function () {
        var param = {
            username: $("#username").val(),
            password: $("#password").val()
        };
        $.ajax({
            type: "post",
            url: "/checkLogin.json",
            data: param,
            dataType: "json",
            success: function (data) {
                if (data.success == false) {
                    alert(data.errorMsg);
                } else {
                    //登录成功
                    window.location.href = "<%=request.getContextPath()%>" + "/index.jhtml";
                }
            },
            error: function (data) {
                alert("调用失败....");
            }
        });
    });
</script>
</html>