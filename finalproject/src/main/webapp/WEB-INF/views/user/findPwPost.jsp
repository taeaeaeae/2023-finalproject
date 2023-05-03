<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
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