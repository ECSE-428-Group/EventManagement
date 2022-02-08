package com.group.eventmanagement.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.group.eventmanagement.model.Tag;
import com.group.eventmanagement.model.User;

public class TestData {

	/////////////////////////// USERS /////////////////////////////////

	// USER 1
	static String user1Username = "user1";
	static String user1Firstname = "Saba";
	static String user1Lastname = "Fathi";
	static String user1Email = "saba@email.com";
	static String user1Password = "test1234";
	static Timestamp user1Birthday = new Timestamp(System.currentTimeMillis());
	static List<Tag> user1Tags = new ArrayList<Tag>();

	// USER 2
	static String user2Username = "user2";
	static String user2Firstname = "John";
	static String user2Lastname = "Smith";
	static String user2Email = "test@email.com";
	static String user2Password = "test1234";
	static Timestamp user2Birthday = new Timestamp(System.currentTimeMillis());
	static List<Tag> user2Tags = new ArrayList<Tag>();
	
	public static User createUser(boolean vaxStatus) {

		if (vaxStatus) { // vax user
			User user = new User();

			user.setUsername(user1Username);
			user.setPassword(user1Password);
			user.setFirstName(user1Firstname);
			user.setLastName(user1Lastname);
			user.setEmail(user1Email);
			user.setBirthday(user1Birthday);
			user.setVaccinationStatus(true);
			user.setTags(user1Tags);

			return user;
		} else { // No vax user
			User user = new User();

			user.setUsername(user2Username);
			user.setPassword(user2Password);
			user.setFirstName(user2Firstname);
			user.setLastName(user2Lastname);
			user.setEmail(user2Email);
			user.setBirthday(user2Birthday);
			user.setVaccinationStatus(false);
			user.setTags(user2Tags);

			return user;
		}
	}

}
