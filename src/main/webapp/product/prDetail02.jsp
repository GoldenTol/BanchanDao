<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/Nav.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.card {
	margin-left: auto;
	margin-right: auto;
}

.leftside {
	margin-left: 0px;
}

.card_borderless {
	border: 0px;
}

#totalprice {
	color: red;
	font-size: 20px;
	font-weight: bolder;
}

.cart {
	background-color: black;
	border: 1px solid black;
} /* 속성 표기법 */
.rightnow {
	background-color: red;
	border: 1px solid red;
}

#qty {
	margin: -10px;
	border: 0px;
	font-size: 0.7rem;
}

.plus, .minus {
	font-size: 1.1rem;
}
/* 리뷰 펼치기 스타일 시트 */
details {
	/* font: 16px "Open Sans", Calibri, sans-serif;
  width: 620px; */
	
}

details>summary {
	/* padding: 2px 6px;
  width: 15em;
  background-color: #ddd;
  border: none;
  box-shadow: 3px 3px 4px black;
  cursor: pointer;
  list-style: none; */
	
}

details>p {
	/*  border-radius: 0 0 10px 10px;
  background-color: #ddd;
  padding: 2px 6px;
  margin: 0;
  box-shadow: 3px 3px 4px black; */
	
}
/*별점을 표현하기 위한 스타일 태그*/
.star-rating {
	width: 305px;
}

.star-rating, .star-rating span {
	display: inline-block;
	height: 55px;
	overflow: hidden;
	background: url(<%=contextPath%>/common/star/star.png) no-repeat;
}

.star-rating span {
	background-position: left bottom;
	line-height: 0;
	vertical-align: top;
}
</style>

<script type="text/javascript">
		/* var price = 10000 ; */ /* 단가 */
		
		/* const 키워드는 읽기 전용(자바의 final과 동일 개념)*/
		const maxPurchaseSize = 5 ; /* 최대 구매 가능 개수 */
	
		$(document).ready(function(){	 
			
			/* alert('단가 : ${bean.price}원'); */
			
			var price = '${bean.price}' ;
			
			$('#qty').innerWidth($('.minus').innerWidth() - 3) ;
			
			$('#totalprice').text('0') ; /* 최초 시작시 금액 0으로 셋팅 */
			
			
			$('.plus').click(function(){ /* 사용자가 + 버튼을 클릭함 */
				var qty = $('#qty').val();
				if(qty == maxPurchaseSize){
					/* swal('최대 5개까지만 주문이 가능합니다.'); */
					swal('최대 ' + maxPurchaseSize + '개까지만 주문이 가능합니다.'); 
					return ; /* return 구문으로 함수 더이상 실행 않하도록 함*/
				}
				/* Number는 Integer.parseInt()와 동일한 효과 */
				var newQty = Number(qty) + 1;
				if(qty == ''){
					$('#qty').val('1');
				}else{
					$('#qty').val(newQty);
				}
				
				var amount = newQty*price ;				
				$('#totalprice').text(amount.toLocaleString()) ;
			});
			
	  		$('.minus').click(function(){ /* 사용자가 - 버튼을 클릭함 */
	  			var qty = $('#qty').val() ;
	  			if(qty == '0'){
	  				swal('최소 1개 이상 구매 가능합니다.');
	  				return ;
	  			}

	  			var newQty = Number(qty) - 1 ;
	  			if(qty == ''){
	  				$('#qty').val('0');
	  				$('#totalprice').text(0);
	  			}else{
	  				$('#qty').val(newQty);
	  				
	  				var amount = newQty*price ;				
					$('#totalprice').text(amount.toLocaleString()) ;	  				
	  			}
	  		});
			
			/* blur 이벤트는 포커스를 잃어 버릴때 반응합니다. */
			$('#qty').blur(function(){
				var qty = $('#qty').val() ;
				
				/* isNaN() 함수는 숫자가 아니면 true를 반환해 줍니다. */
				if(qty == '' || isNaN(qty) == true){
					swal('0이상 ' + maxPurchaseSize + '이하의 숫자만 가능합니다.') ;
					$('#qty').val('0') ;
					$('#qty').focus();
					return ;
				}
				
				if(isNaN(qty) == false){ /* 숫자 형식으로 입력된 경우 */
					var newQty = Number(qty) ;
					if(newQty < 0 || newQty > maxPurchaseSize){
						swal('0이상 ' + maxPurchaseSize + '이하의 숫자만 가능합니다.') ;
						$('#qty').val('0') ;
						$('#qty').focus();
						return ;	
					}
				}
				
			});
			
			
			$('.cart').click(function(){
				var qty = $('#qty').val() ;
				if(qty < 1 || qty > 5){
					swal('최소 1개 이상 카트에 담을 수 있습니다.') ;
					return ;
				}
				
			});
			
			$('.rightnow').click(function(){
				var qty = $('#qty').val() ;
				if(qty < 1 || qty > 5){
					swal('즉시 구매는 최소 1개 이상 가능합니다.') ;
					return ;
				}
			});			
		});
		function validCheak(){/*폼 유효성 검사*/
  			var subject=$('#subject').val();
  			if(subject.length<3 || subject.length>20){
  				alert('글 제목은 3글자 이상 20글자 이하이어야 합니다.');
  				$('#subject').focus();
  				return false;
  				
  			}
  			var content=$('#comments').val();
  			if(comments.length<5 || comments.length>30){
  				alert('글 내용은 5글자 이상 30글자 이하이어야 합니다.');
  				$('#content').focus();
  				return false;
  				
  			}
  			var grade=$('#grade').val();
  			if(grade<0 || grade>10){
  				alert('평점은 0.0~10.0사이 값으로 넣어주세요.');
  				$('#grade').focus();
  				return false;
  				
  			}
  			
  			return true;
  			
  		}
	</script>
