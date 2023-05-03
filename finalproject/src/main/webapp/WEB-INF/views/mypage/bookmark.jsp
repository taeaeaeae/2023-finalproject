<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bookmark</title>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
<%@include file="/WEB-INF/views/common/leftside.jsp" %>

   	<section>
	  <h1 style="text-align: center">북마크 목록</h1>
	  
	  <div id="list">
	    <table>
			<thead>
				<tr class="table-primary">
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
	  </div>
	</section>
	
<%@include file="/WEB-INF/views/common/footer.jsp" %>
	
</body>
</html>