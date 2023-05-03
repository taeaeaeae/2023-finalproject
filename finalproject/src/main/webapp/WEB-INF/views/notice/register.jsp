<%@page import="org.zerock.myapp.domain.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/freeboard/css/freeboard_register.css">
<script src="/resources/freeboard/js/validateForm.js"></script>
<title>공지사항 | 글 작성하기</title>
</head>
<body>
<% LoginVO vo = (LoginVO) session.getAttribute("__AUTH__");
	String uids = vo.getUids();%>
<p>작성자 아이디 : <%= uids %></p>

<%@include file="/WEB-INF/views/common/header.jsp" %>

<section>
  <h1>공지사항</h1>
  <hr>
  <div id="main">
    <form method="POST" action="/notice/register" enctype="multipart/form-data">
      <input type="hidden" name="currPage" value="${param.currPage}">
      <input type="hidden" name="amount" value="${param.amount}">
      <input type="hidden" name="nid" value="${noticeboard.nid}">

      <input type="text" name="title" placeholder="title">
      <hr>
      <input type="file" name="file">
      <br/>

      <textarea name="content" placeholder="내용을 입력해주세요."></textarea>
      <br/>

      <button type="reset" value="초기화" class="button" name="reset" onclick="return confirmReset()">초기화</button>
      <input type="submit" value="글쓰기" class="button">
      <a href="/notice/list" id="listBtn" class="button" onclick="return confirmGoToList()">목록으로</a>
    </form>
  </div>
  <%@include file="/WEB-INF/views/common/footer.jsp" %>	
</section>
<script>
		var listBtn = document.querySelector('#listBtn');

		listBtn.addEventListener('click', function () {
			location.href="/notice/list?currPage=${ param.currPage }&amount=${ param.amount }";
		}); // .addEventListener
	</script>
</body>
</html>