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

    <br>
    <br>

      <form action="/mypage/remove" method="post" id="delForm">

        <div id="unregister">
          <h3 align="center">
            회원 탈퇴시의 아래 사항을 숙지하시기 바랍니다 <br>
             ** 탈퇴 시 회원님께서 작성하신 글과 댓글은 삭제되지 않습니다. 삭제를 원하신다면 탈퇴 전 미리 삭제 부탁드립니다. **
          </h3> 
          
          <br>
          <br>

        <div id="check">
          <h4><input type="hidden" name="uids" value="${mypage.uids}"/></h4>
          <h4>* 비밀번호 <input type="password" name="password" id="password" placeholder="비밀번호를 입력하세요." required></h4>
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
    
  <%@include file="/WEB-INF/views/main/footer.jsp" %>
  
  <script>
   	// 취소시 메인
		var cancleBtn = document.querySelector('#cancleBtn');
	
		   	cancleBtn.addEventListener('click', function () {
			location.href = '/mypage/main';
		});
	   	
	// 비밀번호 확인
		$("#submit").on("click", function(){
			    
			$.ajax({
				url : "/mypage/passChk",
			    type : "POST",
			    dataType : "json",
			    data : {"password" : $("#password").val()},
			    success: function(data){
			    	if(data==0){
			        	alert("패스워드가 틀렸습니다.");
			            return "redirect:/mypage/remove";
			        	} else {
				            if(confirm("회원탈퇴하시겠습니까?")){
				            	$("#delForm").submit();
			        	}
			            	}
			        			},
			        			
			        error: function() {
			            alert("에러가 발생했습니다.");
			        }
			    });
			});


			

  
  </script>

</body>
</html>