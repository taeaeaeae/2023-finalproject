<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>QnA</title>
    	<style>
	* {
	font-family: "GangwonEdu";
}
@font-face {
	font-family: "GangwonEdu";
    src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEdu_OTFBoldA.woff")
      format("woff");
    font-weight: normal;
    font-style: normal;
  }
  h1 {
  text-align: center;
  	color: black;
  }
  ul {
  width: 300px;
  margin-left: auto;
  margin-right: auto;
  }
button {
	
}
	</style>
    
    <link rel="stylesheet" href="WEB-INF/views/common/font.css">
    <link rel="canonical"
	href="https://getbootstrap.com/docs/5.2/examples/album/" />

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />
	
</head>

<body>


	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	
	<br>
	<h1>QnA</h1>
	<br>
	
	<button type="button" id="registerBtn" class="btn btn-primary btn-sm" style="float: right"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">등록하기</font></font></button>
	<div id="wrapper">
	
	<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col" width="10px">No.</th>
      <th scope="col">제목</th>
      <th scope="col">글쓴사람</th>
      <th scope="col">쓴날짜</th>
      <th scope="col">수정날짜</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${list}" var="QnaVO">
    <tr class="table-primary">
		<td>${QnaVO.qid}</td>
		<td>
		<script>
		document.write(${QnaVO.openy_n}?'🔓':'🔒');
		//document.write((${AnswerDTO} != null)?'[답변완료]':'[답변대기]');
		</script>
		<a href="/qna/get?currPage=${param.currPage}&amount=${param.amount}&qid=${QnaVO.qid}">${QnaVO.title}</a></td>
		<td>${QnaVO.uids}</td>
		<td>${QnaVO.insert_ts}</td>
		<td>${QnaVO.update_ts}</td>
    </tr>
    </c:forEach>
    
  </tbody>
</table>

<div>
  <ul class="pagination pagination-sm" >
    <li class="page-item disabled">
      <a class="page-link" href="#"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">&laquo;</font></font></a>
    </li>
                    <c:if test="${pageMaker.prev}">
                        <li class="page-item disabled">
                            <a class="page-link"  data-temp="${pageMaker.cri.setCurrPage(pageMaker.startPage - 1)}"
                                href="/qna/list${pageMaker.cri.pagingUri}"><font style="vertical-align: inherit;">&laquo;</font></font></a>
                        </li>
                    </c:if>
                    
                    <c:forEach var="pageNum" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                        <li class="${param.currPage eq pageNum ? 'currPage' : ''}">
                            <a class="page-link" data-temp="${pageMaker.cri.setCurrPage(pageNum)}"
                                href="/qna/list${pageMaker.cri.pagingUri}"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">${pageNum}</font></font></a>
                        </li>
                    </c:forEach>
                    
                    <c:if test="${pageMaker.next}">
                        <li class="page-item">
                            <a  data-temp="${pageMaker.cri.setCurrPage(pageMaker.endPage + 1)}"
                                href="/board/list${pageMaker.cri.pagingUri}">&raquo;</a>
                        </li>
                    </c:if>
     <c:if test=""></c:if>
    <li class="page-item">
      <a class="page-link" href="#"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">&raquo;</font></font></a>
    </li>
  </ul>
</div>

	</div>
    <script>
        var registerBtn = document.querySelector('#registerBtn');
        document.query

        registerBtn.addEventListener('click', function () {
            location = '/qna/register?currPage=${param.currPage}&amount=${param.amount}';
        }); // .addEventListener

        var result = "${param.result}";

        if(result != null && result != "") {        
            alert('result: ' + result);
        } // if

    </script>
	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>