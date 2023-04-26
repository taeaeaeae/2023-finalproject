<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/join.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<title>Join</title>


</head>
<body>
  <h1>JOIN</h1>
  <form action="/join/joinPost" method="post" name="joinForm">
    
    <div class="join_content">
        <div class="essential">

            <h3>아이디 <span>(영문, 숫자 사용가능)</span></h3>
                <input type="text" id="uids" name="uids" onkeydown="inputIdChk()" pattern="^[a-zA-Z0-9]*$" required>
                <button class="checkId" type="button" id="checkId" onclick="fn_idChk();" value="N">중복확인</button>
                                         
            <h3>비밀번호 <span>(영문, 숫자 사용가능)</span></h3>
                <input type="password" id="password" name="password" title="password" pattern="^[a-zA-Z0-9]*$" required>
                    
            <h3>비밀번호 확인</h3>
				<input type="password" id="pwCheck" name="pwCheck" title="pwCheck" pattern="^[a-zA-Z0-9]*$" required>
				<button class="pwCheck" type="button" id="pwCheck" onclick="fn_checkPw();" value="N">확인</button>
                    
            <h3>이름</h3>
                <input type="text" id="name" name="name" title="name" required>
                
            <h3>전화번호 <span>(숫자만 입력해주세요.)</span></h3>
                <input type="tel" id="phonenumber" name="phonenumber" pattern="[0-9]{11}" required>
                
            <h3>이메일</h3>
                <input type="email" id="email" name="email" title="email" placeholder="example1@xxx.com" required>
                        
            <h3>닉네임 <span>(영문, 숫자 최대 20자 / 한글 최대 10자)</span></h3>
                <input type="text" id="nickname" name="nickname" title="nickname" required>
				<button class="checkNickName" type="button" id="checkNickName" onclick="fn_nickChk();" value="N">중복확인</button>
						
                <br>
                <br>
                        
            <input type="checkbox" id="check" name="agree_all">
                모두 동의합니다.
                        
            <br>
                        
            <input type="checkbox" id="check" name="agree" required>
                (필수) 본인은 만 14세 이상입니다.
                       
            <br>
                        
            <input type="checkbox" id="check" name="agree" required>
                (필수) 개인정보수집에 동의합니다. &nbsp;<span><a href="/common/information" target="_blank">보기</a></span>
                        
            <br>

            <input type="checkbox" id="check" name="agree" required>
                (필수) 이용약관에 동의합니다. &nbsp;<span><a href="/common/clause" target="_blank">보기</a></span>
                       
        </div>
        

    </div>

        <div class="join">
            
            <button type="button" id="cancleBtn">
                취소
            </button>
            
            <button type="submit" id="joinBtn" onclick="submitForm();">
                회원가입
            </button>
            
        </div>
	</form>
	
	<script>

	    // 아이디 중복체크
			function fn_idChk(){
				$.ajax({
					url : "/join/checkId",
					type : "post",
					dataType : "json",
					data : {"uids" : $("#uids").val()},
					success : function(data){
					if(data == 1){
						alert("중복된 아이디입니다.");
					}else if(data == 0){
						$("#checkId").attr("value", "Y");
						alert("사용가능한 아이디입니다.");
					        }
				        }
			        }) // ajax
			      }
	           
	
	    // 닉네임 중복체크
		    function fn_nickChk(){
				$.ajax({
					url : "/join/checkNickName",
					type : "post",
					dataType : "json",
					data : {"nickname" : $("#nickname").val()},
					success : function(data){
					if(data >= 1){
						alert("중복된 닉네임입니다.");
					}else if(data == 0){
						$("#checkNickName").attr("value", "Y");
						alert("사용가능한 닉네임입니다.");
							}
						}
					}) // ajax
				}
	   	    
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
	            
	    // 전체동의
	        const agreeChkAll = document.querySelector('input[name=agree_all]');
	
	        agreeChkAll.addEventListener('change', (e) => {
	            let agreeChk = document.querySelectorAll('input[name=agree]');
	
	            for(let i = 0; i < agreeChk.length; i++){
	            agreeChk[i].checked = e.target.checked;
	        }
	    });
	        
		// 최종 버튼 확인 뭔가 잘 안되고있어 ...
		function submitForm() {
		  if (document.getElementById("checkId").value === 'y' &&
		      document.getElementById("pwCheck").value === 'y' &&
		      document.getElementById("checkNickName").value === 'y') {
		    alert("회원가입 성공!");
		  } else {
		    alert("필수 정보를 모두 확인해주세요.");
		    return false;
		  }
		}
       
	            	    
	    // 취소시 메인
	        var cancleBtn = document.querySelector('#cancleBtn');
	
	        cancleBtn.addEventListener('click', function () {
	        location.href = '/main/index';
	    });
	        
    </script>
        
</body>
</html>