<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>leftside.jsp</title>
</head>
	<style>
		*{
		    box-sizing: border-box;
		    font-family: "GangwonEdu";
		}
		@font-face {
		    font-family: "GangwonEdu";
		    src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEdu_OTFBoldA.woff")
		      format("woff");
		    font-weight: normal;
		    font-style: normal;
	  	}
		nav{
			width: 169px;
			margin-top: 5%;
			margin-left: 10%;
			border-right: none;
			position:fixed;
		}
		.menu li{
			list-style-type: none;
			margin-bottom: 10%;
		}
		.menu li a{
			height: 30px;
			line-height: 30px;
			display:block;
			font-size:18px;
			color: black;
		}
		.menu li a:hover {
			color: blue;
		}
	</style>
<body>
	<section>
		<nav>
				<ul class="menu">
					<li><a href="/mypage/update">회원정보수정</a></li><br>
					<li><a href="/mypage/remove">회원탈퇴</a></li><br>
					<li><a href="/mypage/mywrite">나의 작성 글</a></li><br>
					<li><a href="/mypage/mycomment">내가 쓴 댓글</a></li><br>
					<li><a href="/mypage/likes">좋아요 목록</a></li><br>
					<li><a href="/mypage/bookmark">북마크 목록</a></li><br>
					<li><a href="/mypage/checklist">체크리스트</a></li><br>
				</ul>
		</nav>		
	</section>
</body>
</html>