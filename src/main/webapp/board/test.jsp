<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>동영상과 다르게  부트스트랩 적용 Head 부분만 붙이면 됩니다.</title>
    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- CKEDITOR  -->
<script src="//cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>
</head>
<body>
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">
        <h2 class="text-center">게시글 쓰기</h2>
        <form action="BoardWriterProc.jsp" method="post">
          <table class="table table-striped">
            <tr>
                <td>작성자</td>
                <td><input type="text"  class="form-control" name="writer"></td>
            </tr>
            <tr>
                <td>제목</td>
                <td><input type="text"  class="form-control" name="subject"></td>
            </tr>
            <tr>
                <td>이메일</td>
                <td><input type="email"  class="form-control" name="email"></td>
            </tr>
             
            <tr>
                <td>비밀번호</td>
                <td><input type="password"  class="form-control" name="password"></td>
            </tr>
             
            <tr>
                <td>글내용</td>
                <td><textarea rows="10" cols="50" name="content" class="form-control"></textarea></td>
            </tr>
            <tr>
                 
                <td colspan="2"  class="text-center">
                    <input type="submit" value="글쓰기" class="btn btn-success">
                    <input type="reset" value="다시작성" class="btn btn-warning">
                    <button type="button"  class="btn btn-primary">전체 게시글보기</button>
                </td>
            </tr>
             
          </table>
        </form>
    </div>
</div>

<script>
//CKEDITOR 적용 
CKEDITOR.replace('content', {
         
    width:'100%',
    height:'350'
         
});
</script>
</body>
</html>