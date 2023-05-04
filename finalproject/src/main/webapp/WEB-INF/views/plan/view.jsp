<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- jquery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<!-- bootstrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
	<!-- custom, kakaoMap css -->
	<link href="/resources/css/write.css" rel="stylesheet" type="text/css">
	<title>일정 만들기</title>
</head>
<body id="body">
	<header id="header">
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	</header>
	<main id="main">
		<section id="container">
			<!-- 로그인 유무 확인/로그인 시 -->
			<c:if test="${users.uids != null}">
				<div class="row h-100">
			        <!-- KAKAO MAP VIEW 영역 시작-->
					<div class="col-8 map-area">
						<div class="map_wrap">
							<div id="map"
								style="width: 100%; height: 100%; position: relative; overflow: hidden;">
							</div>
							<ul id="category">
								<li id="AD5" data-order="0"><span class="category_bg hotel"></span>
									숙소</li>
								<li id="AT4" data-order="1"><span class="category_bg tour"></span>
									관광</li>
								<li id="FD6" data-order="2"><span class="category_bg restaurant"></span> 
									맛집</li>
								<li id="OL7" data-order="3"><span class="category_bg oil"></span>
									주유</li>
								<li id="CE7" data-order="4"><span class="category_bg cafe"></span>
									카페</li>
								<li id="MT1" data-order="5"><span class="category_bg mart"></span>
									마트</li>
							</ul>
							<button class="btn btn-primary col-sm-1" id="scheduleMarker-toggle" onclick="hideMarkers()">갈 곳</button>
						</div>
					</div>
					<!-- KAKAO MAP VIEW 영역 끝-->
		        	<div class="col-4 plan-area">
			        	<%@ include file="view_planner.jsp" %>
		    		</div>
	    		</div>
	    	</c:if>
	    	<!-- 로그인 유무 확인/비로그인 시 -->	
	    	<c:if test="${users.uids == null}">
	    		<p>로그인 후에 작성하실 수 있습니다.</p>
	    		<script>
	    			alert("로그인이 필요합니다.");
	    			document.location.href="/user/login";
	    		</script>
			</c:if>
		</section>
	</main>
</body>
<!-- KAKAO MAP JavaScript -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fff2cd2202ebff2b59d4cac25059f6f7&libraries=services"></script>
<!-- custom, kakaoMap JavaScript -->
<script type="text/javascript" src="/resources/js/view_write.js"></script>
<script type="text/javascript" src="/resources/js/view_planner.js"></script>
<!-- bootstrap -->	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8"
crossorigin="anonymous"></script>
</html>