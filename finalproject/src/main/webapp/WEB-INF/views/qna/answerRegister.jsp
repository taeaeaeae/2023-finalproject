<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>신규게시글작성</title>
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
		<form action="/qna/answerRegister" method="POST">
			
			<input type="hidden" name="currPage" value="${param.currPage}">
			<input type="hidden" name="amount" value="${param.amount}">
			<input type="hidden" name="qid" value="${param.qid}">
			
   <fieldset>
		<h1>QnA</h1>
      <div class="form-group">
        <label for="" class="form-label mt-4"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">제목</font></font></label>
        <input name="title" type="text" class="form-control"  placeholder="제목입력">
      </div>      <div class="form-group">
        <label for="exampleInputEmail1" class="form-label mt-4"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"></font></font></label>
        <input name="uids" type="text" class="form-control"  placeholder="작성자" value="${id.uids}" readonly >
      </div>

      <div class="form-group">
        <label for="exampleTextarea" class="form-label mt-4"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">내용</font></font></label>
        <textarea name="content" class="form-control" id="exampleTextarea" rows="3"></textarea>
      </div>
      <div class="form-group">
        <label for="formFile" class="form-label mt-4"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">사진첨부</font></font></label>
        <input name = "image" class="form-control" type="file" id="formFile">
      </div>
			

			<button type="button" id="listBtn">LIST</button>
			<button type="submit" id="submitBtn">등록</button>
			
		</form>
	</div>
	
	<script>
		var listBtn = document.querySelector('#listBtn');
		


		listBtn.addEventListener('click', function () {
			location.href='/qna/list?currPage=${param.currPage}&amount=${param.amount}';
		})

	</script>

	<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>
