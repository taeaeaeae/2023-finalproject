<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/join.css" />
<script src="http://code.jquery.com/jquery-latest.js"></script>

<title>Join</title>


</head>
<body>
  <h1>JOIN</h1>
  <form action="/join/joinPost" method="post" id="joinForm" enctype="multipart/form-data">
    
    <div class="join_content">
        <div class="essential">

            <h3>아이디 <span>(영문, 숫자 사용가능)</span></h3>
                <input type="text" id="uids" name="uids" onkeydown="inputIdChk()" pattern="^[a-zA-Z0-9]*$" maxlength="18" required>
                <button class="checkId" type="button" id="checkId" name="button" onclick="fn_idChk();" value="N">중복확인</button>
                                         
            <h3>비밀번호</h3>
                <input type="password" id="password" name="password" title="password" pattern="^[a-zA-Z0-9]*$" maxlength="18" required>
                    
            <h3>비밀번호 확인</h3>
				<input type="password" id="pwCheck" name="pwCheck" title="pwCheck" pattern="^[a-zA-Z0-9]*$" maxlength="18" required>
				<font id="pwNotice" size="2"></font>
                    
            <h3>이름</h3>
                <input type="text" id="name" name="name" title="name" maxlength="18" required>
                
            <h3>전화번호 <span>(숫자만 입력해주세요.)</span></h3>
                <input type="tel" id="phonenumber" name="phonenumber" pattern="[0-9]{11}" maxlength="11" required>
                
            <h3>이메일</h3>
                <input type="email" id="email" name="email" title="email" placeholder="example1@xxx.com" maxlength="38" required>
				<button class="checkEmail" type="button" id="checkEmail" name="button" onclick="fn_emailChk();" value="N">중복확인</button>
				
			<h3>프로필 사진</h3>
			<input name = "file" class="form-control" type="file" id="formFile" accept="image/*">	
					
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
        
            <button type="submit" id="joinBtn">
                회원가입
            </button>
            
            <button type="button" id="cancleBtn">
                취소
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
					if(data >= 1){
						$("#checkId").attr("value", "N");
						alert("이미 사용중이거나 탈퇴한 아이디입니다.");
					}else if(data == 0){
						$("#checkId").attr("value", "Y");
						alert("사용가능한 아이디입니다.");
					        }
				        }
			        }) // ajax
			      }
	    
		    // 이메일 중복체크
			function fn_emailChk(){
				$.ajax({
					url : "/join/checkEmail",
					type : "post",
					dataType : "json",
					data : {"email" : $("#email").val()},
					success : function(data){
					if(data >= 1){
						$("#checkEmail").attr("value", "N");
						alert("이미 사용중이거나 탈퇴한 이메일입니다.");
					}else if(data == 0){
						$("#checkEmail").attr("value", "Y");
						alert("사용가능한 이메일입니다.");
					        }
				        }
			        }) // ajax
			      }
	   	    
		 // 비밀번호 확인
            $(function(){
                $('#password').keyup(function(){
                $('#pwNotice').html('');
                });

                $('#pwCheck').keyup(function(){

                    if($('#password').val() != $('#pwCheck').val()){
                    $('#pwNotice').html('비밀번호가 일치하지 않습니다,<br><br>');
                    $('#pwNotice').attr('color', '#ff000');
                    } else{
                    $('#pwNotice').html('비밀번호가 일치합니다.<br><br>');
                    $('#pwNotice').attr('color', '#199894b3');
                    }

                });
            }); 
         
	    // 전체동의
	        const agreeChkAll = document.querySelector('input[name=agree_all]');
	
	        agreeChkAll.addEventListener('change', (e) => {
	            let agreeChk = document.querySelectorAll('input[name=agree]');
	
	            for(let i = 0; i < agreeChk.length; i++){
	            agreeChk[i].checked = e.target.checked;
	        }
	    });
   	    
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
	        location.href = '/main/index';
	    });
	        
    </script>
        
</body>
</html>