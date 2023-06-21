<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/Nav.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	/* box model 공부할 것 */
	.input-group{margin: 7px;}
	
	.input-group-text{
		display:block;
		margin-left:auto;
		margin-right:auto;
	}
	#buttonset{margin-top:15px;}
	#productNum{display:none;visibility:hidden;}
</style>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">	
  	<link rel="stylesheet" href="/resources/demos/style.css">  
  	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
  	<script type="text/javascript">
  		$(document).ready(function(){
  	  		/* $('#inputdate').datepicker(); */
  	  		$('#inputdate').datepicker({dateFormat: "yy/mm/dd"});   	  		
  	  		$('#limtdate').datepicker({dateFormat: "yy/mm/dd"});   
  	  		/* alert('${requestScope.categories}'); */
  	  		/* var categories = '${requestScope.categories}' ; */
  	  		/* console.log(categories); */
  	    	  	
  	  		/* $('#cateogory') */
  	  	 
  		}); 		
  		function validCheck(){
  			var name = $('#name').val();
  			if(name.length < 2 || name.length > 10){
  				alert('상품명은 2글자 이상 10글자 이하이어야 합니다.');
  				$('#name').focus() ;
  				return false ;
  			}
  			
  	
  			
  			
  			var comments = $('#comments').val();
  			if(comments.length < 10 || comments.length > 100){
  				alert('상품에 대한 코멘트는 10글자 이상 100글자 이하이어야 합니다.');
  				$('#comments').focus() ;
  				return false ;
  			}  		
  			
  			var image01 = $('#image01').val();
  			if(image01 == ''){
  				alert('대표 이미지는 필수 사항입니다.');
  				return false ;
  			}
  			
  			var isCheck = false ; /* 확장자 체크에 충족하면 true가 됩니다. */
  			const imgCheck = ['.png', '.jpg'] ; /* 확장자 체크용 배열 */
  			for(var i=0 ; i < imgCheck.length ; i++){
  				if(image01.endsWith(imgCheck[i])){
  					isCheck = true ;
  					break ;
  				}	
  			}
  			
  			if(isCheck == false){
  				alert('이미지의 확장자는 png 또는 jpg 형식이어야 합니다.');
  				return false ;
  			}
  			
  			
  			
  			
  			const NumberMsg = '는(은) 숫자 형식이어야 합니다.' ;
  			
  				
  			
  			var stock = $('#stock').val();  			
  			if(stock == '' || isNaN(stock) == true){
  				alert('재고' + NumberMsg);
  				$('#stock').focus();
  				return false ;
  			}else{
  				var numStock = Number(stock);
  				if(numStock < -1 || numStock > 100){
  					alert('재고는 최대 100개까지입니다.');
  	  				$('#stock').focus();
  	  				return false ;
  				}
  			}
  			var price = $('#price').val();  			
  			if(price == '' || isNaN(price) == true){
  				alert('단가' + NumberMsg);
  				$('#price').focus();
  				return false ;
  			}else{
  				var numPrice = Number(price);
  				if(numPrice < 100 || numPrice > 100000){
  					alert('단가은 100이상 100000이하의 값이어야 합니다.');
  	  				$('#price').focus();
  	  				return false ;
  				}
  			}  		
  		
  			
  			var point = $('#point').val();  			
  			if(point == '' || isNaN(point) == true){
  				alert('포인트' + NumberMsg);
  				$('#point').focus();
  				return false ;
  			}else{
  				var numPoint = Number(point);
  				if(numPoint < 3 || numPoint > 100){
  					alert('포인트는 3이상 100이하의 값이어야 합니다.');
  	  				$('#point').focus();
  	  				return false ;
  				}
  			}  		
  			
  			
  			var category = $('#category').val();
  			if(category == '-'){ /* 코딩할 때 option의 value 속성을 하이폰으로 설정했습니다. */
  				alert('카테고리를 선택해 주세요.');
	  			$('#category').focus();
	  			return false ;ㄴ
  			}
  			
  			
  			var inputdate = $('#inputdate').val();
  			
  			var regex = /^\d{4}[\/-][01]\d{1}[\/-][0123]\d{1}$/ ;
  			var result = regex.test(inputdate) ;
  			
  			if(result == false){
  				alert('날짜 형식은 반드시 yyyy/mm/dd 형식 또는 yyyy-mm-dd으로 작성해 주세요.');
  				$('#inputdate').focus() ;
  				return false ;
  			}  			
  			
  			var kcal = $('#kcal').val();  			
  			if(kcal == '' || isNaN(kcal) == true){
  				alert('칼로리' + NumberMsg);
  				$('#kcal').focus();
  				return false ;
  			}else{
  				var numKcal = Number(kcal);
  				if(numKcal < -1 || numKcal > 1000){
  					alert('칼로리는 최대 1000kcal 까지입니다.');
  	  				$('#kcal').focus();
  	  				return false ;
  				}
  			}
  			
  			var gram = $('#gram').val();  			
  			if(gram == '' || isNaN(gram) == true){
  				alert('그램' + NumberMsg);
  				$('#gram').focus();
  				return false ;
  			}else{
  				var numGram = Number(gram);
  				if(numGram < -1 || numGram > 1000){
  					alert('g(그램)은 최대 1000g까지입니다.');
  	  				$('#gram').focus();
  	  				return false ;
  				}
  			}
  			
  			var best = $('#best').val();
  			if(best == '-'){ /* 코딩할 때 option의 value 속성을 하이폰으로 설정했습니다. */
  				alert('카테고리를 선택해 주세요.');
	  			$('#best').focus();
	  			return false ;
  			}
var 		limtdate = $('#limtdate').val();
  			
			result = regex.test(limtdate) ;
  			
  			if(result == false){
  				alert('날짜 형식은 반드시 yyyy/mm/dd 형식 또는 yyyy-mm-dd으로 작성해 주세요.');
  				$('#limtdate').focus() ;
  				return false ;
  			}  			
  		}
  	</script>
