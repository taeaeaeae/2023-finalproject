package org.zerock.myapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.myapp.domain.AnswerVO;
import org.zerock.myapp.domain.Criteria;
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
public class QnaController {
	
//	@Setter(onMethod_ = @Autowired)
	
	private QnaService service;
	private AnswerService aService;
	
	
	@GetMapping("/list")
	public void list(Criteria cri, 	Model model) throws ControllerException {	
		log.trace("list() invoked.");
		
		try {
			List<QnaVO> list = this.service.getList(cri);
			model.addAttribute("list", list);
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // list
	
	
	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("qid") Integer qid, Model model) throws ControllerException {
		log.trace("get({}, {}) invoked.", qid, model);
		
		try {
			QnaVO vo = this.service.get(qid);
			model.addAttribute("qna", vo);
			
			AnswerVO answer = this.aService.get(qid);
			model.addAttribute("answer", answer);
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // get
	
	
//	@PostMapping("/remove")
//	public String remove(Criteria cri ,Integer bno, RedirectAttributes rttrs) throws ControllerException {
//		log.trace("remove({}, {}, {}) invoked.", bno, rttrs, cri);
//		
//		try {
//			boolean success = this.service.remove(bno);
//			log.info("\t+ success: {}", success);
//			rttrs.addAttribute("currPage", cri.getCurrPage());
//			rttrs.addAttribute("amount", cri.getAmount());
//			
//			rttrs.addAttribute("result", (success)? "success" : "failure");
//			
//			return "redirect:/board/list";
//		} catch(Exception e) {
//			throw new ControllerException(e);
//		} // try-catch
//	} // remove
//	
//	
//	@PostMapping("/modify")
//	public String modify(Criteria cri,QnaDTO dto, RedirectAttributes rttrs) throws ControllerException {
//		log.trace("modify({}, {}, {}) invoked.", dto, rttrs, cri);
//		
//		try {
//			boolean success = this.service.modify(dto);
//			log.info("\t+ success: {}", success);
//			rttrs.addAttribute("currPage", cri.getCurrPage());
//			rttrs.addAttribute("amount", cri.getAmount());
//			
//			rttrs.addAttribute("result", (success)? "success" : "failure");
//			
//			return "redirect:/board/list";
//		} catch(Exception e) {
//			throw new ControllerException(e);
//		} // try-catch
//	} // modify
//	
//	
//	@PostMapping("/register")
//	public String register(Criteria cri,QnaDTO dto, RedirectAttributes rttrs) throws ControllerException {
//		log.trace("register({}, {}, {}, {}) invoked.", dto, rttrs, cri);
//		
//		try {
//			boolean success = this.service.register(dto);
//			log.info("\t+ success: {}", success);
//			
//			rttrs.addAttribute("currPage", cri.getCurrPage());
//			rttrs.addAttribute("amount", cri.getAmount());
//			
//			rttrs.addAttribute("result", (success)? "success" : "failure");
//			
//			return "redirect:/board/list";
//		} catch(Exception e) {
//			throw new ControllerException(e);
//		} // try-catch
//	} // register
//	
//	
//	
////	@ModelAttribute("QnaDTO")
////	QnaDTO createQnaDTO() {	
////		log.trace("createQnaDTO() invoked.");
////		
////		QnaDTO dto = new QnaDTO();
////		dto.setBno(1000);
////		dto.setTitle("TEST");
////		
////		return dto;
////	} // createQnaDTO
////	
//	
//	@GetMapping("/register")
//	void register() {
//		log.trace("register() invoked.");
//		
//	} // register
//	

} // end class
