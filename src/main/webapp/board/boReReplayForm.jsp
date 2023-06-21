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
		.boReNo{
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
	<div>
		<h2>${requestScope.boReNo}번 글에 대한 답글 등록</h2>
		<form action="<%=withFormTag%>" method="post">
			<c:set var="userInfo" value="${sessionScope.loginfo.name}(${sessionScope.loginfo.id})"/>
			
			<input type="hidden" name="command" value="boReReply">
			
			<input type="hidden" name="pageNumber" value="<%=request.getParameter("pageNumber")%>">
			<input type="hidden" name="pageSize" value="<%=request.getParameter("pageSize")%>">
			<input type="hidden" name="mode" value="<%=request.getParameter("mode")%>">
			<input type="hidden" name="keyword" value="<%=request.getParameter("keyword")%>">
			
			<input type="hidden" name="boReReadhit" value="<%=request.getParameter("boReReadhit")%>">
			<input type="hidden" name="boReGroupno" value="<%=request.getParameter("boReGroupno")%>">
			<input type="hidden" name="boReOrderno" value="<%=request.getParameter("boReOrderno")%>">
			<input type="hidden" name="boReDepth" value="<%=request.getParameter("boReDepth")%>">
			
			<div class="input-group boReNo">
				<span class="input-group-text col-md-2">글 번호</span> 
				<input id="boReNo" name="boReNo" type="number" class="form-control" placeholder="">
			</div>
			
			<div class="input-group">
				<span class="input-group-text col-md-2">작성자</span> 
				<input id="fakeboReWriter" name="fakeboReWriter" type="text" disabled="disabled" class="form-control" value="${userInfo}" placeholder="">
				<input id="boReWriter" name="boReWriter" type="hidden" class="form-control" value = "${sessionScope.loginfo.id}" placeholder="">
			</div>
			
			<div class="input-group">
				<span class="input-group-text col-md-2">제목</span> 
				<input id="boReSubject" name="boReSubject" type="text" class="form-control" placeholder="">
			</div>
			
			<div class="input-group" style="display: table;">
				<span class="input-group-text col-md-2" style="display: table-cell; vertical-align: middle;">내용</span> 
				<textarea class="form-control" id="boReContent" name="boReContent" rows="5" style="width: 100%;" placeholder=""></textarea>
			</div>
			
			<div class="input-group">
				<c:set var="boReRegdate" value="${requestScope.boReRegdate}"/>
				<span class="input-group-text col-md-2">작성 일자</span> 
				<input id="fakeboReRegdate" name="fakeboReRegdate" type="datetime" disabled="disabled" class="form-control" 
				value="${boReRegdate}" placeholder="">
				<input id="boReRegdate" name="boReRegdate" type="hidden" class="form-control" 
				value="${boReRegdate}" placeholder="">
			</div>
			
			<div id="buttonset" class="input-group">
				<div class="btn-group">
					<button type="submit" class="btn btn-success btn-lg"
  		  				onclick="return validCheck();">답글 작성</button>
				</div>
				&nbsp;&nbsp;
				<div class="btn-group">
					<button type="reset" class="btn btn-danger btn-lg">초기화</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>