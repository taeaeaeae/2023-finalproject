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
  .board_name {
  text-align: center;
  	color: black;
  }
  #ulululul {
  width: 300px;
  margin-left: auto;
  margin-right: auto;
  }
button {
}
a {
  text-decoration-line: none;
}

input[type=submit]{
  padding: 3px;
  margin: 10px;
  background-color: #eee;
  border-color: grey;
}

#search{
  position: static;
  text-align: right;
  padding-right: 11%;
}

#searchWord{
  padding: 4px;
}

section {
  height: 80%;
  text-align: center;
}
	</style>
    
    <link rel="stylesheet" href="WEB-INF/views/common/font.css">
    <link rel="canonical"
	href="https://getbootstrap.com/docs/5.2/examples/album/" />
	
</head>

<body>

	<%@ include file="/WEB-INF/views/common/header.jsp" %>
			<input type="hidden" name="currPage" value="${param.currPage}">
			<input type="hidden" name="amount" value="${param.amount}">
			<input type="hidden" name="result" value="${param.result}">
	<br>
	<h1 class="board_name">QnA</h1>
	<br>
	
	<c:if test="${not empty sessionScope['__AUTH__'].uids}">
	<button type="button" id="registerBtn" class="btn btn-primary btn-sm" style="float: right"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">등록하기</font></font></button>
	</c:if> <br> <br>

	<div id="" style="float: right;">
		<form action="/qna/list" method="GET" id="search-form" >
			<div id="input-group rounded" >
				<input type="hidden" name="currPage" value="1" id="currPage">
				<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
				<select style="height: 32px; padding: 2px;">
					<option value="title"> 제목</option>
					<option value="content">내용</option>
					<option value="title_content">제목 + 내용</option>
					<option value="uids">아이디</option>
				</select>
				<input type="text" name="keyword">
				<button type="submit">검색</button>
			</div>
		</form>
	</div>
	
	<div id="wrapper">
	
	<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col" width="10px">No.</th>
      <th scope="col">제목</th>
      <th scope="col">글쓴사람</th>
      <th scope="col">쓴날짜</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${list}" var="QnaVO" varStatus="qqid">
    <tr class="table-primary">

		<td>${QnaVO.qid}</td>
		<td>
		<script>
		document.write(${QnaVO.openy_n}?'🔓':'🔒');
		</script>
		<c:if test="${QnaVO.title eq link[qqid.index]}">

			<a href="/qna/list?result=${param.result}">[${ans[qqid.index]}] ${QnaVO.title}</a>
		</c:if>
		<c:if test="${'0' eq link[qqid.index]}">
			<a href="/qna/get?currPage=${param.currPage}&amount=${param.amount}&qid=${QnaVO.qid}">[${ans[qqid.index]}] ${QnaVO.title}</a>
		</c:if>
		</td>
		<td>${QnaVO.uids}</td>
		<td>
		<fmt:formatDate value="${QnaVO.insert_ts}" pattern="yyyy-MM-dd HH:mm"/>
		</td>
    </tr>
    </c:forEach>
    
  </tbody>
</table>

<div>
  <ul class="pagination pagination-sm" id="ulululul">
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
    <li class="page-item">
      <a class="page-link" href="#"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">&raquo;</font></font></a>
    </li>
  </ul>
</div>

	</div>
    <script>
        var registerBtn = document.querySelector('#registerBtn');


        registerBtn.addEventListener('click', function () {
            location = '/qna/register?currPage=${param.currPage}&amount=${param.amount}';
        }); // .addEventListener

        //var result = "${param.result}";


        
		 $(document).ready(function() {
	            let message = "${result}";
	            if (message != "" && message != null ) {
	                  alert("비밀글입니다");
	            }else {
	           }
	        });

    </script>
	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
   crossorigin="anonymous"></script>
</body>
</html>