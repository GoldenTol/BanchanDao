<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/Nav.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>Bootstrap Example</title>
	<meta charset="utf-8">
	<style type="text/css">
		.rounded-pill {
			opacity: 0.8;
		}
		
		#noUnderLine {
			text-decoration-line: none;
		}
		
		th, td {
			text-align: center;
		}
		
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
	<script type="text/javascript">
		$(document).ready(function() {

		});

		function searchAll() {
			location.href = "<%=notWithFormTag%>boList";
		}
		
		function writeForm(){
			location.href = "<%=notWithFormTag%>boInsert";		
		}
	</script>
</head>
<body>
	<br/>
	<br/>
	<div class="container mt-3">
		<h2><b>1 : 1 게시판</b></h2>
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
					<th class="w3-center">답글</th>
				</tr>
			</thead>
			<tbody>
				<!-- <tr>
					<td colspan="7" align="center">
						
					</td>
				</tr> -->			
				<c:forEach var="bean" items="${lists}"> 
					<tr>
						<td class="w3-center">${bean.boLiNo}</td>
						<td class="w3-center">${bean.boLiWriter}</td>
						<td>
							<a id="noUnderLine" href="<%=notWithFormTag%>boDetail&no=${bean.boLiNo}">
								<c:forEach begin="1" end="${bean.boLiDepth}">
									<span class="badge rounded-pill bg-secondary">re</span>
								</c:forEach>
								${bean.boLiSubject}	
							</a>
						</td>
						<td class="w3-center">${bean.boLiRegdate}</td>
						<td class="w3-center">
							<c:if test="${bean.boLiReadhit >= 50}">
								<span class="badge rounded-pill bg-primary">${bean.boLiReadhit}</span>
							</c:if>												
							<c:if test="${bean.boLiReadhit < 50}">
								<span class="badge rounded-pill bg-danger">${bean.boLiReadhit}</span>
							</c:if>
						</td>
						<td class="w3-center">
							<c:if test="${sessionScope.loginfo.id == bean.boLiWriter}">
								<a href="<%=notWithFormTag%>boUpdate&no=${bean.boLiNo}${requestScope.pageInfo.flowParameter}"
								style="text-decoration:none;">
									수정
								</a>
							</c:if>
						</td>
						<td class="w3-center">
							<c:if test="${sessionScope.loginfo.id == bean.boLiWriter}">
								<a id="deleteA" href="<%=notWithFormTag%>boDelete&no=${bean.boLiNo}${requestScope.pageInfo.flowParameter}"
								style="text-decoration:none;">
									X
								</a>
							</c:if>
						</td>
						<td class="w3-center">
							<c:if test="${whologin == 2}">
								<c:set var="replyInfo" value="&groupno=${bean.boLiGroupno}&orderno=${bean.boLiOrderno}&depth=${bean.boLiDepth}"/>
								<a href="<%=notWithFormTag%>boLiReply&no=${bean.boLiNo}${requestScope.pageInfo.flowParameter}${replyInfo}"
								style="text-decoration:none;">
									답글
								</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>						 
			</tbody>
		</table>
		<br/>
		<div class="w3-bar">
		  <c:if test="${whologin != 0}">
		  	<button class="w3-button w3-right w3-dark-grey w3-small" onclick="writeForm();"><b>글쓰기</b></button>
		  </c:if>
		</div>
		<br/>
		${requestScope.pageInfo.pagingHtml}
		<br/>
		<br/>
		<br/>
	</div>
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
