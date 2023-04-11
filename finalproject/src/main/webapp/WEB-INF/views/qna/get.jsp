<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>QnA</title>
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
						<td>qna</td>
						<td>${qna.qid}</td>
					</tr>
					<tr>
						<td>제목</td>
						<td>${qna.title}</td>
					</tr>
					<tr>
						<td>내용</td>
						<img src="${qna.image}" alt="${qna.title}">
						<td>${qna.content}</td>
					</tr>
					<tr>
						<td>작성자</td>
						<td>${qna.uids}</td>
					</tr>
				</tbody>
				<tfoot></tfoot>
			</table>
			
			<table>
				<caption></caption>
				<thead></thead>
				
				<tbody>
					<tr>
						<td>answer</td>
						<td>${answer.qid}</td>
					</tr>
					<tr>
						<td>제목</td>
						<td>${answer.title}</td>
					</tr>
					<tr>
						<td>내용</td>
						<img src="${answer.image}" alt="${answer.title}">
						<td>${answer.content}</td>
					</tr>
					<tr>
						<td>작성자</td>
						<td>${answer.uids}</td>
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
			location.href='/qna/list?currPage=${param.currPage}&amount=${param.amount}';
		}
		
		modifyBtn.addEventListener('click', function () {
			self.location = "/qna/modify?currPage=${param.currPage}&amount=${param.amount}&qid=${qna.qid}";
		})
		
	</script>
</body>
</html>