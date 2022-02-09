/*package com.group.eventmanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.jupiter.api.Test;

public class AdminControllerTest {

	@Test
	public void testUserCreation() {
		try {
			URL url = new URL(TestData.SERVER_URL + "/" + TestData.adminUsername);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("firstName", TestData.adminFirstname);
			connection.setRequestProperty("lastName", TestData.adminLastname);
			connection.setRequestProperty("email", TestData.adminEmail);
			connection.setRequestProperty("password", TestData.adminPassword);
			connection.setRequestMethod("POST");
			assertEquals(200, connection.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
*/