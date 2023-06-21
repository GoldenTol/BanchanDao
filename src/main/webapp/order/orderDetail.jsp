<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/Nav.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
	
	</style>
</head>
<body>
	<br/>
	<br/>
	<br/>
	<div class="container mt-3">
		<div class="row">
			<div class="col-sm-1"></div>
			<div class="col-sm-10">
				<h2>주문 상세 내역</h2>
				<br />
				<table class="w3-table w3-card-4 w3-centered w3-bordered">
					<thead>
						<tr class="table-active w3-dark-grey">
							<th>주문 번호</th>
							<th>주문자명</th>
							<th>주문 일시</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${order.oid}</td>
							<td>${sessionScope.loginfo.name}</td>
							<td>${order.orderDate}</td>
						</tr>
					</tbody>
				</table>
				
				<br/>
				<h3>주문 내역</h3>
				<table class="w3-table w3-card-4 w3-centered w3-bordered">
					<thead>
						<tr class="table-active w3-dark-grey">
							<th>상품명</th>
							<th>이미지</th>
							<th>상품 단가</th>
							<th>주문 수량</th>
							<th>주문 금액</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="totalAmount" value="0" />

						<c:forEach var="bean" items="${requestScope.lists}">
							<tr align="center" valign="middle">
								<td>${bean.pname}</td>
								<td><img alt="${bean.image01}" width="45" height="45"
									src="<%=contextPath%>/image/${bean.image01}">
								</td>
								<td>
									<fmt:formatNumber value="${bean.price}" pattern="###,###" />원
								</td>
								<td>${bean.qty}개</td>
								<td>
									<c:set var="amount" value="${bean.price*bean.qty}" /> 
									<c:set var="totalAmount" value="${totalAmount+amount}" />
									<fmt:formatNumber value="${bean.price*bean.qty}" pattern="###,###" />원
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="5" class="w3-right-align">주문 금액 합계 : 
								<fmt:formatNumber value="${totalAmount}" pattern="###,###" /> 원&nbsp;&nbsp;
							</td>
						</tr>

						<%-- 운송비 --%>
						<c:set var="shipExpense" value="0" />

						<c:choose>
							<c:when test="${totalAmount >= 100000}">
								<c:set var="shipExpense" value="0" />
							</c:when>
							<c:when test="${totalAmount >= 50000}">
								<c:set var="shipExpense" value="2000" />
							</c:when>
							<c:otherwise>
								<c:set var="shipExpense" value="4000" />
							</c:otherwise>
						</c:choose>

						<tr>
							<td colspan="5" class="w3-right-align">배송비 : 
								<fmt:formatNumber value="${shipExpense}" pattern="###,###" /> 원&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="5" class="w3-right-align">
								<c:set var="finalAmount" value="${totalAmount+shipExpense}" />
								최종 주문 금액 : <fmt:formatNumber value="${finalAmount}" pattern="###,###" /> 원&nbsp;&nbsp;
							</td>
						</tr>
					</tbody>
				</table>

				<br />
				<h3>결제 정보</h3>
				<table class="w3-table w3-card-4 w3-centered w3-bordered">
					<thead>
					</thead>
					<tbody>
						<tr>
							<td style="font-weight: bold;" align="center">주문 번호</td>
							<td align="left">${requestScope.order.oid}</td>
							<td style="font-weight: bold;" align="center">주문 일자</td>
							<td align="left">${requestScope.order.orderdate}</td>
						</tr>
						<tr>
							<td style="font-weight: bold;" align="center">주문 총액</td>
							<td align="left">
								<fmt:formatNumber value="${finalAmount}" pattern="###,###" /> 원
							</td>
							<td style="font-weight: bold;" align="center">할인 금액</td>
							<td align="left">0 원</td>
						</tr>
						<tr>
							<td style="font-weight: bold;" align="center">결제 금액</td>
							<td align="left">
								<fmt:formatNumber value="${finalAmount}" pattern="###,###" /> 원
							</td>
							<td style="font-weight: bold;" align="center">결제 상태</td>
							<td align="left">결제 완료</td>
						</tr>
					</tbody>
				</table>

				<br />
				<h3>배송 정보</h3>
				<table class="w3-table w3-card-4 w3-centered w3-bordered">
					<thead>
					</thead>
					<tbody>
						<tr>
							<td style="font-weight: bold;" align="center" width="20%">받으시는 분</td>
							<td align="left" width="80%">
								${sessionScope.loginfo.name}(${sessionScope.loginfo.id})
								님
							</td>
						</tr>
						<tr>
							<td style="font-weight: bold;" align="center" width="20%">배송지</td>
							<td align="left" width="80%">
								${sessionScope.loginfo.address}
							</td>
						</tr>
						<tr>
							<td style="font-weight: bold;" align="center" width="20%">배송 방법</td>
							<td align="left" width="80%">택배</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="col-sm-1"></div>
		</div>
	</div>
	<br/><br/><br/><br/><br/><br/>
</body>
</html>