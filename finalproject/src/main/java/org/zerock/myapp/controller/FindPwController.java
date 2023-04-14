package org.zerock.myapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.domain.FindPwDTO;
import org.zerock.myapp.domain.FindPwVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.FindPwService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/user")
@Controller
public class FindPwController {
	
	@Setter(onMethod_= @Autowired)
	private FindPwService service;
	
	
	@PostMapping("/findPwPost")
	public String findPwPost(FindPwDTO dto, Model model) throws ControllerException {
		log.trace(">>>>>>> findPwPost({}, model) invoked.", dto);
		
		try {
			FindPwVO vo = this.service.findPw(dto);
					
			
			log.info("\t+ vo: {}", vo);
			
			if(vo !=null) {
				model.addAttribute("FIND_PW", vo);
				log.info("vo: {}", vo);
				
				String password = vo.getPassword();
				log.info("password: {}", password);
				
				int pwSize = (password.length())-3;
				log.info("pwSize: {}", pwSize);
				
				String exResultPw = password.substring(0,pwSize);
				log.info("exResultPw: {}", exResultPw);
				
				String temp = "***";
				
				String resultPw = exResultPw + temp;
				
				log.info("resultPw: {}", resultPw);
				
				model.addAttribute("PASSWORD", resultPw);
		
				return "/user/findPwPost";
				
			} else {
				
				return "redirect:/user/find_pw";
			}
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
		
		
	}	// findPost
	
}	// end class