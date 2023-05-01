package org.zerock.myapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.persistence.BoardDAO;




@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDAO dao;
	
	//계획 총 갯수
	@Override
	public int boardPlanCnt(String searchType, String keyword) throws Exception {
		return dao.boardPlanCnt(searchType, keyword);
	}
	//유저별 계획 총 갯수

	@Override
	public int boardUserPlanCnt(String uids, String searchType, String keyword) throws Exception {
		searchType="userPlanTitle";
		return dao.boardUserPlanCnt(uids, searchType, keyword);
	}
	

	
	//일정 객체 출력
/*	@Override
	public ScheduleVO formData() throws Exception {
		return dao.list().get(0);
	}*/

}
