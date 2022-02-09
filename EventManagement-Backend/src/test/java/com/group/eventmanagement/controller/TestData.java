package com.group.eventmanagement.controller;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.group.eventmanagement.model.Admin;
import com.group.eventmanagement.model.User;

public class TestData {
	
	/////////////////////////// SERVER /////////////////////////////////
	
	static String SERVER_URL = "https://event-management-app-backend.herokuapp.com/";

	
	/////////////////////////// USERS /////////////////////////////////

	// USER CONSTANTS
	static String userUsername = "user2";
	static String userFirstname = "John";
	static String userLastname = "Smith";
	static String userEmail = "test@email.com";
	static String userPassword = "test1234";
	static Timestamp userBirthday = new Timestamp(System.currentTimeMillis());
	
	static LocalDate userBirthday2 = LocalDate.of(1990, 1, 8);
	static Timestamp userBirthday2Con = Timestamp.valueOf(userBirthday2.atStartOfDay());
	
	// INVALID CONSTANTS
	static String invalidUserUsername = "invalidUser";
	
	public static User createUserObject(String username, String password, String firstName, String lastName,
			Timestamp birthday, String email) {
		User user = new User();
		user.setUsername(username);
		user.setBirthday(birthday);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setEmail(email);
		
		return user;
	}
	
	
	/////////////////////////// ADMINS /////////////////////////////////
	
	// ADMIN CONSTANTS
	static String adminUsername = "admin";
	static String adminFirstname = "Main";
	static String adminLastname = "Admin";
	static String adminEmail = "admin@mail.com";
	static String adminPassword = "adminpassword";
	
	// INVALID CONSTANTS
	static String invalidAdminUsername = "invalid";
	
	public static Admin createAdminObject(String username, String password, String firstName, String lastName,
			String email) {
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setFirstName(firstName);
		admin.setLastName(lastName);
		admin.setPassword(password);
		admin.setEmail(email);
		
		return admin;
	}
}
