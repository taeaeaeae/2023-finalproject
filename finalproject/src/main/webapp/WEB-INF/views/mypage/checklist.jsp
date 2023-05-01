<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
rel="stylesheet"
integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
crossorigin="anonymous" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
<title>checkList</title>
</head>
<body>

<%@include file="/WEB-INF/views/main/header.jsp" %>
	
	<section>
		<h1>체크리스트</h1>
		<hr>
		
		<%@include file="/WEB-INF/views/main/leftside.jsp" %>
		
		    <table id="table" class="table">
				<thead>
					<tr style="background-color: #eee; color: black">
						<th>체크</th>
						<th>No</th>
						<th>아이템</th>
						<th>메모</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				
				<tbody id="listBody">
					<c:forEach var="ChecklistVO" items="${list}">
						<tr>
							<form action="/mypage/listupdate" method="POST">
								<td>
									<input type="checkbox" name="checkbox" value="1" id="input_check" <c:if test="${ChecklistVO.checkbox == 1}">checked</c:if>>
									<input type="hidden" name="checkbox" value="0" id="input_check_hidden"/>
								</td>
								<td>
									<input type="text" style="width:300px" maxlength="20" name="item" value="${ChecklistVO.cid}">
								</td>
								<td>
									<input type="text" style="width:300px" maxlength="20" name="item" value="${ChecklistVO.item}">
								</td>
								<td>
									<input type="text" style="width:600px" maxlength="50" name="memo" value="${ChecklistVO.memo}">
								</td>
						        <td>
							        <input type="hidden" name="cid" value="${ChecklistVO.cid}">
							        <button type="submit" class="btn btn-default btn-sm">수정</button>
						        </td>
						    </form>
							<form action="/mypage/listdelete" method="POST">
								<td>
							        <input type="hidden" name="cid" value="${ChecklistVO.cid}">
							        <button type="submit" class="btn btn-default btn-sm">삭제</button>
							    </td>
							</form>
						</tr>
					</c:forEach>
				    </tbody>
				</table>
			<div>
				<button id="addBtn" onclick="morelist();"><span>더보기</span></button>
			</div>
			
			<br>	  
			
		  	<form action="/mypage/listadd" method="POST">
		  		<input type="hidden" name="uids" value="${uids.uids}">
				<input type="text" style="width:300px" maxlength="20" name="item" value="${ChecklistVO.item}" placeholder="아이템">
				<input type="text" style="width:600px" maxlength="50" name="memo" value="${ChecklistVO.memo}" placeholder="메모">
				<button type="submit" class="btn btn-default btn-sm">추가</button>
			</form>
			
		</section>
		
<%@include file="/WEB-INF/views/main/footer.jsp" %>
	
<script>

	// 체크박스
	if(document.getElementById("input_check").checked) {
	    document.getElementById("input_check_hidden").disabled = true;
	}

	morelist(); //함수 호출
	 
	function morelist() {
	 
	    var startNum = $("#listBody tr").length;  //마지막 리스트 번호를 알아내기 위해서 tr태그의 length를 구함.
	    var addListHtml = "";  
	    console.log("startNum", startNum); //콘솔로그로 startNum에 값이 들어오는지 확인
	 
	     $.ajax({
	        url : "/mypage/morelist",
	        type : "post",
	        dataType : "json",
	        data : {"startNum":startNum},
	        
	        success : function(data) {
	            if(data.length < 6 ){
	                $("#addBtn").remove();   // 더보기 버튼을 div 클래스로 줘야 할 수도 있음
	            }else{
	            var addListHtml ="";
	            if(data.length > 0){
	                console.log("data.length", data.length);
	                
	                for(var i=0; i<data.length;i++) {
	                    var idx = Number(startNum)+Number(i)+1;   
	                    // 글번호 : startNum 이  10단위로 증가되기 때문에 startNum +i (+1은 i는 0부터 시작하므로 )
	                    addListHtml += "<tr>";
	                    addListHtml += "<td>"+ data[i].cid + "</td>";
	                    addListHtml += "<td>"+ data[i].item + "</td>";
	                    addListHtml += "<td>"+ data[i].memo + "</td>";
	                    addListHtml += "</tr>";
	                }	//for
	                
	                $("#listBody").append(addListHtml);
	            }	// if
	            } //if-else
	        } // function
	    });	// ajax
	 
	} //morelist
	
</script>	

	
	

</body>
</html>