package org.zerock.myapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.UsersService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@RequestMapping("/mypage")
@Controller
public class MyPageController {
	
	@Autowired
	private UsersService service;
	
	@GetMapping({"/main","/update","/remove"})
	public void myPage(HttpSession session, Model model) throws ServiceException {
		log.info("mypage({},{}) invoked.", session, model);	
		
		log.info("session: {}", session);
		
		LoginVO uid = (LoginVO)session.getAttribute("__AUTH__");
		log.info("uid: {}", uid);
		
		UsersVO vo = service.select(uid.getUids());
		log.info("vo: {}", vo);
		
		model.addAttribute("mypage", vo);
		
	}//mypage
	
	@PostMapping("/update")
	public String update(UsersDTO dto, RedirectAttributes rttrs) throws ServiceException {
		log.info("update({},{}) invoked.", dto, rttrs);	

		boolean success = this.service.update(dto);
		
		rttrs.addAttribute("result", (success)? "success":"failure");
		
		return "redirect:/mypage/main";
		
	}	//update
	

	@PostMapping("/remove")
	public String remove(String uids, Model model, RedirectAttributes rttrs) throws ControllerException {
		log.trace("remove() invoked.");
		
		try {
			
			boolean success = this.service.remove(uids);
			log.info("\t+ success : {}", success);
			
			rttrs.addAttribute("result",(success)? "success" : "failure");
			
			return "redirect:/user/login";
		} catch(Exception e) {
			throw new ControllerException(e);
		}	//try- catch
	}	// remove
}	// end class

