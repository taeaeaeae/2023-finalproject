<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>

</head>
<body>

    <h1><%=request.getRequestURI() %></h1>
    <hr>

    <button type="button" id="logoutBtn">Logout</button>

    <script>
        var logoutBtn = document.querySelector('#logoutBtn');

        logoutBtn.addEventListener('click', function () {
            location = "/user/logout";
        }); //onclick (Get방식 / user/logout)
    </script>

</body>
</html>