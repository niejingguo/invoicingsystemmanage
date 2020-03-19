<%--
  Created by IntelliJ IDEA.
  User: NieJingGuo
  Date: 2020/3/13
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>main.jsp</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script>
        $(function () {
            //退出登陆
            $("#logout").click(function () {
                $.ajax({
                    url: "login",
                    type: "get",
                    dataType: "json",
                    success: function (result) {
                        if (result.result == true && result.flag == 1) {
                            location.href = "index.jsp";
                        }
                    }
                });
            });
            $("#addSale").click(function () {
                $.ajax({
                    url: "sale",
                    type: "get",
                    success: function (result) {
                        alert(result);
                        $("#content").html(result);
                    }
                });
            });

            $("#getSale").click(function () {
                queryList("");
            });
//进入查看库存页面
            $("#getPro").click(function () {
                $.ajax({
                    url: "qua",
                    type: "get",
                    success: function (result) {
                        $("#content").html(result);
                    }

                });
            });

        });

        function queryList(param) {
            if (param == "") {
                param = {};
                param.curPage = 1;
            }
            param.pageSize = 5;
            $.ajax({
                url: "saleList",
                type: "get",
                data: param,
                success: function (result) {
                    alert(result);
                    $("#content").html(result);
                }
            });
        }

        //首页
        function firstPage() {
            queryList("");
        }

        //上一页
        function prePage() {
            var curPage = $(".curPage").text();
            if (curPage > 1) {
                curPage--;
                var param = {};
                param.curPage = curPage;
                queryList(param);
            }
        }

        //下一页
        function nextPage() {
            var curPage = $(".curPage").text();
            var totalPageCount = $(".totalPageCount").text();
            if (curPage < totalPageCount) {
                curPage++;
                var param = {};
                param.curPage = curPage;
                queryList(param);
            } else {
                alert("已经最后一页了");
            }
        }

        //末页
        function endPage() {
            var curPage = $(".curPage").text();
            var totalPageCount = $(".totalPageCount").text();
            if (curPage < totalPageCount) {
                curPage = totalPageCount;
                var param = {};
                param.curPage = curPage;
                queryList(param);
            }
        }

        function delSale(id) {
            if (confirm("确定要删除吗？")) {
                $.ajax({
                    url: "delSale",
                    type: "get",
                    data: "id=" + id,
                    dataType: "json",
                    success: function (result) {
                        if (result != "") {
                            if (result.result==false){
                                location.href="index.jsp";
                            }else {
                                if (result.flag==1){
                                    queryList("")
                                }
                            }
                        }
                    }
                });
            }
        }
    </script>
    <style type="text/css">
        .pir {
            width: 100%;
            height: 500px;
            position: relative;
        }

        .huanying {
            border: 1px solid navy;
            position: absolute;
            top: 50px;
            right: 400px;
            width: 600px;
            height: 300px;
        }

        .huanying div {
            margin: 0 auto;
        }

        .menu {
            position: absolute;
            top: 70px;
            left: 50px;
            width: 500px;
            height: 300px;
        }

        .menu > div {
            height: 45px;
        }

        table {
            border: 1px solid blue;
        }

    </style>
</head>
<body>
<div>
    欢迎:${user.userName},<a href="#" id="logout">退出登陆</a>
</div>
<div class="Pir">
    <div class="menu">
        <div>
            <a href="#" id="addSale">销售</a>
        </div>
        <div>
            <a href="#" id="getSale">销售信息查询</a>
        </div>
        <div>
            <a href="#" id="getPro">查看库存</a>
        </div>
    </div>
    <div class="huanying">
        <div id="content">
            <h1>欢迎使用小型进销存系统！</h1>
        </div>
    </div>
</div>
</body>
</html>
