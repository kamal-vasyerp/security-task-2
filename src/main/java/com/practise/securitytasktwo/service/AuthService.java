package com.practise.securitytasktwo.service;

import com.practise.securitytasktwo.dto.AuthRequest;
import com.practise.securitytasktwo.dto.AuthResponse;
import com.practise.securitytasktwo.dto.RegisterRequest;

public interface AuthService {

	public String register(RegisterRequest request);
	public AuthResponse authentication(AuthRequest request);
}
