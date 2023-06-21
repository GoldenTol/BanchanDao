<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/Nav.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <script type="text/javascript">
	$(document).ready(function(){
      //Initialize popover
		var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
        var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
        return new bootstrap.Popover(popoverTriggerEl)
   		});
    });
   </script>
  
  <style type="text/css">
		#backButton {
			margin: auto;
		}
		
		.card {
			margin-left: auto;
			margin-right: auto;
		}
		
		.card-img-top {
			width: 300px;
			height: 300px;
		}
		
		.btn {
			padding: 5px;
		}
		
		body {
			padding: 0;
			margin: 0;
		}
		
		div {
			box-sizing: border-box;
		}
		
		.circle {
			display: inline-block;
			width: 5px;
			height: 5px;
			border-radius: 2.5px;
			background-color: #ff0000;
			position: absolute;
			top: -5px;
			left: 110%;
		}
		
		.green {
			color: #24855b;
		}
		
		.wrap {
			background-color: #F8F8F8;
		}
		.greenContainer {
			height: 132px;
			display: flex;
			align-items: flex-end;
			padding: 16px;
		}
		
		.greenContainer .name {
			font-size: 20px;
			font-weight: bold;
			color: #ffffff;
		}
		
		.greenContainer .modify {
			margin-left: auto;
		}
		
		.summaryContainer {
			background-color: white;
			display: flex;
			padding: 21px 16px;
			height: 90px;
			margin-bottom: 10px;
		}
		.summaryContainer .item {
			flex-grow: 1
		}
		.summaryContainer .number {
			font-size: 19px;
			font-weight: bold;
			color: #24855b;
		}
		.summaryContainer .item>div:nth-child(2) {
			font-size: 13px;
		}
		
		.shippingStatusContainer {
			padding: 21px 16px;
			background-color: white;
			margin-bottom: 10px;
		}
		
		.shippingStatusContainer .title {
			font-size: 16px;
			font-weight: bold;
			margin-bottom: 15px;
		}
		
		.shippingStatusContainer .status {
			display: flex;
			justify-content: space-between;
			margin-bottom: 21px;
		}
		.shippingStatusContainer .item {
			display: flex;
		}
		
		.shippingStatusContainer .number {
			font-size: 31px;
			font-weight: 500;
			text-align: center;
		}
		
		.shippingStatusContainer .text {
			font-size: 12px;
			font-weight: normal;
			color: #c2c2c2;
			text-align: center;
		}
		
		.shippingStatusContainer .icon {
			display: flex;
			align-items: center;
			padding: 20px;
			width: 16px;
			height: 16px;
		}
		
		.listContainer {
			padding: 0;
			background-color: #ffffff;
			margin-bottom: 10px;
		}
		
		.listContainer .item {
			display: flex;
			align-items: center;
			padding: 16px;
			color: black;
			text-decoration: none;
			height: 56px;
			box-sizing: border-box;
		}
		
		.listContainer .icon {
			margin-right: 14px;
		}
		
		.listContainer .text {
			font-size: 16px;
			position: relative;
		}
		
		.listContainer .right {
			margin-left: auto;
		}
		
		.listContainer .smallLight {
			font-size: 14px;
			color: #c2c2c2;
		}
		
		.listContainer .smallLight>span {
			margin-left: 10px;
		}
		
		.listContainer .right .blct {
			font-size: 14px;
			font-weight: bold;
			margin-right: 5px;
		}
		
		.infoContainer {
			background-color: white;
			display: flex;
			height: 100px;
			margin-bottom: 10px;
		}
		
		.infoContainer .item {
			flex-grow: 1;
			display: flex;
			align-items: center;
			justify-content: center;
			flex-direction: column;
			font-size: 13px;
			text-decoration: none;
			color: black;
		}
		
		.infoContainer .item>div:first-child {
			margin-bottom: 2px;
		}
		
		.listContainer .item:hover {
			/*   background-color: #f8f8f8; */
			
		}
		
		.infoContainer .item:hover {
			background-color: #bdbbbb;
		}
	</style>
  
</head>
<body>
	<br/>
	<br/>
	<div class="wrap">
		<div class="greenContainer w3-brown">
			<div>
				<div class="name"><span style="font-size: 1.5em;">${bean.name}</span>님</div>
			</div>
			<div class="modify">i</div>
		</div>
		<div class="summaryContainer">
			<div class="item">
				<div class="number">${bean.mpoint}</div>
				<div>포인트</div>
			</div>
		</div>
		<div class="shippingStatusContainer">
			<div class="title">주문/배송조회</div>
			<div class="status">

				<div class="item">
					<div>
						<div class="green number">0</div>
						<div class="text">장바구니</div>
					</div>
					<div class="icon">></div>
				</div>
				<div class="item">
					<div>
						<div class="number">0</div>
						<div class="text">결제완료</div>
					</div>
					<div class="icon">></div>
				</div>
				<div class="item">
					<div>
						<div class="green number">0</div>
						<div class="text">배송중</div>
					</div>
					<div class="icon">></div>
				</div>
				<div class="item">
					<div>
						<div class="green number">0</div>
						<div class="text">구매확정</div>
					</div>
					<div class="icon">></div>
				</div>

			</div>

		</div>
		<div class="listContainer">
			<a href="<%=notWithFormTag%>ctList" class="item">
				<div class="icon">i</div>
				<div class="text">
					장바구니<span class="circle"></span>
				</div>
				<div class="right">></div>
			</a> 
			<a href="<%=notWithFormTag%>orList" class="item">
				<div class="icon">ii</div>
				<div class="text">
					주문목록<span class="circle"></span>
				</div>
				<div class="right">></div>
			</a> 
			<a href="<%=notWithFormTag%>payList" class="item">
				<div class="icon">iii</div>
				<div class="text">
					결제내역<span class="circle"></span>
				</div>
				<div class="right">></div>
			</a>
		</div>
		<div class="infoContainer">
			<a href="<%=notWithFormTag%>boQnList" class="item">
				<div>icon</div>
				<div>고객센터</div>
			</a>
			<a href="<%=notWithFormTag%>boList" class="item">
				<div>icon</div>
				<div>1대1 문의</div>
			</a>
		</div>
	</div>
</body>
</html>
    