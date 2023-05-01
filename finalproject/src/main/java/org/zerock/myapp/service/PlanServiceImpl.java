package org.zerock.myapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.zerock.myapp.controller.PlanController;
import org.zerock.myapp.persistence.PlanDAO;
import org.zerock.myapp.domain.PlanVO;
import org.zerock.myapp.domain.ScheduleVO;

@Repository
public class PlanServiceImpl implements PlanService{
	
	private static final Logger log = LoggerFactory.getLogger(PlanController.class);
	@Autowired
	private PlanDAO dao;
	
	//계획 설정 추가
		@Override
		public void planAdd(PlanVO vo, List<Map<String, Object>> schList) throws Exception {
		dao.planAdd(vo);
		int pid = dao.planNoCheck(vo);
		for(int i = 0; i<schList.size(); i++) {
			ScheduleVO schVo = new ScheduleVO();
			//vo 값 세팅
			schVo.setPid(pid);
			schVo.setUids(vo.getUids());
			schVo.setDescript(schList.get(i).get("descript").toString());
			schVo.setAddr(schList.get(i).get("addr").toString());
			schVo.setPlanDay(Integer.parseInt(schList.get(i).get("planDay").toString()));
			schVo.setStartTime(Integer.parseInt(schList.get(i).get("startTime").toString()));
			schVo.setPlace(schList.get(i).get("place").toString());
			schVo.setLongitude(Double.parseDouble(schList.get(i).get("longitude").toString()));
			schVo.setLatitude(Double.parseDouble(schList.get(i).get("latitude").toString()));
			log.info(schVo.toString());
			dao.planSchAdd(schVo);
		}
		log.info("Insert Plan END");
	}
	
	//일정 List 객체
	@Override
	public Map<String, Object> schAdd(ScheduleVO vo) throws Exception {
		Map<String, Object> schMap = new HashMap<>();
		schMap.put("planDay", vo.getPlanDay());
		schMap.put("descript", vo.getDescript());
		schMap.put("addr", vo.getAddr());
		schMap.put("startTime", vo.getStartTime());
		schMap.put("place", vo.getPlace());
		schMap.put("longitude", vo.getLongitude());
		schMap.put("latitude", vo.getLatitude());
		return schMap;
	}
	
	//장바구니 일정 삭제 (데이터 받아와서 List에서 삭제)
	@Override
	public HashMap<String, Object> planDel(ScheduleVO vo) throws Exception {
		HashMap<String, Object> delMap = new HashMap<String, Object>();
		delMap.put("startTime",vo.getStartTime());
		delMap.put("planDay",vo.getPlanDay());
		delMap.put("descript",vo.getDescript());
		delMap.put("addr",vo.getAddr());
		delMap.put("place", vo.getPlace());
		delMap.put("longitude", vo.getLongitude());
		delMap.put("latitude", vo.getLatitude());
		log.info("delMap : " + delMap.toString());
		return delMap;
	}
	
	//게시판용 계획 목록 및 페이징 및 페이징 처리
	@Override
	public List<PlanVO> planList(int displayPost, int postNum, String searchType, String keyword) throws Exception {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		return dao.planList(data);
	}

	//일정 목록 출력
	@Override
	public List<ScheduleVO> planSchList(int pid) throws Exception {
		return dao.planSchList(pid);
	}
	
	//유저별 계획 목록
	@Override
	public List<PlanVO> planListForUser(String uids, int displayPost, int postNum, String searchType, String keyword) throws Exception {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("userId", uids);
		
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		return dao.planListForUser(data);
	}
	
	//계획 조회
	@Override
	public PlanVO planView(int pid, String uids) throws Exception {
		PlanVO vo = new PlanVO();
		vo.setPid(pid);
		vo.setUids(uids);
		return dao.planView(vo);
	}
	
	//계획 수정
	@Override
	public void planModify(PlanVO vo) throws Exception {
		dao.viewPlanModify(vo);
	}
	
	//계획 삭제
	@Override
	public void viewPlanDelete(PlanVO vo) throws Exception {
		dao.viewPlanDelete(vo);
	}
	
	//view 일정 삭제 Map만들기
	@Override
	public HashMap<String, Object> viewDeleteSch(ScheduleVO vo) throws Exception {
		//view_planner.jsp 일정 삭제 리스트
		HashMap<String, Object> deleteMap = new HashMap<String, Object>();
		deleteMap.put("pids", vo.getPid());
		deleteMap.put("rowNo", vo.getRowNo());
		log.info("deleteMap : " + deleteMap.toString());
		
		return deleteMap;
	}

	//view 수정완료 버튼 삭제 service
	@Override
	public void delSch(List<HashMap<String,Object>> delList) throws Exception {
		for (HashMap<String,Object> s : delList) {
			dao.viewSchDelete(s);
		}
	}
	//view 수정완료 일정 추가 service
	@Override
	public void viewPlanAdd(PlanVO vo, List<Map<String, Object>> schList) throws Exception {
		for(int i = 0; i<schList.size(); i++) {
		ScheduleVO schVo = new ScheduleVO();
		//vo 값 세팅
		schVo.setPid(vo.getPid());
		schVo.setUids(vo.getUids());
		schVo.setDescript(schList.get(i).get("descript").toString());
		schVo.setAddr(schList.get(i).get("addr").toString());
		schVo.setPlanDay(Integer.parseInt(schList.get(i).get("planDay").toString()));
		schVo.setStartTime(Integer.parseInt(schList.get(i).get("startTime").toString()));
		schVo.setPlace(schList.get(i).get("place").toString());
		schVo.setLongitude(Double.parseDouble(schList.get(i).get("longitude").toString()));
		schVo.setLatitude(Double.parseDouble(schList.get(i).get("latitude").toString()));
		log.info(schVo.toString());
		dao.planSchAdd(schVo);
	}
	log.info("Insert Plan END");
}
	

	

}
