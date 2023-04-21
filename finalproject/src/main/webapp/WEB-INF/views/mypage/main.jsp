<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/mypage.css">
<title>회원정보조회</title>
</head>
<body>

	<%@include file="/WEB-INF/views/main/header.jsp" %>
	
	<h1 align="center">회원정보조회</h1>
		
		<div class="container">
			
			<div id="center">
				<form action="/mypage/main" method="post">
							<h3>* 아이디 : ${mypage.uids}</h3></br>

							<h3>* 이름 : ${mypage.name}</h3></br>

							<h3>* 닉네임 : ${mypage.nickname}</h3></br>

							<h3>* 이메일 : ${mypage.email}</h3></br>
				
							<h3>* 전화번호 : ${mypage.phonenumber}</h3></br>
				</form>
			</div>
			
			<div id="text">
				<a href="/mypage/update">회원정보수정</a>
				
				<a href="/mypage/remove">회원탈퇴</a>
				<a href="/mypage/mywrite">나의 작성글 </a>
			</div>	
		</div>
	
	<%@include file="/WEB-INF/views/main/footer.jsp" %>	

</body>
</html>