<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보조회</title>
</head>
<body>

	<div class="container">
	
	<%@include file="/WEB-INF/views/main/header.jsp" %>
	<h1 align="center">회원정보조회</h1>
		<form action="/mypage/main" method="post">
			<table>
				<tr>
					<td>* 아이디</td>
					<td>${mypage.uids}</td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td>${mypage.name}</td>
				</tr>
				<tr>
					<td>* 닉네임</td>
					<td>${mypage.nickname}</td>
				</tr>
				<tr>
					<td>* 이메일</td>
					<td>${mypage.email}</td>
				</tr>
				<tr>
					<td>* 전화번호</td>
					<td>${mypage.phonenumber}</td>
				</tr>
				
			</table>

		</form>
		

		<a href="/mypage/update">회원정보수정</a>
		
		<a href="/mypage/remove">회원탈퇴</a>
		
		<%@include file="/WEB-INF/views/main/footer.jsp" %>
		
	</div>

</body>
</html>