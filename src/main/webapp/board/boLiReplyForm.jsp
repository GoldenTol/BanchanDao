<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/Nav.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시물 등록</title>
	<style type="text/css">
		/* box model */
		.input-group{margin: 7px;}
		
		.input-group-text{
			display:block;
			margin-left:auto;
			margin-right:auto;
		}
		#buttonset{margin-top:15px;}
		.bNo{
			display: none;
			visibility: none;
		}
	</style>
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			
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
	<div class="container mt-3 col-md-5">
		<h2>${requestScope.no}번 글에 대한 답글 등록</h2>
		<form action="<%=withFormTag%>" method="post">
			<c:set var="userInfo" value="${sessionScope.loginfo.name}(${sessionScope.loginfo.id})"/>
			
			<input type="hidden" name="command" value="boLiReply">
			
			<input type="hidden" name="pageNumber" value="<%=request.getParameter("pageNumber")%>">
			<input type="hidden" name="pageSize" value="<%=request.getParameter("pageSize")%>">
			<input type="hidden" name="mode" value="<%=request.getParameter("mode")%>">
			<input type="hidden" name="keyword" value="<%=request.getParameter("keyword")%>">
			
			<input type="hidden" name="readhit" value="<%=request.getParameter("readhit")%>">
			<input type="hidden" name="groupno" value="<%=request.getParameter("groupno")%>">
			<input type="hidden" name="orderno" value="<%=request.getParameter("orderno")%>">
			<input type="hidden" name="depth" value="<%=request.getParameter("depth")%>">
			
			<div class="input-group bNo">
				<span class="input-group-text col-md-3">글 번호</span> 
				<input id="no" name="no" type="number" class="form-control" 
				placeholder="">
			</div>
			
			<div class="input-group">
				<span class="input-group-text col-md-3">작성자</span> 
				<input id="fakewriter" name="fakewriter" type="text" disabled="disabled" class="form-control" value="${userInfo}" placeholder="">
				<input id="writer" name="writer" type="hidden" class="form-control" value = "${sessionScope.loginfo.id}" placeholder="">
			</div>
			
			<div class="input-group">
				<span class="input-group-text col-md-3">제목</span> 
				<input id="subject" name="subject" type="text" class="form-control" 
				placeholder="">
			</div>
			
			<div class="input-group" style="display: table;">
				<span class="input-group-text col-md-3" style="display: table-cell; vertical-align: middle;">내용</span> 
				<textarea class="form-control" id="content" name="content" rows="5" style="width: 100%;" placeholder=""></textarea>
			</div>
			
			<div class="input-group">
				<c:set var="regdate" value="${requestScope.regdate}"/>
				<span class="input-group-text col-md-3">작성 일자</span> 
				<input id="fakeregdate" name="fakeregdate" type="datetime" disabled="disabled" class="form-control" 
				value="${regdate}" placeholder="">
				<input id="regdate" name="regdate" type="hidden" class="form-control" 
				value="${regdate}" placeholder="">
			</div>
			
			<div id="buttonset" class="input-group">
				<div class="btn-group">
					<button type="submit" class="btn btn-primary btn-lg"
					onclick="return validCheck();">답글 작성</button>
				</div>
				&nbsp;&nbsp;
				<div class="btn-group">
					<button type="reset" class="btn btn-secondary btn-lg">초기화</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>