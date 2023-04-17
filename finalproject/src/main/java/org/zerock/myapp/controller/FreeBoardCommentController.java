package org.zerock.myapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FreeBoardCommentDTO;
import org.zerock.myapp.domain.FreeBoardCommentVO;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.FreeBoardCommentService;
import org.zerock.myapp.service.FreeBoardService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
//@NoArgsConstructor
@AllArgsConstructor

@RequestMapping("/comment")
@Controller

@SessionAttributes("freeboardComment")
public class FreeBoardCommentController {
	
	@Setter(onMethod_ = {@Autowired})
	private FreeBoardCommentService service;

	@PostMapping("/register")
	public String commentRegister(Criteria cri, FreeBoardCommentDTO dto, RedirectAttributes rttrs) throws ControllerException{
		log.trace("commentRegister({}, {}, {}) invoked.", cri, dto, rttrs);
		
		try {
			boolean success = this.service.register(dto);
			log.trace("\t register success : {}", success);
			
			rttrs.addAttribute("currPage", cri.getCurrPage());
			rttrs.addAttribute("amount", cri.getAmount());
			rttrs.addAttribute("fid", dto.getFid());

			return "redirect:/freeboard/get";
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // commentRegister
	
	@PostMapping("/modify")
	@ResponseBody
	public Map<String, Object> commentModify(Criteria cri, FreeBoardCommentDTO dto, RedirectAttributes rttrs) throws ControllerException{
		log.trace("commentModify({}, {}, {}) invoked.", cri, dto, rttrs);
		
		try {
			boolean success = this.service.modify(dto);
			log.trace("\t modify success : {}", success);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", success);
			map.put("FreeBoardCommentVO", this.service.get(Integer.parseInt(dto.getFbcid())));
			log.trace("\t+ FreeBoardCommentVO : {}", map.get("FreeBoardCommentVO"));
			
			return map;
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // commentModify
	
//	@PostMapping("/modify") // 두번쨰 실패..
//	public FreeBoardCommentVO commentModify(Criteria cri, FreeBoardCommentDTO dto, RedirectAttributes rttrs) throws ControllerException{
//		log.trace("commentModify({}, {}, {}) invoked.", cri, dto, rttrs);
//		
//		try {
//			boolean success = this.service.modify(dto);
//			log.trace("\t register success : {}", success);
//			
//			return this.service.get(Integer.parseInt(dto.getFbcid()));
//		} catch (Exception e) {
//			throw new ControllerException(e);
//		} // try-catch
//	} // commentRegister

//	@PostMapping("/modify") // 첫번째 실패
//	public void commentModify(Criteria cri, FreeBoardCommentDTO dto, RedirectAttributes rttrs) throws ControllerException{
//		log.trace("commentModify({}, {}, {}) invoked.", cri, dto, rttrs);
//		
//		try {
//			boolean success = this.service.modify(dto);
//			log.trace("\t register success : {}", success);
//			
//			rttrs.addAttribute("currPage", cri.getCurrPage());
//			rttrs.addAttribute("amount", cri.getAmount());
//			rttrs.addAttribute("fid", dto.getFid());
//		} catch (Exception e) {
//			throw new ControllerException(e);
//		} // try-catch
//	} // commentRegister
	
	@PostMapping("/remove")
	public String commentRemove(Criteria cri, Integer fbcid, RedirectAttributes rttrs)throws ControllerException{
		log.trace("commentModify({}, {}, {}) invoked.", cri, fbcid, rttrs);
		
		try {
			FreeBoardCommentVO vo = this.service.get(fbcid);
			boolean success = this.service.remove(fbcid);
			log.trace("\t register success : {}", success);
			
			rttrs.addAttribute("currPage", cri.getCurrPage());
			rttrs.addAttribute("amount", cri.getAmount());
			rttrs.addAttribute("fid", vo.getFid());
			
			return "redirect:/freeboard/get";
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // commentRemove
	
} // end class