<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
    h1.board_name {
  text-align: center;
  	color: black;
  }
  div.card {
  	width: 70%;
  	margin: 0 auto;
  }
  img {
  
  	margin: 0 auto;
  }
  .butt {
  float: right;
  margin-bottom: 10px;
  margin-left: 5px;
  }
	</style>

<link rel="canonical"
	href="https://getbootstrap.com/docs/5.2/examples/album/" />
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/cerulean/bootstrap.min.css" 
	integrity="sha384-3fdgwJw17Bi87e1QQ4fsLn4rUFqWw//KU0g8TvV6quvahISRewev6/EocKNuJmEw" crossorigin="anonymous" />
    <title>QnA</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
    
	<br>
	<h1 class="board_name">QnA</h1>
	<br>
	
    
		<form action="/qna/remove" method="POST">
		
		<input type="hidden" name="currPage" value="${param.currPage}">
		<input type="hidden" name="amount" value="${param.amount}">
		<input type="hidden" name="result" value="${param.result}">
		<input type="hidden" name="qid" value="${qna.qid}">
		<input type="hidden" name="qid" value="${param.qid}">
    <input type="hidden" name="type"    value="${param.type}">
    <input type="hidden" name="keyword" value="${param.keyword}">

		

<div class="card mb-3">
  <h3 class="card-header"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">${qna.title}</font></font></h3>
  <div class="card-body">
    <h5 class="card-title"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">${qna.uids}</font></font></h5>
    <h6 class="card-subtitle text-muted"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">${qna.insert_ts}</font></font></h6>
  </div>
  
  <div style="width: 50%; text-align: center; margin: 0 auto;">
  	<c:if test="${not empty qna.image}"><img src="/resources${qna.image}" alt="${qna.title}" ></c:if>
  </div>
  
  <div class="card-body">
    <p class="card-text"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">${qna.content}</</font></font></p>
  </div>
  
  <div class="card-body">
			<button class="butt" type="button" id="listBtn">LIST</button>
			
  	<c:if test="${sessionScope['__AUTH__'].uids eq qna.uids}">
			<input class="butt" type="button" value="수정하기" id="modifyBtn">
			<button class="butt" type="submit" id="submitBtn">삭제하기</button>
	</c:if>
	
  </div>
</div>
</form>
<br> <br>

  <div class="card mb-3">
    <h4 class="card-header"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">${answer.title}</font></font></h4>
  <div class="card-body">
    <h5 class="card-title"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">${answer.uids}</font></font></h5>
    <h6 class="card-subtitle text-muted"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">
		<fmt:formatDate value="${QnaVO.insert_ts}" pattern="yyyy-MM-dd HH:mm"/></font></font></h6>
  </div>
  
  <div style="width: 50%; text-align: center; margin: 0 auto;">
	<c:if test="${not empty answer.image}"><img src="/resources${answer.image}" alt="${answer.title}"></c:if>
	</div>
  <div class="card-body">
    <p class="card-text"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">${answer.content}</</font></font></p>
  </div>

	
  <div class="card-body">
	<c:if test="${sessionScope['__AUTH__'].uids eq 'admin'}">
		<input class= "butt" type="button" value="수정하기" id="answerModifyBtn">
		<c:if test="${empty answer.qid}">
		<button class="butt" type="button" id="answerRegisterBtn">답변하기</button>
		</c:if>
	</c:if>
	</div>
  </div>


	<script>
		var listBtn = document.querySelector('#listBtn');
		var modifyBtn = document.querySelector('#modifyBtn');
		var AnswerModifyBtn = document.querySelector('#answerModifyBtn');
        var answerRegisterBtn = document.querySelector('#answerRegisterBtn');
		
        answerRegisterBtn.addEventListener('click', function () {
            location = '/qna/answerRegister?currPage=${param.currPage}&amount=${param.amount}&qid=${qna.qid}';
        })        
		
		answerModifyBtn.addEventListener('click', function () {
			location = '/qna/answerModify?currPage=${param.currPage}&amount=${param.amount}&qid=${qna.qid}';
		})
        
        listBtn.addEventListener('click', function () {
			location='/qna/list?currPage=${param.currPage}&amount=${param.amount}';
		})

        modifyBtn.addEventListener('click', function () {
			location = '/qna/modify?currPage=${param.currPage}&amount=${param.amount}&qid=${qna.qid}';
		})

	</script>
	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
   crossorigin="anonymous"></script>
</body>
</html>