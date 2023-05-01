package org.zerock.myapp.persistence;


public interface BoardDAO {
	
	//계획 총 갯수
	public int boardPlanCnt(String searchType, String keyword) throws Exception;
	
	//유저별 계획 총 갯수
	public int boardUserPlanCnt(String uids, String searchType, String keyword) throws Exception;
}
