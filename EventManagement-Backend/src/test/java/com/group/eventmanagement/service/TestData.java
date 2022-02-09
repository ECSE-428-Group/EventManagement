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

}
