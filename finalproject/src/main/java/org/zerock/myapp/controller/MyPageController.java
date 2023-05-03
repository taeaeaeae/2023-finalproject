package org.zerock.myapp.controller;


import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.BookmarkVO;
import org.zerock.myapp.domain.ChecklistDTO;
import org.zerock.myapp.domain.ChecklistVO;
import org.zerock.myapp.domain.LikesVO;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.domain.MycommentVO;
import org.zerock.myapp.domain.MywriteVO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.MypageService;
import org.zerock.myapp.service.UsersService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@RequestMapping("/mypage")
@Controller
public class MyPageController {
	
	@Autowired
	private UsersService service;
	
	@Autowired
	private MypageService mservice;

	
	@Inject
	BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@GetMapping({"/main","/update","/remove"})
	public void myPage(HttpSession session, Model model) throws ServiceException {
		log.info("mypage({},{}) invoked.", session, model);	
		
		log.info("session: {}", session);
		
		LoginVO uid = (LoginVO)session.getAttribute("__AUTH__");
		log.info("uid: {}", uid);
		
		UsersVO vo = service.select(uid.getUids());
		log.info("vo: {}", vo);
		
		model.addAttribute("mypage", vo);
		
	}	//mypage
	
	@PostMapping("/update")
	public String update(UsersDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.info("update({},{}) invoked.", dto, rttrs);	
		try {
			if(dto.getPassword().equals(dto.getPwCheck())) {
			dto.setPassword(bcryptPasswordEncoder.encode(dto.getPassword()));		
			
			
			boolean success = this.service.update(dto);
			log.info("\t+ success : {}", success);
			
			rttrs.addFlashAttribute("result",(success)? "수정이 완료되었습니다." : "failure");
			
			return "redirect:/mypage/main";
			}
			
			rttrs.addFlashAttribute("result", "수정에 실패하였습니다.");
			return "redirect:/mypage/update";
			
		} catch(Exception e) {
			throw new ControllerException(e);
		}	//try- catch
		
	}	//update
	

	@PostMapping("/remove")
	public String remove(UsersDTO dto, HttpSession session, Model model, RedirectAttributes rttrs) throws ControllerException {
		log.trace("remove() invoked.");
		
			
		try {
			LoginVO uid = (LoginVO)session.getAttribute("__AUTH__");
			UsersVO vo = service.select(uid.getUids());
			log.info(">>>>>>>>>>>>>>>>>>vo: {}", vo);
			
			dto.setPassword(dto.getPassword());
			
			if(bcryptPasswordEncoder.matches(dto.getPassword(),vo.getPassword())) {
				
			dto.setUids(vo.getUids());
			
			boolean success = this.service.remove(dto);
			log.info("\t+ success : {}", success);
			
			rttrs.addFlashAttribute("result",(success)? "회원탈퇴가 완료되었습니다." : "failure");
			return "redirect:/user/login";
			
			}
			
			rttrs.addFlashAttribute("result", "비밀번호가 일치하지 않습니다." );
			
			return "redirect:/mypage/remove";
		} catch(Exception e) {
			throw new ControllerException(e);
		}	//try- catch
	}	// remove
	
	@GetMapping("/mywrite")
    public String myWrite(Model model, HttpSession session) {

        LoginVO uid = (LoginVO)session.getAttribute("__AUTH__");
        if (uid == null) {

            return "redirect:/user/login";
        }
     
        ArrayList<MywriteVO> uids = mservice.mywrite(uid.getUids());
        
        log.info("{}",uids);
        model.addAttribute("mywrite", uids);
        
        return "/mypage/mywrite";
    }	//myWrite
	
	
	@GetMapping("/mycomment")
    public String mycomment(Model model, HttpSession session) {

        LoginVO uid = (LoginVO)session.getAttribute("__AUTH__");
        if (uid == null) {

            return "redirect:/user/login";
        }

        
        ArrayList<MycommentVO> uids = mservice.mycomment(uid.getUids());
        model.addAttribute("mycomment", uids);
        
        return "/mypage/mycomment";
    }	//mycomment
	
