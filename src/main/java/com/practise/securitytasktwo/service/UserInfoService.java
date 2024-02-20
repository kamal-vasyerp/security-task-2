package com.practise.securitytasktwo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.practise.securitytasktwo.model.UserInfo;

public interface UserInfoService extends UserDetailsService{

	public void saveUserInfo(UserInfo userInfo);
}
