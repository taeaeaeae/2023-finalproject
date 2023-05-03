<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="ko">
      <head>
        <meta charset="utf-8" />
        <title>TTT</title>
        <meta
          name="viewport"
          content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"
        />
    
      <link rel="stylesheet" href="/resources/css/index.css" />
      </head>
      <body>
        <div class="container">
          <header>
            <div class="logo-img">
              <h1>
                <a href="/main/index"></a>
              </h1>
            </div>
    
            <div class="menu-box">
              <div class="menu-link">
 					<c:if test="${sessionScope['__AUTH__'] != null}">
						<ul class="breadcrumb">
							<li><a href="/mypage/main" class="nav-link px-2 link-dark">마이페이지</a></li>
							<li><a href="/user/logout" class="nav-link px-2 link-dark">로그아웃</a></li>	
						</ul>
		            </c:if> 
		            
		            <c:if test="${sessionScope['__AUTH__'] == null}">
		            	<ul class="breadcrumb">
		            		<li><a href="/user/login" class="nav-link px-2 link-dark">로그인</a></li>
						</ul>
		            </c:if>
              </div>
    
              <div class="menu-list">
                <ul>
                  <style>
                    a {
                      text-decoration: none;
                      color: white;
                    }
                    a:visited {
                      text-decoration: none;
                    }
                    a:hover {
                      text-decoration: none;
                    }
                    a:focus {
                      text-decoration: none;
                    }
                    a:hover,
                    a:active {
                      text-decoration: none;
                    }
                  </style>
                  <li><a href="/plan/list?num=1">루트공유게시판</a></li>
                  <li><a href="/freeboard/list?currPage=1&amount=10"">자유게시판</a></li>
                  <li><a href="/notice/list?currPage=1&amount=10">공지사항</a></li>
                  <li><a href="/qna/list?currPage=1&amount=10">QnA</a></li>
                </ul>
              </div>
            </div>
          </header>
    
          <swiper-container class="mySwiper">
            <swiper-slide>
              <div class="content">
                <div class="left-content">
                  <h2>여행 루트 공유 사이트</h2>
                  <h3>TTT</h3>
                  <c:if test="${sessionScope['__AUTH__'] != null}">
							<button class="btn" onclick="location.href='/user/logout'">로그아웃</button>
		            </c:if> 
		            
		            <c:if test="${sessionScope['__AUTH__'] == null}">
		            		 <button class="btn" onclick="location.href='/user/login'">시작하기</button>
		            </c:if>
                </div>
                <div class="right-content">
                  <video src="${path}/resources/videos/video1.mp4" autoplay loop muted></video>
                </div>
              </div>
            </swiper-slide>
            <swiper-slide>
              <div class="content">
                <style>
                  h3 {
                    font-size: 15px;
                  }
                </style>
                <div class="left-content">
                  <h2>여러분들의 루트를 공유해보세요</h2>
                  <h3>루트 공유 게시판</h3>
    
                  <button class="btn" onclick="location.href='/plan/list?num=1'">공유하기</a></button>
                </div>
                <div class="right-content">
                  <video src="${path}/resources/videos/video2.mp4" autoplay loop muted></video>
                </div>
              </div>
            </swiper-slide>
            <swiper-slide
              ><div class="content">
                <div class="left-content">
                  <h2>자유롭게 글을 작성해보세요</h2>
                  <h3>자유<br />게시판</h3>
                  <button class="btn" onclick="location.href='/freeboard/list?currPage=1&amount=10'">공유하기</button>
                </div>
                <div class="right-content">
                  <video src="${path}/resources/videos/video3.mp4" autoplay loop muted></video>
                </div>
              </div>
            </swiper-slide>
            
            <swiper-slide>
              <div class="content">
                <div class="left-content">
                  <h2>NOTICE</h2>
                  <h3>공지<br />사항</h3>
                  <button class="btn" onclick="location.href='/notice/list?currPage=1&amount=10'">공유하기</button>
                </div>
                <div class="right-content">
                  <video src="${path}/resources/videos/video4.mp4" autoplay loop muted></video>
                </div>
              </div>
            </swiper-slide>
            <swiper-slide>
              <div class="content">
                <div class="left-content">
                  <h2>질문 게시판</h2>
                  <h3>Q&A</h3>
                  <button class="btn" onclick="location.href='/qna/list?currPage=1&amount=10'">공유하기</button>
                </div>
                <div class="right-content">
                  <video src="${path}/resources/videos/video5.mp4" autoplay loop muted></video>
                </div>
              </div>
            </swiper-slide>
          </swiper-container>
    
          <script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-element-bundle.min.js"></script>
        </div>
      </body>
    </html>
