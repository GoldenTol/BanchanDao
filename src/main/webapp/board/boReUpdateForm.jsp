<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/Nav.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시물 수정</title>
	<style type="text/css">
		/* box model */
	</style>
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('#resetButton').click(function(){
				$('#fboard').find('.reset1').each(function(i){
					$(this).val('');
				});
			});
		});
		
		function validCheck(){
			var boReSubject = $('#boReSubject').val();
			if(boReSubject.length < 3 || boReSubject.length > 20){
				alert('글 제목은 3글자 이상 20글자 이하이어야 합니다.');
				$('#boReSubject').focus();
				return false;
			}
			
			var boReContent = $('#boReContent').val();
			if(boReContent.length < 5 || boReContent.length > 30){
				alert('글 내용은 5글자 이상 30글자 이하이어야 합니다.');
				$('#boReContent').focus();
				return false;
			}
			
			return true;
		}
		
	</script>
</head>
<body>
	<br/>
	<br/>
	<br/>
	<div class="container mt-3">
		<h2>게시물 수정</h2>
		<p>사용자가 게시물을 수정하는 페이지</p>
		<form name="fboard" id="fboard" action="<%=withFormTag%>" method="post">
			<input type="hidden" name="command" value="boReUpdate">
			
			<input type="hidden" name="pageNumber" value="<%=request.getParameter("pageNumber")%>">
			<input type="hidden" name="pageSize" value="<%=request.getParameter("pageSize")%>">
			<input type="hidden" name="mode" value="<%=request.getParameter("mode")%>">
			<input type="hidden" name="keyword" value="<%=request.getParameter("keyword")%>">
			
			<input type="hidden" name="boReReadhit" value="<%=request.getParameter("boReReadhit")%>">
			<input type="hidden" name="boReGroupno" value="<%=request.getParameter("boReGroupno")%>">
			<input type="hidden" name="boReOrderno" value="<%=request.getParameter("boReOrderno")%>">
			<input type="hidden" name="boReDepth" value="<%=request.getParameter("boReDepth")%>">

			<table class="table">
				<thead>
					<tr class="table-light">
						<th>글제목</th>
						<td>
							<input id="boReSubject" name="boReSubject" class="reset1" type="datetime" value="${requestScope.bean.boReSubject}">
						</td>
						<th>작성일자</th>
						<td>
							<input id="fakeboReRegdate" name="fakeboReRegdate" class="reset1" type="datetime" disabled="disabled" value="${requestScope.boReRegdate}">
							<input id="boReRegdate" name="boReRegdate" class="reset1" type="hidden" value="${requestScope.boReRegdate}">
						</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>
							<input id="fakeboReWriter" name="fakeboReWriter" disabled="disabled"
								type="text" class="form-control" placeholder=""
								value="${requestScope.bean.boReWriter}"> 
							<input id="boReWriter" name="boReWriter"
								type="hidden" value="${requestScope.bean.boReWriter}">
							<input id="boReNo" name="boReNo" type="hidden" value="${requestScope.bean.boReNo}">
						</td>
					</tr>
				</thead>
			</table>
			<table class="table">
				<tbody>
					<tr>
						<td>
							<textarea class="form-control reset1" id="boReContent" name="boReContent" rows="5" style="width: 100%;" placeholder="">${requestScope.bean.boReContent}</textarea>
						</td>
					</tr>
				</tbody>
			</table>
			<table class="table table-borderless">
				<tr>
					<td align="right">
						<button type="submit" class="btn btn-primary btn-lg" onclick="return validCheck();">수정</button>
						<button type="button" name="resetButton" id="resetButton" class="btn btn-secondary btn-lg">초기화</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>