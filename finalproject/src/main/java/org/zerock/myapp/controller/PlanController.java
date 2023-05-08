package org.zerock.myapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.LoginVO;
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
	
	

	@GetMapping("/plan/write")
	
	public void plannerMain(HttpSession session, Model model) throws Exception{
		
		LoginVO users = (LoginVO)session.getAttribute("__AUTH__");
		
		model.addAttribute("users",users);
		
	}
	
	//계획 초기 설정
	@PostMapping("/plan/write/planSet")
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
	@PostMapping("/plan/write/schAdd")
	@ResponseBody
	public Map<String, Object> schAdd(ScheduleVO vo) throws Exception{
		schList.add(planService.schAdd(vo));
		log.info("schList: " + schList.toString());
      return planService.schAdd(vo);
	}
	//계획 추가
	@GetMapping("/plan/write/planAdd")
	public String planAdd() throws Exception {
		planService.planAdd(planSetList.get(0), schList);
		allPlanListClear();
		log.info("계획 추가 완료");
		return "redirect:/plan/write";
	}
	
	//장바구니 일정 삭제 (데이터 받아와서 List에서 삭제)
	@PostMapping("/plan/write/planDel")
	@ResponseBody
	public void planDel(@RequestBody ScheduleVO vo,HttpSession session, Model model) throws Exception {
		
		//세션추가
		ScheduleVO plan = (ScheduleVO)session.getAttribute("plan");
		model.addAttribute("plan",plan);
		
		schList.remove(planService.planDel(vo));
		log.info("schList : " + schList.toString());
	}
	// 새로고침 시 리스트 초기화
	
	@GetMapping("/plan/write/clear")
	@ResponseBody
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
	
	@PostMapping("/plan/view/modify")
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
		return "redirect:/plan/view?pid=" + vo.getPid() + "&uids=" +vo.getUids();
	}
	
	//view planDelete
	@PostMapping("/plan/view/planDel")
	@ResponseBody
	public void viewPlanDelete(PlanVO vo) throws Exception {
		
		log.info("planDel: "+vo.toString());
		planService.viewPlanDelete(vo);
	}
	
	//view schDelete
	@PostMapping("/plan/view/schDel")
	@ResponseBody
	public void viewSchDelete(@RequestBody ScheduleVO vo,HttpSession session, Model model) throws Exception {
	   LoginVO users = (LoginVO)session.getAttribute("__AUTH__");
		
		model.addAttribute("users",users);
		
		log.info("schDelete : " + vo.toString());
		planService.viewDeleteSch(vo);
		//delList에 만든 deleteMap 추가
		delList.add(planService.viewDeleteSch(vo));
		log.info("delList : " + delList.toString());
	}
}