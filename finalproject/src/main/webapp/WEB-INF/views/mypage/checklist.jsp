<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
rel="stylesheet"
integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
crossorigin="anonymous" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
<title>checkList</title>
</head>
<body>

<%@include file="/WEB-INF/views/main/header.jsp" %>
	
	<section>
		<h1>체크리스트</h1>
		<hr>
		  <div id="list">
		    <table id="more_list">
				<thead>
					<tr style="background-color: #eee; color: black">
						<th>체크</th> <!-- 체크가 데이터베이스에 잘 안되고있음 -->
						<th>아이템</th>
						<th>메모</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="ChecklistVO" items="${list}">
						<tr>
							<form action="/mypage/listupdate" method="POST">
								<td>
									<input type="checkbox" name="checks" >
								</td>
								<td>
									<input type="text" name="item" value="${ChecklistVO.item}">
								</td>
								<td>
									<input type="text" name="memo" value="${ChecklistVO.memo}">
								</td>
						        <td>
							        <input type="hidden" name="cid" value="${ChecklistVO.cid}">
							        <button type="submit" class="btn btn-default btn-sm">수정</button>
						        </td>
						    </form>
							<form action="/mypage/listdelete" method="POST">
								<td>
							        <input type="hidden" name="cid" value="${ChecklistVO.cid}">
							        <button type="submit" class="btn btn-default btn-sm">삭제</button>
							    </td>
							</form>
						</tr>
					</c:forEach>
				</tbody>				
		    </table>

			<br>	  
			<br>
			
		  	<form action="/mypage/listadd" method="POST">
		  		<input type="hidden" name="uids" value="${uids.uids}">
				<input type="text" name="item" value="${ChecklistVO.item}">
				<input type="text" name="memo" value="${ChecklistVO.memo}">
				<button type="submit" class="btn btn-default btn-sm">추가</button>
			</form>
			
		</section>
		
<%@include file="/WEB-INF/views/main/footer.jsp" %>
	
	

	
	

</body>
</html>