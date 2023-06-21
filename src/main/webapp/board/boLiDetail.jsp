<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/Nav.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>${bean.boLiSubject}</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<style type="text/css">
		#backButton{margin:auto; display: block;}
	</style>
</head>
<body>
	<br/>
	<br/>
	<br/>
	<div class="container mt-3">
		<h2>게시물 정보</h2>
		<table class="table">
			<thead>
				<tr class="table-light">
					<th>글제목</th>
					<td>${bean.boLiSubject}</td>
					<th>작성일자</th>
					<td>${bean.boLiRegdate}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${bean.boLiWriter}</td>
					<th>조회수</th>
					<td>${bean.boLiReadhit}</td>
				</tr>
			</thead>
		</table>
		<table class="table">
			<tbody>
				<tr>
					<td>${bean.boLiContent}</td>
				</tr>
			</tbody>
		</table>
		<div>
			<button id="backButton" type="button" class="btn btn-primary"
				onclick="history.back();">돌아가기</button>
		</div>
	</div>
</body>
</html>
