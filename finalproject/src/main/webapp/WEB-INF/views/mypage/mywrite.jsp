<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mywrite</title>
</head>
<body>

<%@include file="/WEB-INF/views/main/header.jsp" %>

   	<section>
	  <h1>내가 쓴 게시물</h1>
	  <hr>
	  <div id="list">
	    <table border="1px">
			<thead>
				<tr style="background-color: #eee; color: black">
					<th>작성자</th>
					<th id="tb2">제목</th>
					<th>작성날짜</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="MywriteVO" items="${mywrite}">
					<tr>
						<td>${MywriteVO.uids}</td>
						<td><a href="/qna/get?currPage=${param.currPage}&amount=${param.amount}&qid=${QnaVO.qid}">${MywriteVO.title}</a></td>
						<td><a href="/freeboard/get?currPage=${param.currPage}&amount=${param.amount}&fid=${FreeBoardVO.fid}&type=${param.type}&keyword=${param.keyword}">${MywriteVO.title}</a></td>
						<td>${MywriteVO.insert_ts}</td>
					</tr>
				</c:forEach>
			</tbody>	
			
	    </table>
	  </div>
	</section>
	
<%@include file="/WEB-INF/views/main/footer.jsp" %>	

</body>
</html>