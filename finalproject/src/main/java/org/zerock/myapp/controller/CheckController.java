package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.myapp.domain.CheckVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.CheckService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/join")
@Controller
public class CheckController {	// 회원가입시 아이디, 이메일 중복확인
	
	@Setter(onMethod_= @Autowired)
	private CheckService service;
	
	@PostMapping("/checkId")
	@ResponseBody
	public int checkId(CheckVO vo) throws ControllerException, ServiceException {
		log.trace(">>>>>>> checkId({}) invoked.", vo);
		
		int result = service.checkId(vo);
		
		return result;
		
	}	// checkId
	
	@PostMapping("/checkEmail")
	@ResponseBody
	public int checkEmail(CheckVO vo) throws ControllerException, ServiceException {
		log.trace(">>>>>>> checkEmail({}) invoked.", vo);
		
		int result = service.checkEmail(vo);
		
		return result;
		
	}	// checkEmail


}	// end class 
