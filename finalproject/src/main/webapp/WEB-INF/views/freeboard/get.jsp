<%@ page import="org.zerock.myapp.domain.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 | ${freeboard.title}</title>
<link rel="stylesheet" type="text/css" href="/resources/freeboard/css/freeboard_view.css">
</head>
<body>
<% 
  HttpSession se = request.getSession();
  LoginVO user = (LoginVO) session.getAttribute("__AUTH__"); 
  
  String userId = "";
  
  if (user != null) {
    userId = user.getUids();
  }
%>

<p>로그인한 유저 아이디: <%= userId %></p>
  <!-- 게시글 폼 -->
  <form action="/freeboard/get" method="post" class="board-post">
    <input type="hidden" name="currPage"value="${param.currPage}">
    <input type="hidden" name="amount"  value="${param.amount}">
    <input type="hidden" name="fid"     value="${freeboard.fid}">
    <input type="hidden" name="type"    value="${param.type}">
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
       	<button type="button" onclick="openReportPopup()" id="report-post-btn">게시글 신고하기</button>
      </div>
    </section>

    <section class="board-body">
      <hr class="board-divider">
      <c:if test="${not empty freeboard.image}"><img src="/resources${freeboard.image}" alt="${freeboard.title}"></c:if>
      <div class="post-content"><pre>${freeboard.content}</pre></div>
    </section>
  </form>
  
  <!-- 북마크 / 목록창 -->
  <p>${bookmarked}</p>
  <form action="/freeboard/list" method="post">
    <section class="list_form">
      <hr class="board-divider">
        <c:if test="${not registered}">
          <button class="bookmark_ico" onclick="toggleBookmark()">☆ 북마크 추가</button>
        </c:if>
        <c:if test="${registered}">
          <button class="bookmark_ico" onclick="toggleBookmark()">★ 북마크 취소</button>
        </c:if>

        <div class="list_button">
          <input type="hidden" name="fid"     value="${freeboard.fid}">
          <input type="hidden" name="currPage"value="${param.currPage}">
          <input type="hidden" name="amount"  value="${param.amount}">
          <input type="hidden" name="type"    value="${param.type}">
          <input type="hidden" name="keyword" value="${param.keyword}">
          
          <button type="button" id="prePostBtn"  class="button" ${empty prevFid ? 'disabled' : '' }>이전글</button>
          <c:if test="${not empty sessionScope['__AUTH__'] and sessionScope['__AUTH__'].uids eq freeboard.uids}">
            <button type="button" id="modifyBtn" class="button">수정</button>
            <button type="button" id="removeBtn" class="button" >삭제</button>
          </c:if>
          <button type="button"  id="listBtn"    class="button">목록</button>
          <button type="button" id="nextPostBtn" class="button" ${empty nextFid ? 'disabled' : '' }>다음글</button>
        </div>
      <hr class="board-divider">
    </section>
  </form>
    
  <!-- 댓글 -->
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
                <c:if test="${not empty sessionScope['__AUTH__'] and sessionScope['__AUTH__'].uids eq FreeBoardCommentVO.uids}">
                  <a href="#" class="comment-modify-link" data-fbcid="${FreeBoardCommentVO.fbcid}" data-uids="${FreeBoardCommentVO.uids}" data-content="${FreeBoardCommentVO.content}">수정</a>
                  <form action="/comment/remove" method="post">
                    <input type="hidden" name="fbcid" value="${FreeBoardCommentVO.fbcid}">
                    <button type="submit" class="comment-remove-btn">삭제</button>
                  </form>
                </c:if>
              </div>
            </div>
          </li>
        </c:forEach>
      </ul>
      
       <!-- 댓글 수정 -->
       <form class="commentModifyForm" style="display: none;"  >
         <input type="hidden" name="fid" value="${freeboard.fid}">
         <div class="form-group">
           <textarea class="form-control" id="comment-content" rows="3"></textarea>
         </div>
         <input type="hidden" id="comment_fbcid">
         <input type="hidden" id="comment_uids">
         <button type="button" class="commentModifyBtn">수정 완료</button>
       </form>
      
      <!-- 댓글 등록 -->
      <form class="comment-form" action="/comment/register" method="post" onsubmit="return commentForm()">
        <input type="hidden" name="fid" value="${freeboard.fid}">
        <input type="hidden" name="currPage" value="${param.currPage}">
        <input type="hidden" name="amount" value="${param.amount}">

        <div class="form-group">
          <label for="comment_content">댓글</label>
          <textarea name="content" id="comment_content" rows="3" ${empty sessionScope['__AUTH__'] ? 'disabled placeholder="로그인 후 댓글을 작성할 수 있습니다. 로그인해주세요."' : '' }></textarea>
        </div>
        <button type="submit" class="comment-submit-btn">댓글 작성</button>
      </form>
    </div>
  </section>
</body>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue"></script>
<script>
// 글 버튼
/* list button */
let listBtn = document.querySelector("#listBtn");

