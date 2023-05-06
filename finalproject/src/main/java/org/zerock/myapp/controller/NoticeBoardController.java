package org.zerock.myapp.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FreeBoardDTO;
import org.zerock.myapp.domain.FreeBoardVO;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.domain.NoticeDTO;
import org.zerock.myapp.domain.NoticeVO;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.NoticeBoardService;
import org.zerock.myapp.utils.UploadFileUtils;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
//@NoArgsConstructor
@AllArgsConstructor

@RequestMapping("/notice")
@Controller

@SessionAttributes({"noticeboard", "noticeDTO"})
public class NoticeBoardController {
	
	@Setter(onMethod_ = {@Autowired})
	private NoticeBoardService service;
	
	@Qualifier("uploadPath")
	private String uploadPath;
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) throws ControllerException {
		log.trace("list({}, {}) invoked.", cri, model);
		
		try {
//			List<FreeBoardVO> list = this.service.getList(cri);
			List<NoticeVO> list = this.service.getListPageSearch(cri);
			model.addAttribute("list", list);
			log.trace("\t+ list : {}", list);
			
			Integer totalAmount = this.service.getTotalAmount(cri);
			PageDTO pageDTO = new PageDTO(cri, totalAmount);
			log.info("\t+ pageDTO = {}", pageDTO);
			
			model.addAttribute("pageMaker", pageDTO);
			model.addAttribute("cri.getKeyword", cri.getKeyword());
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // list 
	
	@GetMapping({"/get", "/modify"})
	public void get(Criteria cri, Integer nid, Model model) throws ControllerException{
		log.trace("get({}, {}, {}) invoked.", cri, nid, model);
		
		try {
//			increaseViewCount(fid, req, res);
			
			NoticeVO vo = this.service.get(nid);
			Integer totalAmount = this.service.getTotalAmount(cri);
			PageDTO pageDTO = new PageDTO(cri, totalAmount);
			
			model.addAttribute("noticeboard", vo);
			model.addAttribute("pageMaker", pageDTO);
			model.addAttribute("nid", vo.getNid());
			
			model.addAttribute("prevNid", this.service.getPrevPost(nid));
			model.addAttribute("nextNid", this.service.getNextPost(nid));
			
			// comment
//			List<FreeBoardCommentVO> commentList = this.commentService.getList(fid);
//			model.addAttribute("commentList", commentList);
			
//			return "/freeboard/get";
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // get
	
	@PostMapping("/register")
	public String register(Criteria cri, NoticeDTO dto, RedirectAttributes rttrs, HttpSession session, MultipartFile file) throws ControllerException{
		log.trace("register({}, {}, {}, {}, {}) invoked.", cri, dto, rttrs, session, file);
		
		try {
			LoginVO loginVO = (LoginVO)session.getAttribute("__AUTH__");
			log.info("\t+ __AUTH__ : {}", loginVO);
			
			if(session.getAttribute("__AUTH__") == null) {		// session에 인증객체 없을 시
				
				rttrs.addAttribute("result", "failure");
			} else {
				// image upload용
				String imgUploadPath = uploadPath + "/" + "imgUpload";
				String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
				System.out.println("1. : " + imgUploadPath);
				System.out.println("2. : " + ymdPath);
				System.out.println("3. : " + File.separator);
				
				String fileName = null;

				if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
					System.out.printf("4. file.getOriginalFilename : %s", file.getOriginalFilename());
					 fileName =  UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath); 
					 System.out.println("5. fileName :" + fileName);
					 dto.setImage("\\" + "imgUpload" + ymdPath + "/" + fileName);
					} else {
					 fileName = uploadPath + "/" + "images" + "/" + "none.png";
					 System.out.println("6. fileName :" + fileName);
				}

				dto.setUids(loginVO.getUids());
				boolean success = this.service.register(dto);

				log.info("\t+ success : {}", success);

				rttrs.addAttribute("currPage", cri.getCurrPage());
				rttrs.addAttribute("amount", cri.getAmount());
				rttrs.addAttribute("result", (success) ? "등록 완료" : "failure");
			} // if-else
			return "redirect:/notice/list";
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // register
	
	@GetMapping("/register")
	void register() {
		log.trace("register() invoked.");
		
	} // register
	
	@PostMapping({"/remove"})
	public String remove(Criteria cri, Integer nid, RedirectAttributes rttrs, HttpSession session) throws ControllerException{
		log.trace("remove({}, {}, {}) invoked.", cri, nid, rttrs);
		
		try {
			LoginVO loginVO = (LoginVO)session.getAttribute("__AUTH__");
			log.info("\t+ __AUTH__ : {}", loginVO);
			
			if(session.getAttribute("__AUTH__") == null) {		// session에 인증객체 없을 시
				
				rttrs.addAttribute("result", "failure");
			} else {
				NoticeVO noticeVO = this.service.get(nid);
				log.info("\t+ __AUTH__ : {}", loginVO);
				
				boolean isAuth = noticeVO.getUids().equals(loginVO.getUids());
				
				if(isAuth) {
					boolean success = this.service.remove(nid);
					log.info("\t+ success : {}", success);
					
					rttrs.addAttribute("currPage", cri.getCurrPage());
					rttrs.addAttribute("amount", cri.getAmount());
					
					rttrs.addAttribute("result", ( success ) ? "삭제 완료" : "failure");
					
				} else {
					rttrs.addAttribute("result", ( isAuth ) ? "success" : "failure");
				} // if-else
			} // if-else
			
			return "redirect:/notice/list";
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // remove
	
	@PostMapping("/modify")
	public String modify(Criteria cri, NoticeDTO dto, RedirectAttributes rttrs, HttpSession session) throws ControllerException {
		log.trace("modify() invoked.");
		
		try {
			LoginVO loginVO = (LoginVO)session.getAttribute("__AUTH__");
			log.info("\t+ __AUTH__ : {}", loginVO);
			
			if (session.getAttribute("__AUTH__") == null) {
				
				rttrs.addAttribute("result", "failure");
			} else {
				NoticeVO noticeVO = this.service.get(dto.getNid());
				log.trace("\t+ freeboardVO : {}", noticeVO);
				
				boolean isAuth = noticeVO.getUids().equals(loginVO.getUids());
				
				if(isAuth) {
					boolean success = this.service.modify(dto);
					log.info("\t+ success() : {}", success);
					
					rttrs.addAttribute("currPage", cri.getCurrPage());
					rttrs.addAttribute("amount", cri.getAmount());
					rttrs.addAttribute("nid", dto.getNid());
					
					rttrs.addAttribute("result", (success) ? "수정 완료" : "failure");
				} else {
					rttrs.addAttribute("result", (isAuth) ? "Auth : success" : "Auth : failure");
				}  // if-else
			} // if-else
			return "redirect:/notice/list";
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // modify
	
	@GetMapping("/prev")
	public String getPrev(Criteria cri, Integer nid, Model model, RedirectAttributes rttrs) throws ControllerException{
		log.trace("get({}, {}) invoked.", nid, model);
		
		try {
			Integer prevNid = this.service.getPrevPost(nid);
			
			rttrs.addAttribute("currPage", cri.getCurrPage());
			rttrs.addAttribute("amount", cri.getAmount());
			
			rttrs.addAttribute("nid", prevNid);

			return "redirect:/notice/get";
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // prev
	
	@GetMapping("/next")
	public String getNext(Criteria cri, Integer nid, Model model, RedirectAttributes rttrs) throws ControllerException{
		log.trace("getNext({}, {}, {}, {}) invoked.", cri, nid, model, rttrs);
		
		try {
			Integer nextNid = this.service.getNextPost(nid);
			log.trace("\t+ nextFid : {}", nextNid);
			
			rttrs.addAttribute("currPage", cri.getCurrPage());
			rttrs.addAttribute("amount", cri.getAmount());
			rttrs.addAttribute("nid", nextNid);

			return "redirect:/notice/get";
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // next
	
	// 조회수 증가 중복 cookie
//	public void increaseViewCount(Integer fid, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
//	    Cookie[] cookies = request.getCookies();
//	    boolean isDuplicated = false;
//	    
//	    if (cookies != null) {
//	        for (Cookie cookie : cookies) {
//	            if (cookie.getName().equals("viewed_" + fid)) {
//	                isDuplicated = true;
//	                break;
//	            } // if
//	        } // for
//	    } // if
//	    if (!isDuplicated) {
//	        Cookie cookie = new Cookie("viewed_" + fid, "true");
//	        cookie.setMaxAge(24 * 60 * 60);
//	        cookie.setPath("/");
//	        response.addCookie(cookie);
//	        service.viewCountUp(fid);
//	    } // if
//	} // increaseViewCount

} // end class