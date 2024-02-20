package com.practise.securitytasktwo.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.practise.securitytasktwo.constants.RoleConstants;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_"+RoleConstants.ADMIN))) {
			response.sendRedirect("/security/api/v3/admin");
		}else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_"+RoleConstants.USER))) {
			response.sendRedirect("/security/api/v3/user");
		}else{
			response.sendRedirect("/security/api/v3/visitor");
		}
	}

}
