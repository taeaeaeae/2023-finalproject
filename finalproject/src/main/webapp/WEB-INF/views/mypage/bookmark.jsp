<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bookmark</title>
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.2/examples/album/" />
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
<%@include file="/WEB-INF/views/common/leftside.jsp" %>

   	<section>
	  <h1 style="text-align: center">북마크 목록</h1>
	  
	    <table class="table table-hover" style="width:60%; margin-left: auto; margin-right: auto; margin-bottom: 10%;">
			<thead>
				<tr>
					<td>제목</td>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="BookmarkVO" items="${mybookmark}">
					<tr>
					<td><c:choose>
							<c:when test="${BookmarkVO.board_name eq 'plan'}"><a href="/${BookmarkVO.board_name}/view?${BookmarkVO.bm}=${BookmarkVO.bno}&uids=${sessionScope['__AUTH__'].uids} ">${BookmarkVO.title}</a></c:when>
							<c:otherwise><a href="/${BookmarkVO.board_name}/get?${BookmarkVO.bm}=${BookmarkVO.bno} ">${BookmarkVO.title}</a></c:otherwise>
						</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>	
	    </table>
	</section>
	


<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
   crossorigin="anonymous"></script>
	
</body>
</html>