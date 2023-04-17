<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/litera/bootstrap.min.css" 
    integrity="sha384-enpDwFISL6M3ZGZ50Tjo8m65q06uLVnyvkFO3rsoW0UC15ATBFz3QEhr3hmxpYsn" crossorigin="anonymous">

	<title>findPost.jsp</title>
</head>
<body>
    <h1>귀하의 비밀번호는</h1>
    <%
 	request.setCharacterEncoding("utf-8");
	%>
	<h1><%= request.getAttribute("PASSWORD") %>입니다.</h1>
	
	<button type="button" id="loginBtn">Login</button>

    <script>
        var logoutBtn = document.querySelector('#loginBtn');

        logoutBtn.addEventListener('click', function () {
            location = "/user/login";
        }); //onclick
    </script>

</body>
</html>