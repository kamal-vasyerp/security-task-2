package com.practise.securitytasktwo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/security/api/v2")
public class SecurityController {

	@GetMapping("/admin")
	public ModelAndView adminPage() {
		ModelAndView mnv = new ModelAndView("admin");
		return mnv;
	}

	@GetMapping("/user")
	public ModelAndView userPage() {
		ModelAndView mnv = new ModelAndView("user");
		return mnv;
	}
	
	@GetMapping("/visitor")
	public ModelAndView visitorPage() {
		ModelAndView mnv = new ModelAndView("visitor");
		return mnv;
	}
	
}
