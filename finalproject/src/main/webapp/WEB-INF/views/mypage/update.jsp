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
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="/css/update.css">
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

						<h3>* 비밀번호 <input type="password" value="${mypage.password}" id="password" name="password" pattern="^[a-zA-Z0-9]*$" maxlength="18" required/></h3>

						<h3>* 비밀번호 확인 <input type="password" value="${mypage.password}" id="pwCheck" name="pwCheck" title="pwCheck" pattern="^[a-zA-Z0-9]*$" maxlength="18" required/></h3>						
                		<button class="pwCheck" type="button" id="pwCheck" onclick="fn_checkPw();" value="N">확인</button>
						
						<h3>* 이름 <input type="text" name="name" value="${mypage.name}" readonly/></h3>
						
						<h3>* 전화번호 <input type="text" name="phonenumber" value="${mypage.phonenumber}" placeholder="숫자만 입력하세요" pattern="[0-9]{11}" maxlength="11" required/></h3>
						
						<h3>* 이메일 <input type="email" name="email" value="${mypage.email}" placeholder="example1@xxx.com" maxlength="38" required/></h3>
						

					</div>	

					<div id="gogo">
				      <button type="submit" id="submit" class="btn btn-outline-primary">수정</button>
				      
				      <button type="button" id="cancleBtn" class="btn btn-outline-primary">취소</button>
					</div>				
				</form>
		</div>
	
	<%@include file="/WEB-INF/views/main/footer.jsp" %>

	<script>
		// 비밀번호 확인
	    function fn_checkPw() {
	    	  var pw = $("#password").val();
	    	  var pwCheck = $("#pwCheck").val();
	
	    	  if (pw !== pwCheck) {
	   	    alert("비밀번호가 일치하지 않습니다.");
	    	    return false;
	    	  } else if (pw == pwCheck) {
	    		 $("#pwCheck").attr("value", "Y");
	    		alert("비밀번호가 일치합니다.");
	    	  }
	            	  
	   	  return true;
		}
		
	    // 완료 알림
		$(document).ready(function() {
		      let message = "${result}";
		      if (message != "" && message != null ) {
		            alert(message);
		      }else {
		     }
		  });
	    
	    // 취소시 메인
        var cancleBtn = document.querySelector('#cancleBtn');

        cancleBtn.addEventListener('click', function () {
        location.href = '/mypage/main';
    });
	</script>
	

</body>
</html>