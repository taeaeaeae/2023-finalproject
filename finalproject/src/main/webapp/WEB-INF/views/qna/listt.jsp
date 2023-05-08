<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.104.2">
    <title>Sidebars · Bootstrap v5.2</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/sidebars/">

    
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link href="sidebars.css" rel="stylesheet">
  </head>
  <body>
  
<header>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
</header>


<div class="container">
<%@include file="/WEB-INF/views/qna/listt2.jsp" %>

		
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
  
  <div class="card-body" align="right">
			<button class="butt" type="button" id="listBtn" class="button">LIST</button>
			
	
  	<c:if test="${(sessionScope['__AUTH__'].uids eq qna.uids) || (sessionScope['__AUTH__'].uids eq 'admin')}">
			<input class="butt" type="button" value="수정하기" id="modifyBtn" class="btn btn-primary btn-sm">
			<button class="butt" type="submit" id="submitBtn" class="btn btn-primary btn-sm">삭제하기</button>
	</c:if>
	
  </div>
</div>
    
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


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>

      <script src="sidebars.js"></script>
  </body>
</html>
