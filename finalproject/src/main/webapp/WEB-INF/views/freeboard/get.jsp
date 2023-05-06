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
<%@ include file="/WEB-INF/views/common/header.jsp" %>
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
       	
      </div>
    </section>

    <section class="board-body">
      <hr class="board-divider">
      <c:if test="${not empty freeboard.image}"><img src="/resources${freeboard.image}" alt="${freeboard.title}"></c:if>
      <div class="post-content"><pre>${freeboard.content}</pre></div>
    </section>
  </form>
  
  <!-- 북마크 / 목록창 -->
  <form action="/freeboard/list" method="post">
    <section class="list_form">
      <hr class="board-divider">
        <div class="bookmark-container">
          <button type="button" class="bookmark" onclick="toggleBookmark(${freeboard.fid}, ${isBookmarked})">
            ${isBookmarked ? "★ 북마크 취소" : "☆ 북마크 추가"}
          </button>
          <button type="button" onclick="openReportPopup()" id="report-post-btn">게시글 신고하기</button>
        </div>

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
    
  <!-- 댓글 등록-->
  <section class="comment">
    <div class="comment-wrap">
      <h2 class="comment-title">댓글</h2>
      <p>[${commentCount}]개의 댓글이 달렸습니다.</p>
      <ul class="comment-list" ></ul>

      <div class="form-group">
        <label for="comment-content">댓글 작성창</label>
        <input type="hidden" name="auth_uids" value="${loginVO.uids}">
        <input type="hidden" name="uids" value="${loginVO.uids}">
        <input type="hidden" name="fid" value="${freeboard.fid}"> 
        <textarea name="content" id="comment_content" cols="30" rows="10" ${empty sessionScope['__AUTH__'] ? 'disabled placeholder="로그인 후 댓글을 작성할 수 있습니다. 로그인해주세요."' : '' }></textarea>
        <button type="button" id="comment-submit-btn">댓글 작성</button>
      </div>
    </div>
  </section>
  
<%@ include file="/WEB-INF/views/common/footer.jsp" %> 
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.1/jquery-migrate.min.js"></script>
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
// removeCommentBtn(댓글 삭제 버튼)
function removeComment(fbcid) {
  $.ajax({
    type : 'delete',
    url : '/comment/remove/' + fbcid,
    headers : {
      'Content-type' : 'application/json',
      'X-HTTP-Method-Override' : 'POST'
    },
    dataType : 'text',
    success : function (result) {
      if(result === 'success'){
        getAllList();
      } else{
        alert('댓글 삭제에 실패하셨습니다.');
      } // if-else
    } // success
  });
}

/* comment 등록 */
let fid = $('input[name=fid]').val();

$.getJSON("/comment/list/" + fid, function (data) {
    
    $(data).each(
        function () {
            console.log(this);
            console.log("------------");
        });
    console.log(data.length);
});

// 댓글 list javascript
function getAllList() {
  let htmls = "";

  $.getJSON("/comment/list/" + fid, function (data) {
    if(data.length === 0){
      htmls = "<p>등록된 댓글이 없습니다. 첫번째 댓글을 작성해보세요.</p>";
    } else{
      $(data).each(
          function () {
            htmls += `<li class='comment-item'>`;
            htmls += `<div class='comment-info'>`;
            htmls += `<p class='comment-author'> \${this.uids} | \${this.insert_ts}</p>`;
            htmls += `<p class='comment-content'>\${this.content}</p>`;
            htmls += `<div class='comment-actions'>`;
  
            let currentUserId = $("input[name='auth_uids']").val();
  
            if (currentUserId && this.uids === currentUserId) {
              htmls += '<button type="button" class="comment-modify-btn" data-comment=\'' + JSON.stringify(this) + '\' onclick="commentModify(event)">수정</button>';
              htmls += '<button type="button" class="comment-remove-btn" data-fbcid="' + this.fbcid + '">삭제</button>';
            }
  
            htmls += `</div></div></li>`;
  
        });
    }
    $(".comment-list").html(htmls);

    $(".comment-remove-btn").click(function () {
      let fbcid = $(this).data("fbcid");
      removeComment(fbcid);
    });
  });
}

getAllList();

// 댓글 등록 js
$("#comment-submit-btn").on("click", function () {
    let uids = $('input[name=uids]').val();
    let content = $("#comment_content").val();

    $.ajax({
        type : 'post',
        url : '/comment/register',
        headers : {
            "Content-type" : "application/json",
            "X-HTTP-Method-Override" : "POST"
        },
        dataType : "text",
        data : JSON.stringify({
            fid : fid,
            uids : uids,
            content : content
        }),
        success : function (result) {
            if(result == 'success'){
              getAllList();
            } // if
        } // success
    }); // ajax
}); // jq

// 댓글 수정 폼
function commentModify(event) {
  let data = JSON.parse($(event.target).attr('data-comment'));
  
  let $commentItem = $(event.target).closest(".comment-item");
  let $commentForm = $commentItem.find(".commentModifyForm");

  if($commentForm.length === 0) {
    let ModifyFormHtml = 
    `<form class="commentModifyForm" style="display: none;">
      <div class="comment-form">
        <textarea class="comment-input" id="comment-modify-content" rows="3"></textarea>
      </div>
      <button type="button" class="comment-save-btn">저장</button>
      <button type="button" class="comment-cancel-btn">취소</button>
    </form>`;
    $commentItem.append(ModifyFormHtml);
    $commentForm = $commentItem.find(".commentModifyForm");
  }
  
  let $commentContent = $commentItem.find(".comment-content");
  let $commentInput = $commentForm.find(".comment-input");

  $commentInput.val(data.content);
  $commentContent.hide();
  $commentForm.show();

  // 저장 버튼 클릭 시 댓글 내용 수정 요청 보내기
  $commentForm.find(".comment-save-btn").off().click(function(event) {
    event.stopImmediatePropagation(); // 이벤트 전파 중지
    
    let fbcid = data.fbcid;
    let content = $commentInput.val();

    $.ajax({
      type: 'post',
      url: `/comment/modify/` + fbcid,
      headers: {
        "Content-type": "application/json",
        "X-HTTP-Method-Override": "POST"
      },
      dataType: "text",
      data: JSON.stringify({
        content: content
      }),
      success: function(result) {
        if (result === 'success') {
          // 댓글 수정 성공 시 화면 업데이트
          data.content = content;
          $commentForm.hide();
          $commentContent.text(content).show();
        }
      }
    });
  });

  $commentForm.find(".comment-cancel-btn").off().click(function() {
    $commentForm.hide();
    $commentContent.show();
  });
}

/* 신고창 관련 */
// 팝업창 띄우기
function openReportPopup() {
  window.open("/reports/register", "신고하기", "width=500, height=500");
};

/* 북마크 */
function toggleBookmark(fid, isBookmarked) {
  // Ajax 요청 보내기
  $.ajax({
    url: "/bookmark/" + (isBookmarked ? "remove" : "register"),
    method: "POST",
    data: {fid: fid},
    success: function(response) {
      // 성공적으로 처리되었을 때 버튼의 텍스트와 아이콘을 바꾸기
      var $button = $(".bookmark");
      var newIsBookmarked = isBookmarked ? false : true;
      $button.text(newIsBookmarked ? "★ 북마크 취소" : "☆ 북마크 추가");
      $button.attr("onclick", "toggleBookmark(" + fid + ", " + newIsBookmarked + ")");
    }
  });
}

</script>
<script src="/resources/freeboard/js/validateForm.js"></script>
<!-- <script src="/resources/freeboard/js/comment.js"></script> -->
</body>
</html>