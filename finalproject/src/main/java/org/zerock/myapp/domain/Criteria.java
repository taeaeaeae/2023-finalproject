package org.zerock.myapp.domain;

import lombok.Data;


//이건 DTO 페이징 전송파라 용도 수집 처리
@Data
public class Criteria {
	private Integer currPage = 1;
	private Integer amount = 10;
	private Integer pagesPerPage;

}// end class
