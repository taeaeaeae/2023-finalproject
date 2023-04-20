<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>

	<div class="container">
	
	<%@include file="/WEB-INF/views/main/header.jsp" %>
	<h1 align="center">회원정보수정</h1>
		<form action="/mypage/update" method="post">
		
				<tr>
					<td>* 아이디</td>
					<td><input type="text" name="uids" value="${mypage.uids}" /><br>
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<input type="password" name="password" value="${mypage.password}" /><br>
				</tr>
				<tr>
					<td>* 이름</td>
					<input type="text" name="name" value="${mypage.name}" /><br>
				</tr>
				<tr>
					<td>* 닉네임</td>
					<input type="text" name="nickname" value="${mypage.nickname}" /><br>
				</tr>
				<tr>
					<td>* 이메일</td>
					<input type="email" name="email" value="${mypage.email}" /><br>
				</tr>
				<tr>
					<td>* 전화번호</td>
					<input type="text" name="phonenumber" value="${mypage.phonenumber}" /><br>
				</tr>
		
            <input type="submit" value="수정" />
            
     	</form>
		
		<%@include file="/WEB-INF/views/main/footer.jsp" %>
		
	</div>
	
	

</body>
</html>