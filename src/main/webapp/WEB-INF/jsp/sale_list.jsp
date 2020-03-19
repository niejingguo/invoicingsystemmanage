<%--
  Created by IntelliJ IDEA.
  User: NieJingGuo
  Date: 2020/3/17
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
    $(function () {
        //按照排序方式查询
        $("#searchPage").click(function () {
            alert("6666");
            var orderBy = $(".orderBy option.selected").val();
            var param = {};
            param.orderBy = orderBy;
            param.curPage = 1;
            queryList(param)
        });
    });
</script>
<div>
    <div>
        <h2>销售信息查询：
            排序方式：<select class="orderBy">
                <c:if test="${not empty responseResult.date.orderBy}">
                    <c:if test="${responseResult.date.orderBy eq 'saleDate'}">
                        <option value="saleDate" selected>销售日期</option>
                        <option value="totalPrice">销售总价</option>
                    </c:if>
                    <c:if test="${responseResult.date.orderBy eq 'totalPrice'}">
                        <option value="saleDate">销售日期</option>
                        <option value="totalPrice" selected>销售总价</option>
                    </c:if>
                </c:if>
                <c:if test="${responseResult.date.orderBy}">
                    <option value="saleDate">销售日期</option>
                    <option value="totalParice">销售单价</option>
                </c:if>

            </select>
            <input type="button" id="searchPage" value="提交"/>
        </h2>
    </div>
    <div class="one">
        <c:if test="${empty responseResult}">
            没有数据！
        </c:if>
        <c:if test="${not empty responseResult}">
            <c:if test="${responseResult.result==false}">
                <script>
                    location.href = "index.jsp";
                </script>
            </c:if>
            <c:if test="${responseResult.result==true}">
                <table border="1">
                    <tr>
                        <th>销售编号</th>
                        <th>销售商品</th>
                        <th>销售单价</th>
                        <th>销售数量</th>
                        <th>销售员</th>
                        <th>销售总金额</th>
                        <th>销售日期</th>
                        <th>操作</th>
                    </tr>
                        <%--items是集合  var这里是声明集合里面所存放的子元素的名字 --%>
                    <c:forEach items="${responseResult.date.saleList}" var="saleList">
                        <tr>
                                <%--saleList取元素中的元素值--%>
                            <td>${saleList.id}</td>
                            <td>${saleList.productName}</td>
                            <td>${saleList.price}</td>
                            <td>${saleList.quantity}</td>
                            <td>${saleList.userName}</td>
                            <td>${saleList.totalPrice}</td>
                            <td><fmt:formatDate value="${saleList.saleDate}" pattern="yyyy-MM-dd"/></td>
                            <td><a>修改</a>|<a href="javascript:delSale(${saleList.id})">删除</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </c:if>
    </div>
    <div class="page">
        <a href="javascript:firstPage()">首页</a>
        <a href="javascript:prePage()">上一页</a>
        <a href="javascript:nextPage()">下一页</a>
        <a href="javascript:endPage()">末页</a>
        <span>
            当前页：第<span class="curPage">${responseResult.date.curPage}</span>页
            /共<span class="totalPageCount">${responseResult.date.totalPageCount}</span>页
        </span>
    </div>
</div>