<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/mypage.css">
<title>회원정보조회</title>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
	
	<h1 align="center">회원정보조회</h1>
	
	<%@include file="/WEB-INF/views/common/leftside.jsp" %>
	
		<section>		
			<div id="center">
				<form action="/mypage/main" method="post">
					<h3>* 아이디 : ${mypage.uids}</h3></br>
		
					<h3>* 이름 : ${mypage.name}</h3></br>
		
					<h3>* 이메일 : ${mypage.email}</h3></br>
						
					<h3>* 전화번호 : ${mypage.phonenumber}</h3></br>
					
					<h3>* 프로필사진 : </h3>
					<c:if test="${not empty mypage.image}"><img src="/resources${mypage.image}"></c:if>
			
					</br>
				</form>
			</div>
		</section>

	<%@include file="/WEB-INF/views/common/footer.jsp" %>	

</body>
</html>