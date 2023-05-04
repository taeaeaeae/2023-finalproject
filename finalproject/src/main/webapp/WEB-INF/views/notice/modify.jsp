<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/freeboard/css/freeboard_register.css">
<script src="/resources/freeboard/js/validateForm.js"></script>
<title>공지사항 | 글 수정하기</title>
</head>
<body>

<%@include file="/WEB-INF/views/common/header.jsp" %>
<section>
    <h1>공지사항</h1>
    <hr>
    <div id="main">
        <form onsubmit="return validateForm()" method="POST" action="/notice/modify">
        <input type="hidden" name="currPage" value="${param.currPage}">
        <input type="hidden" name="amount" value="${param.amount}">
        <input type="text" name="nid" value="${noticeboard.nid}">

        <input type="text" name="title" value="${noticeboard.title}" placeholder="title">
        <hr>
        <input type="file" name="pic" value="사진 선택"><br/>
        <textarea name="content">${noticeboard.content}</textarea>
        <br>
        <p> 상단 고정 
		<input type="checkbox" name="top" value="1" id="input_check" <c:if test="${noticeboard.top == 1}">checked</c:if>>
		<input type="hidden" name="top" value="0" id="input_check_hidden"/> </p>
        <button type="reset" value="초기화" class="button" name="reset" onclick="return confirmReset()">초기화</button>
        <input type="submit" value="글쓰기" class="button">
        <a href="/notice/list" id="listBtn" class="button" onclick="return confirmGoToList()">목록으로</a>
        </form>
    </div>
</section>
<%@include file="/WEB-INF/views/common/footer.jsp" %>	
<script>
var listBtn = document.querySelector('#listBtn');

listBtn.addEventListener('click', function () {
    location.href="/notice/list?currPage=${ param.currPage }&amount=${ param.amount }";
}); // .addEventListener

//체크박스
if(document.getElementById("input_check").checked) {
    document.getElementById("input_check_hidden").disabled = true;
}

</script>
</body>
</html>