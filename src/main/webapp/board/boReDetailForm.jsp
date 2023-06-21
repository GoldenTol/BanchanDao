<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/Nav.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <style type="text/css">
  	#backButton{margin:auto;}
  </style>
     <style type="text/css">
      /* 댓글들을 위한 스타일 지정 */
      * {
         padding: 0;
         margin: 0;
         color: #333;
      }
      ul { list-style: none; }
      #container { padding: 30px 20px; }
      #insertComment {
         padding: 20px 15px;
         border-bottom: 1px solid #7BAEB5;
      }

      #insertComment label {
         display: inline-block;
         width: 80px;
         font-size: 14px;
         font-weight: bold;
         margin-bottom: 10px;
      }

      #insertComment input[type='text'], #insertComment textarea {
         border: 1px solid #ccc;
         vertical-align: middle;
         padding: 3px 10px;
         font-size: 12px;
         line-height: 150%;
      }

      #insertComment textarea {
         width: 450px;
         height: 120px ;
      }

      .commentItem {
         font-size: 13px;
         color: #333;
         padding: 15px;
         border-bottom: 1px dotted #ccc;
         line-height: 150%;
      }

      .commentItem .writer {
         color: #555;
         line-height: 200%;
      }

      .commentItem .writer input {
         vertical-align: middle;
      }

      .commentItem .writer .name {
         color: #222;
         font-weight: bold;
         font-size: 14px;
      }
      
      .form-group {
         margin-bottom: 3px;
      }
      
      .form-control {
         height: 25px;
      }
      .btn-primary{opacity: 0.8;}
   </style>
  
</head>
<body>
	<div class="container mt-3">
	<br/>
	<br/>
	<br/>
	<h2>${requestScope.bean.boReNo}번 게시글 정보</h2>
		<table class="table table-striped">
			<thead>
			</thead>
			<tbody>
				<tr>
					<td align="center">작성자</td>
					<td>${bean.boReWriter}</td>
				</tr>
				<tr>
					<td align="center">글제목</td>
					<td>${bean.boReSubject}</td>
				</tr>
				<tr>
					<td align="center">글내용</td>
					<td>${bean.boReContent}</td>
				</tr>
				<tr>
					<td align="center">작성일자</td>
					<td>${bean.boReRegdate}</td>
				</tr>
				<tr>
					<td align="center">조회수</td>
					<td>${bean.boReReadhit}</td>
				</tr>
			</tbody>
		</table>
		<table class="table">
			<tbody>
				<tr>
					<td>${bean.boReContent}</td>
				</tr>
			</tbody>
		</table>
		<div id="backButton">
			<button type="button" class="btn btn-warning" onclick="history.back();">
				이전 페이지
			</button>
		</div>
	</div>
</body>
</html>