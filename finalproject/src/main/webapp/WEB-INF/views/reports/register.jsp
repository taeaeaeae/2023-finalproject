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
        <input type="hidden" name="uids" value="${sessionScope['__AUTH__'].uids}">
        <p>게시글 명 : <input type="text" name="target_board_name" value="free_board" readonly></p>
        <p>게시글 번호 : <input type="text" name="fid" value="${freeboard.fid}" readonly></p>
        <p>신고자 명 : <input type="text" name="target_user" value="${freeboard.uids}" readonly></p>
        <p>신고 사유 :
            <select name="reason" id="reports_reasons">
                <option value="0">욕설 및 비방</option>
                <option value="1">부적절한 게시물</option>
                <option value="2">도배성 게시물</option>
                <option value="3">개인정보 침해 게시물</option>
                <option value="4">저작권 침해 게시물</option>
                <option value="5">홍보성 게시물</option>
                <option value="6">사유 직접 작성</option>
            </select>
            <div id="reason_6_textarea" style="display: none;">
                <textarea id="reason_opt_6" name="reason_2" cols="30" rows="10" placeholder="사유를 작성해주세요"></textarea>
            </div>
        <input type="submit" value="제출하기">
    </form>
<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
// $(function () {
//     $('#reports_reasons').change(function () {
//         if($(this).val() === '6' ){
//             $('#reason_6_textarea').show();
//             $('#reason_opt_6').focus();
//         } else{
//             $('#reason_6_textarea').hide();
//         } // if-else
//     });

//     $('form').submit(function () {
//         if($('#reports_reasons').val() === '6' && $('#reason_opt_6').val().trim() === ''){
//             alert('사유를 입력해주세요.');
//             $('#reason_opt_6').focus();
            
//             return false;
//         }
//     });
// });

// // submitForm
// $(function () {

//   $('form').submit(function (event) {
//     event.preventDefault();

//     $.ajax({
//       type: 'POST',
//       url: $(this).attr('action'),
//       data: $(this).serialize(),
//       success: function (data) {
//         var result = data.result;

//         if (result === 'success') {
//           alert('신고가 제출되었습니다.');
//         } else {
//           alert('이미 신고한 게시물입니다.');
//         }

//         window.close();
//       },
//       error: function () {
//         alert('오류가 발생했습니다.');
//       }
//     });
//   });
// });
$(function () {
    $('#reports_reasons').change(function () {
        if ($(this).val() === '6') {
            $('#reason_6_textarea').show();
            $('#reason_opt_6').focus();
        } else {
            $('#reason_6_textarea').hide();
        }
    });

    $('form').submit(function (event) {
        event.preventDefault();

        if ($('#reports_reasons').val() === '6' && $('#reason_opt_6').val().trim() === '') {
            alert('사유를 입력해주세요.');
            $('#reason_opt_6').focus();
            return false;
        }

        $.ajax({
            type: 'POST',
            url: $(this).attr('action'),
            data: $(this).serialize(),
            success: function (data) {
                var result = data.result;
                alert('신고가 제출되었습니다.');

                window.close();
            },
            error: function () {
                alert('오류가 발생했습니다.');
            }
        });
    });
});

</script>
</body>
</html>