	@GetMapping({"/checklist","/listadd","/listdelete","/listupdate"})
	public String viewChecklist(Model model, HttpSession session, @RequestParam(required=false)Integer cid) {
		
		LoginVO uid = (LoginVO)session.getAttribute("__AUTH__");
        if (uid == null) {

            return "redirect:/user/login";
        }
		
		ArrayList<ChecklistVO> list = mservice.checklist(uid.getUids());
		
		model.addAttribute("list", list);
		model.addAttribute("uids",uid);
		log.info("list:{}", list);
		
		return "/mypage/checklist";
		
	}	//viewChecklist
	
	@PostMapping("/listadd")
	public String addChecklist(ChecklistDTO dto, RedirectAttributes rttrs, HttpSession session) throws ControllerException {
		LoginVO uid = (LoginVO)session.getAttribute("__AUTH__");
        if (uid == null) {

            return "redirect:/user/login"; 
        }       
        try {
        	dto.getCid();
			boolean success = this.mservice.listadd(dto);
			log.info("\t+ success : {}", success);
			
			rttrs.addAttribute("result",(success)? "success" : "failure");	
			
			return "redirect:/mypage/checklist";
		} catch(Exception e) {
			throw new ControllerException(e);
		}	//try- catch
		
	} //addChecklist
	
	@PostMapping("/listupdate")
	public String updateChecklist(@RequestParam("cid") Integer cid, ChecklistDTO dto, RedirectAttributes rttrs, HttpSession session) throws ControllerException {
		LoginVO uid = (LoginVO)session.getAttribute("__AUTH__");
        if (uid == null) {

            return "redirect:/user/login";
        }       
        try {
        	dto.getCid();
			boolean success = this.mservice.listupdate(dto);
			log.info("\t+ success : {}", success);
			
			rttrs.addAttribute("result",(success)? "success" : "failure");	
			
			return "redirect:/mypage/checklist";
		} catch(Exception e) {
			throw new ControllerException(e);
		}	//try- catch
		
	} //updateChecklist

	
	@PostMapping("/listdelete")
	public String deleteChecklist(@RequestParam("cid") Integer cid, ChecklistDTO dto, RedirectAttributes rttrs, HttpSession session) throws ControllerException {
		LoginVO uid = (LoginVO)session.getAttribute("__AUTH__");
        if (uid == null) {

            return "redirect:/user/login";
        }       
        try {
        	
        	dto.getUids();
        	dto.getCid();
        	
			boolean success = this.mservice.listdelete(dto);
			log.info("\t+ success : {}", success);
			
			rttrs.addAttribute("result",(success)? "success" : "failure");	
			
			return "redirect:/mypage/checklist";
		} catch(Exception e) {
			throw new ControllerException(e);
		}	//try- catch
		
	} //deleteChecklist
	
	@GetMapping("/likes")
    public String likes(Model model, HttpSession session) {

        LoginVO uid = (LoginVO)session.getAttribute("__AUTH__");
        if (uid == null) {

            return "redirect:/user/login";
        }
        
        ArrayList<LikesVO> uids = mservice.likes(uid.getUids());
        model.addAttribute("mylikes", uids);
        
        return "/mypage/likes";
    }	//likes
	
	
	@GetMapping("/bookmark")
    public String bookmark(Model model, HttpSession session) {

        LoginVO uid = (LoginVO)session.getAttribute("__AUTH__");
        if (uid == null) {

            return "redirect:/user/login";
        }
        
        ArrayList<BookmarkVO> uids = mservice.bookmark(uid.getUids());
        model.addAttribute("mybookmark", uids);
        
        return "/mypage/bookmark";
    }	//bookmark
	
	
}	// end class