listBtn.addEventListener('click', function () {
  location.href="/freeboard/list?currPage=${param.currPage}&amount=${param.amount}&type=${param.type}&keyword=${param.keyword}";
});

/* remove button */
let removeBtn = document.querySelector("#removeBtn");

function confirmRemove() {
  const result = confirm("삭제하시겠습니까?");

  return result;
}

if (removeBtn) {
  removeBtn.addEventListener('click', function(event) {
    event.preventDefault(); // 기본 동작 중단
    if (confirmRemove()) {
      let form = document.querySelector('form');
      
      form.setAttribute('method', 'POST');
      form.setAttribute('action', '/freeboard/remove');
      form.submit();
    }
  });
}

/* modify button */
let modifyBtn = document.querySelector("#modifyBtn");

if(modifyBtn){
    modifyBtn.addEventListener('click', function () {
    location.href="/freeboard/modify?currPage=${param.currPage}&amount=${param.amount}&fid=${freeboard.fid}";
  });
};

// let modifyBtn = document.querySelector("#modifyBtn");

// if(modifyBtn){
//   modifyBtn.addEventListener('click', function() {
//     let form = document.querySelector('form');
    
//     form.setAttribute('method', 'POST');
//     form.setAttribute('action', '/freeboard/modify');
//     form.submit();
//   });
// };

/* prePost button */
let prePostBtn = document.querySelector("#prePostBtn");

prePostBtn.addEventListener('click', function() {
  
  if(prePostBtn.hasAttribute('disabled')){
    alert('이전 글이 없습니다.');
    return;
  };
  
  let form = document.querySelector('form');
  
  form.setAttribute('method', 'GET');
  form.setAttribute('action', '/freeboard/prev');
  form.submit();
});

/* nextPost button */
let nextPostBtn = document.querySelector("#nextPostBtn");

nextPostBtn.addEventListener('click', function() {
  if(nextPostBtn.hasAttribute('disabled')){
    alert('다음 글이 없습니다.');
    return;
  };
  let form = document.querySelector('form');
  
  form.setAttribute('method', 'GET');
  form.setAttribute('action', '/freeboard/next');
  form.submit();
});

// 댓글 버튼
/* commentModifyBtn button */
let commentModifyBtn = document.querySelector(".commentModifyBtn");

commentModifyBtn.addEventListener('click', function () {
  alert("수정 완료");
  location.href="/freeboard/get?currPage=${param.currPage}&amount=${param.amount}&fid=${freeboard.fid}&type=${param.type}&keyword=${param.keyword}";
});

/* removeComment button */
let commentRemoveLink = document.querySelector(".comment-remove-btn");

function confirmCommentRemove() {
  const result = confirm("댓글을 삭제하시겠습니까?");

  return result;
}

if (commentRemoveLink) {
  commentRemoveLink.addEventListener('click', function(event) {
    event.preventDefault(); // 기본 동작 중단
    if (confirmCommentRemove()) {
      let form = document.querySelector('form');
      
      form.setAttribute('method', 'POST');
      form.setAttribute('action', '/comment/remove');
      form.submit();
    }
  });
}

/* 신고창 관련 */
// 팝업창 띄우기
function openReportPopup() {
  window.open("/reports/register", "신고하기", "width=500, height=500");
};

/* 북마크 */
// function toggleBookmark() {
//   const xhr = new XMLHttpRequest(); // XMLHttpRequest 객체 생성
//   xhr.onreadystatechange = function() {
//     if (xhr.readyState === xhr.DONE) {
//       if (xhr.status === 200) {
//         const result = xhr.responseText;
//         if (result === 'true') {
//           // document.getElementById('bookmarkIcon').src = '/resources/bookmark/ico/bookmarked_ico.png';
//         } else if (result === 'false') {
//           // document.getElementById('bookmarkIcon').src = '/resources/bookmark/ico/bookmark_ico.png';
//         }
//       } else {
//         console.error('Failed to toggle bookmark');
//       }
//     }
//   };
  
//   const bookmarked = document.getElementById('bookmarked').value === 'true';
//   const fid = document.getElementById('fid').value;
  
//   const data = new FormData();
//   data.append('fid', fid);
//   data.append('bookmarked', bookmarked);
  
//   xhr.open('POST', '/bookmark');
//   xhr.send(data);
// }

function toggleBookmark(dto) {
  $.ajax({
    url : "/bookmark/register",
    type : "POST",
    data : JSON.stringify(dto),
    contentType : "application/json",
    success : function (result) {
      if(result === "registered"){
        alert("북마크가 등록되었습니다.");
      } else if(result === "unregistered"){
        alert("북마크가 취소되었습니다.");
      }
    },
    error : function (xhr, status, error) {
      console.error("Error occurred : " + error);     
    }
  });
}

</script>
<script src="/resources/freeboard/js/validateForm.js"></script>
<script src="/resources/freeboard/js/comment.js"></script>

</html>