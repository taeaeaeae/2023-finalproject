<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/css/find_id.css" />
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	
	<title>find_id</title>
</head>
<body>

<form action="/user/findIdPost" method="post">

    <div class="container">

        <div class="text">

                <h1>아이디 찾기</h1>

                <h3>아이디는 가입시 입력하신 이름과 전화번호를 통해 찾을 수 있습니다.</h3>

        </div>
            
            <div class="find">

                <input type="text" id="name" name="name" placeholder="이름을 입력하세요." required> <br>

                <input type="text" id="phonenumber" name="phonenumber" placeholder="전화번호를 입력하세요." required>
      
            </div>
            
            <div class="button">
            
                <button type="button" id="cancleBtn">
                    취소
                </button>
                
                
                <button type="submit" id="find_pw" >
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