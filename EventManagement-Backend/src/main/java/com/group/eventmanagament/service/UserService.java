package com.group.eventmanagament.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group.eventmanagement.model.User;
import com.group.eventmanagement.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	///// CREATION OF USER ////////
	@Transactional
	public User createUser(String username, String password, 
			String firstName, String lastName, Timestamp birthday, String email) {
		String error = "";
		
		// Input validation
		if(username == null || username.trim().length() <= 0) {
			error += "Username cannot be empty! ";
		}
		if(password == null || password.length() <= 0) {
			error += "Password cannot be empty! ";
		}
		if(firstName == null || firstName.trim().length() <= 0) {
			error += "First name cannot be empty. ";
		}
		if(lastName == null || lastName.trim().length() <= 0) {
			error += "Last name cannot be empty. ";
		}
		if(birthday == null || birthday.after(new Timestamp(System.currentTimeMillis()))) {
			error += "Date of birth is incorrect. ";
		}
		if(email == null || !email.matches(".+@.+")) {
			error += "Email is incorrect. ";
		}
		
		error = error.trim();
		if(error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		
		// Create user account if no errors
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setBirthday(birthday);
		newUser.setEmail(email);
		
		userRepository.save(newUser);
		
		return newUser;
	}
}
