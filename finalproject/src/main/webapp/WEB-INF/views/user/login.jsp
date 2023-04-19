<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

   
		<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <%@include file="/WEB-INF/views/common/favicon.jsp" %>
        
        <title>login.jsp</title>
    </head>
    <body>

        <%@include file="/WEB-INF/views/main/header.jsp" %>
        
            <form action="/user/loginPost" method="post">
            
                <div id="center"> 
                
                    <div class="form-group">
                    <label for="exampleInputId" class="form-label mt-4">ID</label>
                    <input type="text" class="form-control" id="uids" placeholder="ID" name="uids" required>
                    </div>
                    
                    <div class="form-group">
                    <label for="exampleInputPassword" class="form-label mt-4">PW</label>
                    <input type="password" class="form-control" id="password" placeholder="PW" name="password" required>
                    </div>
    
   
                    <div class="button">
                         <button type="submit" id="loginBtn" class="btn btn-primary btn-lg">로그인</button>
                    </div>  
                                                 
                </div> 

            </form>
            
                <div id="find">
                    <li>
                        <a href="/user/find_id">아이디 찾기</a>
                    
            
                    <li>
                        <a href="/user/find_pw">비밀번호 찾기</a>
                    
            
                    <li>
                        <a href="/user/join">회원가입</a>
                    </li>
                </div>

		<%@include file="/WEB-INF/views/main/footer.jsp" %>
        
        <script>
        
	        // 로그인 실패
			  $(document).ready(function() {
		        let message = "${result}";
		        if (message != "") {
		            alert(message);
		        }else {
		        }
		    });
	        


        </script>
            
                
    </body>
</html>