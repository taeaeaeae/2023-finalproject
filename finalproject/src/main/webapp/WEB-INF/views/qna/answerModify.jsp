<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>신규게시글작성</title>
    <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
     <style>
	* {
	font-family: "GangwonEdu";
}
@font-face {
	font-family: "GangwonEdu";
    src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEdu_OTFBoldA.woff")
      format("woff");
    font-weight: normal;
    font-style: normal;
  }
  h1 {
  text-align: center;
  	color: black;
  }
	</style>
    
    <link rel="stylesheet" href="WEB-INF/views/common/font.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/cerulean/bootstrap.min.css" integrity="sha384-3fdgwJw17Bi87e1QQ4fsLn4rUFqWw//KU0g8TvV6quvahISRewev6/EocKNuJmEw" crossorigin="anonymous">
    
</head>

<body>

	<%@ include file="/WEB-INF/views/common/header.jsp" %>

    
	<div id="wrapper">
		<form action="/qna/answerModify" method="POST">
			
			<input type="hidden" name="currPage" value="${param.currPage}">
			<input type="hidden" name="amount" value="${param.amount}">
			
    <fieldset>
		<h1>QnA</h1>
      <div class="form-group">
      	<input name="qid" type="text" class="form-control" placeholder="글번호" value="${answer.qid}" readonly>
        <label for="" class="form-label mt-4"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">제목</font></font></label>
        <input name="title" type="text" class="form-control"  placeholder="제목입력" value="${answer.title}">
      </div>      
      <div class="form-group">
        <label for="exampleInputEmail1" class="form-label mt-4"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"></font></font></label>
        <input name="uids" type="text" class="form-control"  placeholder="작성자" value="${answer.uids}" readonly >
      </div>

      <div class="form-group">
        <label for="exampleTextarea" class="form-label mt-4"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">내용</font></font></label>
        <textarea name="content" class="form-control" id="exampleTextarea" rows="3">${answer.content}</textarea>
      </div>
      <div class="form-group">
        <label for="formFile" class="form-label mt-4"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">사진첨부</font></font></label>
        <input name = "image" class="form-control" type="file" id="formFile">
      </div>
			

			<button type="button" id="listBtn">LIST</button>
			<button type="submit" id="submitBtn">registerBtn</button>
			<button type="button" id="removeBtn">삭제하기</button>
			
		</form>
	</div>
	
	<script>
		var listBtn = document.querySelector('#listBtn');
		var removeBtn = document.querySelector("#removeBtn");
		


		listBtn.addEventListener('click', function () {
			location.href='/qna/list';
		})
		
		removeBtn.addEventListener('click', function () {
			let form = document.querySelector('form');
			$(form).attr('method', 'POST');
			$(form).attr('action', '/qna/remove');
			$(form).submit();
		})
		

	</script>

	<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>