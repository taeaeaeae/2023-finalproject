package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor

@RequestMapping("/mypage")
@Controller
public class MyPageController {
	
	@GetMapping("/main")
	public void mainPage() {
		//어떡하지 머해야되나흠 졸리다 집가고싶다 하 
	}

}
