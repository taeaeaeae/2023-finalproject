<%@ page import="org.zerock.myapp.domain.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 | ${noticeboard.title}</title>
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

<%@include file="/WEB-INF/views/common/header.jsp" %>

  <!-- 게시글 폼 -->
  <form action="/notice/get" method="post" class="board-post">
    <input type="hidden" name="currPage"value="${param.currPage}">
    <input type="hidden" name="amount"  value="${param.amount}">
    <input type="hidden" name="nid"     value="${noticeboard.nid}">
    <input type="hidden" name="type"    value="${param.type}">
    <input type="hidden" name="keyword" value="${param.keyword}">
  
    <section class="board-header">
      <h1 class="board-title">공지사항</h1>
      <hr class="board-divider">
      <h3 class="post-title">${noticeboard.title}</h3>
      <div class="post-meta">
        <span class="post-number">글번호 : ${noticeboard.nid}</span>
        <span class="post-author">작성자 : ${noticeboard.uids}</span>
        <span class="post-date">작성일 : <fmt:formatDate value="${noticeboard.insert_ts}" pattern="yyyy-MM-dd HH:mm"/></span>
        <span class="post-views">조회수 : ${noticeboard.view_count}</span>
      </div>
    </section>

    <section class="board-body">
      <hr class="board-divider">
      <c:if test="${not empty noticeboard.image}"><img src="/resources${noticeboard.image}" alt="${noticeboard.title}"></c:if>
      <div class="post-content"><pre style="font-family: 'GangwonEdu'; ">${noticeboard.content}</pre></div>
    </section>
  </form>
  
  <!-- 목록창 -->
  
  <form action="/notice/list" method="post">
    <section class="list_form">
      <hr class="board-divider">

        <div class="list_button">
          <input type="hidden" name="nid"     value="${noticeboard.nid}">
          <input type="hidden" name="currPage"value="${param.currPage}">
          <input type="hidden" name="amount"  value="${param.amount}">
          <input type="hidden" name="type"    value="${param.type}">
          <input type="hidden" name="keyword" value="${param.keyword}">
          
          <button type="button" id="prePostBtn"  class="button" ${empty prevNid ? 'disabled' : '' }>이전글</button>
         <c:if test="${sessionScope['__AUTH__'].uids eq 'admin'}">
            <button type="button" id="modifyBtn" class="button">수정</button>
            <button type="button" id="removeBtn" class="button" >삭제</button>
          </c:if>
          <button type="button"  id="listBtn"    class="button">목록</button>
          <button type="button" id="nextPostBtn" class="button" ${empty nextNid ? 'disabled' : '' }>다음글</button>
        </div>
      <hr class="board-divider">
    </section>
  </form>
    <%@include file="/WEB-INF/views/common/footer.jsp" %>	
</body>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue"></script>
<script>
// 글 버튼
/* list button */
let listBtn = document.querySelector("#listBtn");

listBtn.addEventListener('click', function () {
  location.href="/notice/list?currPage=${param.currPage}&amount=${param.amount}&type=${param.type}&keyword=${param.keyword}";
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
      form.setAttribute('action', '/notice/remove');
      form.submit();
    }
  });
}

/* modify button */
let modifyBtn = document.querySelector("#modifyBtn");

if(modifyBtn){
    modifyBtn.addEventListener('click', function () {
    location.href="/notice/modify?currPage=${param.currPage}&amount=${param.amount}&nid=${noticeboard.nid}";
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
  form.setAttribute('action', '/notice/prev');
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
  form.setAttribute('action', '/notice/next');
  form.submit();
});


</script>
<script src="/resources/freeboard/js/validateForm.js"></script>
<script src="/resources/freeboard/js/comment.js"></script>

</html>