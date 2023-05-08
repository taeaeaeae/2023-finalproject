package org.zerock.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.domain.Paging;
import org.zerock.myapp.domain.PlanVO;
import org.zerock.myapp.domain.ScheduleVO;
import org.zerock.myapp.service.BoardService;
import org.zerock.myapp.service.PlanService;



@Controller
public class BoardController {

	@Autowired
	private PlanService planService;
	@Autowired
	private BoardService boardService;

	// 게시판에 계획 리스트 출력 및 페이징
	@GetMapping("/plan/list")
	public String planList(Model model, @RequestParam("num") int num,
			@RequestParam(value = "searchType", required = false, defaultValue = "planTitle") String searchType,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword, HttpSession session)
			throws Exception {
		// 정적 계획 리스트 초기화
		PlanController.allPlanListClear();
		// 페이징 필드 값 생성자 통해서 초기화
		Paging page = new Paging(num, searchType, keyword);
		// 전체 계획 갯수 구하기 (검색어 입력시 해당 검색 계획 갯수)
		page.setCount(boardService.boardPlanCnt(searchType, keyword));
		// 계획 리스트 (검색어 입력시 해당 검색 리스트 출력)
		List<PlanVO> planList = planService.planList(page.getDisplayPost(), page.getPostNum(), searchType, keyword);
		// Model에 필요 값들 담아 list page로 리턴
		model.addAttribute("planList", planList);
		model.addAttribute("page", page);

		LoginVO users = (LoginVO) session.getAttribute("__AUTH__");

		model.addAttribute("users", users);

		return "/plan/list";
	}

	// 유저별 계획 리스트 출력
	@GetMapping("/plan/list/user")
	public String planListForUser(Model model, @RequestParam("uids") String uids, @RequestParam("num") int num,
			@RequestParam(value = "searchType", required = false, defaultValue = "planTitle") String searchType,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword, HttpSession session)
			throws Exception {
		// 정적 계획 리스트 초기화
		PlanController.allPlanListClear();

		// 페이징 필드 값 생성자 통해서 초기화
		Paging page = new Paging(num, searchType, keyword);
		// 전체 계획 갯수 구하기 (검색어 입력시 해당 검색 계획 갯수)
		page.setCount(boardService.boardUserPlanCnt(uids, searchType, keyword));
		// 계획 리스트 (검색어 입력시 해당 검색 리스트 출력)
		/*
		 * List<PlanVO> planList = planService.planList(page.getDisplayPost(),
		 * page.getPostNum(), searchType, keyword);
		 */
		List<PlanVO> planListForUser = planService.planListForUser(uids, page.getDisplayPost(), page.getPostNum(),
				searchType, keyword);
		model.addAttribute("planListForUser", planListForUser);
		model.addAttribute("page", page);

		LoginVO users = (LoginVO) session.getAttribute("__AUTH__");

		model.addAttribute("users", users);

		return "/plan/list_user";
	}

	// 계획 조회하기
	@GetMapping("/plan/view")
	public String planView(Model model, @RequestParam("pid") int pid, @RequestParam("uids") String uids,
			HttpSession session) throws Exception {

		LoginVO users = (LoginVO) session.getAttribute("__AUTH__");

		model.addAttribute("users", users);

		// 계획 호출
		PlanVO planView = planService.planView(pid, uids);
		model.addAttribute("planView", planView);

		// 일정 호출
		List<ScheduleVO> scheduleList = planService.planSchList(pid);
		model.addAttribute("scheduleList", scheduleList);
		return "/plan/view";

	}

}// end
