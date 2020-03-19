<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script>
        $(function(){
            $("#loginBtn").click(function () {
                var userName = $("#userName").val();
                var password = $("#password").val();
                //验证参数
                var param = {};
                param.userName = userName;
                param.password = password;
                $.ajax({
                    url: "login",
                    type: "post",
                    data: param,
                    dataType:"json",
                    success: function (result) {
                        if (result.result==true && result.flag==3)

                        alert("登陆成功");
                        location.href="main.jsp";
                    },error:function(result){
                        alert("error"+result);
                    }
                });
            });
        });
    </script>

</head>

<body>
<h2>登陆页面</h2>

    <form>
        <div>用户名：<input type="text" id="userName" /></div>
        <div>密码：<input type="password" id="password" /></div>
        <div><input type="button" id="loginBtn" value="登陆" /></div>
    </form>

</body>
</html>
