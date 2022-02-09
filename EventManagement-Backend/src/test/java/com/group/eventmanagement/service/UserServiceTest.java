package com.group.eventmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.group.eventmanagement.model.User;
import com.group.eventmanagement.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	@Test
	public void testCreateValidUser() {
		// Whenever a user is saved, return nothing
		when(userRepository.save(any(User.class))).thenReturn(null);
		
		User newlyCreatedUser = null;
		try {
			newlyCreatedUser = 
					userService.createUser(TestData.userUsername, TestData.userPassword, TestData.userFirstname, TestData.userLastname, TestData.userBirthday, TestData.userEmail);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(newlyCreatedUser);
		assertEquals(TestData.userUsername, newlyCreatedUser.getUsername());
		assertEquals(TestData.userFirstname, newlyCreatedUser.getFirstName());
		assertEquals(TestData.userLastname, newlyCreatedUser.getLastName());
		assertEquals(TestData.userEmail, newlyCreatedUser.getEmail());
		assertEquals(TestData.userBirthday, newlyCreatedUser.getBirthday());
		assertEquals(TestData.userPassword, newlyCreatedUser.getPassword());
	}
	
	@Test
	public void testCreateInvalidUser() {
		User invalidUser = null;
		String error = "";
		
		try {
			invalidUser = userService.createUser(" ", "", "", "", null, "");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(invalidUser);
		assertEquals("Username cannot be empty! Password cannot be empty! First name cannot be empty. "
				+ "Last name cannot be empty. Date of birth is incorrect. Email is incorrect.", error);
	}
	
	@Test
	public void testCreateUserWithIncorrectEmailFormat() {
		User user = null;
		String error = "";
		
		try {
			user = userService.createUser(TestData.userUsername, TestData.userPassword, TestData.userFirstname, TestData.userLastname, TestData.userBirthday, TestData.invalidUserEmail);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(user);
		assertEquals("Email is incorrect.", error);
	}
	
	@Test
	public void testCreateDuplicateUsernameUser() {
		when(userRepository.existsByUsername(TestData.existentUsername)).thenReturn(true);
		User user = null;
		String error = "";
		
		try {
			user = userService.createUser(TestData.existentUsername, TestData.userPassword, TestData.userFirstname, TestData.userLastname, TestData.userBirthday, TestData.userEmail);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(user);
		assertEquals("This username already exists!", error);
	}
}
