package com.practise.securitytasktwo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.practise.securitytasktwo.dto.AuthRequest;
import com.practise.securitytasktwo.dto.AuthResponse;
import com.practise.securitytasktwo.dto.RegisterRequest;
import com.practise.securitytasktwo.model.UserInfo;
import com.practise.securitytasktwo.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/security/api/v3/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthService authService;

	@PostMapping("/register")
	public ModelAndView register(RegisterRequest request) {
		authService.register(request);
		return new ModelAndView("visitor");
	}

	@PostMapping("/authentication")
	public ModelAndView authentication(AuthRequest request) {
		AuthResponse authResponse = authService.authentication(request);
		String token = authResponse.getToken();
		String user = authResponse.getUser().getUsername();
		ModelAndView mnv = new ModelAndView("home");
		mnv.addObject("token", token);
		mnv.addObject("user", user);
		return mnv;
	}

}
