package org.zerock.myapp.controller;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.UsersService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@RequestMapping("/join")
@Controller
public class JoinController {	
	
	private UsersService service;
	
	@Inject
	BCryptPasswordEncoder bcryptPasswordEncoder;

	@PostMapping("/joinPost")
	public String JoinPost(UsersDTO dto, RedirectAttributes rttrs, Model model) throws ControllerException{
		log.trace("JoinPost({}, {}, {}) invoked.",dto, rttrs, model);
		
		try {
			
			dto.setPassword(bcryptPasswordEncoder.encode(dto.getPassword()));
			
			//dto.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
			
			boolean success = this.service.join(dto);
			log.info("\t+ success : {}", success);
			
			rttrs.addFlashAttribute("dto", dto);
			//rttrs.addAttribute("result",(success)? "success" : "failure");
			rttrs.addFlashAttribute("result",(success)? "회원가입이 완료되었습니다." : "failure");
			
			return "redirect:/user/login";
		} catch(Exception e) {
			throw new ControllerException(e);
		}	// try-catch	
		
	}	// JoinPost
	
	
}	// end class
