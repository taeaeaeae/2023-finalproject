package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class Paging {
	
	public Paging(int num, String searchType, String keyword) {
		setNum(num);
		setSearchType(searchType);
		setKeyword(keyword);
	}
	// 현재 페이지 번호
	private int num;

	// 계획 총 갯수
	private int count;

	// 한 페이지에 출력할 계획 갯수
	private int postNum = 10;

	// 하단 페이징 번호
	private int pageNum;

	// 출력할 계획
	private int displayPost;

	// 한번에 표시할 페이징 번호의 갯수
	private int pageNumCnt = 10;

	// 표시되는 페이지 번호 중 마지막 번호
	private int endPageNum;

	// 표시되는 페이지 번호 중 첫번째 번호
	private int startPageNum;
	
	// 검색 타입
	private String searchType;
	
	// 검색 키워드
	private String keyword;
	
	// 다음/이전 표시 여부
	private boolean prev;
	private boolean next;
	
	
	//각 필드 계산 메서드
	private void dataCalc() {
		 
		 // 마지막 번호
		 endPageNum = (int)(Math.ceil((double)num / (double)pageNumCnt) * pageNumCnt);
		 
		 // 시작 번호
		 startPageNum = endPageNum - (pageNumCnt - 1);
		 
		 // 마지막 번호 재계산
		 int endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNumCnt));
		 
		 if(endPageNum > endPageNum_tmp) {
		  endPageNum = endPageNum_tmp;
		 }
		 
		 prev = startPageNum == 1 ? false : true;
		 next = endPageNum * pageNumCnt >= count ? false : true;
		 
		 displayPost = (num - 1) * postNum;
	}
	
	public String getSearchTypeAndKeyword() {
		if(searchType.equals("") || keyword.equals("")) {
			return "";
		}else {
			return "&searchType="+searchType+"&keyword="+keyword;
		}
	}
}
