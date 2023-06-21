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
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
			var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
			return new bootstrap.Tooltip(tooltipTriggerEl)
			});		
		});
		
		function validCheck(){
			var id = $('#id').val();
			if(id.length < 1){
				swal("아이디를 입력해 주세요.",{
					icon: "warning"
				});
				$('#id').focus();
				return false;
			}
			
			var password = $('#password').val();
			if(password.length < 1){
				swal("비밀번호를 입력해 주세요.",{
					icon: "warning"
				});
				$('#password').focus();
				return false;
			}
		}
	</script>
</head>
<body>
	<br/><br/><br/><br/>
	<section class="h-100">
		<div class="container h-100">
			<div class="row justify-content-sm-center h-100">
				<div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
					<div class="text-center my-5">
						<img src="https://getbootstrap.com/docs/5.0/assets/brand/bootstrap-logo.svg" alt="logo" width="100">
					</div>
					<div class="card shadow-lg">
						<div class="card-body p-5">
							<h1 class="fs-4 card-title fw-bold mb-4">Login</h1>
							<form action="<%=withFormTag%>" method="post">
								<input type="hidden" name="command" value="meLogin">
								<div class="mb-3">
									<label class="mb-2 text-muted" for="id">아이디</label>
									<input id="id" type="text" class="form-control" name="id" value="" autofocus
									data-bs-toggle="tooltip" title="아이디는 최소 3글자 이상 10글자 이하로 입력해 주세요."
									data-bs-placement="left">					
								</div>

								<div class="mb-3">
									<div class="mb-2 w-100">
										<label class="text-muted" for="password">비밀번호</label>
									</div>
									<input id="password" type="password" class="form-control" name="password">
								</div>

								<div class="d-flex align-items-center">
									<button type="submit" class="btn btn-primary ms-auto" onclick="return validCheck();">
										Login
									</button>
								</div>
							</form>
						</div>
						<div class="card-footer py-3 border-0">
							<div class="text-center">
								계정이 없으신가요? <a href="<%=notWithFormTag%>meInsert" class="text-dark">회원가입 하러 가기</a>
							</div>
						</div>
					</div>
					<div class="text-center mt-5 text-muted">
						Copyright &copy; 2023 &mdash; 반찬몰 
					</div>
				</div>
			</div>
		</div>
	</section>	
</body>
</html>