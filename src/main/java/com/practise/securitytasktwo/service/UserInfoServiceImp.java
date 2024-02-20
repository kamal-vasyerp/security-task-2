package com.practise.securitytasktwo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practise.securitytasktwo.model.UserInfo;
import com.practise.securitytasktwo.repository.UserInfoRepository;

@Service
public class UserInfoServiceImp implements UserInfoService{

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Override
	public void saveUserInfo(UserInfo userInfo) {
		userInfoRepository.save(userInfo);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userInfoRepository.findByUserName(username).get();
	}

}
