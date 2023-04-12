<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

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
	</style>

	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/cerulean/bootstrap.min.css" integrity="sha384-3fdgwJw17Bi87e1QQ4fsLn4rUFqWw//KU0g8TvV6quvahISRewev6/EocKNuJmEw" crossorigin="anonymous">
    <title>QnA</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
    
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    
<div class="card mb-3">
  <h3 class="card-header"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">${qna.title}</font></font></h3>
  <div class="card-body">
    <h5 class="card-title"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">${qna.uids}</font></font></h5>
    <h6 class="card-subtitle text-muted"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">${qna.insert_ts}</font></font></h6>
  </div>
   	<img src="${qna.image}" alt="${qna.title}">  
  <div class="card-body">
    <p class="card-text"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">${qna.content}</</font></font></p>
  </div>
  <div class="card-body">
			<input type="button" value="수정하기" id="modifyBtn">
			<button type="button" id="listBtn">LIST</button>
  </div>
  <div class="card-footer text-muted"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">
  ?
  </font></font></div>
</div>
<div class="card">
  <div class="card-body">
    <h4 class="card-title"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">${answer.title}</font></font></h4>
    <h6 class="card-subtitle mb-2 text-muted"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">${answer.uids}</font></font></h6>
						<img src="${answer.image}" alt="${answer.title}">
    <p class="card-text"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">${answer.content}</font></font></p>
    <a href="#" class="card-link"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">카드 링크 </font></font></a>
    <a href="#" class="card-link"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">또 다른 링크</font></font></a>
  </div>
</div>

	<div id="wrapper">
		
		<form action="#">
			
			<table>
				<caption></caption>
				<thead></thead>
				
				<tbody>
					<tr>
						<td>answer</td>
						<td>${answer.qid}</td>
					</tr>
					<tr>
						<td>제목</td>
						<td>${answer.title}</td>
					</tr>
					<tr>
						<td>내용</td>
						<img src="${answer.image}" alt="${answer.title}">
						<td>${answer.content}</td>
					</tr>
					<tr>
						<td>작성자</td>
						<td>${answer.uids}</td>
					</tr>
				</tbody>
				<tfoot></tfoot>
			</table>
			
			
			<input type="button" value="수정하기" id="modifyBtn">
			<button type="button" id="listBtn">LIST</button>
			
		</form>
		
	</div>
	<script>
		var listBtn = document.querySelector('#listBtn');
		var modifyBtn = document.querySelector("#modifyBtn");
		
		listBtn.onclick = function () {
			location.href='/qna/list?currPage=${param.currPage}&amount=${param.amount}';
		}
		
		modifyBtn.addEventListener('click', function () {
			self.location = "/qna/modify?currPage=${param.currPage}&amount=${param.amount}&qid=${qna.qid}";
		})
		
	</script>
	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>