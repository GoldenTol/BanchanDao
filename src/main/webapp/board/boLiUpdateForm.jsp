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
			var subject = $('#subject').val();
			if(subject.length < 3 || subject.length > 20){
				alert('글 제목은 3글자 이상 20글자 이하이어야 합니다.');
				$('#subject').focus();
				return false;
			}
			
			var content = $('#content').val();
			if(content.length < 5 || content.length > 30){
				alert('글 내용은 5글자 이상 30글자 이하이어야 합니다.');
				$('#content').focus();
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
			<input type="hidden" name="command" value="boUpdate">
			
			<input type="hidden" name="pageNumber" value="<%=request.getParameter("pageNumber")%>">
			<input type="hidden" name="pageSize" value="<%=request.getParameter("pageSize")%>">
			<input type="hidden" name="mode" value="<%=request.getParameter("mode")%>">
			<input type="hidden" name="keyword" value="<%=request.getParameter("keyword")%>">
			
			<input type="hidden" name="readhit" value="<%=request.getParameter("boLiReadhit")%>">
			<input type="hidden" name="groupno" value="<%=request.getParameter("boLiGroupno")%>">
			<input type="hidden" name="orderno" value="<%=request.getParameter("boLiOrderno")%>">
			<input type="hidden" name="depth" value="<%=request.getParameter("boLiDepth")%>">

			<table class="table">
				<thead>
					<tr class="table-light">
						<th>글제목</th>
						<td>
							<input id="subject" name="subject" class="reset1" type="datetime" value="${requestScope.bean.boLiSubject}">
						</td>
						<th>작성일자</th>
						<td>
							<input id="fakeregdate" name="fakeregdate" class="reset1" type="datetime" disabled="disabled" value="${requestScope.regdate}">
							<input id="regdate" name="regdate" class="reset1" type="hidden" value="${requestScope.regdate}">
						</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>
							<input id="fakewriter" name="fakewriter" disabled="disabled"
								type="text" class="form-control" placeholder=""
								value="${requestScope.bean.boLiWriter}"> 
							<input id="writer" name="writer"
								type="hidden" value="${requestScope.bean.boLiWriter}">
							<input id="no" name="no" type="hidden" value="${requestScope.bean.boLiNo}">
						</td>
					</tr>
				</thead>
			</table>
			<table class="table">
				<tbody>
					<tr>
						<td>
							<textarea class="form-control reset1" id="content" name="content" rows="5" style="width: 100%;" placeholder="">${requestScope.bean.boLiContent}</textarea>
						</td>
					</tr>
				</tbody>
			</table>
			<table class="table table-borderless">
				<tr>
					<td align="right">
						<button type="submit" class="btn btn-primary" onclick="return validCheck();">수정</button>
						<button type="button" name="resetButton" id="resetButton" class="btn btn-secondary">초기화</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>