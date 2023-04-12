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
	font-family: "GangwonEdu";
}
@font-face {
	font-family: "GangwonEdu";
    src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEdu_OTFBoldA.woff")
      format("woff");
    font-weight: normal;
    font-style: normal;
  }
  h1 {
  text-align: center;
  	color: black;
  }
	</style>
    
    <link rel="stylesheet" href="WEB-INF/views/common/font.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/cerulean/bootstrap.min.css" integrity="sha384-3fdgwJw17Bi87e1QQ4fsLn4rUFqWw//KU0g8TvV6quvahISRewev6/EocKNuJmEw" crossorigin="anonymous">
    
</head>

<body>


	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	
	<br>
	<h1>QnA</h1>
	<br>

	<div id="wrapper">
	
	<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">No.</th>
      <th scope="col">제목</th>
      <th scope="col">글쓴사람</th>
      <th scope="col">쓴날짜</th>
      <th scope="col">수정날짜</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${list}" var="QnaVO">
    <tr class="table-primary">
		<td>${QnaVO.qid}</td>
		<td>
		<script>
		document.write(${QnaVO.openy_n}?'🔓':'🔒');
		//document.write((${AnswerDTO} != null)?'[답변완료]':'[답변대기]');
		</script>
		<a href="/qna/get?currPage=${param.currPage}&amount=${param.amount}&qid=${QnaVO.qid}">${QnaVO.title}</a></td>
		<td>${QnaVO.uids}</td>
		<td>${QnaVO.insert_ts}</td>
		<td>${QnaVO.update_ts}</td>
    </tr>
    </c:forEach>
  </tbody>
</table>
	</div>

	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>