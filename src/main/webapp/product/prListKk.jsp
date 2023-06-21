<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/Nav.jsp"%>
<!-- 상단 바 연동 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="assets/img/apple-icon.png">
<link rel="shortcut icon" type="image/x-icon"
	href="assets/img/favicon.ico">

<!-- Load fonts style after rendering the layout styles -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
<link rel="stylesheet" href="assets/css/fontawesome.min.css">
</head>
<body>
	<!-- 카테고리 시작 코딩-->

	<div class="container py-5">
		<div class="row">

			<div class="col-lg-3">
				<h1 class="h2 pb-4">Categories(조리법)</h1>
				<ul class="list-unstyled templatemo-accordion">
					<li class="pb-3"><a
						class="collapsed d-flex justify-content-between h3 text-decoration-none"
						href="#"> 국,탕 </a></li>
					<li class="pb-3"><a
						class="collapsed d-flex justify-content-between h3 text-decoration-none"
						href="#"> 나물,볶음 </a></li>
					<li class="pb-3"><a
						class="collapsed d-flex justify-content-between h3 text-decoration-none"
						href="#"> 찜 </a></li>
				</ul>
			</div>

			<div class="col-lg-9">
				<div class="row">
					<div class="col-md-6">
						<ul class="list-inline shop-top-menu pb-3 pt-1">
							<li class="list-inline-item"><a
								class="h3 text-dark text-decoration-none mr-3" href="#">국, 탕
							</a></li>

						</ul>
					</div>
					<div class="col-md-6 pb-4">
						<div class="d-flex">
							<select class="form-control">
								<option>Featured</option>
								<option>A to Z</option>
								<option>Item</option>
							</select>
						</div>
					</div>
				</div>


				<c:set var="colsu"
					value="${applicationScope.map['product_column_size']}" />

				<c:forEach var="bean" items="${requestScope.datalist}"
					varStatus="status">
					<c:if test="${status.index mod colsu == 0}">


						<div class="row">
					</c:if>

					<div class="col-md-4">
						<div class="card mb-4 product-wap rounded-0">
							<div class="card rounded-0">
								<a href="<%=notWithFormTag%>prDetail&num=${bean.num}"> <img
									class="card-img rounded-0 img-fluid"
									src="<%=contextPath%>/image/${bean.image01}">
									<div
										class="card-img-overlay rounded-0 product-overlay d-flex align-items-center justify-content-center">

									</div>
								</a>
							</div>

							<div class="card-body">
								<a href="<%=notWithFormTag%>prDetail&num=${bean.num}"
									class="h3 text-decoration-none">육개장</a>
								<ul
									class="w-100 list-unstyled d-flex justify-content-between mb-0">

									<li class="pt-2"><span
										class="product-color-dot color-dot-red float-left rounded-circle ml-1"></span>
										<span
										class="product-color-dot color-dot-blue float-left rounded-circle ml-1"></span>
										<span
										class="product-color-dot color-dot-black float-left rounded-circle ml-1"></span>
										<span
										class="product-color-dot color-dot-light float-left rounded-circle ml-1"></span>
										<span
										class="product-color-dot color-dot-green float-left rounded-circle ml-1"></span>
									</li>
								</ul>
								<ul class="list-unstyled d-flex justify-content-center mb-1">
									<li><i class="text-warning fa fa-star"></i> <i
										class="text-warning fa fa-star"></i> <i
										class="text-warning fa fa-star"></i> <i
										class="text-muted fa fa-star"></i> <i
										class="text-muted fa fa-star"></i></li>
								</ul>
								<p class="text-center mb-0">$250.00</p>
							</div>

						</div>
				</c:forEach>
		</div>


	</div>


	</div>

	<!-- End Content -->


	<!-- Start Script -->
	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/templatemo.js"></script>
	<script src="assets/js/custom.js"></script>
	<!-- End Script -->
</body>
</html>