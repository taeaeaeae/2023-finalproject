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
<%@include file="/WEB-INF/views/common/header.jsp" %>
<%@include file="/WEB-INF/views/common/leftside.jsp" %>
	
	<section>
		<h1 style="text-align: center">체크리스트</h1>
		
			<div id="add" style="text-align: center;">
				<form action="/mypage/listadd" method="POST">
			  		<input type="hidden" name="uids" value="${uids.uids}">
					<input type="text" style="width:300px" maxlength="20" name="item" value="${ChecklistVO.item}" placeholder="아이템" required>
					<input type="text" style="width:600px" maxlength="50" name="memo" value="${ChecklistVO.memo}" placeholder="메모" required>
					<button type="submit" class="btn btn-default btn-sm">추가</button>
				</form>
			</div>
			
			<br>
			
			<div id=table style="margin-left: auto; margin-right: auto;">
		    <table id="table" class="table" style="margin-left: auto; margin-right: auto; width: 300px;">
				<thead>
					<tr>
						<th></th>
						<th>아이템</th>
						<th>메모</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				
				<tbody id="listBody">
					<c:forEach var="ChecklistVO" items="${list}">
						<tr>
							<form action="/mypage/listupdate" method="POST">
								<td>
									<input type="checkbox" style= "float: right" name="checkbox" value="1" id="input_check" <c:if test="${ChecklistVO.checkbox == 1}">checked</c:if>>
									<input type="hidden" name="checkbox" value="0" id="input_check_hidden"/>
								</td>
								<td style="width:300px">
									<input type="text" style="width:300px" maxlength="20" name="item" value="${ChecklistVO.item}">
								</td>
								<td style="width:600px">
									<input type="text" style="width:600px" maxlength="50" name="memo" value="${ChecklistVO.memo}">
								</td>
						        <td style="width:50px">
							        <input type="hidden" name="cid" value="${ChecklistVO.cid}">
							        <button type="submit" style="width:50px" class="btn btn-default btn-sm">수정</button>
						        </td>
						    </form>
							<form action="/mypage/listdelete" method="POST">
								<td style="width:50px">
							        <input type="hidden" name="cid" value="${ChecklistVO.cid}">
							        <button type="submit" style="width:50px" class="btn btn-default btn-sm">삭제</button>
							    </td>
							</form>
						</tr>
					</c:forEach>
				    </tbody>
				    
				</table>
			</div>	
		</section>
		

<script>

	// 체크박스
	if(document.getElementById("input_check").checked) {
	    document.getElementById("input_check_hidden").disabled = true;
	}
	
</script>		

</body>
</html>