<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/Nav.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
		$(document).ready(function(){
		});
		
		function boQnInsertForm(){
			location.href="<%=notWithFormTag%>boQnInsert";
		}
		function searchAll(){
			location.href = '<%=notWithFormTag%>boQnList';
		} 
			
	</script>
	<style type="text/css">
		#noUnderLine{text-decoration-line:none;}
		
		.page-link {
			color: #999;
			background-color: #fafafa;
			border-color: 1px solid #ccc;
		}
		
		.page-item.active .page-link {
			z-index: 1;
			color: #ccc;
			font-weight: bold;
			background-color: #333;
			border-color: #444;
		}
		
		.page-link:focus, .page-link:hover {
			color: #ccc;
			background-color: #222;
			border-color: #444;
		}
	</style>
</head>
<body>
	<br/>
	<br/>
	<div class="container mt-3">
		<h2><b>자주 묻는 질문 ( FAQ )</b></h2>
		<br/>
		<form class="form-inline" role="form" name="myform" action="<%=withFormTag%>" method="get">
			<input type="hidden" name="command" value="boList">
			<div class="w3-row-padding w3-stretch">
			  <div class="w3-col" style="width: 10%">
			    <select class="w3-select w3-bar-item w3-border" id="mode" name="mode">
							<option value="all" selected="selected">--
							<option value="boLiNo">글번호
							<option value="boLiWriter">작성자
							<option value="boLiSubject">글제목
				</select>
			  </div>
			  <div class="w3-col" style="width: 70%">
			    <input class="w3-bar-item w3-border w3-input" type="text" name="keyword" id="keyword" placeholder="키워드를 입력해 주세요.">
			  </div>
			  <div class="w3-col" align="right" style="width: 20%">
			    <button class="w3-bar-item w3-button w3-dark-grey" onclick=""><b>검색</b></button>
			  	<button class="w3-bar-item w3-button w3-dark-grey" onclick="searchAll();"><b>전체 검색</b></button>
			  </div>
			</div>
		</form>
		&nbsp;
		<table class="w3-table w3-bordered">
			<thead>
				<tr class="w3-blue-grey">
					<th class="w3-center">글번호</th>
					<th class="w3-center">작성자</th>
					<th class="w3-center">글제목</th>
					<th class="w3-center">작성 일자</th>
					<th class="w3-center">조회수</th>
					<th class="w3-center">수정</th>
					<th class="w3-center">삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${datalist}">
					<tr>
						<td class="w3-center">${bean.boQnNo}</td>
						<td class="w3-center">관리자</td>
						<td>
							<a id="noUnderLine" href="<%=notWithFormTag%>boQnDetail&boQnNo=${bean.boQnNo}">
								<c:forEach begin="1" end="${bean.boQnDepth}">
									<span class="badge rounded-pill bg-secondary">re</span>
								</c:forEach>
								${bean.boQnSubject}	
							</a>
						</td>
						<td class="w3-center">${bean.boQnRegdate}</td>
						<td class="w3-center">
							<c:if test="${bean.boQnReadhit >= 50}">
								<span class="badge rounded-pill bg-primary">${bean.boQnReadhit}</span>
							</c:if>												
							<c:if test="${bean.boQnReadhit < 50}">
								<span class="badge rounded-pill bg-danger">${bean.boQnReadhit}</span>
							</c:if>
						</td>
						<td class="w3-center">
							<c:if test="${whologin == 2}">
								<a href="<%=notWithFormTag%>boQnUpdate&boQnNo=${bean.boQnNo}${requestScope.pageInfo.flowParameter}"
								style="text-decoration:none;">
									수정
								</a>
							</c:if>
						</td>
						<td class="w3-center">
							<c:if test="${whologin == 2}">
								<a id="deleteA" href="<%=notWithFormTag%>boQnDelete&boQnNo=${bean.boQnNo}${requestScope.pageInfo.flowParameter}"
								style="text-decoration:none;">
									X
								</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br/>
		<div class="w3-bar">
		  <c:if test="${whologin == 2}">
		  	<button class="w3-button w3-right w3-dark-grey w3-small" onclick="boQnInsertForm();"><b>글쓰기</b></button>
		  </c:if>
		</div>
		<br/>
		${requestScope.pageInfo.pagingHtml}
	</div>
	<br /> <br /> <br />
	<script type="text/javascript">
		/* 필드 검색시 입력했던 콤보 박스(mode)의 내용과 키워드(keyword) 입력 상자에 있던 내용은 보존되어야 합니다. */
		$(document).ready(function() {
			var myoptions = $('#mode option');

			for (var i = 0; i < myoptions.length; i++) {
				if (myoptions[i].value == '${requestScope.pageInfo.mode}') {
					myoptions[i].selected = true;
				}
			}
			$('#keyword').val('${requestScope.pageInfo.keyword}');
		});
	</script>
</body>
</html>
