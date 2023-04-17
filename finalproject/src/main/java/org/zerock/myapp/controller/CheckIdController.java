package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.myapp.service.UsersService;

import lombok.extern.log4j.Log4j2;

@Log4j2

@RequestMapping("/user")
@Controller
public class CheckIdController {
	
	@Autowired
	private UsersService usersService;
	
	
		//아이디 중복체크
		@PostMapping("/idCheck")
		@ResponseBody
		public int idCheck(@RequestParam("uids") String uids) {
			
			int cnt = usersService.idCheck(uids);
			log.info("\t+cnt: {}", cnt);
			
			return cnt;
			
		}
		
	
}