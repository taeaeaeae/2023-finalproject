<%@page import="org.zerock.myapp.domain.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 | 목록</title>
<link rel="stylesheet" type="text/css" href="/resources/freeboard/css/freeboard_list.css">
</head>
<body>

<%@include file="/WEB-INF/views/common/header.jsp" %>

	<section>
	  <h1>공지사항</h1>
	  <hr>
	  <div id="list">
	    <table border="1px">
			<thead>
				<tr style="background-color: #eee; color: black">
					<th>글번호</th>
					<th id="tb2">제목</th>
					<th>작성자</th>
					<th>작성날짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty list}">
						<tr>
							<td colspan="5">${param.keyword}와 일치하는 게시글이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="NoticeVO" items="${list}">
							<tr>
								<td>${NoticeVO.nid}</td>
								<td><a href="/notice/get?currPage=${param.currPage}&amount=${param.amount}&nid=${NoticeVO.nid}&type=${param.type}&keyword=${param.keyword}">${NoticeVO.title}</a></td>
								<td>${NoticeVO.uids}</td>
								<td><fmt:formatDate value="${NoticeVO.insert_ts}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td>${NoticeVO.view_count}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>	
	    </table>
	  </div>
	   <c:if test="${sessionScope['__AUTH__'].uids eq 'admin'}">
           <button type="button" class="button" id="registerBtn">글 작성하기</button>
       </c:if>
	  
	</section>
	
	<div id="search">
		<form action="/notice/list" method="GET" id="search-form">
			<div id="search_area">
				<input type="hidden" name="currPage" value="1" id="currPage">
				<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
				<select name="type">
					<option value="title">제목</option>
					<option value="content">내용</option>
					<option value="title_content">제목 + 내용</option>
					<option value="uids">아이디</option>
				</select>
				<input type="text" name="keyword">
				<button type="submit">검색</button>
			</div>
		</form>
	</div>

	<div id="pagination">
		<form id="paginationForm">
			<c:if test="${pageMaker.prev}">
				<li class="prev">
					<a 	data-temp="${pageMaker.cri.setCurrPage(pageMaker.startPage - 1)}"
						href="/notice/list${pageMaker.cri.pagingUri}">PREV</a>
				</li>
			</c:if>

			<c:forEach var="pageNum" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
				<li class="${param.currPage eq pageNum ? 'currPage' : ''}">
					<a	data-temp="${pageMaker.cri.setCurrPage(pageNum)}"
						href="/notice/list${pageMaker.cri.pagingUri}">${pageNum}</a>
				</li>
			</c:forEach>

			<c:if test="${pageMaker.next}">
				<li class="next">
					<a  data-temp="${pageMaker.cri.setCurrPage(pageMaker.endPage + 1)}"
						href="/notice/list${pageMaker.cri.pagingUri}">NEXT</a>
				</li>
			</c:if>
		</form>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>	

<script>
var registerBtn = document.querySelector('#registerBtn');

registerBtn.addEventListener('click', function () {
    location = '/notice/register?currPage=${ param.currPage }&amount=${ param.amount }';
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
			url : "/notice/list",
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