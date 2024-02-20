package com.practise.securitytasktwo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practise.securitytasktwo.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{

	Optional<UserInfo> findByUserName(String userName);
}
