package org.zerock.myapp.controller;

import java.io.File;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.UsersService;
import org.zerock.myapp.utils.UploadFileUtils;

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
	
	@Qualifier("uploadPath")
	private String uploadPath;

	@PostMapping("/joinPost")
	public String JoinPost(UsersDTO dto, RedirectAttributes rttrs, Model model, MultipartFile file) throws ControllerException{
		log.trace("JoinPost({}, {}, {}) invoked.",dto, rttrs, model);
		
		try {
			
				// 프로필 사진 저장
				String imgUploadPath = uploadPath + "/" + "imgUpload";
				String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
				System.out.println(imgUploadPath);
				System.out.println(File.separator);
				
				String fileName = null;

				if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
				 fileName =  UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath); 
				 dto.setImage("/" + "imgUpload" + ymdPath + "/" + fileName);
				 
				} else {
				 fileName = uploadPath + "/" + "images" + "/" + "none.png";
				}

		
				// DB에 저장된 아이디 및 이메일이 없으며, 입력한 비밀번호가 일치 할 경우 회원가입 완료
				if(service.select(dto.getUids()) == null && service.selectEmail(dto.getEmail()) == null && dto.getPassword().equals(dto.getPwCheck())) {
				
				// 입력한 비밀번호를 암호화 한 후 DB에 저장
				dto.setPassword(bcryptPasswordEncoder.encode(dto.getPassword()));
				
				boolean success = this.service.join(dto);
				log.info("\t+ success : {}", success);
				
				rttrs.addFlashAttribute("dto", dto);
				rttrs.addFlashAttribute("result",(success)? "회원가입이 완료되었습니다." : "failure");
				
				return "redirect:/user/login";
			
			}
			
			rttrs.addFlashAttribute("result", "중복여부를 확인해주세요.");
			return "redirect:/user/join";
			
		} catch(Exception e) {
			throw new ControllerException(e);
		}	// try-catch	
		
	}	// JoinPost
	
	
}	// end class
