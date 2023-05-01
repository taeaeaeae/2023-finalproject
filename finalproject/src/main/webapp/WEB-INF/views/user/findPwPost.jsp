<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/findPwPost.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/litera/bootstrap.min.css" 
    integrity="sha384-enpDwFISL6M3ZGZ50Tjo8m65q06uLVnyvkFO3rsoW0UC15ATBFz3QEhr3hmxpYsn" crossorigin="anonymous">

	<title>findPost.jsp</title>
</head>
<body>

    <script>
    
    // 임시비밀번호 발송 완료
	$(document).ready(function() {
	      let message = "${result}";
	      if (message != "" && message != null ) {
	            alert(message);
	      }else {
	     }
	  });
        
    </script>

</body>
</html>