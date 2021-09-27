package com.careerit.rcs.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerit.rcs.auth.domain.User;
import com.careerit.rcs.auth.service.UserServiceImpl;


@RestController
@RequestMapping("/auth")
public class AuthController {

		@Autowired
		private UserServiceImpl userService;
		
		@PostMapping("/register")
		public User registerUser(@RequestBody User user) {
			return userService.registerUser(user);
		}
		
}
