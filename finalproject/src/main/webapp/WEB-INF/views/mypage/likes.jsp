<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>likes</title>
</head>
<body>
<%@include file="/WEB-INF/views/main/header.jsp" %>

   	<section>
	  <h1>좋아요 목록</h1>
	  <hr>
	  <div id="list">
	    <table border="1px">
			<thead>
				<tr style="background-color: #eee; color: black">
					<th>글번호</th>
					<th></th>
					<th id="tb2">제목</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="LikesVO" items="${mylikes}">
					<tr>
						<td>${LikesVO.lid}</td>
						<td><td><a href="#">${LikesVO.title}</a></td>
					</tr>
				</c:forEach>
			</tbody>	
			
	    </table>
	  </div>
	</section>
	
<%@include file="/WEB-INF/views/main/footer.jsp" %>	

</body>
</html>