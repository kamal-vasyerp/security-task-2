package com.practise.securitytasktwo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.practise.securitytasktwo.model.UserInfo;
import com.practise.securitytasktwo.model.UserInfoDto;
import com.practise.securitytasktwo.service.UserInfoService;

@Controller
@RequestMapping("/security/api/v3")
public class SecurityController {

	@Autowired
	private UserInfoService userInfoService;
	
	@GetMapping("/signup-page")
	public ModelAndView signupPage() {
		ModelAndView mnv = new ModelAndView("signup");
		return mnv;
	}
	
	@GetMapping("/home")
	public ModelAndView homePage() {
		ModelAndView mnv = new ModelAndView("home");
		return mnv;
	}
	
	@PostMapping("/signup")
	public String saveUser(UserInfoDto userInfoDto) {
		UserInfo user = new UserInfo();
		user.setUserName(userInfoDto.getUserName());
		user.setRole("ROLE_"+userInfoDto.getRole().toUpperCase());
		user.setPass(new BCryptPasswordEncoder().encode(userInfoDto.getPassword()));
		userInfoService.saveUserInfo(user);
		return "redirect:/security/api/v3/home";
	}
	
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
