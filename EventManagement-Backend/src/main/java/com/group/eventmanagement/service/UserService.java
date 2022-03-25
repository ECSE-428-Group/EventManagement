package com.group.eventmanagement.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;

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

		if(!validateUsername(username)) {
			error += "Username is invalid. ";
		}

		if(!validatePassword(password)) {
			error += "Password is invalid. ";
		}

		if(firstName == null || firstName.trim().length() <= 0) {
			error += "First name cannot be empty. ";
		}
		if(lastName == null || lastName.trim().length() <= 0) {
			error += "Last name cannot be empty. ";
		}
		if(birthday == null || birthday.after(new Timestamp(System.currentTimeMillis()))) {
			error += "Birthday is invalid. ";
		}
		try {
			validateEmail(email);
		} catch (AddressException e) {
			error += "Email is invalid.";
		}

		error = error.trim();
		if(error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		// Create user account if no errors
		User newUser = new User();
		newUser.setUsername(username.trim());
		newUser.setPassword(password);
		newUser.setFirstName(firstName.trim());
		newUser.setLastName(lastName.trim());
		newUser.setBirthday(birthday);
		newUser.setEmail(email);

		userRepository.save(newUser);

		return newUser;
	}

	///// UPDATE OF USER /////
	@Transactional
	public User updateUser(String username, String curPassword, String newPassword, String newFirstName, String newLastName, Timestamp newBirthday, String newEmail) {
		String error = "";

		if(!userRepository.existsByUsername(username)) {
			error += "User does not exist. ";
		}

		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		User user = userRepository.findUserByUsername(username);

		if(!curPassword.equals(user.getPassword())) {
			error += ("Incorrect Password. ");
		}

		if(newPassword == null || newPassword.trim().length() == 0) {
			newPassword = user.getPassword();
		}

		if(newFirstName == null || newFirstName.trim().length() == 0) {
			newFirstName = user.getFirstName();
		}

		if(newLastName == null || newLastName.trim().length() == 0) {
			newLastName = user.getLastName();
		}

		if(!validatePassword(newPassword)) {
			error += "Password is invalid. ";
		}

		if(newBirthday == null) {
			newBirthday = user.getBirthday();
		} else if (newBirthday.after(new Timestamp(System.currentTimeMillis()))) {
			error += "Birthday is invalid. ";
		}

		if(newEmail == null) {
			newEmail = user.getEmail();
		}

		try {
			validateEmail(newEmail);
		} catch (AddressException e) {
			error += "Email is invalid. ";
		}



		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}


		user.setPassword(newPassword);
		user.setFirstName(newFirstName.trim());
		user.setLastName(newLastName.trim());
		user.setBirthday(newBirthday);
		user.setEmail(newEmail);

		userRepository.save(user);

		return user;
	}

	private static void validateEmail(String email) throws AddressException { // validate that email address is valid format. Throws AddressException if it does not, otherwise, does nothing.
		InternetAddress address = new InternetAddress(email);
		address.validate();
	}

	private static boolean validatePassword(String pass) { // validate password. Returns true if valid, otherwise returns false.
		boolean valid = true;
		if (pass == null) {
			valid = false;
		} else if (pass.length() > 26 || pass.length() < 6) {
			valid = false;
		}
		return valid;
	}

	private static boolean validateUsername(String username) { // validate username. Returns true if valid, otherwise returns false.
		boolean valid = true;
		if (username == null) {
			valid = false;
		} else if (username.length() < 4 || username.length() > 26 || username.contains(" ")) {
			valid = false;
		}
		return valid;
	}

	@Transactional
	public Boolean checkUser(String username, String password) {
		if (userRepository.existsByUsername(username)){
			return userRepository.findUserByUsername(username).getPassword().equals(password);
		} else {
			throw new IllegalArgumentException("User does not exist");
		}
	}

	@Transactional
	public List<User> getAllUsers() {
		return toList(userRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T user : iterable) {
			resultList.add(user);
		}
		return resultList;
	}
	
	@Transactional
	public User getUser(String username) {
		return userRepository.findUserByUsername(username);
	}
}
