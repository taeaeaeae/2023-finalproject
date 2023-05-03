package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Controller
@RequestMapping("/")
public class MainController {
	
	@GetMapping("/TTT")
	public String main() {
			
		return "index";
	}
	
	
}