</head>
<body>
	<div class="container mt-3 col-md-5">
		<h2>상품 등록</h2>
		<p>사용자들이 구매할 상품을 등록해 주는 페이지</p>
		
		<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data">
			<input type="hidden" name="command" value="prInsert">
			<div id="productNum" class="input-group">
				<span class="input-group-text col-md-2">상품 번호</span> 
				<input id="num" name="num" type="number" class="form-control" placeholder="">
			</div> 
			<div class="input-group">
				<span class="input-group-text col-md-2">상품명</span> 
				<input id="name" name="name" type="text" class="form-control" placeholder="" value="">
			</div>
			
			<div class="input-group">
				<span class="input-group-text col-md-2">코멘트</span> 
				<input id="comments" name="comments" type="text" class="form-control" placeholder="" value="">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">이미지01</span> 
				<input id="image01" name="image01" type="file" class="form-control" placeholder="">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">재고</span> 
				<input id="stock" name="stock" type="number" class="form-control" placeholder="" value="">
			</div>	
			<div class="input-group">
				<span class="input-group-text col-md-2">단가</span> 
				<input id="price" name="price" type="number" class="form-control" placeholder="" value="">
			</div>		
			<div class="input-group">
				<span class="input-group-text col-md-2">포인트</span> 
				<input id="point" name="point" type="number" class="form-control" placeholder="" value="">
			</div>	
			<div class="input-group">
				<span class="input-group-text col-md-2">카테고리</span> 
				<select id="category" name="category" class="form-select form-select-lg">
					<option value="-">-- 항목을 선택해 주세요.</option>
					<option value="볶음">나물</option>
				  	<option value="탕">국,탕</option>
				  	<option value="찜">찜</option>
				</select>
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">입고 일자</span> 
				<input id="inputdate" name="inputdate" type="datetime" class="form-control" placeholder="">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">칼로리</span> 
				<input id="kcal" name="kcal" type="number" class="form-control" placeholder="" value="">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">그램</span> 
				<input id="gram" name="gram" type="number" class="form-control" placeholder="" value="">
			</div>			
			
				
			
			<div class="input-group">
				<span class="input-group-text col-md-2">베스트</span> 
				<select id="best" name="best" class="form-select form-select-lg">
					<option value="-">-- 항목을 선택해 주세요.</option>
					<option value="best">베스트</option>
				  	<option value="일반">일반</option>
				  	
				</select>
			</div>	
				<div class="input-group">
				<span class="input-group-text col-md-2">비고</span>
				<input id="remark" name="remark" type="text" disabled="disabled" class="form-control" placeholder="" value="">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">유통 기한</span> 
				<input id="limtdate" name="limtdate" type="datetime" class="form-control" placeholder="">
			</div>	
			
			
			<div id="buttonset" class="input-group">
				<button type="submit" class="btn btn-primary btn-lg"
					onclick="return validCheck();"> 
					등록
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-secondary btn-lg">초기화</button>
			</div>
		</form>
	</div>
</body>
</html>