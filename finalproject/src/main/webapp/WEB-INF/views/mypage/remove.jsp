<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="/css/remove.css">
<title>회원 탈퇴</title>
</head>
<body>

  <%@include file="/WEB-INF/views/main/header.jsp" %>

	<div class="container">
	
    <h1 align="center">회원탈퇴</h1>
    
    <%@include file="/WEB-INF/views/main/leftside.jsp" %>

    <br>
    <br>
	<section>
      <form action="/mypage/remove" method="post">

        <div id="unregister">
          <h3 align="center">
            회원 탈퇴시의 아래 사항을 숙지하시기 바랍니다 <br>
             ** 탈퇴 시 회원님께서 작성하신 글과 댓글은 삭제되지 않습니다. <br>
             삭제를 원하신다면 탈퇴 전 미리 삭제 부탁드립니다. **
          </h3> 
          
          <br>
          <br>

        <div id="check">
          <h4><input type="password" id="password" name="password" title="password" pattern="^[a-zA-Z0-9]*$" maxlength="18" required></h4>   
        </div>

        <br>
        <br>

        <div id="discomfort">

            <h3>무엇이 불편하셨나요?</h3>
            
            <fieldset>
	            <input type="radio" name="reason" value="서비스 기능에 대한 불만">서비스 기능에 대한 불만
	            <input type="radio" name="reason" value="방문 빈도가 낮음">방문 빈도가 낮음
	            <input type="radio" name="reason" value="개인정보 유출 우려">개인정보 유출 우려
	            <input type="radio" name="reason" value="기타" checked>기타
	            <br>
	            <textarea name="reason" id="advice" value=""cols="50" rows="2" maxlength="50" placeholder="회원님의 진심어린 충고 부탁드립니다."></textarea>
          </fieldset>
        </div>

      </div>

	      <div id="gogo">
		      <button type="submit" id="submit" class="btn btn-outline-primary">탈퇴</button>
		      
		      <button type="button" id="cancleBtn" class="btn btn-outline-primary">취소</button>
	      </div>
      </form>
  
    </div>
  </section>  
  <%@include file="/WEB-INF/views/main/footer.jsp" %>
  
  <script>
	
   	// 취소시 메인
		var cancleBtn = document.querySelector('#cancleBtn');
	
		   	cancleBtn.addEventListener('click', function () {
			location.href = '/mypage/main';
		});
	   	


	// 완료 알림
		$(document).ready(function() {
			  let message = "${result}";
			  if (message != "" && message != null ) {
			        alert(message);
			  }else {
			}
		});


  </script>

</body>
</html>