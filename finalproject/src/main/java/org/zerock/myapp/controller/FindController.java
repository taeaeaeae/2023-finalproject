package org.zerock.myapp.controller;


import java.security.SecureRandom;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.EmailDTO;
import org.zerock.myapp.domain.FindDTO;
import org.zerock.myapp.domain.FindVO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.EmailService;
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
	
	@Autowired
	EmailService emailService;
	
	@Inject
	BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@GetMapping("/find_id")
	public void find_id() {
		log.info("find_id() invoked.");
	}	//find_id
	
	@GetMapping("/find_pw")
	public void find_pw() {
		log.info("find_pw() invoked.");
	}	//find_pw
	
	public class PasswordGenerator {

	    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	    private static final String NUMBER = "0123456789";
	    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER ;
	    private static final Random random = new SecureRandom();

	    public static String generatePassword(int length) {
	        if (length < 8) {
	            throw new IllegalArgumentException("Password length must be at least 8 characters.");
	        }
	        StringBuilder password = new StringBuilder(length);
	        for (int i = 0; i < length; i++) {
	            int index = random.nextInt(PASSWORD_ALLOW_BASE.length());
	            password.append(PASSWORD_ALLOW_BASE.charAt(index));
	        }
	        return password.toString();
	    } // generate newpassword 
	    
	}	// end class

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
				rttrs.addFlashAttribute("result", "회원정보가 존재하지 않습니다.");
				
				return "redirect:/user/find_id";
			}	//if -else
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	}	// findIdPost
	
	
	@PostMapping("/findPwPost")
	public String findPwPost(FindDTO dto, RedirectAttributes rttrs, Model model) throws ControllerException {
		log.trace(">>>>>>> findPwPost({}, model) invoked.", dto);
		
		try {
			FindVO vo = this.service.findPw(dto);
			log.info("\t+ vo: {}", vo);
			
			if(vo !=null) {
				
				String password = PasswordGenerator.generatePassword(10);
				log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>password:{}", password);
				
				dto.setPassword(bcryptPasswordEncoder.encode(password));
				log.info("dto:{}", dto);
				
				UsersVO newPassword = this.service.newPassword(dto);
				log.info("####################### newpassword:{}", newPassword);
				
				EmailDTO emailDTO = new EmailDTO();
				
				String adress = vo.getEmail();
				
				emailDTO.setReceiveMail(adress);
				emailDTO.setSenderMail("shiningdubhe@gmail.com");
				emailDTO.setSenderName(adress);
				
				emailDTO.setSubject("TTT 임시비밀번호 입니다. ["+dto.getUids()+"]");
				emailDTO.setMessage("임시비밀번호: [" + password + "] 확인 후 비밀번호 수정 부탁드립니다.");
				
				emailService.sendMail(emailDTO);
				
				rttrs.addFlashAttribute("result", "임시비밀번호발송 완료.");
				
				return "redirect:/user/login";
				
			} else {
				rttrs.addFlashAttribute("result", "회원정보가 존재하지 않습니다.");
				
				return "redirect:/user/find_pw";
			}	//if -else
			 
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	}	// findPwPost
	
	
}	// end class