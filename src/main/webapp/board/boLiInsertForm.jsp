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
		#cke_1_path{
			display: none;
		}
	</style>
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	<script src="//cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>
	<script src="https://kit.fontawesome.com/yourcode.js" crossorigin="anonymous"></script>
	
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
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<i class='fas fa-edit' style="float:left; margin-right: 5px;"></i>
			<h2>1대1 게시물 등록</h2>
			<br/>
			<form action="<%=withFormTag%>" method="post">
				<c:set var="userInfo" value="${sessionScope.loginfo.name}(${sessionScope.loginfo.id})"/>
				
				<input type="hidden" name="name" value="${sessionScope.loginfo.name}">
				<input type="hidden" name="command" value="boInsert">
				<input id="no" name="no" type="hidden" class="form-control" placeholder="">
				
				<table class="w3-table w3-border">
					<tr>
						<td>작성자</td>
						<td>
							<input id="fakewriter" name="fakewriter" type="text" disabled="disabled" 
							class="form-control" value="${userInfo}" placeholder="">
							<input id="writer" name="writer" type="hidden" 
							class="form-control" value = "${sessionScope.loginfo.id}" placeholder="">
						</td>
					</tr>
					<tr>
						<td>제목</td>
						<td>
							<input id="subject" name="subject" type="text" class="form-control" 
								placeholder="">
						</td>
					</tr>
					<tr>
						<td>글내용</td>
						<td>
							<textarea class="form-control" id="content" name="content" rows="10" cols="50">
							</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<c:set var="regdate" value="${requestScope.regdate}"/>
							<input id="regdate" name="regdate" type="hidden" class="form-control" 
								value="${regdate}" placeholder="">
							<input type="submit"
								value="글쓰기" class="btn btn-success" onclick="return validCheck();"> 
							<input type="reset"
								value="다시작성" class="btn btn-warning">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<script>
		//CKEDITOR 적용 
		CKEDITOR.replace('content', {
		    width:'100%',
		    height:'350'
		});
	</script>
</body>
</html>