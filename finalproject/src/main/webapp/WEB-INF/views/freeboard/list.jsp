<%@page import="org.zerock.myapp.domain.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 | 목록</title>
<!--  <link rel="stylesheet" type="text/css" href="/resources/freeboard/css/freeboard_list.css"> -->
<style>
body {
	overflow-y:scroll;
}

#list_ul {
	width: 300px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 70px;
}
.board_name {
	text-align: center;
	color: black;
}
.page-item.active .page-link, .btn.btn-primary{
	background-color: #D2EEFA;
	border-color: #FFF;
}

#title_name:hover{
	text-decoration-line: none;
}
#search{
	float: right;
	margin-right: 10%;
}
#registerBtn{
	float: left;
	margin-left:10%; 
	width: 100px; 
	
	background-color: #D2EEFA; 
	color: black;
	border: none;
	border-radius: 5px;
}
a:link {text-decoration: none; color: black;}
a:hover {text-decoration: none; color: black;}
a:visited {text-decoration: none; color: black;}
a:active {text-decoration: none; color: black;}
</style>
<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/album/" />
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<section>
		<h1 class="board_name"><a href="/freeboard/list?currPage=1&amount=10" id="title_name">자유게시판</a></h1>
		<hr>
		<div id="list">
			<table class="table table-hover" style="width: 80%; margin-left:auto; margin-right:auto;">
				<thead>
					<tr>
						<th>No.</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일자</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty list}">
							<tr>
								<td colspan="10">${param.keyword}와 일치하는 게시글이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="FreeBoardVO" items="${list}">
								<tr>
									<td>${FreeBoardVO.fid}</td>
									<td><a href="/freeboard/get?currPage=${param.currPage}&amount=${param.amount}&fid=${FreeBoardVO.fid}&type=${param.type}&keyword=${param.keyword}">${FreeBoardVO.title}</a></td>
									<td>${FreeBoardVO.uids}</td>
									<td><fmt:formatDate value="${FreeBoardVO.insert_ts}" pattern="yyyy-MM-dd HH:mm"/></td>
									<td>${FreeBoardVO.view_count}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	  	<button type="button" class="btn btn-secondary" id="registerBtn">
			<font style="vertical-align: inherit;">등록하기</font>
		</button>
	</section>
	
	<div id="search">
		<form action="/freeboard/list" method="GET" id="search-form">
			<div id="search_area">
				<input type="hidden" name="currPage" value="1" id="currPage">
				<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
				<select name="type" style="height:30px">
					<option value="title">제목</option>
					<option value="content">내용</option>
					<option value="title_content">제목 + 내용</option>
					<option value="uids">아이디</option>
				</select>
				<input type="text" name="keyword" >
				<button type="submit" class="btn btn-secondary" style="background-color: #D2EEFA; border: none; color: black;">검색</button>
			</div>
		</form>
	</div>

	<div id="pagination" style="text-align: center; margin: 0 auto;">
		<ul class="pagination pagination-sm" id="list_ul">
			<c:if test="${pageMaker.prev}">
				<a class="page-link" data-temp="${pageMaker.cri.setCurrPage(pageMaker.startPage - 1)}"
					href="/freeboard/list${pageMaker.cri.pagingUri}">
					<font style="vertical-align: inherit;">
						<font style="vertical-align: inherit;">&laquo;</font>
					</font>
				</a>
			</c:if>

			<c:forEach var="pageNum" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
				<li class="page-item">
				<!-- <li class="${param.currPage eq pageNum ? 'currPage' : ''}"> -->
					<a class="page-link" data-temp="${pageMaker.cri.setCurrPage(pageNum)}"
						href="/freeboard/list${pageMaker.cri.pagingUri}">${pageNum}</a>
				</li>
			</c:forEach>

			<c:if test="${pageMaker.next}">
				<li class="page-item">
					<a class="page-link" data-temp="${pageMaker.cri.setCurrPage(pageMaker.endPage + 1)}"
						href="/freeboard/list${pageMaker.cri.pagingUri}">
						<font style="vertical-align: inherit;">
							<font style="vertical-align: inherit;">&raquo;</font>
						</font>
					</a>
				</li>
			</c:if>
		</ul>
	</div>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>  
<script>
var registerBtn = document.querySelector('#registerBtn');

registerBtn.addEventListener('click', function () {
    location = '/freeboard/register?currPage=${ param.currPage }&amount=${ param.amount }';
}); // .addEventListener

var result = "${ param.result }";

if(result != null && result != ""){
    alert('result : ' + result )
} // if

var prevAndNext = document.querySelectorAll('a.Prev, a.Next');
console.log('prevAndNext: ', prevAndNext);

prevAndNext.addEventListener('click', function (e) {
    e.preventDefault();
    
    console.log('clicekd....');
}); // .addEventListener

$(document).ready(function() {
	// 폼 전송 이벤트 바인딩
	$('#search-form').submit(function(event) {
		event.preventDefault();
		search();
	});

	// 검색 함수
	function search() {
		var formData = $('#search-form').serialize();

		$('#currPage').val(1);

		$.ajax({
			type : "get",
			url : "/freeboard/list",
			data : formData,
			success : function(response) {
				$('#result').html(response);
			}
		});
	}
});
</script>
<script src="/resources/freeboard/js/validateForm.js"></script>
</body>
</html>