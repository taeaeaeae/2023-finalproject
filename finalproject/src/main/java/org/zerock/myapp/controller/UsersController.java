package org.zerock.myapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.UsersService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@RequestMapping("/user")
@Controller
public class UsersController {	
	
	private UsersService service;
	
	@GetMapping({"/select","/update"})
	public void select(@RequestParam("uids") String uids, Model model) throws ControllerException {
		log.trace("select({}, {}) invoked.", uids, model);
		
		try {
			UsersVO vo = this.service.select(uids);
			model.addAttribute("users", vo);
		} catch(Exception e) {
			throw new ControllerException(e);
		}	//try- catch
		
	}	// select
	
	@PostMapping("/remove")
	public String remove(UsersDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.trace("remove({}, {}) invoked.", dto, rttrs);
		
		try {
			boolean success = this.service.remove(dto);
			log.info("\t+ success : {}", success);
			
			rttrs.addAttribute("result",(success)? "success" : "failure");
					
			return "redirect:/users/login";
		} catch(Exception e) {
			throw new ControllerException(e);
		}	//try- catch
		
	}	// remove
	
	@PostMapping("/update")
	public String update(UsersDTO dto, RedirectAttributes rttrs) throws ControllerException{
		log.trace("update({}, {} ) invoked.", dto, rttrs);
		
		try {
			boolean success = this.service.update(dto);
			log.info("\t+ success : {}", success);

			rttrs.addAttribute("result",(success)? "success" : "failure");
			
			return "redirect:/users/mypage";
		} catch(Exception e) {
			throw new ControllerException(e);
		}	// try-catch	
	}	//update

	
}	// end class
