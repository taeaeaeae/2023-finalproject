package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;

@NoArgsConstructor

@Controller
@RequestMapping("/TTT")
public class mapController {
 
@GetMapping("/map")
public String map(){
	
	return "root/map";
}
	
}
