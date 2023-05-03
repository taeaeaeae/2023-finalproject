var idx = 0;

//새로고침 감지 및 Controller 정적 계획 리스트 초기화
if(performance.navigation.type == 1){
   var param = window.location.pathname+window.location.search;
   $.ajax({
      url : "/plan/write/clear",
      type : "GET",
      data : {param: param},
      dataType : "text",
      success : function(data) {
         location.href=data;
      },
      error : function(){
         alert("refresh clear err");
      }
   });
}

$(function(){
   //PlanVO 필드 변수 선언
   var uids = $('#uidsCheck').val();
   var planTitle;
   var startDate;
   var planTotalDay = 0;
   var deleteCount = 0;
   var markerCount = 0;
   
   //collapse 생성 함수
   //id 마다 i 부여
   function createCollapse(i) {
      var createStringCollap;
      createStringCollap = '<div class="card card-body">';
      createStringCollap += '<form id="schFrm'+i+'">';
      createStringCollap += '<input type="hidden" id="uids" name="uids" value="'+ uids +'">';
      createStringCollap += '<input type="hidden" id="schDay'+i+'" name="planDay" value="'+i+'" readonly style="width: 20px; text-align: center"/>';
      createStringCollap += '<input type="hidden" id="addr' + i +'" name="addr" value="" readonly/>';
      createStringCollap += '<input type="hidden" id="longitude' + i +'" name="longitude" value="" readonly/>';
      createStringCollap += '<input type="hidden" id="latitude' + i +'" name="latitude" value="" readonly/>';
      createStringCollap += '<input type="hidden" class="markerNo" id="markerNo1" name="markerNo" value="1" readonly/>';
      createStringCollap += '<div class="input-group input-group-sm mb-3">'
      createStringCollap += '<span class="input-group-text" id="inputGroup-sizing-sm">장소</span>'
      createStringCollap += '<input type="text" class="form-control" id="placeInit'+i+'" name="place" readonly style="background-color:#FFFFF0" placeholder="지도에서 장소를 선택해주세요."></div>'
      createStringCollap += '<div class="input-group mb-3">'
      createStringCollap += '<label class="input-group-text" for="startTimeInit">시작시간</label>'
      createStringCollap += '<select class="form-select startTime" id="startTimeInit" name="startTime"></select></div>'
      createStringCollap += '<div class="input-group">'
      createStringCollap += '<span class="input-group-text">설명</span>'
      createStringCollap += '<textarea class="form-control" id="contentInit'+i+'" name="descript"></textarea></div>'   
      createStringCollap += '<input type="button" id="schFrmSubmit'+i+'" class="btn btn-primary schFrmSubmit" data-bs-target="#collapseExample" data-bs-toggle="collapse'+i+'" value="추가">';
      createStringCollap += '</div>';
      
      $("#collapse"+i).html(createStringCollap);
   };
   
   //드롭다운 값변경 스크립트
    $(".dropdown-menu li a").click(function(){
       $(".btn-day:first-child").text($(this).text());
       $(".btn-day:first-child").val($(this).text());
       //총 일수 변수 초기화
       planTotalDay = $(this).attr('value');
   });
    
   // Plan 설정 유효성 검사 및 제출
   $('#planFrmSubmit').on('click', function(){
	   for(var i=0; i<scheduleMarkers.length; i++){
	   scheduleMarkers[i].setMap(null);
	   }
	   
      //버튼 인덱스 값 초기화
      idx=0;
      
      //계획타이틀 값 검사 및 초기화
      if($('#planTitle').val()==""){
         alert('일정 타이틀을 입력해주세요!');
         $('#planTitle').focus();
         return false;
      }else{
         planTitle = $('#planTitle').val();
      }
      //여행 일수 값 검사 초기화는 위 드롭다운 함수에!
      if(planTotalDay==0){
         alert('여행 일수를 선택해주세요!');
         return false;
      }
      
    //method="post" action="/plan/write/planSet"
      startDate = $('#startDate').val();
      $.ajax({
         url: "/plan/write/planSet",
         data: {uids: uids, planTitle: planTitle, startDate: startDate, planTotalDay: planTotalDay},
         dataType:"json",
         type: "POST",
         cache : false,
         success: function(data){
            var planOutput = '';
            for(var i = 1; i<=planTotalDay; i++){
               var createDay = '<div><h4 class="day">DAY'+ i + '</h4>';
               
               createDay += '<button class="btn btn-primary schCreateBtn"id="schAddBtn'+i+'"type="button"data-bs-toggle="collapse" data-bs-target="#collapse'+i+'"aria-expanded="false"aria-controls="collapseExample">';
               createDay += '일정 생성';
               createDay += '</button>';
               createDay += '<div class="collapse" id="collapse'+i+'"></div>'
               createDay += '<div id="disp'+i+'"></div>'
               createDay += '</div>';
               if(i == planTotalDay){
                  createDay += '<button type="button" class="btn btn-primary submitBtn" id="planAddBtn" style="float:right;">일정 등록</button>';
               }
               planOutput = planOutput + createDay;
            }
            $("#schDiv").html(planOutput);
             for(var i = 1; i<=planTotalDay; i++){
               createCollapse(i);
               
            }
         },
         error: function(){
             alert("계획 초기 설정 실패!");
             return false;
          }
      });
   });
   
   // planAdd 유효성 검사
   $(document).on('click', 'button[id=planAddBtn]', function(){
      for(var i=1; i<=planTotalDay; i++){
         //한 DAY안의 일정 개수 측정, 값이 없을 시 0
         if ($('#disp'+i+' .card').length == 0) {
            alert('DAY에 일정을 추가해 주세요!');
            return false;
         }
      }
      location.href='/plan/write/planAdd';
   });

   //일정 생성 버튼마다 day 순서 받아오기
   $(document).on("click", 'button[id^=schAddBtn]', function(){
	   //startTime select에 사용될 변수 선언
	   if(idx==0){
	        for(var i = 6; i<=24; i++){
				$(".startTime").append("<option value="+i+">"+ i + ":00</option>");			
				//기본값 09:00로 함
				$('select option[value="9"]').attr("selected",true);
			}
	   }
	   
	   //버튼 tag만! index 값 변수로 받기
        idx = $('button[id^=schAddBtn]').index(this)+1;
        
        //다른 일정 작성폼이 열려 있을 때
        if($('.collapse').hasClass('show')){
           //modal schDay input에 값 추가
           $("#schDay"+idx).attr({"value":idx});
           //collapse 닫기
           $('.collapse').removeClass('show');
        }
        //다른 일정 작성폼이 안 열려 있을 때
        else {
           //modal schDay input에 값 추가
           $("#schDay"+idx).attr({"value":idx});
        }
   });
   
   //method="post" action="/plan/write/schAdd"
   $(document).on('click', 'input[id^=schFrmSubmit]', function(){
      // 장소 값 없이 일정 추가 방지 (장소 유효성 검사)
      if($('#placeInit'+idx).val()==''){
         alert('장소를 추가해 주세요!');
         return false;
      }
       $.ajax({
           url: "/plan/write/schAdd",
           data: $('#schFrm'+idx).serialize(),
           dataType:"json",
           type: "POST",
           success: function(data){
            markerCount += 1;
            deleteCount += 1;
            var schOutput='';
            
            //startTime 형태 바꾸기.
            var hour = data.startTime;
            var min = '00';
                        
            //card형식으로 바꿈.
            schOutput+= '<div class="card card-marker card-count'+data.planDay+'" style="width: 28rem;">';
            schOutput+= '<div class="card-body cardTable">';
            schOutput+= '<h5 class="card-title">' + data.place + '</h5>';
            schOutput+= '<h6 class="card-title">' + data.addr + '</h6>';
            schOutput+= '<h4 class="card-title" style="display:none;">' + data.planDay + '</h4>';
            schOutput+= '<h3 class="card-title card-startTime card-time'+data.planDay+'"+ style="display:none;">' + data.startTime + '</h3>';
            schOutput+= '<p id="longitude" style="display:none;">' + data.longitude + '</p>';
            schOutput+= '<p id="latitude" style="display:none;">' + data.latitude + '</p>';
            schOutput+= '<p id="markerNo' +markerCount+ '" style="display:none;">' +markerCount+ '</p>';
            schOutput+= '<h6 class="card-subtitle mb-2 text-muted">' + hour + ':' + min + '' + '</h6>';
            schOutput+= '<p id="descript" class="card-text">' + data.descript + '</p>';
            schOutput+= '<button type="button" class="btn btn-primary btn-sm" id="deletePlan'+deleteCount+'">delete</button>';
            schOutput+= '</div></div>';
                        
            $("#disp"+data.planDay).append(schOutput);
            
            //지도에 마커 찍기 LatLng/위,경 '33.450701, 126.570667'
            scheduleAddMarker(data.latitude, data.longitude, data);
              
           },
           error: function(){
               alert("일정 추가 실패! 장소를 선택해 주세요!");
           },
                   
           complete: function(){
              $('#contentInit'+idx).val('');
              $('#placeInit'+idx).val('');
              $('#addr'+idx).val('');
              $('#longitude'+idx).val('');
              $('#latitude'+idx).val('');
              setTimeout(function() { // 동시에 입력된 일정은 정렬 안되는 문제 있어서 delay를 0.1초 주었음
        			var i, j;
        			var dispNum = 1;
        			
        			//day loop
        			while(dispNum<=planTotalDay){
        				
        				//버블정렬
        				for (i = 0; i<($(".card-count"+dispNum).length - 1); i++) {
        					for(j = 0; j<($(".card-count"+dispNum).length -1 - i); j++) {
        						if(parseInt($(".card-time"+dispNum).eq(j).html()) > parseInt($(".card-time"+dispNum).eq(j+1).html())) {
        							//구조 분해 할당 : [3, 5] = [5, 3] --> [5, 3]
        							[document.getElementsByClassName("card-count"+dispNum)[j].innerHTML, document.getElementsByClassName("card-count"+dispNum)[j+1].innerHTML] 
        							= [document.getElementsByClassName("card-count"+dispNum)[j+1].innerHTML, document.getElementsByClassName("card-count"+dispNum)[j].innerHTML];
        						}
        					}
        				}
        				dispNum++;
        			}
        		}, 100);//setTimeOut End
           }
       });
   });
   
   //장바구니에서 일정 빼기 버튼 (-)
   $(document).on("click", 'button[id^=deletePlan]', function(){
      
      //버튼이 있는 행의 td들의 객체를 변수 선언      
      var deleteMap = {startTime :$(this).siblings('h3').html(), 
                  planDay : $(this).siblings('h4').html(), 
                  descript :$(this).siblings('#descript').html(), 
                  place :  $(this).siblings('h5').html(), 
                  addr :  $(this).siblings('h6').html(), 
                  longitude : $(this).siblings('#longitude').html(), 
                  latitude : $(this).siblings('#latitude').html(), 
                  markerNo : $(this).siblings('p[id^=markerNo]').html()
                  }
      $.ajax({
         url : "/plan/write/planDel",
         type : "POST",
         data : JSON.stringify(deleteMap),
         contentType : "application/json; charset=utf-8;",
         dataType : "text",
         success : function(data){
         },
         error : function() {
            alert("simpleWithObject err");
         }
      });
      $(this).parent().parent().remove();
   });
});

/* 여행날짜 기본 값 삽입 스크립트 */
document.getElementById('startDate').value = new Date().toISOString().substring(0, 10);