package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.FreeBoardDTO;
import org.zerock.myapp.domain.FreeBoardVO;
import org.zerock.myapp.domain.ReportsDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.FreeBoardService;
import org.zerock.myapp.service.ReportsService;

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
	private FreeBoardService freeboardService;
	
	@PostMapping("/register")
	public String RegisterReports(Integer fid, ReportsDTO dto, Model model) throws ServiceException{
		log.trace("RegisterReport({}, {}, {}) invoked", dto, fid, model);
		
		try {
			boolean success = this.service.InsertPostReports(dto);
			log.info("\t+ success : {}", success);
			
			model.addAttribute("result", (success) ? "success" : "failure");
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		return "redirect:/reports/register";
	} // RegisterReport 가져오기
	
	@GetMapping("/register")
	public void RegisterReport() throws ServiceException{
		log.trace("RegisterReport() invoked");
		
	} // RegisterReport

} // end class