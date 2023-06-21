<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>   
<%@ include file="./../common/Nav.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>Sample Payment</title>
    <!-- jQuery -->
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
    <script>
    	// 스크립트 관련 참고 Url : https://portone.gitbook.io/docs/
        var IMP = window.IMP; 
        IMP.init("imp47342176"); 
      
        var today = new Date();   
        var hours = today.getHours(); // 시
        var minutes = today.getMinutes();  // 분
        var seconds = today.getSeconds();  // 초
        var milliseconds = today.getMilliseconds();
        var makeMerchantUid = hours +  minutes + seconds + milliseconds;
        
        var mid = "IMP"+makeMerchantUid;
        
        function requestPay() {
        	var _prName = $('#prName').val();
        	var prNameList = _prName.split(',');
        	var prName = "";
        	
        	if(prNameList.length > 1){
        		prName = prNameList[0]+' 외 '+(prNameList.length-1)+'건';	
        	}else{
        		prName = prNameList[0];
        	}
        	
        	var price = $('#prPrice').val();
            var prPrice = Number(price);
            
            var mName = "";
            var mAddress = "";
            
            var paiedList = $('#paiedList').val();
            
            IMP.request_pay({
                pg : 'html5_inicis.INIpayTest',
                pay_method: 'card',
                merchant_uid: "IMP"+makeMerchantUid, 
                name : prName, // 상품 이름 배열
                amount : prPrice, // 상품 총 가격
                buyer_email : '', // 구매자 이메일
                buyer_name : mName, // 구매자 이름
                buyer_tel : '', // 구매자 전화번호
                buyer_addr : mAddress, // 구매자 주소
                buyer_postcode : '', // 구매자 우편번호
            }, function (rsp) { // callback
            	console.log(rsp);
                if(rsp.success) {
                	/* var msg = "결제에 성공하였습니다. \n주문 번호: " + rsp.merchant_uid;
                	msg += "\n상품 이름: " + rsp.name;
                	msg += "\n결제 금액: " + rsp.paid_amount;
                	msg += "\n주문자 주소: " + rsp.buyer_addr;
                	msg += "\n결제 방식: " + rsp.pg_provider;
                	msg += "\n결제 상태: " + rsp.status;
                	
                	alert(msg); */
                	
                	$.ajax({
    					url : '<%=notWithFormTag%>payInsert',
    					data : 'prName=' + prName + '&prPrice=' + prPrice + '&paidTime=' + rsp.paid_at,
    					type : 'get',
    					dataType : 'text',
    					success : function(result, status) {
    						location.href = "<%=notWithFormTag%>payResult"; 
    					}
    				});
                }else {
                	var msg = '결제에 실패하였습니다.\n';
                    if(prPrice == 0){
                    	msg += '오류 내용 : 결제 요청 금액이 0원입니다.'
                    }
                    
                    alert(msg);
                }
            });
        } 
    </script>
</head>
<body>
	<br />
	<br />
	<br />
	<div class="container mt-3">
		<h2>장바구니</h2>
		<br/>
		<table class="w3-table w3-card-4 w3-centered w3-bordered">
			<thead align="center">
				<tr class="table-active w3-dark-grey">
					<th>상품 이름</th>
					<th>상품 수량</th>
					<th>상품 단가</th>
					<th>총 가격</th>
					<th>기능</th>
				</tr>
			</thead>
			<tbody align="center">
				<c:set var="priceName" value="" />
				<c:set var="paiedList" value="${orderList}"></c:set>
				<c:forEach var="bean" items="${sessionScope.orderList}" varStatus="status">
					<c:choose>
						<c:when test="${status.last}">
							<c:set var="priceName" value="${priceName}${bean.prName}" />
						</c:when>

						<c:otherwise>
							<c:set var="priceName" value="${priceName}${bean.prName}," />
						</c:otherwise>
					</c:choose>
					<tr>
						<td align="center" valign="middle">
							<img alt="${bean.prName}" width="45" height="45" class="rounded"
							src="<%=contextPath%>">
							${bean.prName}
						</td>
						<td>${bean.prPrice}</td>
						<td>
							${bean.prStock}
							<input type="text" id="edit_${bean.prNum}" name="edit_${bean.prNum}">
							<br/>
							<a href="#" onclick="editQty('${bean.prNum}');">
								수정
							</a>
						</td>
						<td><fmt:formatNumber value="${bean.prPrice*bean.prStock}" type="currency" /></td>
						<td>
							<button class="w3-button w3-red">x</button>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<th class="table-active w3-amber" colspan="3">주문 가격 :</th>
					<td colspan="1">
						<input id="paiedList" name="paiedList" type="hidden" value="${paiedList}"> 
						<input id="prName" name="prName" type="hidden" value="${priceName}"> 
						<input id="prPrice" name="prPrice" type="hidden" value="${bean.prPrice*bean.prStock}">
						<fmt:formatNumber value="${bean.prPrice*bean.prStock}" type="currency" />
					</td>
				</tr>
			</tbody>
		</table>
		<br/>
		<div align="right">
			<button class="btn btn-primary" onclick="requestPay()">결제하기</button>
		</div>
	</div>
</body>
</html>