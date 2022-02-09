package com.group.eventmanagement.controller;

import java.sql.Timestamp;

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
	
	
	/////////////////////////// ADMINS /////////////////////////////////
	
	// ADMIN CONSTANTS
	static String adminUsername = "admin";
	static String adminFirstname = "Main";
	static String adminLastname = "Admin";
	static String adminEmail = "admin@mail.com";
	static String adminPassword = "adminpassword";
}
