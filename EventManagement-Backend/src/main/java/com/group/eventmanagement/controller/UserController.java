package com.group.eventmanagement.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.eventmanagement.model.User;
import com.group.eventmanagement.service.UserService;


@CrossOrigin(origins = "*")
@RestController
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	/////////// CREATE USER ACCOUNT ////////////////
	@PostMapping(value = {
			"/userprofile/{username}",
			"/userprofile/{username}/"
			})
	public User createUser(
			@PathVariable("username") String username,
			@RequestParam("password") String password,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("birthday") Timestamp birthday,
			@RequestParam("email") String email
			) throws IllegalArgumentException {
		User newUser = userService.createUser(username, password, 
				firstName, lastName, birthday, email);
		return newUser;
	}
}
