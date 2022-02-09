/*package com.group.eventmanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.jupiter.api.Test;

public class UserControllerTest {
	
	@Test
	public void testUserCreation() {
		try {
			URL url = new URL(TestData.SERVER_URL + TestData.userUsername);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("firstName", TestData.userUsername);
			connection.setRequestProperty("lastName", TestData.userLastname);
			connection.setRequestProperty("birthday", TestData.userBirthday.toString());
			connection.setRequestProperty("email", TestData.userEmail);
			connection.setRequestProperty("password", TestData.userPassword);
			connection.setRequestMethod("POST");
			assertEquals(200, connection.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
} 
*/