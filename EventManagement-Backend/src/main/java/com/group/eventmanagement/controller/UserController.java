package com.group.eventmanagement.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	/////////// CREATE USER ACCOUNT ///////////
	@PostMapping(value = {
			"/userprofile/{username}",
			"/userprofile/{username}/"
			})
	public User createUser(
			@PathVariable("username") String username,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName,
			@RequestParam(name = "birthday") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthday,
			@RequestParam(name = "email") String email
			) throws IllegalArgumentException {
		Timestamp convertedBirthday = Timestamp.valueOf(birthday.atStartOfDay());
		User newUser = userService.createUser(username, password,
				firstName, lastName, convertedBirthday, email);
		return newUser;
	}

	/////////// UPDATE USER ACCOUNT ///////////
	@PutMapping(value = {
			"/userprofile/{username}",
			"/userprofile/{username}/"
			})
	public User updateUser(
			@PathVariable("username") String username,
			@RequestParam(name = "curPassword") String curPassword,
			@RequestParam(name = "newPassword") String newPassword,
			@RequestParam(name = "firstName") String newFirstName,
			@RequestParam(name = "lastName") String newLastName,
			@RequestParam(name = "birthday") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newBirthday,
			@RequestParam(name = "email") String newEmail
			) throws IllegalArgumentException {
		Timestamp convertedBirthday = Timestamp.valueOf(newBirthday.atStartOfDay());
		User updatedUser = userService.updateUser(username,curPassword,newPassword,
				newFirstName, newLastName, convertedBirthday, newEmail);
		return updatedUser;
	}

	@GetMapping(value = {
		"/users/",
		"/users",
		})
	public List<User> getAllUsers() {

		return userService.getAllUsers();
	}

	@GetMapping(value = {
		"/users/getUser/{username}/",
		"/users/getUser/{username}",
		})
	public User getUser(@PathVariable("username") String username) {

		return userService.getUser(username);
	}
}
