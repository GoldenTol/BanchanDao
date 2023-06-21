<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/Nav.jsp"%>
<!-- 상단 바 연동 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">



<!--상품 페이지 상단 이미지 코딩 -->
<style>
.main_image {
	position: relative;
}

.main_image_text {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	color: white;
}

/*상품 페이지 상단 상품 카테고리 버튼 코딩*/
.find-btn1 {
	font-size: 17px;
	font-weight: 600;
	border-color: black;
	margin-top: 60px;
	margin-left: 50px;
	width: 250px;
	height: 50px;
}
/*상품 카테고리 클릭시 효과.*/
#btn1 {
	background-color: white;
}

#btn2 {
	background-color: FF0000;
}

#btn3 {
	background-color: #e7e7e7;
}

#btn4 {
	background-color: #e7e7e7;
}

/*상품 리스트 카드 설정*/
/* 카드 전체를 감싸는 클래스 */
.section {
	padding: -30px 0;
}
/*카드 외곽 설정 조정.*/
.card {
	margin-left: 25px;
}
/*상품 수정 버튼.*/
.find-btn5 {
	margin-left: 90px;
	width: 150px;
	height: 40px;
	text-align: center;
}
</style>

</head>
<body>
	<!--상품 페이지 상단 이미지 코딩 -->
	<div class="main_image col-lg-12" style="text-align: center">
		<img src="<%=imagefiles%>/image/01_2.jpg" width="70%" height="400px">
		<h1 class="main_image_text" style="font-size: 50px">메뉴 소개</h1>
	</div>

<!--상품 페이지 상단 버튼 코딩 -->
	<div class="find-btn col-lg-12" style="text-align: center">
		<button type="button" name="btn1"
			class="btn btn-grey navbar-btn find-btn1" onclick="location.href='#'">전체</button>
		<button type="button" name="btn2"
			class="btn btn-grey navbar-btn find-btn1" onclick="location.href='#'">추천
			메뉴</button>
		<button type="button" name="btn3"
			class="btn btn-grey navbar-btn find-btn1" onclick="location.href='#'">리뷰
			순위</button>
		<button type="button" name="btn4"
			class="btn btn-grey navbar-btn find-btn1" onclick="location.href='#'">찜</button>
		<button type="button" name="btn5"
			class="btn btn-grey navbar-btn find-btn1" onclick="location.href='#'">탕</button>
		<button type="button" name="btn6"
			class="btn btn-grey navbar-btn find-btn1" onclick="location.href='#'">볶음</button>
	</div>
	<!-- 상품리스트 시작 코딩-->
	<section class="card__wrap Score section">
		<c:set var="colsu"
			value="${applicationScope.map['product_column_size']}"></c:set>
		<c:forEach var="bean" items="${datalist}" varStatus="status">
			<c:if test="${status.index mod colsu==0}">
				<div class="container py-5">
					<!-- 행 변경할때 같이 복사해야함. -->
					<div class="row">
			</c:if>
			<div class="col-12 col-md-4 mb-4">
				<div class="card h-100">
					<a href="<%=notWithFormTag%>prDetail&num=${bean.num}"> <!-- 이미지 사이즈는 800x800 -->
						<img src="<%=imagefiles%>/image/${bean.image01}"
						class="card-img-top" alt="...">
					</a>
					<div class="card-body">
						<%-- 리뷰와 연동할 부분. --%>
						<c:set var="avg" value="${bean.avg}"></c:set>
						<div class="star-ratings" style="font-size: 50px;">
							<div class="star-ratings-fill space-x-2 text-lg"
								style="width: ${avg}%" style="font-size: 50px;" >
								<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
							</div>
							<div class="star-ratings-base space-x-2 text-lg">
								<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
							</div>
						</div>
						<a class="h2 text-decoration-none text-dark">${bean.name}</a>
						
						<p class="card-text">
							
							<fmt:formatNumber value="${bean.price}" pattern="###,###" />
							원
						</p>
						<p class="card-text">적립 포인트: ${bean.point}</p>
						<c:if test="${whologin==2}">
							<a class="btn btn-primary"
							href="<%=notWithFormTag%>prUpdate&num=${bean.num}
									${requestScope.PageInfo.flowParameter}">상품
							수정</a>
							<a id="deleteAnchor" class="btn btn-primary" href="<%=notWithFormTag%>prDelete&num=${bean.num}${requestScope.pageInfo.flowParameter}">
		    									삭제
		    								</a>	
							</c:if>
					</div>
				</div>
			</div>
		<c:if test="${status.index mod colsu==colsu-1}">
			</div>
			</div>
		</c:if>
	</c:forEach>
</section>
	${requestScope.PageInfo.pagingHtml}
</body>
<%@ include file="./../common/bottom Nav.jsp"%>
</html>