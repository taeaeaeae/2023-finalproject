<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
			crossorigin="anonymous" />
        <link rel="stylesheet" href="/css/login.css">
		<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <%@include file="/WEB-INF/views/common/favicon.jsp" %>
        
        <title>login.jsp</title>
    </head>
    <body>

    <%@include file="/WEB-INF/views/common/header.jsp" %>
        
        <div id="center"> 
            <form action="/user/loginPost" method="post">
            
                    <div class="form-group">
	                    <label for="exampleInputId" class="form-label mt-4">ID</label>
	                    <input type="text" class="form-control" id="uids" placeholder="ID" name="uids" pattern="^[a-zA-Z0-9]*$" maxlength="18" required>
                    </div>
                    
                    <div class="form-group">
	                    <label for="exampleInputPassword" class="form-label mt-4">PW</label>
	                    <input type="password" class="form-control" id="password" placeholder="PW" name="password" pattern="^[a-zA-Z0-9]*$" maxlength="18" required>
                    </div> 
                    
                    <div class="d-grid gap-2">
						  <button class="btn btn-lg btn-primary" type="submit" id="loginBtn">로그인</button>
					</div>
				


                                            
            </form>
            
                <div class="find">
                    <li>
                        <a href="/user/find_id">아이디 찾기</a>
                    </li>
            
                    <li>
                        <a href="/user/find_pw">비밀번호 찾기</a>
                    </li>
            
                    <li>
                        <a href="/user/join">회원가입</a>
                    </li>
                </div>
                
        </div> 

		<%@include file="/WEB-INF/views/common/footer.jsp" %>
        
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