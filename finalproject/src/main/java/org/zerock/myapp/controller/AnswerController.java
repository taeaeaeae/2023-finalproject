package org.zerock.myapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.AnswerDTO;
import org.zerock.myapp.domain.AnswerVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.domain.QnaVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.AnswerService;
import org.zerock.myapp.service.QnaService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@AllArgsConstructor

@RequestMapping("/qna")
@Controller

//@SessionAttributes({"board", "QnaDTO"})
public class AnswerController {
	
//	@Setter(onMethod_ = @Autowired)
	
	private QnaService qService;
	private AnswerService service;
	
	
	@PostMapping("/answerModify")
	public String modify(Criteria cri,AnswerDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.trace("modify({}, {}, {}) invoked.", dto, rttrs, cri);
		
		try {
			boolean success = this.service.modify(dto);
			log.info("\t+ success: {}", success);
			rttrs.addAttribute("currPage", cri.getCurrPage());
			rttrs.addAttribute("amount", cri.getAmount());
			
			rttrs.addAttribute("result", (success)? "success" : "failure");
			
			return "redirect:/qna/list";
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // modify
	
	
	@PostMapping("/answerRegister")
	public String register(Criteria cri, AnswerDTO dto, RedirectAttributes rttrs, HttpSession session) throws ControllerException {
		log.trace("register({}, {}, {}, {}) invoked.", dto, rttrs, cri);
		
		try {
			LoginVO login= (LoginVO)session.getAttribute("__AUTH__");
			
			if((login != null) && (login.getUids().equals("admin"))) {
				boolean success = this.service.register(dto);
				rttrs.addAttribute("result", "등록완료");
			} else {
				rttrs.addAttribute("result", "실패");
			}//if-else
			log.info("\t+ succ");
			
			
			rttrs.addAttribute("currPage", cri.getCurrPage());
			rttrs.addAttribute("amount", cri.getAmount());
			
			
			return "redirect:/qna/list";
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // register
	
//	@GetMapping({"/answerRegister" })
//	public void get(@RequestParam("qid") Integer qid, Model model, HttpSession session) throws ControllerException {
//		log.trace("get({}, {}) invoked.", qid, model);
//
//		
//		try {
//			LoginVO login= (LoginVO)session.getAttribute("__AUTH__");
//			log.info("login: {}", login);
//
//			QnaVO vo = this.service.get(qid);
//			AnswerVO answer = this.aService.get(qid);
//			
//			String loginn = (login == null)?null:login.getUids();				
//			String writer = vo.getUids();
//			
//			if( (vo.isOpeny_n() == false) && ((loginn == null) || (writer.equals(loginn) != true))) {
//								
//			} else {
//				model.addAttribute("qna", vo);
//				model.addAttribute("answer", answer);	
//			}
//			
//		} catch(Exception e) {
//			throw new ControllerException(e);
//		} // try-catch
//	} // get
	

	
//	@GetMapping("/answerRegister")
//	void register(@RequestParam("qid") Integer qid, HttpSession session, Model model) {
//		log.trace("register() invoked.");
//		
//		LoginVO login= (LoginVO)session.getAttribute("__AUTH__");
//		model.addAttribute("id",login);
//	} // register
	

} // end class
