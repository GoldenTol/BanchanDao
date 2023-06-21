<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/Nav.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
body {
	position: relative;
}
</style>
</head>
<body data-bs-spy="scroll" data-bs-target=".navbar" data-bs-offset="50">

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark ">
		<div class="container-fluid">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="#section1">Section
						1</a></li>
				<li class="nav-item"><a class="nav-link" href="#section2">Section
						2</a></li>
				<li class="nav-item"><a class="nav-link" href="#section3">Section
						3</a></li>
			</ul>
		</div>
	</nav>

	<div id="section1" class="container-fluid bg-white text-success"
		style="padding: 100px 20px;">
		<img src="./../image/소고기무국.jpg" class="img-thumbnail" alt="${bean.name}" width="500" height="600">
		<p>소고기 무국</p>
		<p> 시원한 맛이 좋은 소고기 무국은 간단하게 만들 수 있고 영양이 풍부해서 
		누구나 좋아하는 요리입니다. 오래 끓일수록 깊은 맛이 나니 약불에 은근하게 끓여주세요.</p>
	</div>

	<div id="section2" class="container-fluid bg-warning"
		style="padding: 100px 20px;">
		<h1>Section 2</h1>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
	</div>

	<div id="section3" class="container-fluid bg-secondary text-white"
		style="padding: 100px 20px;">
		<h1>Section 3</h1>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
		<p>Try to scroll this section and look at the navigation bar while
			scrolling! Try to scroll this section and look at the navigation bar
			while scrolling!</p>
	</div>

</body>
</html>