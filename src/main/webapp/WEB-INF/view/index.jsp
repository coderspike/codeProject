<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
欢迎登录
<button id="loginbtn">退出</button>
</body>
<script type="text/javascript">
    $('#loginbtn').click(function () {
        $.ajax({
            type: "post",
            url: "/logout.json",
            dataType: "json",
            success: function (data) {
                if (data.success == false) {
                    alert(data.errorMsg);
                } else {
                    //登出成功
                    window.location.href = "<%=request.getContextPath()%>" + "/login.jhtml";
                }
            },
            error: function (data) {
                alert("调用失败....");
            }
        });
    });
</script>
</html>