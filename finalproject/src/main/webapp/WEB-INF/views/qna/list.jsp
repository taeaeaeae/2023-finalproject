<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>QnA</title>
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
    		
    		background-color: black;
    		color: white;
    		font-size: 10px;
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
			
				<c:forEach items="${list}" var="QnaVO">
				<tr>
					<td>${QnaVO.qid}</td>
					<td>
					<script>
						document.write(${QnaVO.openy_n}?'🔓':'🔒');
						//document.write((${AnswerVO.qid} != null)?'[답변완료]':'[답변대기]');
					</script>
					<a href="/qna/get?currPage=${param.currPage}&amount=${param.amount}&qid=${QnaVO.qid}">${QnaVO.title}</a></td>
					<td>${QnaVO.uids}</td>
					<td>${QnaVO.insert_ts}</td>
					<td>${QnaVO.update_ts}</td>
				</tr>
				</c:forEach>
				
			</tbody>
			
			<tfoot></tfoot>
		</table>
	</div>

	

</body>
</html>