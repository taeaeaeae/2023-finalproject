package org.zerock.myapp.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ControllerAdvice
public class CommonExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, Model model) {
		log.trace("handleException({}) invoked.", e.getClass().getName());
		
		model.addAttribute("__EXCEPTION__", e);
		
		Throwable t = e;
		
		do {
			log.error("====================================");
			log.error("Exception Type: {}", t.getClass().getName());
			log.error("Exception Message: {}", t.getMessage());
			
		} while((t = t.getCause()) !=null);
		
		return "errorPage";	//예외응답화면생성 View 이름
	}	// handleException
	
	

}	// end class
