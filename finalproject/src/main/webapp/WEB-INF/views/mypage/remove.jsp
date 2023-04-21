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
<link rel="stylesheet" href="/css/remove.css">
<title>회원 탈퇴</title>
</head>
<body>

  <%@include file="/WEB-INF/views/main/header.jsp" %>

	<div class="container">
	
    <h1 align="center">회원탈퇴</h1>

    <br>
    <br>

      <form action="/mypage/remove" method="post">

        <div id="unregister">
          <h3 align="center">
            회원 탈퇴시의 아래 사항을 숙지하시기 바랍니다 <br>
             ** 탈퇴 시 회원님께서 작성하신 글과 댓글은 삭제되지 않습니다. 삭제를 원하신다면 탈퇴 전 미리 삭제 부탁드립니다. **
          </h3> 
          
          <br>
          <br>

        <div id="user_id">
          <h4>* 아이디 <input type="text" name="uids" value="${mypage.uids}"/></h4>

          <h4>* 비밀번호 <input type="password" name="password" placeholder="비밀번호를 입력하세요."></h4>
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
	            <textarea name="reason" id="advice" value=""cols="50" rows="7" placeholder="회원님의 진심어린 충고 부탁드립니다."></textarea>
          </fieldset>
        </div>

      </div>

      <div id="gogo">
      
      <input type="submit" class="btn btn-outline-primary" value="탈퇴">
      
      <button type="button" class="btn btn-outline-primary">취소</button>
      
      </div>
      </form>
  
    </div>
    
  <%@include file="/WEB-INF/views/main/footer.jsp" %>

  <!-- 비밀번호를 입력해야만 탈퇴가능 -> 현재 비밀번호랑 같아야 한다. -->

</body>
</html>