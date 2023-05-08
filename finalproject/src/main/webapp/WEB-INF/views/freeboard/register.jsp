<%@page import="org.zerock.myapp.domain.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/freeboard/css/freeboard_register.css">
<title>자유게시판 | 글 작성하기</title>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<section>
  <h1>자유게시판</h1>
  <hr>
  <div id="main">
    <form method="POST" action="/freeboard/register" enctype="multipart/form-data">
      <input type="hidden" name="currPage" value="${param.currPage}">
      <input type="hidden" name="amount" value="${param.amount}">
      <input type="hidden" name="fid" value="${freeboard.fid}">

      <input type="text" name="title" placeholder="title">
      <hr>
      <input type="file" name="file">
      <br/>

      <textarea name="content" placeholder="내용을 입력해주세요."></textarea>
      <br/>

      <button type="reset" value="초기화" class="button" name="reset" onclick="return confirmReset()">초기화</button>
      <input type="submit" value="글쓰기" class="button" onclick="return validateForm()">
      <a href="/freeboard/list" id="listBtn" class="button" onclick="return confirmGoToList()">목록으로</a>
    </form>
  </div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 
<script>
/* 목록 이동 버튼 */
var listBtn = document.querySelector('#listBtn');

listBtn.addEventListener('click', function () {
  location.href="/freeboard/list?currPage=${ param.currPage }&amount=${ param.amount }";
}); // .addEventListener

// function confirmGoToList() {
//   const title = document.getElementsByName("title")[0].value;
//   const content = document.getElementsByName("content")[0].value;

//   if (title.trim() !== "" || content.trim() !== "") {
//       const result = confirm("작성 중인 내용이 있습니다. 목록으로 이동하시겠습니까?");

//       if (!result) {
//           return false;
//       }
//   }
//   return true;
// } 
</script>
<script src="/resources/freeboard/js/validateForm.js"></script>
</body>
</html>