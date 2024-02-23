package com.practise.securitytasktwo.dto;

import com.practise.securitytasktwo.model.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AuthResponse {

	String token;
	UserInfo user;
}
