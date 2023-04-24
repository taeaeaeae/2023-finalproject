package org.zerock.myapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.AnswerVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.domain.QnaDTO;
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
	public void list(Criteria cri, 	Model model, HttpSession session, RedirectAttributes rttrs) throws ControllerException {	
		log.trace("list() invoked.");
		try {
			
			List<QnaVO> list = this.service.getList(cri);
			model.addAttribute("list", list);
			
			System.out.println(list);
			
			LoginVO login= (LoginVO)session.getAttribute("__AUTH__");
			log.info("login: {}", login);
			
			List<String> link = new ArrayList<String>();
			
			for(QnaVO vo : list) {
				String loginId = (login == null)?null:login.getUids();				
				String writer = vo.getUids();
				if( (vo.isOpeny_n() == false) && ((loginId == null) || (writer.equals(loginId) != true))) {
					link.add(vo.getTitle());
				} else {
					String temp = "0";
					link.add(temp);
					rttrs.addAttribute("result", "비밀글입니다.");
				}//if-else
			}//for
			
			rttrs.addAttribute("currPage", cri.getCurrPage());
			rttrs.addAttribute("amount", cri.getAmount());
			
			int totalAmount = this.service.getTotalAmount();
			PageDTO pageDTO = new PageDTO(cri, totalAmount);
			log.info("\t+ pageDTO : {}", pageDTO);
			
			model.addAttribute("pageMaker", pageDTO);
			model.addAttribute("link", link);
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // list
	
	
	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("qid") Integer qid, Model model, HttpSession session) throws ControllerException {
		log.trace("get({}, {}) invoked.", qid, model);

		
		try {
			LoginVO login= (LoginVO)session.getAttribute("__AUTH__");
			log.info("login: {}", login);

			QnaVO vo = this.service.get(qid);
			AnswerVO answer = this.aService.get(qid);
			
			String loginn = (login == null)?null:login.getUids();				
			String writer = vo.getUids();
			
			if( (vo.isOpeny_n() == false) && ((loginn == null) || (writer.equals(loginn) != true))) {
								
			} else {
				model.addAttribute("qna", vo);
				model.addAttribute("answer", answer);	
			}
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // get
	
	
	@PostMapping("/remove")
	public String remove(Criteria cri ,Integer qid, RedirectAttributes rttrs, HttpSession session) throws ControllerException {
		log.trace("remove({}, {}, {}) invoked.", qid, rttrs, cri);
		
		try {
			LoginVO login= (LoginVO)session.getAttribute("__AUTH__");
			log.info("login: {}", login);
			
			QnaVO vo = this.service.get(qid);
			log.info("\n\n\n{},{}, {}",vo,qid );
			
				
			if(login.getUids().equals(vo.getUids())) {
				boolean success = this.service.remove(qid);
				log.info("\t+ success: {}", success);
				rttrs.addAttribute("result", (success)? "success" : "failure");
			} else {
				rttrs.addAttribute("result","안돼요");				
			}
			
			
			rttrs.addAttribute("currPage", cri.getCurrPage());
			rttrs.addAttribute("amount", cri.getAmount());

			
			return "redirect:/qna/list";
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // remove
	
	
	@PostMapping("/modify")
	public String modify(Criteria cri,QnaDTO dto, RedirectAttributes rttrs, HttpSession session) throws ControllerException {
		log.trace("modify({}, {}, {}) invoked.", dto, rttrs, cri);
		
		try {
			
			LoginVO login= (LoginVO)session.getAttribute("__AUTH__");
			log.info("login: {}", login);
			
			service.get(dto.getQid());
			String loginn = login.getUids();
			String writer = dto.getUids();
			
			
			if(writer.equals(loginn)) {
				
				boolean success = this.service.modify(dto);
				rttrs.addAttribute("currPage", cri.getCurrPage());
				rttrs.addAttribute("amount", cri.getAmount());
				
				
				
				rttrs.addAttribute("result", (success)? "수정되었습니다." : "실패함");
				
			} else {
				rttrs.addAttribute("result", "실패함");				
			}

			
			return "redirect:/qna/list";
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // modify
	
	
	@PostMapping("/register")
	public String register(Criteria cri,QnaDTO dto, RedirectAttributes rttrs, HttpSession session) throws ControllerException {
		log.trace("register({}, {}, {}, {}) invoked.", dto, rttrs, cri);
		
		try {

			boolean success = this.service.register(dto);
			log.info("\t+ success: {}", success);
			
			rttrs.addAttribute("currPage", cri.getCurrPage());
			rttrs.addAttribute("amount", cri.getAmount());
			
			rttrs.addAttribute("result", (success)? "등록되었습니다" : "등록안됨");
			
			return "redirect:/qna/list";
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // register

	@GetMapping("/register")
	void register(HttpSession session, Model model) {
		log.trace("register() invoked.");
		LoginVO login= (LoginVO)session.getAttribute("__AUTH__");
		model.addAttribute("id",login);
	} // register
	

} // end class