</head>
<body>
	<div class="container mt-3">
		<table class="table table-borderless ">
			<thead>
			</thead>
			<tbody>
				<tr>
					<td class="col-lg-6">
						<div class="card" style="width: 40rem;">
							<img src="<%=imagefiles%>/${bean.category}/${bean.image01}"
								class="card-img-top active_image zoom" alt="${bean.name}">
						</div>
					</td>
					<td class="col-lg-1"></td>
					<td class="col-lg-5">
						<div class="card leftside card_borderless" style="width: 40rem;">
							<h1 style="font-size: 24px; font-weight: 600;">${bean.name}</h1>

							<p class="card-text">
								판매가 :
								<fmt:formatNumber value="${bean.price}" pattern="###,###" />
								원
							</p>

							<p class="card-text">원재료 명/ 함량: ${bean.comments}</p>

							<p class="card-text">
								<fmt:formatNumber value="${bean.gram}" pattern="###,###" />
								g
							</p>

							<p class="card-text">
								칼로리:
								<fmt:formatNumber value="${bean.kcal}" pattern="###,###" />
								kcal
							</p>

							<p class="card-text">
								합계 : <span id="totalprice">0</span>원
							</p>

							<ul class="pagination">
								<li class="page-item"><a class="page-link minus" href="#">
										- </a></li>
								<li class="page-item"><a class="page-link" href="#"> <input
										type="text" id="qty" value="0">
								</a></li>
								<li class="page-item"><a class="page-link plus" href="#">
										+ </a></li>
							</ul>


							<div class="btn-group">
								<button type="button" class="btn btn-primary cart">장바구니</button>
								<button type="button" class="btn btn-primary rightnow">바로
									구매</button>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<%-- 리뷰 작성 위치. --%>
	<hr
		style="width: 70%; height: 5px; border: none; background-color: blue; margin: auto;">
	<div class="container mt-3">
		<h2 class="h2">리뷰 작성</h2>
		<table class="table">
			<thead>
			</thead>
			<tbody>
				<form action="<%=withFormTag%>" method="post">
					<input type="hidden" name="command" value="reInsert">
					<input type="hidden" name="num" value="${bean.num}">
					<input type="hidden" name="category" value="${bean.category}">
					<tr>
						<td class="col-lg-2" valign="middle" align="right">
							제목
						</td>
						<td class="col-lg-8" valign="middle">
							<div class="card leftside card_borderless" style="width: 15cm;">
								<input type="text" name="subject" id="subject">
							</div>
						</td>
						<td class="col-lg-2"></td>
					</tr>
					<tr>
						<td class="col-lg-2" align="right">
							댓글
						</td>
						<td class="col-lg-8">
							<div class="card leftside card_borderless" style="width: 15cm;">
								<textarea name="comments" rows="3" cols="50" id="comments"></textarea>
							</div>
						</td>
						<td class="col-lg-2"></td>
					</tr>
					<tr>
						<td class="col-lg-2" align="right" valign="middle">
							이미지 
						</td>
						<td class="col-lg-8">
								<input type="file" name="image01" id="image01">
						</td>
						<td class="col-lg-2"></td>
					</tr>
					<tr>
						<td class="col-lg-2" align="right" valign="middle">
							평점
						</td>
						<td class="col-lg-8">
							<input type="text" name="grade" id="grade" placeholder="0~10 사이값 넣어주세요.">
						</td>
						<td class="col-lg-2">
							<div class="card leftside card_borderless" style="width: 5cm;">
								<button type="submit" class="btn btn-info" onclick="return validCheak();">저장하기</button>
							</div>
						</td>
					</tr>
				</form>
			</tbody>
		</table>
	</div>
	<br>
	<%-- 상품 리뷰 항목. --%>
	<hr
		style="width: 70%; height: 5px; border: none; background-color: blue; margin: auto;">
	<div class="container mt-3">
		<details style="">
			<summary style="text-align: center;">
				<h2 class="h2">Review 펼쳐보기</h2>
			</summary>
			<c:forEach var="bean" items="${lists}" varStatus="status">
				<table class="table table-borderless ">
					<thead>
					</thead>
					<tbody>
						<tr>
							<td class="col-lg-4">
								<div class="card leftside card_borderless" style="width: 5rem;">
									<h1 style="font-size: 24px; font-weight: 600;">${bean.writer}</h1>
								</div>
							</td>
							<td class="col-lg-8">
								<div class="card leftside card_borderless" style="width: 15rem;">
									<p class="card-text">${bean.inputdate}</p>

								</div>
							</td>
						</tr>
						<tr>
							<td class="col-lg-12">
								<div class="card leftside card_borderless" style="width: 15rem;">
									<p class="card-text">${bean.subject}</p>
									<%--평점 구간별로 나눠 별점으로 표기합니다. --%>
									<c:set var="grade" value="${bean.grade}"></c:set>
									<div class="star-ratings">
										<div class="star-ratings-fill space-x-2 text-lg"
											style="width: ${grade}%">
											<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
										</div>
										<div class="star-ratings-base space-x-2 text-lg">
											<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
										</div>
									</div>
									<p class="card-text">${bean.comments}</p>
								</div>
						</tr>
						<%--이미지 파일이 없을 경우 이미지를 표기하지 않습니다. --%>
						<c:if test="${bean.image01}!=null">
							<tr>
								<td class="col-lg-12">
									<div class="card leftside" style="width: 20rem;">
										<img src="<%=imagefiles%>/${bean.category}/${bean.image01}"
											class="card-img-top active_image zoom" alt="${bean.name}">
									</div>
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<hr
					style="width: 100%; height: 5px; border: none; margin: auto; background-color: black;">
			</c:forEach>
		</details>
	</div>


</body>
<%@ include file="./../common/bottom Nav.jsp"%>
</html>