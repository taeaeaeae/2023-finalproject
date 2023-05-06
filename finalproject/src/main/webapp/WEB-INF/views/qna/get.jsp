<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">



<link rel="stylesheet" type="text/css" href="/resources/freeboard/css/freeboard_view.css">
    <title>QnA</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
    
	<br>
	<br>

    
	<form action="/qna/remove" method="POST" class="board-post" style="width: 80%; margin: 0 auto;">
		
		<input type="hidden" name="currPage" value="${param.currPage}">
		<input type="hidden" name="amount" value="${param.amount}">
		<input type="hidden" name="result" value="${param.result}">
		<input type="hidden" name="qid" value="${qna.qid}">
		<input type="hidden" name="qid" value="${param.qid}">
	    <input type="hidden" name="type"    value="${param.type}">
    	<input type="hidden" name="keyword" value="${param.keyword}">

    <section class="board-header">
      <h1 class="board-title">Question</h1>
      <hr class="board-divider">
      <h3 class="post-title">${qna.title}</h3>
      <div class="post-meta">
        <span class="post-number">글번호 : ${qna.qid}</span>
        <span class="post-author">작성자 : ${qna.uids}</span>
        <span class="post-date">작성일 : <fmt:formatDate value="${qna.insert_ts}" pattern="yyyy-MM-dd HH:mm"/></span>

      </div>
    </section>
    
    <section class="board-body">
      <hr class="board-divider">
      <c:if test="${not empty qna.image}"><img src="/resources${qna.image}" alt="${qna.title}"></c:if>
      <div class="post-content"><pre style="font-family: 'GangwonEdu'; ">${qna.content}</pre></div>
    </section>
    
    <br><br>
  
  <div class="card-body" align="right">
			<button class="butt" type="button" id="listBtn" class="button">LIST</button>
			
	
  	<c:if test="${(sessionScope['__AUTH__'].uids eq qna.uids) || (sessionScope['__AUTH__'].uids eq 'admin')}">
			<input class="butt" type="button" value="수정하기" id="modifyBtn" class="btn btn-primary btn-sm">
			<button class="butt" type="submit" id="submitBtn" class="btn btn-primary btn-sm">삭제하기</button>
	</c:if>

	
  </div>
</div>
</form>
<br> <br>
<c:if test="${not empty answer.qid}">
<div class="board-post">
    <section class="board-header">
      <h1 class="board-title">Answer</h1>
      <hr class="board-divider">
      <h3 class="post-title">${answer.title}</h3>
      <div class="post-meta">
        <span class="post-number">글번호 : ${answer.qid}</span>
        <span class="post-author">작성자 : ${answer.uids}</span>
        <span class="post-date">작성일 : <fmt:formatDate value="${answer.insert_ts}" pattern="yyyy-MM-dd HH:mm"/></span>

      </div>
    </section>
    
    <section class="board-body">
      <hr class="board-divider">
      <c:if test="${not empty answer.image}"><img src="/resources${answer.image}" alt="${answer.title}"></c:if>
      <div class="post-content"><pre style="font-family: 'GangwonEdu';">${answer.content}</pre></div>
    </section>
  <div class="card-body" align="right">
	<c:if test="${sessionScope['__AUTH__'].uids eq 'admin'}">
		<input class= "butt" type="button" value="수정하기" id="answerModifyBtn">
		<c:if test="${empty answer.qid}">
		<button class="butt" type="button" id="answerRegisterBtn">답변하기</button>
		</c:if>
	</c:if>
	</div>
</div>
</c:if>
	


	<script>
		var listBtn = document.querySelector('#listBtn');
		var modifyBtn = document.querySelector('#modifyBtn');
		var AnswerModifyBtn = document.querySelector('#answerModifyBtn');
        var answerRegisterBtn = document.querySelector('#answerRegisterBtn');
		
		
        
        listBtn.addEventListener('click', function () {
			location='/qna/list?currPage=${param.currPage}&amount=${param.amount}';
		})

        modifyBtn.addEventListener('click', function () {
			location = '/qna/modify?currPage=${param.currPage}&amount=${param.amount}&qid=${qna.qid}';
		})

        answerRegisterBtn.addEventListener('click', function () {
            location = '/qna/answerRegister?currPage=${param.currPage}&amount=${param.amount}&qid=${qna.qid}';
        })        
        
		answerModifyBtn.addEventListener('click', function () {
			location = '/qna/answerModify?currPage=${param.currPage}&amount=${param.amount}&qid=${qna.qid}';
		})
	</script>
	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
   crossorigin="anonymous"></script>
</body>
</html>