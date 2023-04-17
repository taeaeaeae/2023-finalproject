<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/mypage_main.css">
    <script src="https://kit.fontawesome.com/0afe3af558.js" crossorigin="anonymous"></script>
    <title>트트트</title>
</head>

<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!-- 옆에 메뉴바 -->

<div class="container">
  <div class="page_menu">
    <dl>
      <dt>마이페이지
        <dd><img src="${Users.image}"/></dd>
        <dd><a href="#">닉네임/아이디</a></dd>
        <dd><a href="/finalproject/마이페이지/회원정보수정3.html">개인정보 수정</a></dd>
        <dd><a href="/finalproject/마이페이지/나의일정2.html">나의 일정</a></dd>
        <dd><a href="/내저장.html">저장목록</a></dd>
      </dt>
    </dl>
  </div>
  
    <!-- </div> -->
  <div class = "mypage_main">
     <h2>User님, 마이페이지입니다.</h2>
  </div>
</div>


	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>