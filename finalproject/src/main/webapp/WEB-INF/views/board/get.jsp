<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글상세조회</title>
	<style>
		#wrapper {
			font-size: 18px;
		}
	</style>

</head>
<body>
    <h1><%= request.getRequestURI() %></h1>
    <hr>
    
    <p>&nbsp;</p>
    <p>&nbsp;</p>

	<div id="wrapper">
		
		<form action="#">
			
			<table>
				<caption></caption>
				<thead></thead>
				<tbody>
					<tr>
						<td>BNO</td>
						<td>${board.bno}</td>
					</tr>
					<tr>
						<td>제목</td>
						<td>${board.title}</td>
					</tr>
					<tr>
						<td>내용</td>
						<td>${board.content}</td>
					</tr>
					<tr>
						<td>작성자</td>
						<td>${board.writer}</td>
					</tr>
				</tbody>
				<tfoot></tfoot>
			</table>
			
			<input type="button" value="수정하기" id="modifyBtn">
			<button type="button" id="listBtn">LIST</button>
			
		</form>
		
	</div>
	<script>
		var listBtn = document.querySelector('#listBtn');
		var modifyBtn = document.querySelector("#modifyBtn");
		
		listBtn.onclick = function () {
			location.href='/board/list?currPage=${param.currPage}&amount=${param.amount}';
		}
		
		modifyBtn.addEventListener('click', function () {
			self.location = "/board/modify?currPage=${param.currPage}&amount=${param.amount}&bno=${board.bno}";
		})
		
	</script>
</body>
</html>