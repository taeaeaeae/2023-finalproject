<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://unpkg.com/@yaireo/tagify"></script>
    <script src="https://unpkg.com/@yaireo/tagify/dist/tagify.polyfills.min.js"></script>
    <link href="https://unpkg.com/@yaireo/tagify/dist/tagify.css" rel="stylesheet" type="text/css" />
    <link href="https://cdn.jsdelivr.net/npm/bootswatch@5.2.3/dist/litera/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${path}/resources/csss/map.css" />
    <title>트트트</title>


<meta charset="UTF-8">
<title>Insert title here</title>
<script>

      //해시태그
var maxTags = 5; // 최대 태그 수
var tagify1 = new Tagify(document.querySelector('#hash_tag1'), {
maxTags: maxTags,
});
var tagify2 = new Tagify(document.querySelector('#hash_tag2'), {
maxTags: maxTags,
});

//태그가 추가되면 이벤트 발생
tagify1.on("add", function () {
console.log(tagify1.value); // 입력된 태그 정보 객체
});
tagify2.on("add", function () {
console.log(tagify2.value); // 입력된 태그 정보 객체
});


//var input = document.querySelector("input");
//var tagify = new Tagify(hash_tag2);

//tagify.on("add",function(){
//  console.log(tagify.value);
//});

//날짜
//document.getElementById('currentDate').value = new Date().toISOString().substring(0, 10);

//function generateForms() {
//var startDateInput = document.getElementById("start-date");
//var endDateInput = document.getElementById("end-date");
//var startDateValue = startDateInput.value;
//var endDateValue = endDateInput.value;

//var startDate = new Date(startDateValue);
//var endDate = new Date(endDateValue);

//// 여행 기간 계산
//var timeDiff = Math.abs(endDate.getTime() - startDate.getTime());
//var travelDays = Math.ceil(timeDiff / (1000 * 3600 * 24)) + 1;

//// 글쓰기 폼 생성
//for (var i = 1; i <= travelDays; i++) {
//  var form = document.createElement("form");
//  var label = document.createElement("label");
//  var input = document.createElement("input");

//  label.innerHTML = "Day " + i;
//  input.name = "day" + i;
//  input.type = "text";

//  form.appendChild(label);
//  form.appendChild(input);
//  document.body.appendChild(form);
//}
//}
//function generateForms() {
//var startDateInput = document.getElementById("start-date");
//var endDateInput = document.getElementById("end-date");
//var startDateValue = startDateInput.value;
//var endDateValue = endDateInput.value;

//var startDate = new Date(startDateValue);
//var endDate = new Date(endDateValue);

//// 여행 기간 계산
//var timeDiff = Math.abs(endDate.getTime() - startDate.getTime());
//var travelDays = Math.ceil(timeDiff / (1000 * 3600 * 24)) + 1;

//// 글쓰기 폼 생성
//var formsContainer = document.getElementById("forms-container");
//formsContainer.innerHTML = ""; // 이전에 생성된 폼을 삭제

//for (var i = 1; i <= travelDays; i++) {
//  var form = document.createElement("form");
//  var label = document.createElement("label");
//  var input = document.createElement("input");

//  label.innerHTML = "Day " + i;
//  input.name = "day" + i;
//  input.type = "input";

//  form.appendChild(label);
//  form.appendChild(input);
//  formsContainer.appendChild(form);
//}
//}
function generateForms() {
var startDateInput = document.getElementById("start-date");
var endDateInput = document.getElementById("end-date");
var startDateValue = startDateInput.value;
var endDateValue = endDateInput.value;

var startDate = new Date(startDateValue);
var endDate = new Date(endDateValue);

// 여행 기간 계산
var timeDiff = Math.abs(endDate.getTime() - startDate.getTime());
var travelDays = Math.ceil(timeDiff / (1000 * 3600 * 24)) + 1;

// 글쓰기 폼 생성
for (var i = 1; i <= travelDays; i++) {
 var form = document.createElement("form");
 var label = document.createElement("label");
 var textarea = document.createElement("textarea");

 label.innerHTML = "Day " + i;
 textarea.name = "day" + i;

 form.appendChild(label);
 form.appendChild(textarea);
 document.body.appendChild(form);
}
}
</script>

</head>
<body>

    <%@ include file="/WEB-INF/views/common/header.jsp"%>
    
    <!-- 제목을 입력하는 폼 -->

    <div class="form-group">
        <input type="text" class="form-control" placeholder="제목을 입력해주세요." id="inputDefault">
    </div>


    <!-- 글쓰기  폼-->
    <div class="content">
        <div class="right-content">
                        <div class="form-group">
                            <h3 id="destination">여행지</h3>
                            <input id="hash_tag1" placeholder="여행지 입력해주세요.">

                            <h3 id="travel_theme">여행테마</h3>
                            <input id="hash_tag2" placeholder="여행테마를 입력해 주세요." />
                            
                            <h3 id="selectdate">날짜를 선택하세요</h3>
                            <label for="start-date">여행 시작일:</label>
                            <input type="date" id="start-date" name="start-date" />

                            <label for="end-date">여행 종료일:</label>
                            <input type="date" id="end-date" name="end-date" />

                            <button onclick="generateForms()" id="generateForms">폼 생성</button>
                            <div id="forms-container"></div>
                        </div>
        </div>
    </div>


    <!-- 지도 -->
    <div class="left-content">
        <div id="map" style="width:500px;height:400px;"></div>
        <div id="clickLatlng"></div>
    </div>


    <!-- 비밀글 공개글 라디오 버튼 -->
  <div class="rd_button">
        <input type="radio" name="chk_info" value="비밀글">
        <span>비밀글</span>
        <input type="radio" name="chk_info" value="공개글">
        <span>공개글</span>
  </div>

    <!-- 지도 밑에 하단버튼 -->
   <div class="ud_btn">
        <button type="button1" class="btn1">북마크</button>
        <button type="button2" class="btn2">임시저장</button>
        <button type="button3" class="btn3">저장</button>
        <button type="button4" class="btn4">취소</button>
    </div>

   <%@ include file="/WEB-INF/views/common/footer.jsp"%>

</body>
</html>