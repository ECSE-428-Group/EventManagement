package com.group.eventmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.group.eventmanagement.model.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserPersistence {
	
	@Autowired
	UserRepository userRepository;
	
	@AfterEach
	public void clearDatabase() {
		userRepository.deleteAll();
	}
	
	@Test
	public void testAndLoadUserPersistence() {
		// Create valid user
		User validUser = TestData.createUser(true);
		String validUsername = validUser.getUsername();
		
		userRepository.save(validUser);
		
		validUser = null;
		validUser = userRepository.findUserByUsername(validUsername);
		
		assertNotNull(validUser);
		assertEquals(validUsername, validUser.getUsername());
	}
	
	@Test
	public void testExistsUserByUsername() {
		// Save a user in repository
		User user = TestData.createUser(false);
		String username = user.getUsername();
		userRepository.save(user);
		
		// Check if user exists in database
		assertTrue(userRepository.existsByUsername(username));
	}
	
	@Test
	public void testNonExistentUserByUsername() {
		User nonExistentUser = TestData.createUser(true);
		String nonExistentUsername = nonExistentUser.getUsername();
		
		assertFalse(userRepository.existsByUsername(nonExistentUsername));
	}
	
	@Test
	public void testFindAllUsers() {
		// Save multiple users in repository
		User user1 = TestData.createUser(false);
		User user2 = TestData.createUser(true);
		User user3 = TestData.createUser(true); // user2 and user3 have the same credentials
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		
		int count = userRepository.findAll().size();
		assertEquals(2, count);
	}
}
