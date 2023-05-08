<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
<!-- custom css -->
<link href="/resources/css/list.css" rel="stylesheet" type="text/css">
<title>공유 일정 목록</title>
<link rel="stylesheet" href="WEB-INF/views/common/font.css">
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.2/examples/album/" />
<style>
body{
  overflow: scroll;

}
#ulululul {
  width: 300px;
  margin-left: 554px;
  margin-top:70px; 
}
input[type=submit]{
  padding: 3px;
  margin: 10px;
  background-color: #eee;
  border-color: grey;
}
.board_name {
   text-align: center;
  	color: black;
}
section {
  height: 80%;
  text-align: center;
}
#search{
  position: static;
  text-align: right;
  padding-right: 11%;
}
#searchWord{
  padding: 4px;
}
.search-form {
   float:right;
}
#searchBtn{
    color: black;
    background-color: #D2EEFA;
    height: 40px;
    width: 117px;
}
.btn.btn-secondary{
	float:left;
}
</style>
</head>
<body>
<header>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
</header>

    <br>
	<h1 class="board_name">루트공유게시판</h1>
	<br>

	<!-- 계획 출력 시작 -->
	<c:if test="${users != null}">
		<div class="container">
			<div class="table-responsive">
				<!-- <table class="table table-striped"> -->
				<table class="table table-hover" style="width: 100%; margin-left:auto; margin-right:auto;">

					<thead>	
						<tr>
							<th class="col-md-1">번호</th>
							<th class="col-md-2">여행 시작날짜</th>
							<th class="col-md-1">일수</th>
							<th class="col-md-4">계획 제목</th>
							<th class="col-md-2">아이디</th>
							<th class="col-md-2">작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${planList}" var="planList">
							<tr>
								<td>${planList.pid}</td>
								<td><fmt:formatDate value="${planList.startDate}" pattern="yyyy-MM-dd"/></td>
								<td>${planList.planTotalDay} 일</td>
								<td><a href="<c:url value="/plan/view?pid=${planList.pid}&uids=${planList.uids}"/>">${planList.planTitle}</a></td>
								<td>${planList.uids}</td>
								<td><fmt:formatDate value="${planList.regDate}" pattern="yyyy-MM-dd"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table> 
				<!-- 계획 출력 끝 -->
				<!-- 페이징 -->
				<!-- <div class="col-md-offset-3">
					<ul class="pagination justify-content-center">
						<c:if test="${page.prev}">
							<li class="page-item"><a class="page-link" href="/plan/list?num=${page.startPageNum -1}${page.searchTypeAndKeyword}">이전</a></li>
						</c:if>
						<c:forEach begin="${page.startPageNum}" end="${page.endPageNum}" var="num">
							<c:if test="${page.num != num}">
								<li class="page-item"><a class="page-link" href="/plan/list?num=${num}${page.searchTypeAndKeyword}">${num}</a></li>
							</c:if>
							<c:if test="${page.num == num}">
								<li class="page-item active"><a class="page-link" href="/plan/list?num=${num}${page.searchTypeAndKeyword}">${num}</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${page.next}">
							<li class="page-item"><a class="page-link" href="/plan/list?num=${page.endPageNum +1}${page.searchTypeAndKeyword}">다음</a></li>
						</c:if>
					</ul>
				</div> --> 

				<!-- 페이징 끝 -->
				<div>
					<span class="search-form">
						<select name="searchType" class="form-select">
							<option value="planTitle" <c:if test="${page.searchType eq 'planTitle'}">selected</c:if>>제목</option>
							<option value="uids" <c:if test="${page.searchType eq 'uids'}">selected</c:if>>아이디</option>
						</select>
					 
						<input type="text" class="input-keyword" name="keyword" value="${page.keyword}"/>
						<button class="btn btn-primary" type="submit" id="searchBtn">검색</button>
					</span>
					<button type="button" class="btn btn-secondary"  onclick="location.href='/plan/write'">글쓰기</button>
				</div>

						<!-- 태경씨 페이징 -->
				<div style="text-align: center; margin: 0px;">
					<ul class="pagination pagination-sm" id="ulululul">
					<li class="page-item disabled">
						<a class="page-link" href="#"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">&laquo;</font></font></a>
					</li>
					<c:if test="${page.prev}">
						<li class="page-item"><a class="page-link" href="/plan/list?num=${page.startPageNum -1}${page.searchTypeAndKeyword}">이전</a></li>
					</c:if>
					<c:forEach begin="${page.startPageNum}" end="${page.endPageNum}" var="num">
						<c:if test="${page.num != num}">
							<li class="page-item"><a class="page-link" href="/plan/list?num=${num}${page.searchTypeAndKeyword}">${num}</a></li>
						</c:if>
						<c:if test="${page.num == num}">
							<li class="page-item active"><a class="page-link" href="/plan/list?num=${num}${page.searchTypeAndKeyword}">${num}</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${page.next}">
						<li class="page-item"><a class="page-link" href="/plan/list?num=${page.endPageNum +1}${page.searchTypeAndKeyword}">다음</a></li>
					</c:if>
					<li class="page-item">
						<a class="page-link" href="#"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">&raquo;</font></font></a>
					</li>
					</ul>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${users.uids == null}">
  		<script>
  			alert("로그인이 필요합니다.");
  			document.location.href="/";
  		</script>
	</c:if>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>  
	<script>
		document.getElementById("searchBtn").onclick = function () {
		 var searchType = document.getElementsByName("searchType")[0].value;
		 var keyword =  document.getElementsByName("keyword")[0].value;
		 console.log(searchType)
		 console.log(keyword)
		 location.href="/plan/list?num=1"+"&searchType="+searchType+"&keyword="+keyword;
		};
	</script>
	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8"
			crossorigin="anonymous">d</script> 
		

</body>
</html>