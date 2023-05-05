package org.zerock.myapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.myapp.domain.BookmarkDTO;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.BookmarkService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@ToString

@Controller
public class BookmarkController {
	
	@Setter(onMethod_ = {@Autowired})
	private BookmarkService service;
	
	@ResponseBody
	@PostMapping("/bookmark/register")
	public Map<String, Object> registerBookmark(BookmarkDTO dto, HttpSession session) throws ServiceException {
		
		try {
			
			LoginVO loginVO = (LoginVO)session.getAttribute("__AUTH__");
			log.info("\t+ __AUTH__ : {}", loginVO);
			
			Map<String, Object> response = new HashMap<>();

			if(loginVO != null) {
				dto.setUids(loginVO.getUids());
				boolean success = this.service.register(dto);
				log.trace("register success", success);
				
				response.put("bookmarking", success);
				response.put("unbookmarking", false);
				
				log.trace("response : {}", response);
			} // if
			return response;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // registerBookmark
	
	@ResponseBody
	@PostMapping("/bookmark/remove")
	public Map<String, Object> removeBookmark(BookmarkDTO dto, HttpSession session) throws ServiceException {
		
		try {
			
			LoginVO loginVO = (LoginVO)session.getAttribute("__AUTH__");
			log.info("\t+ __AUTH__ : {}", loginVO);
			Map<String, Object> response = new HashMap<>();

			if(loginVO != null) {
				dto.setUids(loginVO.getUids());
				boolean success = this.service.remove(dto);
				log.trace("remove success", success);
				
				response.put("bookmarking", false);
				response.put("unbookmarking", success);
				
				log.trace("response : {}", response);
				
			} // if
			return response;
		} catch (Exception e){
			throw new ServiceException(e);
		} // try-catch
	} // removeBookmark
	
} // end class
