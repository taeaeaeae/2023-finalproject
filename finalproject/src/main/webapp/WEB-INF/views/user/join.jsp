<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/join.css" />
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<title>Join</title>

</head>
<body>
  <h1>JOIN</h1>
  <form action="/user/joinPost" method="post">
    
    <div class="join_content">
            <div class="essential">

                        <h3>아이디</h3>
                        <input type="text" id="uids" name="uids" oninput = "checkId()">
                        
                        <span class="id_ok">사용 가능한 아이디입니다.</span>
						<span class="id_already">누군가 이 아이디를 사용하고 있어요.</span>
                    
                        <h3>비밀번호</h3>
                        <input type="password" id="password" name="password" title="password">
                    
                        <h3>비밀번호 확인</h3>
						<input type="password" id="pwCheck" name="pwCheck" title="pwCheck">
                        <font id="pwNotice" size="2"></font>
                        
                        <h3>이름</h3>
                        <input type="text" id="name" name="name" title="name">
                
                        <h3>전화번호</h3>
                        <input type="text" id="phonenumber" name="phonenumber" title="phonenumber">
                
                        <h3>이메일</h3>
                        <input type="email" id="email" name="email" title="email">
                        
                        <h3>닉네임</h3>
                        <input type="text" id="nickname" name="nickname" title="nickname">

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
            
		    function checkId(){
		        var uids = $('#uids').val(); //id값이 "id"인 입력란의 값을 저장
		        $.ajax({
		            url:'./idCheck', //Controller에서 요청 받을 주소
		            type:'post', //POST 방식으로 전달
		            data:{uids:uids},
		            success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다 
		                if(cnt == 0){ //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
		                    $('.id_ok').css("display","inline-block"); 
		                    $('.id_already').css("display", "none");
		                } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
		                    $('.id_already').css("display","inline-block");
		                    $('.id_ok').css("display", "none");
		                    alert("아이디를 다시 입력해주세요");
		                    $('#uids').val('');
		                }
		            },
		            error:function(){
		                alert("에러입니다");
		            }
		        });
		        };

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

            // 취소시 메인
            var cancleBtn = document.querySelector('#cancleBtn');

            cancleBtn.addEventListener('click', function () {
            location.href = '/main/index';
            }); // .addEventListener
  
        </script>
        
</body>
</html>