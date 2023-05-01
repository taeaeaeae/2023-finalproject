package org.zerock.myapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.zerock.myapp.domain.PlanVO;
import org.zerock.myapp.domain.ScheduleVO;
import org.zerock.myapp.service.PlanService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class PlanController {
	
	
	@Autowired
	private PlanService planService;
		
	//계획 초기 설정 저장 객체
	static List<PlanVO> planSetList = new ArrayList<>();
	//일정 추가 리스트
	static List<Map<String,Object>> schList = new ArrayList<>();
	//view 일정 삭제 리스트
	static List<HashMap<String,Object>> delList = new ArrayList<>();
	//로그인 후 일정 만들기 화면으로 이동
	@RequestMapping(value="/plan/write", method = RequestMethod.GET)
	public void plannerMain() throws Exception{
	}
	
	//계획 초기 설정
	@RequestMapping(value="/plan/write/planSet", method=RequestMethod.POST)
	@ResponseBody
	public PlanVO planSet(PlanVO vo,RedirectAttributes rttr)throws Exception{
		if(planSetList.size()!=0) {
			allPlanListClear();
			planSetList.add(vo);
		}else {
			planSetList.add(vo);
		}
		log.info("planSetList :"+planSetList.toString());
		log.info("schList :"+schList.toString());
		return vo;
	}
	
	//일정 추가
	@RequestMapping(value="/plan/write/schAdd", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> schAdd(ScheduleVO vo) throws Exception{
		schList.add(planService.schAdd(vo));
		log.info("schList: " + schList.toString());
      return planService.schAdd(vo);
	}
	//계획 추가
	@RequestMapping(value="/plan/write/planAdd", method=RequestMethod.GET)
	public String planAdd() throws Exception {
		planService.planAdd(planSetList.get(0), schList);
		allPlanListClear();
		log.info("계획 추가 완료");
		return "redirect:/plan/write";
	}
	
	//장바구니 일정 삭제 (데이터 받아와서 List에서 삭제)
	@RequestMapping(value="/plan/write/planDel", method=RequestMethod.POST)
	@ResponseBody
	public void planDel(@RequestBody ScheduleVO vo) throws Exception {
		schList.remove(planService.planDel(vo));
		log.info("schList : " + schList.toString());
	}
	// 새로고침 시 리스트 초기화
	@ResponseBody
	@RequestMapping(value="/plan/write/clear", method=RequestMethod.GET)
	public String planRefresh(String param) throws Exception{
		log.info("refreshMapping: "+param);
		allPlanListClear();
		return param;
	}
	
	// 전체 리스트 초기화 메서드
	public static void allPlanListClear() {
		planSetList.clear();
		schList.clear();
		delList.clear();
		log.info("All PlanList Clear");
	}
		
	//계획 수정하기(완료버튼)
	@RequestMapping(value = "/plan/view/modify", method = RequestMethod.POST)
	public String viewPlanModify(PlanVO vo) throws Exception {
		log.info("PlanVO : " + vo.toString());
		//계획 수정하기 함수
		planService.planModify(vo);
		//일정 삭제 함수
		planService.delSch(delList);
		//일정 추가 함수
		planService.viewPlanAdd(vo, schList);
		//list초기화
		allPlanListClear();
		return "redirect:/plan/view?planNo=" + vo.getPid() + "&uids=" +vo.getUids();
	}
	
	//view planDelete
	@RequestMapping(value="/plan/view/planDel", method=RequestMethod.POST)
	@ResponseBody
	public void viewPlanDelete(PlanVO vo) throws Exception {
		log.info("planDel: "+vo.toString());
		planService.viewPlanDelete(vo);
	}
	
	//view schDelete
	@RequestMapping(value="/plan/view/schDel", method=RequestMethod.POST)
	@ResponseBody
	public void viewSchDelete(@RequestBody ScheduleVO vo) throws Exception {
		log.info("schDelete : " + vo.toString());
		planService.viewDeleteSch(vo);
		//delList에 만든 deleteMap 추가
		delList.add(planService.viewDeleteSch(vo));
		log.info("delList : " + delList.toString());
	}
}