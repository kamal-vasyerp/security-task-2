package com.practise.securitytasktwo.service;

import com.practise.securitytasktwo.constants.RoleConstants;
import com.practise.securitytasktwo.dto.AuthRequest;
import com.practise.securitytasktwo.dto.AuthResponse;
import com.practise.securitytasktwo.dto.RegisterRequest;
import com.practise.securitytasktwo.model.UserInfo;
import com.practise.securitytasktwo.repository.UserInfoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.practise.securitytasktwo.config.JwtService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserInfoRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtservice;
	private final AuthenticationManager authManager;

	@Override
	public String register(RegisterRequest request) {
		var user = UserInfo.builder().userName(request.getUserName()).pass(passwordEncoder.encode(request.getPass()))
				.role("ROLE_"+RoleConstants.USER).build();
		userRepository.save(user);

		String message = "User created";
		return message;
	}

	@Override
	public AuthResponse authentication(AuthRequest request) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPass()));
		UserInfo user = userRepository.findByUserName(request.getUserName())
				.orElseThrow(() -> new EntityNotFoundException("User is Not Present in the database."));
		String jwtToken = jwtservice.generateToken(user);
		return new AuthResponse(jwtToken,user);
	}

}
