<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/findIdPost.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/litera/bootstrap.min.css" 
    integrity="sha384-enpDwFISL6M3ZGZ50Tjo8m65q06uLVnyvkFO3rsoW0UC15ATBFz3QEhr3hmxpYsn" crossorigin="anonymous">

	<title>findPost.jsp</title>
</head>
<body>

	<div id="container">
		
		<div class="card border-primary mb-3" style="max-width: 20rem;">
	        <div class="card-header">아이디 찾기</div>
	        
	        <div class="card-body">
	            <h4 class="card-title">" <%= request.getAttribute("UIDS") %> " 입니다.</h4>
	        </div>
	            
	    </div>
	    
	    <button type="button" id="cancleBtn">
	     	취소
	    </button>
	
	    <button type="button" id="loginBtn">
	    	로그인
	    </button>
	    
	 </div>      

    <script>
        var logoutBtn = document.querySelector('#loginBtn');

        logoutBtn.addEventListener('click', function () {
            location = "/user/login";
        }); //onclick
        
        var logoutBtn = document.querySelector('#cancleBtn');

        logoutBtn.addEventListener('click', function () {
            location = "/main/index";
        }); //onclick
        
    </script>

</body>
</html>