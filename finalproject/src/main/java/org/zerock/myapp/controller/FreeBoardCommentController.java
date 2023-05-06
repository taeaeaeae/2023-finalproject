package org.zerock.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FreeBoardCommentDTO;
import org.zerock.myapp.domain.FreeBoardCommentVO;
import org.zerock.myapp.domain.FreeBoardVO;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.FreeBoardCommentService;
import org.zerock.myapp.service.FreeBoardService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
//@AllArgsConstructor

@RequestMapping("/comment")
@RestController
public class FreeBoardCommentController {
	
	@Setter(onMethod_ = {@Autowired})
	private FreeBoardCommentService service;
	
	@GetMapping(value = "/list/{fid}", 
				produces = {MediaType.APPLICATION_ATOM_XML_VALUE,
							MediaType.APPLICATION_JSON_UTF8_VALUE}) // ?
	public ResponseEntity<List<FreeBoardCommentVO>> getList(@PathVariable("fid") Integer fid) throws ControllerException{
		
		ResponseEntity<List<FreeBoardCommentVO>> entity = null;
		
		try {
			entity = new ResponseEntity<List<FreeBoardCommentVO>>(this.service.getList(fid), HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<FreeBoardCommentVO>>(HttpStatus.BAD_REQUEST);
			throw new ControllerException(e);
		} // try-catch
		
		return entity;
	} // getList
	
	@PostMapping(value = "/register", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> commentRegister(@RequestBody FreeBoardCommentDTO dto, HttpSession session) throws ControllerException{
		
		ResponseEntity<String> entity = null;
		LoginVO loginVO = (LoginVO)session.getAttribute("__AUTH__");
		
		try {
			
			if(loginVO == null) {
				entity = new ResponseEntity<String>("Not logged in", HttpStatus.UNAUTHORIZED);
				
				return entity;
			} else {
				this.service.register(dto);
				entity = new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			throw new ControllerException(e);
		} // try-catch
		
		return entity;
	} // commentRegister
	
	@PostMapping(value = "/modify/{fbcid}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> commentModify(
										@PathVariable("fbcid") Integer fbcid, 
										@RequestBody FreeBoardCommentDTO dto,
										HttpSession session) throws ControllerException{
		log.trace("commentModify({}) invoked.", fbcid);
		
		ResponseEntity<String> entity = null;
		LoginVO loginVO = (LoginVO)session.getAttribute("__AUTH__");
		
		try {
			
			if(loginVO == null) {
				entity = new ResponseEntity<String>("Not logged in", HttpStatus.UNAUTHORIZED);
				return entity;
			} else {
				FreeBoardCommentVO vo = this.service.get(fbcid);
				dto.setFbcid(vo.getFbcid());
				this.service.modify(dto);
				
				entity = new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);

			throw new ControllerException(e);
		}
		return entity;
	} // commentModify
	
	@DeleteMapping(value = "/remove/{fbcid}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> commentRemove(@PathVariable("fbcid") Integer fbcid, HttpSession session) throws ControllerException{
		
		ResponseEntity<String> entity = null;
		LoginVO loginVO = (LoginVO)session.getAttribute("__AUTH__");
		
		try {
			if(loginVO == null) {
				entity = new ResponseEntity<String>("Not logged in", HttpStatus.UNAUTHORIZED);
				return entity;
			} else {
				this.service.remove(fbcid);
				entity = new ResponseEntity<String>("success", HttpStatus.OK);
			} // if-else
		} catch (Exception e) {
			new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			throw new ControllerException(e);
		} // try-catch
		
		return entity;
	} // remove
	
} // end class