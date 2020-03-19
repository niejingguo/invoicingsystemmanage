<%--
  Created by IntelliJ IDEA.
  User: NieJingGuo
  Date: 2020/3/13
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(function () {

        $("#save").click(function () {
            var productId = $("#pro option:selected").val();
            var price = $("#price").val();
            var quantity = $("#quantity").val();
            if (checkParam(productId, price, quantity)) {
                var param = {};
                param.productId = productId;
                param.price = price;
                param.quantity = quantity;
                $.ajax({
                    url: "sale",
                    type: "post",
                    data: param,
                    dataType: "json",
                    success: function (result) {
                        alert(JSON.stringify(result));
                        if (result != "") {
                            if (result.result == false) {
                                if (result.flag == 7) {
                                    location.href = "index.jsp";
                                }
                                if (result.flag == 0) {
                                    alert(result.data);
                                }
                                if (result.flag == 1) {
                                    alert(result.data);
                                }
                                if (result.flag == 2) {
                                    alert(result.data);
                                }
                                if (result.flag == 3) {
                                    alert(result.data);
                                }
                            } else {
                                if (result.flag == 7) {
                                    alert(result.data);
                                    //请求销售记录信息列表
                                }
                            }
                        }
                    }
                });
            }
        });
    });

    function checkParam(productId, price, quantity) {
        if (productId == "") {
            alert("商品不能为空");
            return false;
        }
        if (price == "") {
            alert("销售单价不能为空");
            return false;
        }
        if (quantity == "") {
            alert("销售数量不能为空");
            return false;
        }
        return true;
    }

</script>


<div id="addModel">
    <h1>添加銷售</h1>
    <div>商品名稱：
        <select id="pro">
            <c:if test="${not empty proList}">
                <c:forEach var="product" items="${proList}">
                    <option value="${product.id}">${product.productName}</option>
                </c:forEach>
            </c:if>
        </select>
    </div>
    <div>
        銷售單價：
        <input id="price"/>
    </div>
    <div>
        銷售總量：
        <input id="quantity"/>
    </div>
    <input type="button" id="save" value="保存"/>
</div>