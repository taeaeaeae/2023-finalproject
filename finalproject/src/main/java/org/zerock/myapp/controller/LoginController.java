package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.LoginService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@RequestMapping("/user")
@Controller
public class LoginController {	
	
	private LoginService service;
	
	@PostMapping("/loginPost")
	public String loginPost(LoginDTO dto, RedirectAttributes rttrs, Model model) throws ControllerException{
		log.trace("loginPost({}, {}, {}) invoked.",dto, rttrs, model);
		
		try {
			LoginVO vo = this.service.login(dto);
			
			log.info("\t+vo:{}", vo);
			
			if(vo != null) {	
				model.addAttribute("__AUTH__",vo);	
				
				return null;			
			} else {			
				rttrs.addAttribute("result", "Login Failed");
				
				return "redirect:/user/login";			
			}	//if-else
			
		} catch(Exception e) {
			throw new ControllerException(e);
		}	// try-catch
		
	}	// loginPost
	
	
	@GetMapping("/logout")
	public void dummyLogout() {
		
		log.trace("dummyLogout() invoked.");
		
		}	//logout
	
}	// end class
