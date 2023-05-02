<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.2/examples/album/" />


<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />

<link rel="stylesheet" href="${path}/resources/css/root.css" />

</head>
<body>
	<header class="p-3 mb-3 border-bottom">
		<div class="container">
			<div
				class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
				<a href="/main/index"
					class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
					<img src="https://picsum.photos/id/684/100/50" alt=""> </svg>
				</a>
	
				<ul
					class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
					<li><a href="/plan/list?num=1" class="nav-link px-2 link-secondary">루트공유게시판</a></li>
					<li><a href="/freeboard/list?currPage=1&amount=10" class="nav-link px-2 link-dark">자유게시판</a></li>
					<li><a href="/notice/list?currPage=1&amount=10" class="nav-link px-2 link-dark">공지사항</a></li>
					<li><a href="/qna/list?currPage=1&amount=10" class="nav-link px-2 link-dark">Q&A</a></li>
				</ul>
	
				<div class="dropdown text-end">					
									
					<!-- 로그인 체크 --> 
					<c:if test="${sessionScope['__AUTH__'] != null}">
						<ol class="breadcrumb">
							<li><a href="/mypage/main" class="nav-link px-2 link-dark">마이페이지</a></li>
							<li><a href="/user/logout" class="nav-link px-2 link-dark">로그아웃</a></li>	
						</ol>
		            </c:if> 
		            
		            <c:if test="${sessionScope['__AUTH__'] == null}">
		            	<ol class="breadcrumb">
		            		<li><a href="/user/login" class="nav-link px-2 link-dark">로그인</a></li>
						</ol>
		            </c:if>
				</div>
			</div>
		</div>
	</header>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
</body>
</html>
