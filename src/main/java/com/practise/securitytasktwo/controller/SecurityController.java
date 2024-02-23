package com.practise.securitytasktwo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.practise.securitytasktwo.config.JwtService;
import com.practise.securitytasktwo.constants.RoleConstants;
import com.practise.securitytasktwo.dto.AuthResponse;
import com.practise.securitytasktwo.model.UserInfo;
import com.practise.securitytasktwo.service.UserInfoService;

@RestController
@RequestMapping("/security/api/v3")
public class SecurityController {

	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserInfoService userService;

	@GetMapping("/signup-page")
	public ModelAndView signupPage() {
		ModelAndView mnv = new ModelAndView("signup");
		return mnv;
	}

	@GetMapping("/login-page")
	public ModelAndView loginPage() {
		ModelAndView mnv = new ModelAndView("mylogin");
		return mnv;
	}

	@GetMapping("/home")
	public ModelAndView homePage() {
		ModelAndView mnv = new ModelAndView("home");
		return mnv;
	}

	@PostMapping("/admin")
	public ModelAndView adminPage(String token, String user) {
		UserInfo currentUser = (UserInfo) userService.loadUserByUsername(user);
		if (!jwtService.isTokenValid(token, currentUser)) {
			throw new RuntimeException("your token is invalid");
		}
		if (!currentUser.getRole().equals("ROLE_" + RoleConstants.ADMIN)) {
			throw new RuntimeException("Access Denied");
		}
		ModelAndView mnv = new ModelAndView("admin");
		return mnv;
	}

	@PostMapping("/user")
	public ModelAndView userPage(String token, String user) {
		UserInfo currentUser = (UserInfo) userService.loadUserByUsername(user);
		if (!jwtService.isTokenValid(token, currentUser)) {
			throw new RuntimeException("your token is invalid");
		}
		if (currentUser.getRole().equals("ROLE_" + RoleConstants.USER)
				|| currentUser.getRole().equals("ROLE_" + RoleConstants.ADMIN)) {
			ModelAndView mnv = new ModelAndView("user");
			return mnv;

		} else {
			throw new RuntimeException("Access Denied");
		}

	}

	@GetMapping("/visitor")
	public ModelAndView visitorPage() {
		ModelAndView mnv = new ModelAndView("visitor");
		return mnv;
	}

}
