package org.zerock.myapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zerock.myapp.domain.PlanVO;
import org.zerock.myapp.domain.ScheduleVO;

public interface PlanService {

	//일정 List 객체
	public Map<String, Object> schAdd(ScheduleVO vo)throws Exception;
	
	//장바구니 일정 삭제 (데이터 받아와서 List에서 삭제)
	public HashMap<String, Object> planDel(ScheduleVO vo) throws Exception;
	
	//계획 설정 추가, PlanNo 확인, 계획 DB 추가
	public void planAdd(PlanVO vo, List<Map<String,Object>> schList) throws Exception;
	//게시판용 계획 목록 및 페이징 처리
	public List<PlanVO> planList(int displayPost, int postNum, String searchType, String keyword) throws Exception;
	

	//계획일정 목록
	public List<ScheduleVO> planSchList(int pid) throws Exception;

	//유저별 계획 목록
	public List<PlanVO> planListForUser(String uids, int displayPost, int postNum, String searchType, String keyword) throws Exception;

	//계획 조회
	public PlanVO planView(int pid, String uids) throws Exception;

	//계획 수정
	public void planModify(PlanVO vo) throws Exception;

	//계회 삭제
	public void viewPlanDelete(PlanVO vo) throws Exception;

	//view 일정 삭제 Map만들기
	public HashMap<String, Object> viewDeleteSch(ScheduleVO vo) throws Exception;
	
	//view 수정완료 버튼 삭제 service
	public void delSch(List<HashMap<String,Object>> delList) throws Exception;
	
	//view 수정완료 일정 추가 service
	public void viewPlanAdd(PlanVO vo, List<Map<String, Object>> schList) throws Exception;
}
