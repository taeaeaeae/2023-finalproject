<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mycomment</title>
</head>
<body>

<%@include file="/WEB-INF/views/common/header.jsp" %>

   	<section>
	  <h1>내가 쓴 댓글</h1>
	  <hr>
	  
	  <%@include file="/WEB-INF/views/common/leftside.jsp" %>
	  
	  <div id="list">
	    <table border="1px">
			<thead>
				<tr style="background-color: #eee; color: black">
					<th>작성자</th>
					<th id="tb2">내용</th>
					<th>작성날짜</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="MycommentVO" items="${mycomment}">
					<tr>
						<td>${MycommentVO.uids}</td>
						<td><a href="/${MycommentVO.board_name}/get?${MycommentVO.bm}=${MycommentVO.bno} ">${MycommentVO.content}</a></td>
						<td><fmt:formatDate value="${MycommentVO.insert_ts}" pattern="yyyy-MM-dd HH:mm"/></td>
					</tr>
				</c:forEach>
			</tbody>	
			
	    </table>
	  </div>
	</section>
	
<%@include file="/WEB-INF/views/common/footer.jsp" %>	

<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
   crossorigin="anonymous"></script>

</body>
</html>