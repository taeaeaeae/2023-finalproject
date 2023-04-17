package org.zerock.myapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.domain.FindIdDTO;
import org.zerock.myapp.domain.FindIdVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.FindIdService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/user")
@Controller
public class FindIdController {
	
	@Setter(onMethod_= @Autowired)
	private FindIdService service;
	
	
	@PostMapping("/findIdPost")
	public String findIdPost(FindIdDTO dto, Model model) throws ControllerException {
		log.trace(">>>>>>> findPost({}, model) invoked.", dto);
		
		try {
			FindIdVO vo = this.service.findId(dto);
			
			log.info("\t+ vo: {}", vo);
			
			if(vo !=null) {
				model.addAttribute("FIND_ID", vo);
				log.info("vo: {}", vo);
				
				String uids = vo.getUids();
				log.info("uids: {}", uids);
				
				model.addAttribute("UIDS", uids);
		
				return "/user/findIdPost";
				
			} else {
				return "redirect:/user/find_id";
			}
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
		
		
	}	// findPost
	
}	// end class