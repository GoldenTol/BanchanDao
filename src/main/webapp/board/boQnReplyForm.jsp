<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/Nav.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
	.input-group{margin: 7px;}
	
	.input-group-text{
		display:block;
		margin-left:auto;
		margin-right:auto;
	}
	
	#buttonset{margin-top:15px;}
	#boQnNo{display : none; visibility : hidden;}
	
	</style>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  		<link rel="stylesheet" href="/resources/demos/style.css">
 		
 		<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
 		<script type="text/javascript">
 			$(document).ready(function(){
 				$('#boQnRegdate').datepicker({dateFormat:"yy/mm/dd"});
 			});
 			
 			function validCheck(){ /* 폼 유효성 검사 */
 				var boQnSubject = $('#boQnSubject').val();
 				if(boQnSubject.length < 3 || boQnSubject.length > 50){
 					alert('글 제목은 3글자 이상 50글자 이하여야 합니다.');
 					$('#boQnSubject').focus();
 					return false;
 				}
 				
 				var boQnContent = $('#boQnContent').val();
 				if(boQnContent.length < 5 || boQnContent.length > 50){
 					alert('글 내용은 5글자 이상 50글자 이하여야 합니다.');
 					$('#boQnContent').focus();
 					return false;
 				}
 				var boQnRegdate = $('#boQnRegdate').val();
 				
 				var regex = /^\d{4}\/[01]\d{1}\/[0123]\d{1}$/;
 				var result = regex.test(boQnRegdate);
 				
 				if(result == false){
 					alert('날짜 형식은 반드시 yyyy/mm/dd 형식으로 작성해 주세요.');
 					$('#boQnRegdate').focus();
 					return false;
 				}
 				
 				return true;
 			}
 		</script>
</head>
<body>
	<div>
	<br/>
	<br/>
	<br/>
	<h2>게시판</h2>
	<p>답글을 작성하는 페이지</p>
	<form action="<%=withFormTag%>" method="post">
			<input type="hidden" name="command" value="boQnReply">
			
			<input type="text" name="pageNumber" value="<%=request.getParameter("pageNumber")%>">
			<input type="text" name="pageSize" value="<%=request.getParameter("pageSize")%>">
			<input type="text" name="mode" value="<%=request.getParameter("mode")%>">
			<input type="text" name="keyword" value="<%=request.getParameter("keyword")%>">
			
			<input type="text" name="boQnGroupno" value="<%=request.getParameter("boQnGroupno")%>">
			<input type="text" name="boQnOrderno" value="<%=request.getParameter("boQnOrderno")%>">
			<input type="text" name="boQnDepth" value="<%=request.getParameter("boQnDepth")%>">
			
		
		<div class="input-group" id="boQnNo">
  		  <span class="input-group-text col-md-2" >게시글 번호</span>
   		  <input id="boQnNo" name="boQnNo" type="number" class="form-control" placeholder="">
		</div>
		<div class="input-group">
  		  <span class="input-group-text col-md-2">작성자</span>
   		  <input id="boQnWriter" name="fakeboQnWriter" disabled="disabled" type="text" class="form-control" value="${sessionScope.loginfo.name} (${sessionScope.loginfo.id})" placeholder="">
   		  <input type="hidden" name="boQnWriter" value="${sessionScope.loginfo.id}">
		</div>
		<div class="input-group">
  		  <span class="input-group-text col-md-2">글제목</span>
   		  <input id="boQnSubject" name="boQnSubject" type="text" class="form-control" placeholder="">
		</div>
		<div class="input-group">
  		  <span class="input-group-text col-md-2">글내용</span>
   		  <textarea id="boQnContent" name="boQnContent" type="text" class="form-control" placeholder=""rows="10" cols="50"></textarea>
		</div>
		<div class="input-group">
  		  <span class="input-group-text col-md-2">등록 일자</span>
   		  <input id="boQnRegdate" name="boQnRegdate" type="datetime" class="form-control" placeholder="">
		</div>
		<div id="buttonset" class="input-group">
  		  <button type="submit" class="btn btn-success btn-lg"
  		  onclick="return validCheck();">답글 작성</button>
  		  &nbsp;&nbsp;
  		  <button type="reset" class="btn btn-danger btn-lg">초기화</button>
		</div>
	</form>
</body>
</html>