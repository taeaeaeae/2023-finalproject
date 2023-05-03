package org.zerock.myapp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.AnswerVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.domain.QnaDTO;
import org.zerock.myapp.domain.QnaVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.AnswerService;
import org.zerock.myapp.service.QnaService;
import org.zerock.myapp.utils.UploadFileUtils;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@AllArgsConstructor

@RequestMapping("/qna")
@Controller

//@SessionAttributes({"board", "QnaDTO"})
public class QnaController {
	
//	@Setter(onMethod_ = @Autowired)
	
	private QnaService service;
	private AnswerService aService;
	
	@Qualifier("uploadPath")
	private String uploadPath;
	
	@GetMapping("/search")
	public void search(Criteria cri, Model model, HttpSession session, RedirectAttributes rttrs) throws ControllerException {	
		log.trace("search() invoked.");
		try {
			
			List<QnaVO> list = this.service.searchList(cri);
			model.addAttribute("list", list);
			
			System.out.println(list);
			
			LoginVO login= (LoginVO)session.getAttribute("__AUTH__");
			log.info("login: {}", login);
			
			List<String> link = new ArrayList<String>();
			List<String> ans = new ArrayList<String>();
			
			for(QnaVO vo : list) {
				
				aService.get(vo.getQid());
				if(aService.get(vo.getQid()) == null) {
					ans.add("답변대기");
				} else {
					ans.add("답변완료");
				}
				
				String loginId = (login == null)?null:login.getUids();				
				String writer = vo.getUids();
				

				if((loginId != null)&&(loginId.equals("admin"))) {
					String temp = "0";
					link.add(temp);
				} else if((vo.isOpeny_n() == false) && ((loginId == null) || (writer.equals(loginId) != true))) {
					link.add(vo.getTitle());
					rttrs.addAttribute("result", "비밀글입니다.");
				} else {
					String temp = "0";
					link.add(temp);
				}//if-else
			}//for
			
			rttrs.addAttribute("currPage", cri.getCurrPage());
			rttrs.addAttribute("amount", cri.getAmount());
			
			int totalAmount = this.service.getTotalAmount();
			PageDTO pageDTO = new PageDTO(cri, totalAmount);
			log.info("\t+ pageDTO : {}", pageDTO);
			
			model.addAttribute("ans",ans);
			model.addAttribute("pageMaker", pageDTO);
			model.addAttribute("link", link);
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // list
	
	@GetMapping("/list")
	public void list(Criteria cri, 	Model model, HttpSession session, RedirectAttributes rttrs) throws ControllerException {	
		log.trace("list() invoked.");
		try {

			List<QnaVO> list = this.service.searchList(cri);
//			List<QnaVO> list = this.service.getList(cri);
			model.addAttribute("list", list);
			
			System.out.println(list);
			
			LoginVO login= (LoginVO)session.getAttribute("__AUTH__");
			log.info("login: {}", login);
			
			List<String> link = new ArrayList<String>();
			List<String> ans = new ArrayList<String>();
			
			for(QnaVO vo : list) {
				
				aService.get(vo.getQid());
				if(aService.get(vo.getQid()) == null) {
					ans.add("답변대기");
				} else {
					ans.add("답변완료");
				}
				
				String loginId = (login == null)?null:login.getUids();				
				String writer = vo.getUids();
				

				if((loginId != null)&&(loginId.equals("admin"))) {
					String temp = "0";
					link.add(temp);
				} else if((vo.isOpeny_n() == false) && ((loginId == null) || (writer.equals(loginId) != true))) {
					link.add(vo.getTitle());
				} else {
					String temp = "0";
					link.add(temp);
				}//if-else
			}//for
			
			rttrs.addAttribute("currPage", cri.getCurrPage());
			rttrs.addAttribute("amount", cri.getAmount());
			rttrs.addAttribute("result","비밀");
			
			int totalAmount = this.service.getTotalAmount();
			PageDTO pageDTO = new PageDTO(cri, totalAmount);
			log.info("\t+ pageDTO : {}", pageDTO);
			
			model.addAttribute("ans",ans);
			model.addAttribute("pageMaker", pageDTO);
			model.addAttribute("link", link);
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // list
	
	
	@GetMapping({ "/get", "/modify", "/answerRegister" , "/answerModify"})
	public void get(@RequestParam("qid") Integer qid, Model model, HttpSession session) throws ControllerException {
		log.trace("get({}, {}) invoked.", qid, model);

		
		try {

			QnaVO vo = this.service.get(qid);
			AnswerVO answer = this.aService.get(qid);
			
			LoginVO login = (LoginVO)session.getAttribute("__AUTH__");	
			
			log.info("login: {}", login);
			model.addAttribute("id", login);
			
			String loginn = (login == null)?null:login.getUids();
			String writer = vo.getUids();
			
			if((loginn != null)&&(loginn.equals("admin"))) {
				model.addAttribute("qna", vo);
				model.addAttribute("answer", answer);
			} else if((vo.isOpeny_n() == false) && ((loginn == null) || (writer.equals(loginn) != true))) {
			} else {
				model.addAttribute("qna", vo);
				model.addAttribute("answer", answer);
			}//if-else if-else
			
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // get
	
	
	@PostMapping("/remove")
	public String remove(Criteria cri ,Integer qid, RedirectAttributes rttrs, HttpSession session) throws ControllerException {
		log.trace("remove({}, {}, {}) invoked.", qid, rttrs, cri);
		
		try {
			LoginVO login= (LoginVO)session.getAttribute("__AUTH__");
			log.info("login: {}", login);
			
			QnaVO vo = this.service.get(qid);
			log.info("\n\n\n{},{}, {}",vo,qid );
			
			if(login.getUids().equals(vo.getUids())) {
				boolean success = this.service.remove(qid);
				log.info("\t+ success: {}", success);
				rttrs.addAttribute("result", (success)? "success" : "failure");
			} else {
				rttrs.addAttribute("result","안돼요");				
			}
			
			rttrs.addAttribute("currPage", cri.getCurrPage());
			rttrs.addAttribute("amount", cri.getAmount());
			
			return "redirect:/qna/list";
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // remove
	
	
	@PostMapping("/modify")
	public String modify(Criteria cri,QnaDTO dto, RedirectAttributes rttrs, HttpSession session) throws ControllerException {
		log.trace("modify({}, {}, {}) invoked.", dto, rttrs, cri);
		
		try {
			
			LoginVO login= (LoginVO)session.getAttribute("__AUTH__");
			log.info("login: {}", login);
			
			service.get(dto.getQid());
			String loginn = login.getUids();
			String writer = dto.getUids();
			
			
			if(writer.equals(loginn)) {
			
				boolean success = this.service.modify(dto);
				rttrs.addAttribute("currPage", cri.getCurrPage());
				rttrs.addAttribute("amount", cri.getAmount());
				
				
				
				rttrs.addAttribute("result", (success)? "수정되었습니다." : "실패함");
				
			} else {
				rttrs.addAttribute("result", "실패함");				
			}

			
			return "redirect:/qna/list";
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // modify
	
	
	@PostMapping("/register")
	public String register(Criteria cri,QnaDTO dto, RedirectAttributes rttrs, HttpSession session, MultipartFile file) throws ControllerException {
		log.trace("register({}, {}, {}, {}) invoked.", dto, rttrs, cri);
		
		try {

			LoginVO login= (LoginVO)session.getAttribute("__AUTH__");

			if((login != null) && (login.getUids().equals(dto.getUids()))) {

				//
				String imgUploadPath = uploadPath + "/" + "imgUpload";
				String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
				System.out.println(imgUploadPath);
				System.out.println(File.separator);
				
				String fileName = null;
				System.out.println("imgUploadPath"+imgUploadPath);
				System.out.println(" file.getOriginalFilename()"+ file.getOriginalFilename());

				if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
				 fileName =  UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath); 
				 dto.setImage("/" + "imgUpload" + ymdPath + "/" + fileName);
				} else {
				 fileName = uploadPath + "/" + "images" + "/" + "none.png";
				}

				//
				boolean success = this.service.register(dto);
				rttrs.addAttribute("result", "등록완료");
			} else {
				
				rttrs.addAttribute("result", "회원만 이용할 수 있습니다.");
				
//				return "redirect:/user/login";
			}//if-else
			
			rttrs.addAttribute("currPage", cri.getCurrPage());
			rttrs.addAttribute("amount", cri.getAmount());
			
//			rttrs.addAttribute("result", (success)? "등록되었습니다" : "회원만 이용할 수 있습니다.");
			
			return "redirect:/qna/list";
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // register

	@GetMapping("/register")
	void register(HttpSession session, Model model) {
		log.trace("register() invoked.");
		LoginVO login= (LoginVO)session.getAttribute("__AUTH__");
		model.addAttribute("id",login);
	} // register
	

} // end class
