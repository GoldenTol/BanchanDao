<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%
	//imagefiles 이미지 파일 경로 설정.
	String imagefiles = request.getContextPath() + "";
	
	//css 파일 경로 설정.
	String cssfiles = request.getContextPath() + "/common/assets/css";
	
	String contextPath = request.getContextPath();
	String urlPatterns = "/bMall"; // in FrontController.java

	// form 태그일 때
	String withFormTag = contextPath + urlPatterns;
	
	// form 태그가 아닐 때
	String notWithFormTag = contextPath + urlPatterns + "?command=";
	
	//out.print("contextPath=" + contextPath + "<br/>");
	//out.print("notWithFormTag=" + notWithFormTag);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="whologin" value="0" />
<c:if test="${not empty sessionScope.loginfo}">
	<c:if test="${sessionScope.loginfo.id=='admin'}">
		<c:set var="whologin" value="2"/>
	</c:if>
	<c:if test="${sessionScope.loginfo.id!='admin'}">
		<c:set var="whologin" value="1"/>
	</c:if>
</c:if>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="<%=cssfiles%>/bootstrap.min.css">
	<link rel="stylesheet" href="<%=cssfiles%>/templatemo.css">
	<link rel="stylesheet" href="<%=cssfiles%>/custom.css">
	<link rel="stylesheet"
		href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
	<link rel="stylesheet" href="<%=cssfiles%>/fontawesome.min.css">
	<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<link rel="stylesheet" href="<%=cssfiles%>/fontawesome.min.css">
	
	<!--리뷰의 별점 css 코딩 파일. -->
	<link rel="stylesheet" href="<%=cssfiles%>/star.css">
</head>
<!-- 상단 내비게이션 바 -->
<body class="pt-5">
	<nav class="navbar navbar-expand-lg navbar-light fixed-top" >
		<div class="container-fluid">
			<a class="navbar-brand" href="<%=contextPath%>home">
      			<img src="<%=contextPath%>/image/free-icon-instagram-185985.png" alt="" width="50" height="50">
	    	</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
				data-bs-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			
			<!-- 드롭 다운 설정 -->
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav">
					<li class="nav-item">
						<c:if test="${whologin == 0}">
							<a class="nav-link" href="">미로그인</a>
						</c:if>
						<c:if test="${whologin != 0}">
							<a class="nav-link" href="">
								${sessionScope.loginfo.id}님
							</a>
						</c:if>
					</li>
					
					<!-- 회원 section -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">회원</a>
						<ul class="dropdown-menu">
							<c:if test="${whologin == 0}">
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meInsert">회원가입</a></li>
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meLogin">로그인</a></li>
							</c:if>
							<c:if test="${whologin != 0}">
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meLogout">로그아웃</a></li>
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meDetail">마이페이지</a></li>
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meDelete&id=${sessionScope.loginfo.id}">회원탈퇴</a></li>
							</c:if>	
							<c:if test="${whologin == 2}">
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meList">회원목록 보기</a></li>
							</c:if>
						</ul>
					</li>
					
					<!-- 게시물 section -->
					<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">고객센터</a>
						<ul class="dropdown-menu">
							<c:if test="${whologin == 0}">
								<li><a class="dropdown-item" href="<%=notWithFormTag%>boQnList">F&A</a></li>
							</c:if>
							<c:if test="${whologin != 0}">
								<li><a class="dropdown-item" href="<%=notWithFormTag%>boQnList">F&A</a></li>
								<li><a class="dropdown-item" href="<%=notWithFormTag%>boList">1:1 게시판</a></li>
								<li><a class="dropdown-item" href="<%=notWithFormTag%>boReList">리뷰 게시판</a></li>
							</c:if>
							
						</ul>
					</li>
					
					<!-- 상품 section -->
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown">상품</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">베스트</a></li>
							<li><a class="dropdown-item" href="#">추천</a></li>
							<li><a class="dropdown-item" href="#">할인</a></li>
							<li><a class="dropdown-item"
								href="<%=notWithFormTag%>prListAll">메뉴 소개</a></li>
							<c:if test="${whologin == 2}">
								<li><a class="dropdown-item"
									href="<%=notWithFormTag%>prInsert">상품 등록</a></li>
							</c:if>
						</ul>
					</li>
				</ul>
				<nav class="navbar fixed-bottom navbar-light "></nav>
			</div>
		</div>
		<div class="container-fluid justify-content-around">
			<a class="navbar-brand"></a> <a class="navbar-brand "></a>
		</div>
	</nav>

	<c:if test="${not empty sessionScope.alertMsg}">
		<div class="alert alert-danger alert-dismissible">
		  <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
		  ${sessionScope.alertMsg}
		</div>
	</c:if>
	<%-- session 영역의 변수 alertMsg를 빈 문자열로 셋팅합니다.--%>
	<c:set var="alertMsg" value="" scope="session"/>
	
	<%-- session 영역의 변수 alertMsg를 제거합니다.--%>
	<c:remove var="alertMsg" scope="session" />
</body>
</html>