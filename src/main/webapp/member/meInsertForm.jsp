<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/Nav.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 등록</title>
	<style type="text/css">
		/* box model */
		.input-group{margin: 7px;}
		
		.input-group-text{
			display:block;
			margin-left:auto;
			margin-right:auto;
		}
		#buttonset{margin-top:15px;}
		.container{margin-top:30px;}
		.btn{margin-bottom: 35px;}
	</style>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#regdate').datepicker({dateFormat: "yy/mm/dd"});
		});
		
		function validCheck(){
			/* alert('반환 값이 false이면 이벤트 전파가 되지 않습니다.'); */
			var id = $('#id').val();
			if(id.length < 4 || id.length > 10){
				swal('아이디는 4자리 이상 10자리 이하로 입력해 주세요.',{
					icon: "warning"
				});
				$('#id').focus();
				return false;
			}
			
			var name = $('#name').val();
			if(name.length < 1){
				swal('이름을 입력해 주세요.',{
					icon: "warning"
				});
				$('#name').focus();
				return false;
			}
			
			var password = $('#password').val();
			if(password.length < 5 || password.length > 12){
				swal('비밀번호는 5자리 이상 12자리 이하로 입력해 주세요.',{
					icon: "warning"
				});
				$('#password').focus();
				return false;
			}
			
			var regex = /^[a-z]\S{4,11}$/; /* 정규 표현식 */
			var result = regex.test(password); 
			
			if(result == false){
				swal('첫 글자는 반드시 소문자로 작성되어야 합니다.',{
					icon: "warning"
				});
				return false;
			}
			
			if(password.indexOf("!") <= 0 && password.indexOf("@") <= 0 && password.indexOf("#") <= 0 && password.indexOf("$") <= 0){
				swal('특수 문자 !, @,#,$ 중에서 최소 1개 이상 입력해 주세요.',{
					icon: "warning"
				});
				return false;
			}
			
			var name = $('#address').val();
			if(name.length < 1){
				swal('주소를 입력해 주세요.',{
					icon: "warning"
				});
				$('#address').focus();
				return false;
			}
			
			var radioList = $("input[name='age']:checked");
			if(radioList.length == 0){
				swal('연령대를 선택해 주세요.',{
					icon: "warning"
				});
				return false;
			}
			
			var radioList = $("input[name='family']:checked");
			if(radioList.length == 0){
				swal('가족 구성을 선택해 주세요.',{
					icon: "warning"
				});
				return false;
			}
			
			var hiredate = $('#regdate').val();
			var regex = /^\d{4}\/(0[1-9]|1[012])\/(0[1-9]|[12][0-9]|3[01])$/;
			var result = regex.test(hiredate);
			if(result == false){
				alert('날짜 형식은 yyyy/mm/dd 형식으로 작성해 주세요.');
				$('#hiredate').focus();
				return false;
			}
			
			return true;
		}
	</script>
</head>
<body>
	<div class="container">
        <div class="py-5 text-center" >
            <img class="d-block mx-auto mb-4" src="<%=contextPath%>/image/free-icon-instagram-185985.png" alt="" width="72" height="72">
            <h2>회원가입</h2>
            <p class="lead">회원가입하세요. 가입하면 쇼핑몰을 이용할 수 있습니다.</p>
        </div>

        <div class="col-md-12 order-md-1">
            <form action="<%=withFormTag%>" method="post">
            	<input type="hidden" name="command" value="meInsert"/>
                <div class="mb-3">
                    <label for="username">아이디 (username)</label>
                    <input id="id" name="id" type="text" class="form-control" placeholder="영문자 4~10자리">
                </div>
                
                <div class="mb-3">
                    <label for="password">비밀번호</label> 
                    <input id="password" name="password" type="password" class="form-control" placeholder="첫글자 소문자, 특수문자 @,#,$ 중 하나 포함">
                </div>

                <div class="mb-3">
                    <label for="firstName">이름</label> 
                    <input id="name" name="name" type="text" class="form-control" placeholder="">
                </div>

                <div class="mb-3">
                    <label for="address">주소</label>
                    <input id="address" name="address" type="text" class="form-control"/>
                </div>

                <div class="mb-3">
                    <label for="address">연령대</label>
                    <div class="form-control">
						<label class="radio-inline radio_gender"> 
							<input type="radio" name="age" value="10대"> 10대
						</label> 
						&nbsp;
						<label class="radio-inline radio_gender"> 
							<input type="radio" name="age" value="20대"> 20대
						</label>
						&nbsp;
						<label class="radio-inline radio_gender"> 
							<input type="radio" name="age" value="30대"> 30대
						</label>
						&nbsp;
						<label class="radio-inline radio_gender"> 
							<input type="radio" name="age" value="40대"> 40대
						</label>
						&nbsp;
						<label class="radio-inline radio_gender"> 
							<input type="radio" name="age" value="50대"> 50대
						</label>
					</div>
                </div>
                
                <div class="mb-3">
                    <label for="address">가족 구성</label>
                    <div class="form-control">
						<label class="radio-inline radio_gender"> 
							<input type="radio" name="family" value="1인 가구"> 1인 가구
						</label> 
						&nbsp;
						<label class="radio-inline radio_gender"> 
							<input type="radio" name="family" value="4인 가구"> 4인 가구
						</label>
						&nbsp;
						<label class="radio-inline radio_gender"> 
							<input type="radio" name="family" value="다인 가구"> 다인 가구
						</label>
					</div>
                </div>
				<input id="regdate" name="regdate" type="hidden" class="form-control" value="${requestScope.regdate}">

				<div class="mb-3">
					<input
						id="mpoint" name="fakempoint" style="text-align: right;"
						disabled="disabled" type="hidden" class="form-control"
						placeholder=""> <input type="hidden" name="mpoint"
						value="0">
				</div>

				<div class="mb-3">
					<input
						id="remark" name="fakeremark" disabled="disabled" type="hidden"
						class="form-control" placeholder=""> <input type="hidden"
						name="remark" value="">
				</div>
				
				<div class="mb-3" align="center">
					<button class="btn btn-primary btn-lg btn-block" type="submit" onclick="return validCheck();">가입하기</button>
				</div>
            </form>
        </div>

    </div>
</body>
</html>