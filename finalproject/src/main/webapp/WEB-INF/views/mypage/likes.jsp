<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>likes</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
<%@include file="/WEB-INF/views/common/leftside.jsp" %>

   	<section>
	  <h1 style="text-align: center">좋아요 목록</h1>

	  <div id="list">
	    <table>
			<thead>
				<tr>
					<th>제목</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="LikesVO" items="${mylikes}">
					<tr>
						<td><a href="/${LikesVO.board_name}/get?${LikesVO.bm}=${LikesVO.bno} ">${LikesVO.title}</a></td>
					</tr>
				</c:forEach>
			</tbody>	
			
	    </table>
	  </div>
	</section>
	
<%@include file="/WEB-INF/views/common/footer.jsp" %>	

</body>
</html>