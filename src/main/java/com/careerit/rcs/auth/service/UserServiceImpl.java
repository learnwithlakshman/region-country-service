package com.careerit.rcs.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.careerit.rcs.auth.domain.User;
import com.careerit.rcs.auth.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public User registerUser(User user) {
		// Validation
		log.info("User {}",user);
		user.setPassword(encoder.encode(user.getPassword()));
		user = userRepo.save(user);
		log.info("User registed with id :{}", user.getUserId());
		return user;

	}
}
