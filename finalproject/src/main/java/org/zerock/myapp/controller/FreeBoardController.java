package org.zerock.myapp.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FreeBoardCommentVO;
import org.zerock.myapp.domain.FreeBoardDTO;
import org.zerock.myapp.domain.FreeBoardVO;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.FreeBoardCommentService;
import org.zerock.myapp.service.FreeBoardService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
//@AllArgsConstructor

@RequestMapping("/freeboard")
@Controller

@SessionAttributes({"freeboard", "freeboardDTO"})
public class FreeBoardController {
	
	@Setter(onMethod_ = {@Autowired})
	private FreeBoardService service;
	
	@Setter(onMethod_ = {@Autowired})
	private FreeBoardCommentService commentService;
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) throws ControllerException {
		log.trace("list({}, {}) invoked.", cri, model);
		
		try {
//			List<FreeBoardVO> list = this.service.getList(cri);
			List<FreeBoardVO> list = this.service.getListPageSearch(cri);
			model.addAttribute("list", list);
			log.trace("\t+ list : {}", list);
			
			Integer totalAmount = this.service.getTotalAmount(cri);
			PageDTO pageDTO = new PageDTO(cri, totalAmount);
			log.info("\t+ pageDTO = {}", pageDTO);
			
			model.addAttribute("pageMaker", pageDTO);
			model.addAttribute("cri.getKeyword", cri.getKeyword());
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // list 
	
	@GetMapping({"/get", "modify"})
	public void get(Criteria cri, Integer fid, Model model) throws ControllerException{
		log.trace("get({}, {}, {}, {}, {}) invoked.", cri, fid, model);
		
		try {
//			increaseViewCount(fid, req, res);
			
			FreeBoardVO vo = this.service.get(fid);
			Integer totalAmount = this.service.getTotalAmount(cri);
			PageDTO pageDTO = new PageDTO(cri, totalAmount);
			
			model.addAttribute("freeboard", vo);
			model.addAttribute("pageMaker", pageDTO);
			model.addAttribute("fid", vo.getFid());
			
			model.addAttribute("prevFid", this.service.getPrevPost(fid));
			model.addAttribute("nextFid", this.service.getNextPost(fid));
			
			// comment
			List<FreeBoardCommentVO> commentList = this.commentService.getList(fid);
			model.addAttribute("commentList", commentList);
			
//			return "/freeboard/get";
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // get
	
	@PostMapping("/register")
	public String register(Criteria cri, FreeBoardDTO dto, RedirectAttributes rttrs, HttpServletRequest req) throws ControllerException{
		log.trace("register({}, {}, {}) invoked.", cri, dto, rttrs);
		
		try {
			HttpSession session = req.getSession();
			LoginVO loginVO = (LoginVO)session.getAttribute("__AUTH__");
			log.info("\t+ __AUTH__ : {}", loginVO);
			
			if(session.getAttribute("__AUTH__") == null) {		// session에 인증객체 없을 시
				
				rttrs.addAttribute("result", "failure");
			} else {
				dto.setUids(loginVO.getUids());
				boolean success = this.service.register(dto);

				log.info("\t+ success : {}", success);

				rttrs.addAttribute("currPage", cri.getCurrPage());
				rttrs.addAttribute("amount", cri.getAmount());
				rttrs.addAttribute("result", (success) ? "success" : "failure");
			} // if-else
			return "redirect:/freeboard/list";
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // register
	
	@GetMapping("/register")
	void register() {
		log.trace("register() invoked.");
		
	} // register
	
	@PostMapping({"/remove"})
	public String remove(Criteria cri, Integer fid, RedirectAttributes rttrs, HttpServletRequest req) throws ControllerException{
		log.trace("remove({}, {}, {}) invoked.", cri, fid, rttrs);
		
		try {
			HttpSession session = req.getSession();
			LoginVO loginVO = (LoginVO)session.getAttribute("__AUTH__");
			log.info("\t+ __AUTH__ : {}", loginVO);
			
			if(session.getAttribute("__AUTH__") == null) {		// session에 인증객체 없을 시
				
				rttrs.addAttribute("result", "failure");
			} else {
				FreeBoardVO freeboardVO = this.service.get(fid);
				log.info("\t+ __AUTH__ : {}", loginVO);
				
				boolean isAuth = freeboardVO.getUids().equals(loginVO.getUids());
				
				if(isAuth) {
					boolean success = this.service.remove(fid);
					log.info("\t+ success : {}", success);
					
					rttrs.addAttribute("currPage", cri.getCurrPage());
					rttrs.addAttribute("amount", cri.getAmount());
					
					rttrs.addAttribute("result", ( success ) ? "success" : "failure");
				} else {
					rttrs.addAttribute("result", ( isAuth ) ? "success" : "failure");
				} // if-else
			} // if-else
			
			return "redirect:/freeboard/list";
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // remove
	
	@PostMapping("/modify")
	public String modify(Criteria cri, FreeBoardDTO dto, RedirectAttributes rttrs, HttpServletRequest req) throws ControllerException {
		log.trace("modify() invoked.");
		
		try {
			HttpSession session = req.getSession();
			LoginVO loginVO = (LoginVO)session.getAttribute("__AUTH__");
			log.info("\t+ __AUTH__ : {}", loginVO);
			
			if (session.getAttribute("__AUTH__") == null) {
				
				rttrs.addAttribute("result", "failure");
			} else {
				FreeBoardVO freeboardVO = this.service.get(dto.getFid());
				log.trace("\t+ freeboardVO : {}", freeboardVO);
				
				boolean isAuth = freeboardVO.getUids().equals(loginVO.getUids());
				
				if(isAuth) {
					
					boolean success = this.service.modify(dto);
					log.info("\t+ success() : {}", success);
					
					rttrs.addAttribute("currPage", cri.getCurrPage());
					rttrs.addAttribute("amount", cri.getAmount());
					
					rttrs.addAttribute("result", (success) ? "sucess" : "failure");
				} else {
					rttrs.addAttribute("result", (isAuth) ? "Auth : success" : "Auth : failure");
				}  // if-else
			} // if-else
			return "redirect:/freeboard/get";
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // modify
	
	@GetMapping("/prev")
	public String getPrev(Criteria cri, Integer fid, Model model, RedirectAttributes rttrs) throws ControllerException{
		log.trace("get({}, {}) invoked.", fid, model);
		
		try {
			Integer prevFid = this.service.getPrevPost(fid);
			
			rttrs.addAttribute("currPage", cri.getCurrPage());
			rttrs.addAttribute("amount", cri.getAmount());
			
			rttrs.addAttribute("fid", prevFid);

			return "redirect:/freeboard/get";
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // prev
	
	@GetMapping("/next")
	public String getNext(Criteria cri, Integer fid, Model model, RedirectAttributes rttrs) throws ControllerException{
		log.trace("getNext({}, {}, {}, {}) invoked.", cri, fid, model, rttrs);
		
		try {
			Integer nextFid = this.service.getNextPost(fid);
			log.trace("\t+ nextFid : {}", nextFid);
			
			rttrs.addAttribute("currPage", cri.getCurrPage());
			rttrs.addAttribute("amount", cri.getAmount());
			rttrs.addAttribute("fid", nextFid);

			return "redirect:/freeboard/get";
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // next
	
	// 조회수 증가 중복 cookie
//	public void increaseViewCount(Integer fid, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
//	    Cookie[] cookies = request.getCookies();
//	    boolean isDuplicated = false;
//	    
//	    if (cookies != null) {
//	        for (Cookie cookie : cookies) {
//	            if (cookie.getName().equals("viewed_" + fid)) {
//	                isDuplicated = true;
//	                break;
//	            } // if
//	        } // for
//	    } // if
//	    if (!isDuplicated) {
//	        Cookie cookie = new Cookie("viewed_" + fid, "true");
//	        cookie.setMaxAge(24 * 60 * 60);
//	        cookie.setPath("/");
//	        response.addCookie(cookie);
//	        service.viewCountUp(fid);
//	    } // if
//	} // increaseViewCount

} // end class