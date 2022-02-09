package com.group.eventmanagement.service;

import java.sql.Timestamp;


public class TestData {

	/////////////////////////// USERS /////////////////////////////////

	// USER CONSTANTS
	static String userUsername = "user2";
	static String userFirstname = "John";
	static String userLastname = "Smith";
	static String userEmail = "test@email.com";
	static String userPassword = "test1234";
	static Timestamp userBirthday = new Timestamp(System.currentTimeMillis());

	// INVALID CONSTANTS
	static String invalidUserEmail = "something@";

	// EXISTENT USERNAME
	static String existentUsername = "Mia123";

	
	/////////////////////////// ADMINS /////////////////////////////////
	
	// ADMIN CONSTANTS
	static String adminUsername = "admin";
	static String adminEmail = "admin@mail.com";
	static String adminFirstName = "Admin";
	static String adminLastName = "Test";
	static String adminPassword = "adminpassword123*";
	
	// INVALID CONSTANTS
	static String invalidAdminEmail = "@yahoo.com";
	

}
