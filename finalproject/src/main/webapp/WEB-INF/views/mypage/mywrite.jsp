<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mywrite</title>
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.2/examples/album/" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
<%@include file="/WEB-INF/views/common/leftside.jsp" %>

   	<section>
	  <h1 style="text-align: center">나의 작성 글</h1>
	  
	    <table class="table table-hover" style="width:60%; margin-left: auto; margin-right: auto;">
			<thead>
				<tr>
					<th scope="col">작성자</th>
					<th scope="col">제목</th>
					<th scope="col">작성일자</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="MywriteVO" items="${mywrite}">
					<tr>
						<td>${MywriteVO.uids}</td>
						<td>
							<c:choose>
								<c:when test="${MywriteVO.board_name eq 'plan'}"><a href="/${MywriteVO.board_name}/view?${MywriteVO.bm}=${MywriteVO.bno}&uids=${sessionScope['__AUTH__'].uids} ">${MywriteVO.title}</a></c:when>
								<c:otherwise><a href="/${MywriteVO.board_name}/get?${MywriteVO.bm}=${MywriteVO.bno}&currPage=1&amount=10 ">${MywriteVO.title}</a></c:otherwise>
							</c:choose></td>
						<td><fmt:formatDate value="${MywriteVO.insert_ts}" pattern="yyyy-MM-dd HH:mm"/></td>
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