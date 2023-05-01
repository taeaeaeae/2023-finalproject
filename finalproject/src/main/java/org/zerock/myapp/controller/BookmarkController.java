package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.zerock.myapp.domain.BookmarkDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.BookmarkService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@Controller
public class BookmarkController {
	
	@Setter(onMethod_ = {@Autowired})
	private BookmarkService service;
	
	@PostMapping("/bookmark/register")
	public String registerBookmark(BookmarkDTO dto) throws ServiceException {
		
		try {
			if(this.service.isBookmarked(dto)) {
				this.service.remove(dto);
				
				return "unregistered";
			} else {
				this.service.register(dto);
				
				return "registered";
			} // if-else
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // registerBookmark
	
	@GetMapping("/bookmark")
	public ResponseEntity<String> getBookmark(Model model, Integer fid) throws ServiceException{
		BookmarkDTO dto = new BookmarkDTO();
		dto.setFid(fid);
		boolean result = false;
		
		try {
			model.addAttribute("dto", dto);
			result = this.service.isBookmarked(dto);
		} catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} // try-catch
		return new ResponseEntity<>(String.valueOf(result), HttpStatus.OK);
	} // getBookmark
	
} // end class
