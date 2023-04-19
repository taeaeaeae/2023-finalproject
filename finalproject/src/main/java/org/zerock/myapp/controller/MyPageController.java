package org.zerock.myapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.UsersService;

import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@RequestMapping("/mypage")
@Controller
//@SessionAttributes({"main", "UsersVO"})
public class MyPageController {
	
	@Autowired
	private UsersService service;
	
	@GetMapping("/main")
	public String myPage(HttpSession session, RedirectAttributes rttrs, Model model) throws ServiceException {
		log.info("mypage() invoked.");	
		
		log.info("session: {}", session);
		
		LoginVO uid = (LoginVO)session.getAttribute("__AUTH__");
		log.info("uid: {}", uid);
		
		UsersVO vo = service.select(uid.getUids());
		log.info("vo: {}", vo);
		
		model.addAttribute("mypage", vo);
		
		return "/mypage/main";
		
	}	//mypage
	
	
//	@PostMapping("/update")
//	public String update(LoginDTO dto, RedirectAttributes rttrs) throws ControllerException{
//		log.trace("update({}, {} ) invoked.", dto, rttrs);
//		
//		try {
//			boolean success = this.service.update(dto);
//			log.info("\t+ success : {}", success);
//
//			rttrs.addAttribute("result",(success)? "success" : "failure");
//			
//			return "redirect:/users/mypage";
//		} catch(Exception e) {
//			throw new ControllerException(e);
//		}	// try-catch	
//	}	//update
	

}	// end class

	

	
	

	
//	@PostMapping("/remove")
//	public String remove(String uids, RedirectAttributes rttrs) throws ControllerException {
//		log.trace("remove({}, {}) invoked.", uids, rttrs);
//		
//		try {
//			boolean success = this.service.remove(uids);
//			log.info("\t+ success : {}", success);
//			
//			rttrs.addAttribute("result",(success)? "success" : "failure");	
//					
//			return "redirect:/users/login";
//		} catch(Exception e) {
//			throw new ControllerException(e);
//		}	//try- catch
//	}	// remove
	



