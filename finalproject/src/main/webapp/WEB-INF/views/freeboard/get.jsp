<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	Object obj = request.getAttribute("freeboard");
	System.out.println("*********** obj: " + obj);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 | ${freeboard.title}</title>
<link rel="stylesheet" type="text/css" href="/resources/freeboard/css/freeboard_view.css">

</head>
<body>
    <!-- <form action="/freeboard/get" method="post">
      <input type="hidden" name="currPage" value="${param.currPage}">
      <input type="hidden" name="amount" value="${param.amount}">
      <input type="hidden" name="fid" value="${freeboard.fid}">
      <input type="hidden" name="type" value="${param.type}">
      <input type="hidden" name="keyword" value="${param.keyword}">
      
      
      <section class="head">
        <h1>자유게시판</h1>
        <hr>
        <h3>제목 : ${freeboard.title}</h3>
        <div class="board_title">
          <div class="info">
            <span class="fid">글번호 : ${freeboard.fid}</span>
            <span class="nickname">작성자 : ${freeboard.uids}</span>
            <span class="day">작성일 : ${freeboard.insert_ts}</span>
          </div>	
          <span class="visitcount">조회수 : ${freeboard.view_count}</span>
        </div>
      </section>
      
      <section class="main">
        <hr>
        <td>${freeboard.content}</td>
        <hr>
      </section>
    </form> -->

    <form action="/freeboard/get" method="post" class="board-post">
      <input type="hidden" name="currPage" value="${param.currPage}">
      <input type="hidden" name="amount" value="${param.amount}">
      <input type="hidden" name="fid" value="${freeboard.fid}">
      <input type="hidden" name="type" value="${param.type}">
      <input type="hidden" name="keyword" value="${param.keyword}">
    
      <section class="board-header">
        <h1 class="board-title">자유게시판</h1>
        <hr class="board-divider">
        <h3 class="post-title">${freeboard.title}</h3>
        <div class="post-meta">
          <span class="post-number">글번호 : ${freeboard.fid}</span>
          <span class="post-author">작성자 : ${freeboard.uids}</span>
          <span class="post-date">작성일 : <fmt:formatDate value="${freeboard.insert_ts}" pattern="yyyy-MM-dd HH:mm"/></span>
          <span class="post-views">조회수 : ${freeboard.view_count}</span>
        </div>
      </section>
    
      <section class="board-body">
        <hr class="board-divider">
        <div class="post-content">${freeboard.content}</div>
      </section>
    </form>
    
      
    <!-- <section class="comment">
      <div class="comments">[댓글창]
        <c:forEach var="FreeBoardCommentVO" items="${commentList}">
          <li>
            <div>
              <p>${FreeBoardCommentVO.uids} | <fmt:formatDate value="${FreeBoardCommentVO.insert_ts}" pattern="yyyy-MM-dd HH:mm"/></p>
              <p>${FreeBoardCommentVO.content}</p>
              <form action="/comment/remove" method="post">
                <a href="#" class="commentModifyLink" data-fbcid="${FreeBoardCommentVO.fbcid}" data-uids="${FreeBoardCommentVO.uids}" data-content="${FreeBoardCommentVO.content}">
                  <button type="submit">수정</button></a> 
                  <input type="hidden" name="fbcid" value="${FreeBoardCommentVO.fbcid}">
                  <a href="#" class="commentRemoveLink" onclick="return confirmCommentRemove()">
                  <button type="submit">삭제</button></a>
                </form>
            </div>
          </li>
        </c:forEach>
          
        <form class="commentModifyForm" style="display: none;">
          <input type="hidden" name="fid" value="${freeboard.fid}">
          <div class="form-group">
            <textarea class="form-control" id="comment-content" rows="3"></textarea>
          </div>
          <input type="hidden" id="comment_fbcid">
          <input type="hidden" id="comment_uids">
          <button type="button" class="commentModifyBtn">수정 완료</button>
        </form>
        
        <form action="/comment/register" method="post" onsubmit="return commentForm();">
            <input type="hidden" name="fid" value="${freeboard.fid}">
            <input type="hidden" name="currPage" value="${param.currPage}">
            <input type="hidden" name="amount" value="${param.amount}">
            <label for="comment-input"></label>
            <input type="text" name="uids" placeholder="아이디를 작성하세요">
            <br>
            <textarea name="content" id="comment_Content" cols="100" rows="10" placeholder="댓글을 작성하세요."></textarea>
            <br>
            <button type="submit">댓글 작성</button>
        </form>
      </div>
    </section> -->
    
    <section class="comment">
      <div class="comment-wrap">
        <h2 class="comment-title">댓글</h2>
        <ul class="comment-list">
          <c:forEach var="FreeBoardCommentVO" items="${commentList}">
            <li class="comment-item">
              <div class="comment-info">
                <p class="comment-author">${FreeBoardCommentVO.uids} | <fmt:formatDate value="${FreeBoardCommentVO.insert_ts}" pattern="yyyy-MM-dd HH:mm"/></p>
                <p class="comment-content">${FreeBoardCommentVO.content}</p>
                <div class="comment-actions">
                  <a href="#" class="comment-modify-link" data-fbcid="${FreeBoardCommentVO.fbcid}" data-uids="${FreeBoardCommentVO.uids}" data-content="${FreeBoardCommentVO.content}">수정</a>
                  <form action="/comment/remove" method="post" onsubmit="return confirmCommentRemove()">
                    <input type="hidden" name="fbcid" value="${FreeBoardCommentVO.fbcid}">
                    <button type="submit" class="comment-remove-btn">삭제</button>
                  </form>
                </div>
              </div>
            </li>
          </c:forEach>
        </ul>

        <form class="commentModifyForm" style="display: none;">
          <input type="hidden" name="fid" value="${freeboard.fid}">
          <div class="form-group">
            <textarea class="form-control" id="comment-content" rows="3"></textarea>
          </div>
          <input type="hidden" id="comment_fbcid">
          <input type="hidden" id="comment_uids">
          <button type="button" class="commentModifyBtn">수정 완료</button>
        </form>
        
        <form class="comment-form" action="/comment/register" method="post" onsubmit="return commentForm()">
          <input type="hidden" name="fid" value="${freeboard.fid}">
          <input type="hidden" name="currPage" value="${param.currPage}">
          <input type="hidden" name="amount" value="${param.amount}">
          <div class="form-group">
            <label for="comment-uids">아이디</label>
            <input type="text" name="uids" id="comment-uids">
          </div>
          <div class="form-group">
            <label for="comment-content">댓글</label>
            <textarea name="content" id="comment-content" rows="3"></textarea>
          </div>
          <button type="submit" class="comment-submit-btn">댓글 작성</button>
        </form>
      </div>
    </section>
    
      
    <div class="list_button">
      <button type="button" id="prePostBtn" class="button">이전글</button>
      <button type="submit" id="modifyBtn" class="button">수정</button>
      <button type="button" id="removeBtn" class="button">삭제</button>
      <button type="button" id="listBtn" class="button">목록</button>
      <button type="button" id="nextPostBtn" class="button">다음글</button>
    </div>
