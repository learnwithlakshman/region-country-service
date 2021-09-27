package com.careerit.rcs.auth.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class User {
	
	@Id
	private String userId;
	private String username;
	private String password;
	private String email;

}
