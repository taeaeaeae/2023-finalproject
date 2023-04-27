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
  .board_name {
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
		<form action="/qna/register" method="POST" enctype="multipart/form-data">
			
			<input type="hidden" name="currPage" value="${param.currPage}">
			<input type="hidden" name="amount" value="${param.amount}">
			
   <fieldset>
		<h1 class="board_name">QnA</h1>
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
        <input name = "file" class="form-control" type="file" id="formFile" accept="image/*">
      </div> 
      
      <script>
		$("formFile").change(function() {
			if(this.file && this.file[0]) {
				var reader = new FileReader;
				reader.onload = function(data) {
					$(".form-group imge").arrt("src", data.target.result).wdith(500);
				}
				reader.readAsDataURL(this.file[0]);
			}
		});
		</script>
		
      <fieldset class="form-group">
        <legend class="mt-4"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">비공개</font></font></legend>

	  <div class="form-check">
        <input class="form-check-input" type="radio" name="openy_n" id="optionsRadios1" value="true" checked="">
        <label class="form-check-label" for="optionsRadios1"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">
          공개
        </font></font></label>
      </div>
      
      <div class="form-check">
        <input class="form-check-input" type="radio" name="openy_n" id="optionsRadios2" value="false">
        <label class="form-check-label" for="optionsRadios2"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">
          비공개
        </font></font></label>
      </div>		
      </fieldset>
			

			<button type="button" id="listBtn">LIST</button>
			<button type="submit" id="submitBtn">등록</button>
			
		</form>
	</div>
	
	<script>
		var listBtn = document.querySelector('#listBtn');
		

		listBtn.addEventListener('click', function () {
			location.href='/qna/list?currPage=${param.currPage}&amount=${param.amount}';
		});

	</script>

	<%@ include file="/WEB-INF/views/common/footer.jsp" %>

<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
   crossorigin="anonymous"></script>
</body>
</html>