</body>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="/resources/freeboard/js/validateForm.js"></script>
<script src="/resources/freeboard/js/comment.js"></script>
<script>
/* list button */
let listBtn = document.querySelector("#listBtn");

listBtn.addEventListener('click', function () {
  location.href="/freeboard/list?currPage=${param.currPage}&amount=${param.amount}&type=${param.type}&keyword=${param.keyword}";
});
  
/* commentModifyBtn button */
let commentModifyBtn = document.querySelector(".commentModifyBtn");

commentModifyBtn.addEventListener('click', function () {
  alert("수정 완료");
  location.href="/freeboard/get?currPage=${param.currPage}&amount=${param.amount}&fid=${freeboard.fid}&type=${param.type}&keyword=${param.keyword}";
});
  
/* remove button */
let removeBtn = document.querySelector("#removeBtn");

removeBtn.addEventListener('click', function() {
  let form = document.querySelector('form');
  
  form.setAttribute('method', 'POST');
  form.setAttribute('action', '/freeboard/remove');
  form.submit();
});

/* removeComment button */
let commentRemoveLink = document.querySelector(".comment-remove-btn");

if(commentRemoveLink){
  commentRemoveLink.addEventListener('click', function() {
    console.log("commentRemoveLink clicked.");
    let form = document.querySelector('form');
    
    form.setAttribute('method', 'POST');
    form.setAttribute('action', '/comment/remove');
    form.submit();
  });
};

/* modify button */
let modifyBtn = document.querySelector("#modifyBtn");

modifyBtn.addEventListener('click', function () {
  location.href="/freeboard/modify?currPage=${param.currPage}&amount=${param.amount}&fid=${freeboard.fid}";
});

/* prePost button */
let prePostBtn = document.querySelector("#prePostBtn");

prePostBtn.addEventListener('click', function() {
  let form = document.querySelector('form');
  
  form.setAttribute('method', 'GET');
  form.setAttribute('action', '/freeboard/prev');
  form.submit();
});

/* nextPost button */
let nextPostBtn = document.querySelector("#nextPostBtn");

nextPostBtn.addEventListener('click', function() {
  let form = document.querySelector('form');
  
  form.setAttribute('method', 'GET');
  form.setAttribute('action', '/freeboard/next');

  if (nextPostBtn.getAttribute('disabled') === null) {
    form.submit();
  }
  // if(!nextPostBtn.disabled){
  //   form.submit();
  // }
});

/* commentForm */
function commentForm() {
  const content = document.getElementById("commentContent").value;
  
  if(content.trim() === ""){
    alert("댓글 내용을 입력해주세요.");
    document.getElementById("commentContent").focus();
    return false;
  }
  return true;
};
</script>

</html>