<%--
  Created by IntelliJ IDEA.
  User: NieJingGuo
  Date: 2020/3/18
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(function () {
        $("#searchBtn").click(function () {
            var productId = $("#pro option:selected").val();
            $.ajax({
                url: "queryQua",
                type: "get",
                data: "pId=" + productId,
                success: function (result) {
                    $(".three").html(result);
                }
            });
        });
    });
</script>
<div>
    <h1>查看库存</h1>
    <div>请选择商品名称：
        <select id="pro">
            <c:if test="${not empty proList}">
                <c:forEach var="product" items="${proList}">
                    <option value="${product.id}">${product.productName}</option>
                </c:forEach>
            </c:if>
        </select>
        <input type="button" id="searchBtn" value="查询"/>
    </div>
    <div class="three">

    </div>
</div>