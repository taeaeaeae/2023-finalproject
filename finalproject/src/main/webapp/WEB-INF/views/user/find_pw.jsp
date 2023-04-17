<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/find_pw.css" />

<title>find_pw</title>
</head>
<body>

<form action="/user/findPwPost" method="post">

    <div class="container">

            <div class="text">

                <h1>비밀번호 찾기</h1>

                <h3>비밀번호는 가입시 입력하신 아이디과 이메일을 통해 찾을 수 있습니다.</h3>

            </div>
        

            <div class="find">

                <input type="text" id="uids" name="uids" placeholder="아이디를 입력하세요." required> <br>

                <input type="email" id="email" name="email" placeholder="이메일을 입력하세요." required>
      
            </div>
            
            <div class="button">
            
                <button type="button" id="cancleBtn">
                    취소
                </button>
                
                <button type="submit" id="find_id" >
                    찾기
                </button>
                
            </div>

    </div>
</form>

    <script>
        var logoutBtn = document.querySelector('#cancleBtn');

        logoutBtn.addEventListener('click', function () {
            location = "/main/index";
        }); //취소 시 메인으로
        
    </script>
</body>
</html>