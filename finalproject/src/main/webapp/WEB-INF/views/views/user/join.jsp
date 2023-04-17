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
  <form action="/user/joinPost" method="post">
    
    <div class="join_content">
            <div class="essential">

                        <h3>아이디</h3>
                        <input type="text" id="uids" name="uids" onkeydown="inputIdChk()" required>
                        <button class="checkId" type="button" id="checkId" onclick="fn_idChk();", value="N">중복확인</button>
                                         
                        <h3>비밀번호</h3>
                        <input type="password" id="password" name="password" title="password" required>
                    
                        <h3>비밀번호 확인</h3>
						<input type="password" id="pwCheck" name="pwCheck" title="pwCheck" required>
                        <font id="pwNotice" size="2"></font>
                        
                        <h3>이름</h3>
                        <input type="text" id="name" name="name" title="name" required>
                
                        <h3>전화번호</h3>
                        <input type="text" id="phonenumber" name="phonenumber" title="phonenumber" required>
                
                        <h3>이메일</h3>
                        <input type="email" id="email" name="email" title="email" required>
                        
                        <h3>닉네임</h3>
                        <input type="text" id="nickname" name="nickname" title="nickname" required>
						<button class="nickCheck" type="button" id="nickCheckBtn">중복확인</button>
						
                        <br>
                        <br>
                        
                        <input type="checkbox" id="check" name="agree_all">
                        모두 동의합니다.
                        
                        <br>
                        
                        <input type="checkbox" id="check" name="agree">
                        (필수) 본인은 만 14세 이상입니다.
                       
                        <br>
                        
                        <input type="checkbox" id="check" name="agree">
                        (필수) 개인정보수집에 동의합니다. &nbsp;<span><a href="/common/information" target="_blank">보기</a></span>
                        
                        <br>

                        <input type="checkbox" id="check" name="agree">
                        (필수) 이용약관에 동의합니다. &nbsp;<span><a href="/common/clause" target="_blank">보기</a></span>
                       
            </div>
        

    </div>

        <div class="join">
            
            <button type="button" id="cancleBtn">
                취소
            </button>
            
            <button type="submit" id="joinBtn">
                회원가입
            </button>
            
        </div>
	</form>
	
	<script>

            // 아이디 중복체크
			function fn_idChk(){
				$.ajax({
					url : "/user/checkId",
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
				})
			}
            

            // 닉네임 중복체크


            // 비밀번호 확인
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
            
            // 전체동의
            const agreeChkAll = document.querySelector('input[name=agree_all]');

            agreeChkAll.addEventListener('change', (e) => {
                let agreeChk = document.querySelectorAll('input[name=agree]');

                for(let i = 0; i < agreeChk.length; i++){
                agreeChk[i].checked = e.target.checked;
                }
            });
            
            // 모든 유효성 체크 후 회원가입 활성화

            // 취소시 메인
            var cancleBtn = document.querySelector('#cancleBtn');

            cancleBtn.addEventListener('click', function () {
            location.href = '/main/index';
            }); // .addEventListener

          
  
        </script>
        
</body>
</html>