<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mywrite</title>
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
<%@include file="/WEB-INF/views/common/leftside.jsp" %>

   	<section>
	  <h1>신고내역</h1>

	  <div id="list">
	    <table>
			<thead>
				<tr>
					<th>작성자</th>
					<th>제목</th>
					<th>작성날짜</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="ReportVO" items="${mywrite}">
					<tr>
						<td>${MywriteVO.uids}</td>
						<td><a href="/${MywriteVO.board_name}/get?${MywriteVO.bm}=${MywriteVO.bno} ">${MywriteVO.title}</a></td>
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