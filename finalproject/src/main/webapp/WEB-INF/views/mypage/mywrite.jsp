<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mywrite</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
<%@include file="/WEB-INF/views/common/leftside.jsp" %>

   	<section>
	  <h1 style="text-align: center">나의 작성 글</h1>

	  <div class="list">
	    <table>
			<thead>
				<tr>
					<th>작성자</th>
					<th>제목</th>
					<th>작성날짜</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="MywriteVO" items="${mywrite}">
					<tr>
						<td>${MywriteVO.uids}</td>
						<td>
							<c:choose>
								<c:when test="${MywriteVO.board_name eq 'plan'}"><a href="/${MywriteVO.board_name}/view?${MywriteVO.bm}=${MywriteVO.bno}&uids=${sessionScope['__AUTH__'].uids} ">${MywriteVO.title}</a></c:when>
								<c:otherwise><a href="/${MywriteVO.board_name}/get?${MywriteVO.bm}=${MywriteVO.bno} ">${MywriteVO.title}</a></c:otherwise>
							</c:choose></td>
						<td><fmt:formatDate value="${MywriteVO.insert_ts}" pattern="yyyy-MM-dd HH:mm"/></td>
					</tr>
				</c:forEach>
			</tbody>
				
	    </table>
	  </div>
	</section>

<%@include file="/WEB-INF/views/common/footer.jsp" %>	

</body>
</html>