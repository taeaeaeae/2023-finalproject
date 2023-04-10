<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>
    <meta charset="UTF-8">
    <title>게시글상세수정</title>
    <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
</head>

<body>
    <h1><%= request.getRequestURI() %></h1>
    <hr>

    <p>&nbsp;</p>
    <p>&nbsp;</p>

	<div id="wrapper">
		
		<form action="/board/modify" method="POST">
						
			<input type="hidden" name="currPage" value="${param.currPage}">
			<input type="hidden" name="amount" value="${param.amount}">
			
			<table>
				<caption></caption>
				<thead></thead>
				<tbody>
					<tr>
						<td>BNO</td>
						<td><input type="text" name="bno" value="${board.bno}" readonly></td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" name="title" value="${board.title}" size="70"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td> <textarea name="content" rows="10" cols="70">"${board.content}" </textarea></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td><input type="text" name="writer" value="${board.writer}" readonly></td>
					</tr>
				</tbody>
				<tfoot></tfoot>
			</table>
			

			<input type="button" value="삭제하기" id="removeBtn">
			<button type="button" id="listBtn">LIST</button>
			<button type="submit" id="submitBtn">MODIFY</button>
			
		</form>
		
	</div>
	<script>
		var listBtn = document.querySelector('#listBtn');
		var removeBtn = document.querySelector("#removeBtn");
		

		listBtn.addEventListener('click', function () {
			location.href='/board/list?currPage=${param.currPage}&amount=${param.amount}';
		})
		
		removeBtn.addEventListener('click', function () {
			var form = document.querySelector('form');
			$(form).attr('method', 'POST');
			$(form).attr('action', '/board/remove');
			$(form).submit();
		})
		
	</script>
</body>
</html>