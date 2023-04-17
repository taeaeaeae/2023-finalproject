<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/0afe3af558.js" crossorigin="anonymous"></script>
    <title>트트트</title>
	<link rel="stylesheet" href="WEB-INF/views/common/font.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/cerulean/bootstrap.min.css" integrity="sha384-3fdgwJw17Bi87e1QQ4fsLn4rUFqWw//KU0g8TvV6quvahISRewev6/EocKNuJmEw" crossorigin="anonymous">
    <link rel="stylesheet" href="main.css">
    <style>
    *{
  margin: 0px;
  padding: 0px;
}
a{
  text-decoration-line: none;
}
.container{
  box-sizing: border-box;
  padding: 30px 30px;
  display: flex;
  justify-content: center;
}
.header a{
  color: black;
}
.bar_logo h1{
  font-size: 40px;
  font-weight: bold;
  text-shadow:  3px 3px 0px #bdbdbd;
}
.bar_logo h3{
  box-sizing: border-box;
  margin-top: -30px;
  margin-left: 150px;
  font-size: 20px;
}
.bar_menu{
  list-style: none;
  text-align: center;
  width: 50%;
  display: flex;
  justify-content: space-around;
}
.bar_menu a{
  color: black;
}
.bar_menu li{
  margin-top: 30px;
}
.bar_menu li :hover{
 color:#9cd5ee; 
 font-weight: bold;
 transition: 0.3s;
}
.bar_userimg{
  width: 30px;
  height: 30px;
  border-radius: 70px;
  background-color: #bdbdbd;
   margin-top: 30px;
 margin-right: 5px;
}
.bar_userimg i{
 padding-left: 8px;
 padding-top: 5px;
 color: black;
}
#User{
  color: black;
}
.bar_logout , .bar_Mypage{
  margin-top: 30px;
  margin-right: 5px;
  width: 50px;
  height:25px;
  font-size: small;
  background-color:#D2EEFA;
  border-radius: 8px;
  text-align: center;
}
.bar_logout a,.bar_Mypage a{
  text-decoration-line: none;
  color: black;
}
.page_menu{
 text-align: center;
 font-size: 180%;
 float: left;
 width: 30%;
 margin-top: 73px;
}
.page_menu a{
 text-decoration-line: none;
 color: black;
}
.page_menu img{
 margin-top: 10%;
 width:80px;
 height:80px;
 border-radius: 70%;
}
.page_menu dd {
 margin-top: 40px;
 font-size: 70%;
}
.page_menu dd > a:hover{
 margin-top: 20px;
 color:#9cd5ee; 
 font-weight: bold;
 transition: 0.3s;
}
.mypage_main {
    margin-top: 77px;
    width: 70%;
}
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