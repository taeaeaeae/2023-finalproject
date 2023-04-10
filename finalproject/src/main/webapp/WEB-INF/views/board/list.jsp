<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판목록</title>
    <style>
    	* {
    		margin: 0 auto;
    		padding: 0;
    	}
    	table {
    		border: 1px ridge black;
    		border-collapse: collapse;
    	}
    	th {
    		padding: 10px;
    		
    		background-color: purple;
    		color: white;
    		font-size: 18px;
    	}
    	td {
    		padding: 5px;
    	}
    	tr:hover {
    		background-color: bisque;
    	}
    	a, a:link, a:visited {
    		text-decoration: none;
    		color: black;
    	}
    	#registerBtn {
    		width: 150px;
    		height: 40px;
    		border: 0;
    		margin-bottom: 10px;
    		
    		font-size: 19px;
    		font-weight: bold;
    		
    		background-color: black;
    		color: white;
    	}
    </style>
    
</head>

<body>
    <h1><%= request.getRequestURI() %></h1>
    <hr>
	
	<br>
	<br>

	<div id="wrapper">
		<table border="1">
			<caption>
				<button type="button" id="registerBtn">register</button>
			</caption>
			<thead>
				<tr>
					<th>No.</th>
					<th>제목</th>
					<th>writer</th>
					<th>insert_ts</th>
					<th>update_ts</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${list}" var="boardVO">
				<tr>
					<td>${boardVO.bno}</td>
					<td><a href="/board/get?currPage=${param.currPage}&amount=${param.amount}&bno=${boardVO.bno}">${boardVO.title}</a></td>
					<td>${boardVO.writer}</td>
					<td>${boardVO.insert_ts}</td>
					<td>${boardVO.update_ts}</td>
				</tr>
				</c:forEach>
				
			</tbody>
			
			<tfoot></tfoot>
		</table>
	</div>

	<script>
		var registerBtn = document.querySelector('#registerBtn');
		
		registerBtn.addEventListener('click', function () {
			location='/board/register?currPage=${param.currPage}&amount=${param.amount}';
		})
		

		
	</script>
	

</body>
</html>