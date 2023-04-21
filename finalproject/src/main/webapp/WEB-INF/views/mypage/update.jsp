<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />
<link rel="stylesheet" href="/css/update.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>회원정보수정</title>
</head>
<body>
	
	<%@include file="/WEB-INF/views/main/header.jsp" %>

		<h1 align="center">회원정보수정</h1>
		<h3 align="center">비밀번호 및 이메일 , 전화번호만 수정 가능합니다.</h3>

			<div class="container">

				<form action="/mypage/update" method="post">
					
					<div id="center">

						<h3>* 아이디 <input type="text" name="uids" value="${mypage.uids}" readonly/></h3>					

						<h3>* 비밀번호 <input type="password" id="password" name="password" minlength="4" maxlength="12"/></h3>

						<h3>* 비밀번호 확인 <input type="password" id="pwCheck" name="pwCheck" title="pwCheck" minlength="4" maxlength="12"></h3>						
                		<font id="pwNotice" size="2"></font>
						
						<h3>* 이름 <input type="text" name="name" value="${mypage.name}" readonly/></h3>
						
						<h3>* 닉네임 <input type="text" name="nickname" value="${mypage.nickname}" readonly/></h3>
						
						<h3>* 이메일 <input type="email" name="email" value="${mypage.email}" placeholder="example1@xxx.com"/></h3>
						
						<h3>* 전화번호 <input type="text" name="phonenumber" value="${mypage.phonenumber}" placeholder="010-xxxx-xxxx" /></h3>
						

					</div>	

					<div id="gogo">
				      <input type="submit" class="btn btn-outline-primary" value="수정"></button>
				      
				      <button type="button" class="btn btn-outline-primary">취소</button>
					</div>				
				</form>
		</div>
	
	<%@include file="/WEB-INF/views/main/footer.jsp" %>

	<script>
		//비밀번호 확인		
		$(function(){
            $('#password').keyup(function(){
            $('#pwNotice').html('');
            });

            $('#pwCheck').keyup(function(){

            if($('#password').val() != $('#pwCheck').val()){
                $('#pwNotice').html('비밀번호가 일치하지 않습니다.');
                $('#pwNotice').attr('color', '#ff000');
            } else{
                $('#pwNotice').html('비밀번호가 일치합니다.');
                $('#pwNotice').attr('color', '#199894b3');
            }

        });
    });
	</script>
	

	<!-- 
	비밀번호 확인시 일치해야만 한다.
	빈칸이 없어야만 수정 가능 -->

</body>
</html>