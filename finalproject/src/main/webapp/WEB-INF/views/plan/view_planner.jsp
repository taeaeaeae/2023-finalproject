<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap Script -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<style>
  .dropdown-menu{
  	width:50px;
  }
  .schDiv{
  	margin-right: 10px;
  }
  #plannerDiv{
  	overflow:auto; 
  	width:100%; 
  	height:90%;
  }
  .card-marker{
  	margin : 5px auto;
  	border-color : orange;
  }
  .day{
  	text-align:center;
  }
  .schCreateBtn{
  	margin : 1px auto;
  }
  .schFrmSubmit{
  	margin-top : 3px;
  }
  .markerNo{
  	display: none;
  }
  #planModifyEnd{
  	display: none;
  	margin: 0px 0px 16px 0px;
  }
</style>
</head>
<body>
	<input type="hidden" id="userIdCheck" value="${users.uids}">
	<div id="plannerDiv">
	<!-- 계획 입력 -->
	<div class="col-md-12">
	<form id="planFrm" name="planFrm" method="post" action="/plan/view/modify" >
		<div class="row">
			<input type="hidden" id="viewPlanNo" name="planNo" value="${planView.pid}"/>
			<input type="hidden" id="viewUserId" name="userId" value="${users.uids}"/>
			<div class="form-group col-sm-12">
				<label>계획 제목</label>
				<input type="text" class="form-control" id="planTitle" name="planTitle" value="${planView.planTitle}" placeholder="일정 타이틀" readonly/>
			</div>
			<div class="form-group col-sm-8"> 
				<label>여행 날짜</label>
				<input type="date" class="form-control" id="startDate" name="startDate" value="<fmt:formatDate value="${planView.startDate}" pattern="yyyy-MM-dd"/>" readonly/>
			</div>
			
			<div class="form-group col-sm-4">
				<label>여행 일수</label>
				<select id="planTotalDayCount" class="form-select">
				  <option value="${planView.planTotalDay}" selected>${planView.planTotalDay}일</option>
				</select>
			</div>
			<c:if test="${member.userId == planView.userId}">
				<div class="form-group col-mb-6" style="text-align: right;">
					<input type="button" class="btn btn-primary mb-3" id="planModifyStart" name="planModifyStart" value="수정">
					<input type="submit" class="btn btn-primary mb-3" id="planModifyEnd" name="planModifyEnd" value="완료">
					<input type="button" class="btn btn-primary mb-3" id="planDelBtn" name="planDelBtn" value="전체삭제">
				</div>
			</c:if>
		</div>
	</form>
	</div>
	<!-- 계획 입력 끝 -->

	<!-- 계획 출력 DIV -->
	<div id="schDiv">
		<c:forEach var="dayCnt" begin="1" end="${planView.planTotalDay}" step="1">
			<h3 class="day">Day${dayCnt}</h3>
			
			<!-- collapse 보이기 버튼 -->
			<button class="btn btn-primary schCreateBtn"id="schAddBtn${dayCnt}"type="button"data-bs-toggle="collapse" data-bs-target="#collapse${dayCnt}"style="display:none;">
			일정 생성</button>
			<!-- 일정 추가 form collapse -->
			<div class="collapse" id="collapse${dayCnt}">
				<div class="card card-body">
				<form id="schFrm${dayCnt}">
					<input type="hidden" id="userId${dayCnt}" name="userId" value="${users.uids}">
					<input type="hidden" id="schDay${dayCnt}" name="planDay" value="${dayCnt}" readonly	style="width: 20px; text-align: center"/>
					<input type="hidden" id="addr${dayCnt}" name="addr" value="" readonly/>
					<input type="hidden" id="longitude${dayCnt}" name="longitude" value="" readonly/>
					<input type="hidden" id="latitude${dayCnt}" name="latitude" value="" readonly/>
					<div class="input-group input-group-sm mb-3">
						<span class="input-group-text" id="inputGroup-sizing-sm">장소</span>
						<input type="text" class="form-control" id="placeInit${dayCnt}" name="place" readonly style="background-color:#FFFFF0">
					</div>
					<div class="input-group mb-3">
						<label class="input-group-text" for="startTimeInit">시작시간</label>
						<select class="form-select startTime" id="startTimeInit${dayCnt}" name="startTime"></select>
					</div>
					<div class="input-group">
						<span class="input-group-text">설명</span>
						<textarea class="form-control" id="contentInit${dayCnt}" name="descript"></textarea>
					</div>
					<input type="button" id="schFrmSubmit${dayCnt}" class="btn btn-primary schFrmSubmit" data-bs-target="#collapseExample" data-bs-toggle="collapse${dayCnt}" value="추가">
				</form>
				</div>
			</div>
				
			<!-- 일정 카드 html -->
			<div id="disp${dayCnt}" class="disp">
			<c:forEach items="${scheduleList}" var="scheduleList" varStatus="status">
				<c:if test="${scheduleList.planDay == dayCnt}">
				<div class="card-container">
					<div class="card card-marker card-count${dayCnt}" style="width: 28rem;">
						<div class="card-body cardTable">
						<h5 class="card-title place">${scheduleList.place}</h5>
						<h6 class="card-title addr">${scheduleList.addr}</h6>
						<h4 class="card-title" style="display:none;">${scheduleList.planDay}</h4>
						<h3 class="card-title card-startTime card-time${dayCnt}" style="display:none;">${scheduleList.startTime}</h3>
						<p class="rowNo" style="display:none;">${scheduleList.rowNo}</p>
						<p class="longitude" style="display:none;">${scheduleList.longitude}</p>
						<p class="latitude" style="display:none;">${scheduleList.latitude}</p>
						<p class="planNo" style="display:none;">${scheduleList.planNo}</p>
						<p class="markerNo" id="markerNo${status.count}" style="display:none;">${status.count}</p>
						<h6 class="card-subtitle mb-2 text-muted">${scheduleList.startTime} : 00</h6>
						<p class="card-text descript">${scheduleList.descript}</p>
						<button type="button" class="btn btn-primary btn-sm deleteSch" id="deletePlan${status.count}" style="display:none;">delete</button>								
						</div>
					</div>
				</div>
				</c:if>
			</c:forEach>
			</div>
		</c:forEach>
	</div>
</div>
</body>
</html>