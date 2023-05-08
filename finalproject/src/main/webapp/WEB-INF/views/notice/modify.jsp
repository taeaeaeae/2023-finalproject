<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/resources/freeboard/js/validateForm.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/cerulean/bootstrap.min.css" integrity="sha384-3fdgwJw17Bi87e1QQ4fsLn4rUFqWw//KU0g8TvV6quvahISRewev6/EocKNuJmEw" crossorigin="anonymous">
<title>공지사항 | 글 수정하기</title>
</head>
<body>

<%@include file="/WEB-INF/views/common/header.jsp" %>
<section>
    <h1 style="text-align: center; color: black;">공지사항</h1>
    
    <div id="main" style="margin-left: 10%; margin-right: 10%;">
        <form onsubmit="return validateForm()" method="POST" action="/notice/modify">
        <input type="hidden" name="currPage" value="${param.currPage}">
        <input type="hidden" name="amount" value="${param.amount}">
        <input type="hidden" name="nid" value="${noticeboard.nid}">
        
        <div class="form-group">
        <label for="" class="form-label mt-4"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">제목</font></font></label>
        <input name="title" type="text" class="form-control"  value="${noticeboard.title}">
      </div>      
      
      <div class="form-group">
        <label for="exampleInputEmail1" class="form-label mt-4"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">작성자</font></font></label>
        <input name="uids" type="text" class="form-control"  placeholder="작성자" value="${noticeboard.uids}" readonly >
      </div>

      <div class="form-group">
        <label for="exampleTextarea" class="form-label mt-4"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">내용</font></font></label>
        <textarea name="content"  class="form-control" id="exampleTextarea" rows="3">${noticeboard.content}</textarea>
      </div>
      
      <div class="form-group">
        <label for="formFile" class="form-label mt-4"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">사진첨부</font></font></label>
        <input name = "file" class="form-control" type="file" id="formFile" accept="image/*">
      </div> 
      
      <fieldset class="form-group">
	  <div class="form-check">
        <input class="form-check-input" type="radio" name="top" id="optionsRadios1" value="1" checked="">
        <label class="form-check-label" for="optionsRadios1"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">
          상단 고정
        </font></font></label>
      </div>
      
      <div class="form-check">
        <input class="form-check-input" type="radio" name="top" id="optionsRadios2" value="0">
        <label class="form-check-label" for="optionsRadios2"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">
          상단 비고정
        </font></font></label>
      </div>		
      </fieldset>
      
      <br>
      
      <input type="submit" value="글쓰기" class="btn btn-secondary" style="background-color: #D2EEFA; border: none; color: black; float: right;">
      <button type="reset" value="초기화" class="btn btn-secondary" style="background-color: #D2EEFA; border: none; color: black; float: right;" name="reset" onclick="return confirmReset()" >초기화</button>
      
        </form>
    </div>
</section>
<%@include file="/WEB-INF/views/common/footer.jsp" %>	

</body>
</html>