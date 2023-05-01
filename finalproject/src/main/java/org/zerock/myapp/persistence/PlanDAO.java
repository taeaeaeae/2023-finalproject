package org.zerock.myapp.persistence;

import java.util.HashMap;
import java.util.List;

import org.zerock.myapp.domain.PlanVO;
import org.zerock.myapp.domain.ScheduleVO;

public interface PlanDAO {
	
	//계획 추가
	public void planAdd(PlanVO vo) throws Exception;
	
	//계획 번호 확인
	public int planNoCheck(PlanVO vo) throws Exception;
	
	//일정 개별 추가
	public void planSchAdd(ScheduleVO vo) throws Exception;
	
	//게시판용 계획 목록 및 페이징
	List<PlanVO> planList(HashMap<String, Object> data) throws Exception;
	
	//계획일정 목록
	public List<ScheduleVO> planSchList(int pid) throws Exception;

	//유저별 계획 목록
	List<PlanVO> planListForUser(HashMap<String, Object> data) throws Exception;

	//계획 조회
	PlanVO planView(PlanVO vo) throws Exception;

	//계획 수정
	void viewPlanModify(PlanVO vo);

	//계획 삭제
	void viewPlanDelete(PlanVO vo) throws Exception;
	
	//view 삭제 추가 수정 완료버튼
	void viewSchDelete(HashMap<String, Object> map) throws Exception;
}
