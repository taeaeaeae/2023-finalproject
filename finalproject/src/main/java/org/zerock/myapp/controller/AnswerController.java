package org.zerock.myapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.AnswerDTO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.LoginVO;
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
	public String register(Criteria cri, AnswerDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.trace("register({}, {}, {}, {}) invoked.", dto, rttrs, cri);
		
		try {
			boolean success = this.service.register(dto);
			log.info("\t+ success: {}", success);
			
			rttrs.addAttribute("currPage", cri.getCurrPage());
			rttrs.addAttribute("amount", cri.getAmount());
			
			rttrs.addAttribute("result", (success)? "success" : "failure");
			
			return "redirect:/qna/list";
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // register
	
	

	
	@GetMapping("/answerRegister")
	void register(HttpSession session, Model model) {
		log.trace("register() invoked.");
		
		LoginVO login= (LoginVO)session.getAttribute("__AUTH__");
		model.addAttribute("id",login);
		
		
	} // register
	

} // end class
