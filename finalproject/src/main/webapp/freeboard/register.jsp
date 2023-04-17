<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../fix/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/freeboard/css/freeboard_register.css">
<script src="/resources/freeboard/js/validateForm.js"></script>
<title>자유게시판 | 글 작성하기</title>
</head>
<body>
<section>
  <h1>자유게시판</h1>
  <hr>
  <div id="main">
    <form onsubmit="return validateForm()" method="POST" action="/freeboard/register">
      <input type="hidden" name="currPage" value="${param.currPage}">
      <input type="hidden" name="amount" value="${param.amount}">
      <input type="hidden" name="fid" value="${freeboard.fid}">

      <input type="text" name="uids" value="${freeboard.uids}" placeholder="uids">
      <input type="text" name="title" value="${freeboard.title}" placeholder="title">
      <input type="text" name="image" value="${freeboard.image}" placeholder="image">
      <hr>
      <input type="file" name="pic" value="사진 선택"><br/>
      <textarea name="content">${freeboard.content}</textarea>
      <br>
      <button type="reset" value="초기화" class="button" name="reset" onclick="return confirmReset()">초기화</button>
      <input type="submit" value="글쓰기" class="button">
      <a href="/freeboard/list" id="listBtn" class="button" onclick="return confirmGoToList()">목록으로</a>
    </form>
  </div>
</section>
<script>
		var listBtn = document.querySelector('#listBtn');

		listBtn.addEventListener('click', function () {
			location.href="/board/list?currPage=${ param.currPage }&amount=${ param.amount }";
		}); // .addEventListener
	</script>
</body>
<%@ include file="../fix/footer.jsp" %>
</html>