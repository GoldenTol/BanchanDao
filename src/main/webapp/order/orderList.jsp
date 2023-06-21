<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/Nav.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br/>
	<br/>
	<br/>
	<div class="container mt-3">
		<h2>주문 내역</h2>
		<br/>
		<form action="<%=withFormTag%>" method="post">
			<table class="w3-table w3-card-4 w3-centered w3-bordered w3-hoverable">
				<thead>
					<tr class="table-active w3-dark-grey">
						<th>주문 번호</th>
						<th>주문 일시</th>
						<th>주문명</th>
						<th>주문 가격</th>
					</tr>
				</thead>
				<tbody> 
					<c:forEach var="bean" items="${orderList}">
						<a href="<%=notWithFormTag%>orderDetail&oid=${bean.oid}">
							<tr>
								<td>${bean.oid}</td>
								<td>${bean.orderDate}</td>
								<td>${bean.orderName}</td>
								<td>${bean.orderAllPrice}</td>
							</tr>
						</a>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>