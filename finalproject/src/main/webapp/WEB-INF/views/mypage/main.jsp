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
	
		<section>
			<div id="text">
				<ul>
					<li><a href="/mypage/update">회원정보수정</a></li><br>
					<li><a href="/mypage/remove">회원탈퇴</a></li><br>
					<li><a href="/mypage/mywrite">나의 작성 글</a></li><br>
					<li><a href="/mypage/mycomment">내가 쓴 댓글</a></li><br>
					<li><a href="/mypage/likes">좋아요 목록</a></li><br>
					<li><a href="/mypage/bookmark">북마크 목록</a></li><br>
					<li><a href="/mypage/checklist">체크리스트</a></li><br>
				</ul>
			</div>		
		</section>
		
		<section>		
			<div id="center">
				<form action="/mypage/main" method="post">
					<h3>* 아이디 : ${mypage.uids}</h3></br>
		
					<h3>* 이름 : ${mypage.name}</h3></br>
		
					<h3>* 이메일 : ${mypage.email}</h3></br>
						
					<h3>* 전화번호 : ${mypage.phonenumber}</h3></br>
				</form>
			</div>
		</section>

	
<%@include file="/WEB-INF/views/main/footer.jsp" %>	
</body>
</html>