<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/Nav.jsp"%>
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
		<h2><b>회원 목록</b></h2>
		<div align="right">
			${requestScope.pageInfo.pagingStatus}
		</div>
		&nbsp;
		<table class="w3-table w3-bordered w3-card-4">
			<thead>
				<tr class="w3-blue-grey">
					<th class="w3-center">아이디</th>
					<th class="w3-center">이름</th>
					<th class="w3-center">가입 일자</th>
					<th class="w3-center">적립 포인트</th>
					<th class="w3-center">비고</th>
				</tr>
			</thead>
			<tbody>
		    	<c:forEach var="bean" items="${datalist}">
		    		<tr>
		        		<td class="w3-center" align="center">${bean.id}</td>
		       			<td class="w3-center">${bean.name}</td>
		       	 		<td class="w3-center">${bean.regdate}</td>
		        		<td class="w3-center">${bean.mpoint}</td>
		        		<td class="w3-center">${bean.remark}</td>
					</tr>
		    	</c:forEach>
    		</tbody>
  		</table>
  		&nbsp;
 	 	${requestScope.pageInfo.pagingHtml}
	</div>
</body>
</html>

