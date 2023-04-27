package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/bookmark")
	public ResponseEntity<String> getBookmark(@RequestParam Integer fid) {
		log.debug("getBookmark({}) invoked.", fid);
		
		BookmarkDTO dto = new BookmarkDTO();
		dto.setFid(fid);
		
		boolean result = false;
		try {
			result = this.service.isBookmarked(dto);
		} catch (ServiceException e) {
			log.error("Exception: {}", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(String.valueOf(result), HttpStatus.OK);
	} // getBookmark
	
	@PostMapping("/bookmark")
	public ResponseEntity<Void> toggleBookmark(@RequestBody BookmarkDTO dto) {
		log.debug("toggleBookmark({}) invoked.", dto);
		
		boolean result = false;
		try {
			if (this.service.isBookmarked(dto)) {
				result = this.service.remove(dto);
			} else {
				result = this.service.register(dto);
			}
		} catch (ServiceException e) {
			log.error("Exception: {}", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (result) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	} // toggleBookmark
	
} // end class
