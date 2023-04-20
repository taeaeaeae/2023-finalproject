package org.zerock.myapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.FindDTO;
import org.zerock.myapp.domain.FindVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.FindService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/user")
@Controller
public class FindController {
	
	@Setter(onMethod_= @Autowired)
	private FindService service;
	
	
	@PostMapping("/findIdPost")
	public String findIdPost(FindDTO dto,RedirectAttributes rttrs, Model model) throws ControllerException {
		log.trace(">>>>>>> findIdPost({}, model) invoked.", dto);
		
		try {
			FindVO vo = this.service.findId(dto);
			
			log.info("\t+ vo: {}", vo);
			
			if(vo !=null) {
				model.addAttribute("FIND_ID", vo);
				log.info("vo: {}", vo);
				
				String uids = vo.getUids();
				log.info("uids: {}", uids);
				
				model.addAttribute("UIDS", uids);
				
		
				return "/user/findIdPost";
				
			} else {
				rttrs.addFlashAttribute("result", "일치하는 회원정보가 없습니다.");
				
				return "redirect:/user/find_id";
			}
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	}	// findPost
	
	@PostMapping("/findPwPost")
	public String findPwPost(FindDTO dto,RedirectAttributes rttrs, Model model) throws ControllerException {
		log.trace(">>>>>>> findPwPost({}, model) invoked.", dto);
		
		try {
			FindVO vo = this.service.findPw(dto);
					
			
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
				rttrs.addFlashAttribute("result", "일치하는 회원정보가 없습니다.");
				
				return "redirect:/user/find_pw";
			}
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
		
		
	}	// findPost
	
	
}	// end class