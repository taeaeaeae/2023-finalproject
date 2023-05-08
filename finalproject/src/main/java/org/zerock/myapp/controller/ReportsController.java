package org.zerock.myapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.EmailDTO;
import org.zerock.myapp.domain.LoginVO;
import org.zerock.myapp.domain.ReportsDTO;
import org.zerock.myapp.domain.ReportsVO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.EmailService;
import org.zerock.myapp.service.FreeBoardService;
import org.zerock.myapp.service.ReportsService;
import org.zerock.myapp.service.UsersService;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@RequestMapping("/reports")
@Controller
public class ReportsController {
	
	@Setter(onMethod_ = {@Autowired})
	private ReportsService service;
	private UsersService uservice;
	private EmailService eservice;
	private FreeBoardService freeboardService;
	
	@PostMapping("/register")
	public void RegisterReports(Integer fid, ReportsDTO dto, Model model) throws ServiceException{
		log.trace("RegisterReport({}, {}, {}) invoked", dto, fid, model);
		
		try {
			
			boolean success = this.service.InsertPostReports(dto);
			log.info("\t+ success : {}", success);
			
			model.addAttribute("result", (success) ? "success" : "failure");
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // RegisterReport 가져오기
	
	@GetMapping("/register")
	public void RegisterReport() throws ServiceException{
		log.trace("RegisterReport() invoked");
		
	} // RegisterReport
	
	@PostMapping("/dropout")
    public String dropout(ReportsDTO dto, Model model, HttpSession session, RedirectAttributes rttrs) throws ServiceException {
		
        LoginVO uid = (LoginVO)session.getAttribute("__AUTH__");
        if (uid == null) {

            return "redirect:/user/login";
        }
        
        boolean success = this.service.dropout(dto);
        boolean status = this.service.status(dto);
        String reason = dto.getReason();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\t+ reason : {}", reason);

     // 임시비밀번호 메일 발송
		EmailDTO emailDTO = new EmailDTO();
		
        UsersVO vo = this.uservice.select(dto.getTarget_user());
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\t+ vo : {}", vo);
        
        String email = vo.getEmail();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>\t+ email : {}", email);
		
		emailDTO.setReceiveMail(email);
		emailDTO.setSenderMail("traveltrademate@gmail.com");
		emailDTO.setSenderName(email);
		
		emailDTO.setSubject("TTT에서 알립니다.");
		emailDTO.setMessage("귀하는 " + reason + "으로 다른 사용자에게 신고를 당하여 탈퇴되었음을 알립니다.");
		
		eservice.sendMail(emailDTO);
        
		log.info("\t+ success : {}", success);
		log.info("\t+ status : {}", status);
			
		rttrs.addFlashAttribute("result",(success)? "회원탈퇴가 완료되었습니다." : "failure");
        
        return "redirect:/mypage/report";
    }	//dropout
	
	@PostMapping("/dropin")
	public String dropin(ReportsDTO dto, Model model, HttpSession session, RedirectAttributes rttrs) throws ServiceException{
		
		 LoginVO uid = (LoginVO)session.getAttribute("__AUTH__");
	        if (uid == null) {

	            return "redirect:/user/login";
	        }
	        
		try {
			
			boolean success = this.service.dropin(dto);
			boolean dropinStatus = this.service.dropinStatus(dto);
			log.info("\t+ success : {}", success);
			log.info("\t+ dropinStatus : {}", dropinStatus);
			
			model.addAttribute("result", (success) ? "success" : "failure");
			
			return "redirect:/mypage/report";
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch

	} // RegisterReport 가져오기
	
} // end class