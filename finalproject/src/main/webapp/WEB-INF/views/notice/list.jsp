<%@page import="org.zerock.myapp.domain.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 | 목록</title>
<style>
	 #ulululul {
	  width: 300px;
	  margin-left: auto;
	  margin-right: auto;
	  }
	.board_name {
	  text-align: center;
	  	color: black;
	  }
	.page-item.active .page-link, .btn.btn-primary{
		background-color: #D2EEFA;
		border-color: #FFF;
	}
	body {overflow:hidden;}
	a:link {text-decoration: none; color: black;}
	a:hover {text-decoration: underline; color: black;}
	a:visited {text-decoration: none; color: black;}
	a:active {text-decoration: none; color: black;}
</style>
<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/album/" />
</head>
<body>

<%@include file="/WEB-INF/views/common/header.jsp" %>

	<section>
		<br>
		<h1 class="board_name">공지사항</h1>
		<br>
		
	  	<div>
		  <div id="list">
		    <table class="table table-hover" style="width: 80%; margin-left:auto; margin-right:auto;">
				<thead>
					<tr>
						<th scope="col" width="10px">No.</th>
						<th scope="col">제목</th>
						<th scope="col">작성자</th>
						<th scope="col">작성일자</th>
						<th scope="col">조회수</th>
					</tr>
				</thead>
				<tbody>
						<c:forEach var="NoticeVO" items="${list}">
							<tr>
								<td>${NoticeVO.nid}</td>
								<td>
									<script>
										document.write(${NoticeVO.top}?'📌':' ');
									</script>
								<a href="/notice/get?currPage=${param.currPage}&amount=${param.amount}&nid=${NoticeVO.nid}&type=${param.type}&keyword=${param.keyword}">${NoticeVO.title}</a></td>
								<td>${NoticeVO.uids}</td>
								<td><fmt:formatDate value="${NoticeVO.insert_ts}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td>${NoticeVO.view_count}</td>
							</tr>
						</c:forEach>
				</tbody>	
		    </table>
		  </div>
		</div>
	  
	</section>
	
	  <c:if test="${sessionScope['__AUTH__'].uids eq 'admin'}">
           <button type="button" id="registerBtn" class="btn btn-secondary" style="margin-left:10%; width: 100px; background-color: #D2EEFA; border: none; color: black;"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">등록하기</font></font></button>
      </c:if>

<br>
<br>

<div style="text-align: center; margin: 0px;">
	<ul class="pagination pagination-sm" id="ulululul">
	    <li class="page-item disabled">
	      <a class="page-link" href="#"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">&laquo;</font></font></a>
	    </li>
	    
        <c:if test="${pageMaker.prev}">
			<li class="page-item disabled">
				<a class="page-link"  data-temp="${pageMaker.cri.setCurrPage(pageMaker.startPage - 1)}"
				href="/notice/list${pageMaker.cri.pagingUri}"><font style="vertical-align: inherit;">&laquo;</font></a>
			</li>
		</c:if>
                    
		<c:forEach var="pageNum" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			<c:if test="${param.currPage != pageNum}">
               <li class="page-item"><a class="page-link" data-temp="${pageMaker.cri.setCurrPage(pageNum)}" href="/notice/list${pageMaker.cri.pagingUri}">${pageNum}</a></li>
            </c:if>
            <c:if test="${param.currPage == pageNum}">
               <li class="page-item active" style="background-color: #D2EEFA" ><a class="page-link" data-temp="${pageMaker.cri.setCurrPage(pageNum)}" href="/notice/list${pageMaker.cri.pagingUri}">${pageNum}</a></li>
            </c:if>
		</c:forEach>
                    
		<c:if test="${pageMaker.next}">
			<li class="page-item">
				<a  data-temp="${pageMaker.cri.setCurrPage(pageMaker.endPage + 1)}"
				href="/notice/list${pageMaker.cri.pagingUri}">&raquo;</a>
			</li>
		</c:if>
		
	    <li class="page-item">
	      <a class="page-link" href="#"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">&raquo;</font></font></a>
	    </li>
  	</ul>
</div>

<br>

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
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
   crossorigin="anonymous"></script>
   
</body>
</html>