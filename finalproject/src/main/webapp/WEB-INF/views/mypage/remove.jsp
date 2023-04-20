<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>

	<div class="container">
	
	<%@include file="/WEB-INF/views/main/header.jsp" %>
	<h1 align="center">회원탈퇴</h1>
		<form action="/mypage/remove" method="post">
		<div class="unregister">
    <p>
    <h1>회원 탈퇴 안내</h1>
    </p><br>
    <p>회원님께서 회원 탈퇴를 원하신다니 저희 여행루트 공유 사이트 서비스가 많이 부족하고</p>
    <p>미흡했나 봅니다. 불편하셨던 점이나 불만사항을 알려주시면 적극 반영해서 회원님의
      불편함을 해결해 드리도록 노력하겠습니다. </p>
    <p> 이어 회원 탈퇴시의 아래 사항을 숙지하시기 바랍니다.</p>
    <p> 1.탈퇴 시 회원님께서 작성하신 게시판에 글쓰기나 따로 저장하신 좋아요
      구독 북마크 댓글등 모두 삭제 됩니다. </p>
    <p>2.회원 탈퇴 후 3개월간 재가입이 불가능합니다.</p>

    <div class="user_id">
    			<tr>
					<td>* 아이디</td>
					<td><input type="text" name="uids" value="${mypage.uids}" /><br>
				</tr>
      <p>현재 비밀번호</p>
      <input type="password" name="password" placeholder="비밀번호를 입력하세요.">
    </div>

    <div class="discomfort">
      <p>무엇이 불편하셨나요?</p>
      <input type="checkbox" name="service">서비스 기능에 대한 불만
      <input type="checkbox" name="">방문 빈도가 낮음
      <input type="checkbox" name="">개인정보 유출 우려
      <input type="checkbox" name="">기타
      <textarea name="advice" id="advice" cols="50" rows="7" placeholder="회원님의 진심어린 충고 부탁드립니다."></textarea>
      
    </div>

    
  </div>

			
            <input type="submit" value="탈퇴" />
            
     	</form>
		
		<%@include file="/WEB-INF/views/main/footer.jsp" %>
		
	</div>
	


</body>
</html>