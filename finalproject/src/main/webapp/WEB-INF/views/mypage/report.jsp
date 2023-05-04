<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>report</title>
<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/album/" />
</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
<%@include file="/WEB-INF/views/common/leftside.jsp" %>

<c:if test="${sessionScope['__AUTH__'].uids eq 'admin'}">
 				
   	<section>
	  <h1 style="text-align: center">신고내역</h1>

	  <div id="list">
	    <table class="table table-hover" style="width:60%; margin-left: auto; margin-right: auto;">
			<thead>
				<tr>
					<th scope="col">신고자</th>
					<th scope="col">작성자</th>
					<th scope="col">신고 게시물번호</th>
					<th scope="col">사유</th>
					<th scope="col">기타 사유</th>
					<th scope="col">신고일자</th>
					<th scope="col">상태</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="ReportVO" items="${report}">
					<tr class="table-primary">
						<td>${ReportVO.uids}</td>
						<td>${ReportVO.target_user}</td>
						<td><a href="/${ReportVO.board_name}/get?${ReportVO.bm}=${ReportVO.bno} ">${ReportVO.target_board_id}</a></td>
						<td>${ReportVO.reason}</td>
						<td>${ReportVO.reason_2}</td>
						<td><fmt:formatDate value="${ReportVO.reported_at}" pattern="yyyy-MM-dd HH:mm"/></td>
						<td>${ReportVO.status}</td>
						<form action="/mypage/dropout" method="post">
							<td><button>탈퇴</button></td>
						</form>
					</tr>
				</c:forEach>
			</tbody>	
			
	    </table>
	  </div>
	</section>
	
</c:if>

<%@include file="/WEB-INF/views/common/footer.jsp" %>	
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
   crossorigin="anonymous"></script>
</body>
</html>