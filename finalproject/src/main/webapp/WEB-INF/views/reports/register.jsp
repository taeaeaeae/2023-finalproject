<%@page import="org.zerock.myapp.domain.LoginVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고하기</title>
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
    <h2>신고하기</h2>
    
    <form action="/reports/register" method="post">
        <input type="text" name="uids" value="${sessionScope['__AUTH__'].uids}" readonly>
        <p>게시글 명 : <input type="text" name="target_board_name" value="free_board" readonly></p>
        <p>게시글 번호 : <input type="text" name="target_board_id" value="${freeboard.fid}" readonly></p>
        <p>신고자 명 : <input type="text" name="target_user" value="${freeboard.uids}" readonly></p>
        <p>신고 사유 :
            <select name="reports_reason" id="reports_reason" aria-placeholder="신고사유 선택">
                <option name="reason_1" value="욕설 및 비방">욕설 및 비방</option>
                <option name="reason_2" value="도배">도배</option>
                <option name="reason_3" value="개인정보 침해">개인정보 침해</option>
                <option name="reason_4" value="저작권 침해">저작권 침해</option>
                <option name="reason_5" value="사유 직접 작성">사유 직접 작성</option>
            </select>
            <div id="reason_6_textarea" style="display: none;">
                <textarea name="reason_6_textarea" id="reason_6_textarea" cols="30" rows="10" placeholder=""></textarea>
            </div>
        <input type="submit" value="제출하기">
    </form>
<script>
$(document).click(function () {
    $('#reports_reason').change(function () {
        if($(this).val() === 'reason_6'){
            $('#reason_6_textarea').show();
        } else {
            $('#reason_6_textarea').hide();
        }
    });
});
</script>
</body>
</html>