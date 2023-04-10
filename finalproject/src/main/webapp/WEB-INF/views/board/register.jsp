<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>신규게시글작성</title>
</head>

<body>
    <h1><%= request.getRequestURI() %></h1>
    <hr>
    
	<div id="wrapper">
		<form action="/board/register" method="POST">
			
			<input type="hidden" name="currPage" value="${param.currPage}">
			<input type="hidden" name="amount" value="${param.amount}">
			
			<table>
				<caption></caption>
				<thead></thead>
				<tbody>
					<tr>
						<td>제목</td>
						<td><input type="text" name="title"  size="70"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td> <textarea name="content" rows="10" cols="70"> </textarea></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td><input type="text" name="writer" value="You"></td>
					</tr>
				</tbody>
				<tfoot></tfoot>
			</table>
			

			<button type="button" id="listBtn">LIST</button>
			<button type="submit" id="submitBtn">registerBtn</button>
			
		</form>
		
	</div>
	
	<script>
		var listBtn = document.querySelector('#listBtn');
		


		listBtn.addEventListener('click', function () {
			location.href='/board/list?currPage=${param.currPage}&amount=${param.amount}';
		})

	</script>


</body>
</html>