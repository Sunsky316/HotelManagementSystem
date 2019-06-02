<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>



	<body>
	<%@ include file="/jsp/header.jsp" %>

		
			


		<div class="container">
			<c:if test="${empty cart.cartItems }">
				<div class="row">
					<div class="col-md-12">
						<h1>为您预订心仪的住所！请先浏览房间并加入预订项</h1>
					</div>
				</div>
			</c:if>
			
			<c:if test="${not empty cart.cartItems }">
			<div class="row">
				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong style="font-size:16px;margin:5px 0;">订单详情</strong>
					<table class="table table-bordered">
						<tbody>
							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>天数</th>
								<th>小计</th>
								<th>操作</th>
							</tr>
						  <c:forEach items="${cart.cartItems}" var="item">
							<tr class="active">
								<td width="60" width="40%">
									<input type="hidden" name="id" value="22">
									<img src="${pageContext.request.contextPath}/${item.product.pimage}" width="90" height="90">
								</td>
								<td width="30%">
									<a target="_blank">${item.product.pname}</a>
								</td>
								<td width="20%">
									￥${item.product.shop_price}
								</td>
								<td width="10%">
									<input type="text" name="quantity" value="${item.num}" maxlength="4" size="10">
								</td>
								<td width="15%">
									<span class="subtotal">￥${item.subTotal}</span>
								</td>
								<td>
									<%-- <a href="javascript:void(0);" class="delete" onclick="delCart(${item.product.pid})">删除</a> --%>
									<input type="hidden" name="pid" value="${item.product.pid}"/>
									<a href="javascript:void(0);" title="${item.product.pid}" class="delete" id="${item.product.pid}">删除</a> 
								</td>
							</tr>
						  </c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div style="margin-right:130px;">
				<div style="text-align:right;">
					<em style="color:#ff6600;">
				登录后确认是否享有优惠&nbsp;&nbsp;
			</em> 赠送积分: <em style="color:#ff6600;">${cart.total}</em>&nbsp; 商品金额: <strong style="color:#ff6600;">￥${cart.total}元</strong>
				</div>
				<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
					<a href="${pageContext.request.contextPath}/CartServlet?method=clearCart" id="clear" class="clear">清空购物车</a>
					
					<a href="${pageContext.request.contextPath}/OrderServlet?method=saveOrder">
						<%--提交表单 --%>
						<input type="button" width="100" value="提交订单" name="submit" border="0" style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
						height:35px;width:100px;color:white;">
					</a>
				</div>
			</div>
				
			
			
			</c:if>

		</div>

		
		<%@ include file="/jsp/footer.jsp" %>
	</body>
	
	<script>
	$(function(){
		//页面加载完毕后获取到class的值为delete元素，为其绑定点击事件
		$(".delete").click(function(){
			if(confirm("确认删除？")){
				//获取到被删除房间的pid
				var pid=this.id;
				window.location.href="/store/CartServlet?method=removeCartItem&id="+pid;
			}
		})
	})
		
	
	</script>

</html>