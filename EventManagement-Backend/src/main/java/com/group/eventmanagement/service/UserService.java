package com.group.eventmanagement.service;

import java.sql.Timestamp;
import java.util.Optional;

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

	///// CREATION OF USER /////
	@Transactional
	public User createUser(String username, String password, String firstName, String lastName, Timestamp birthday, String email) {
		String error = "";

		// Input validation
		if(userRepository.existsByUsername(username)) {
			error += "This username already exists! ";
		}
		if(username == null || username.trim().length() <= 0) {
			error += "Username cannot be empty! ";
		}
		if(password == null || password.trim().length() <= 0) {
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
		if(email == null || email.trim().length() <= 0 || !email.matches(".+@.+")) {
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

	public User getUserByUsername(String username){
		if(username == null || username.isEmpty()){
			throw new IllegalArgumentException("Username can't be empty");
		}else if (!userRepository.existsByUsername(username)){
			throw new IllegalArgumentException("No user was found with this username");
		}
		User user = userRepository.findUserByUsername(username);
		return user;
	}

}
