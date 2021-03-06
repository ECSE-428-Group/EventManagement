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
		assertEquals("Username is invalid. Password is invalid. First name cannot be empty. "
				+ "Last name cannot be empty. Birthday is invalid. Email is invalid.", error);
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
		assertEquals("Email is invalid.", error);
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
	
	@Test
	public void testUpdateUserSuccess() {
		
		when(userRepository.save(any(User.class))).thenReturn(null);
		
		User existingUser = new User();
		
		existingUser.setUsername(TestData.userUsername);
		existingUser.setPassword(TestData.userPassword);
		existingUser.setFirstName(TestData.userFirstname);
		existingUser.setLastName(TestData.userLastname);
		existingUser.setBirthday(TestData.userBirthday);
		existingUser.setEmail(TestData.userEmail);
		
		when(userRepository.findUserByUsername(TestData.userUsername)).thenReturn(existingUser);
		when(userRepository.existsByUsername(TestData.userUsername)).thenReturn(true);
		
		try {
			existingUser = userService.updateUser(TestData.userUsername, TestData.userPassword, TestData.userPassword2, TestData.userFirstname2, TestData.userLastname2, TestData.userBirthday2, TestData.userEmail2);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			fail();
		}
		
		assertEquals(TestData.userUsername, existingUser.getUsername());
		assertEquals(TestData.userFirstname2, existingUser.getFirstName());
		assertEquals(TestData.userLastname2, existingUser.getLastName());
		assertEquals(TestData.userEmail2, existingUser.getEmail());
		assertEquals(TestData.userBirthday2, existingUser.getBirthday());
		assertEquals(TestData.userPassword2, existingUser.getPassword());
	}
	
	@Test
	public void testUpdateUserNullValues() {
		
		when(userRepository.save(any(User.class))).thenReturn(null);
		
		User existingUser = new User();
		existingUser.setUsername(TestData.userUsername);
		existingUser.setPassword(TestData.userPassword);
		existingUser.setFirstName(TestData.userFirstname);
		existingUser.setLastName(TestData.userLastname);
		existingUser.setBirthday(TestData.userBirthday);
		existingUser.setEmail(TestData.userEmail);
		
		when(userRepository.findUserByUsername(TestData.userUsername)).thenReturn(existingUser);
		when(userRepository.existsByUsername(TestData.userUsername)).thenReturn(true);
		
		try {
			existingUser = userService.updateUser(TestData.userUsername, TestData.userPassword, null, null, null, null, null);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			fail();
		}
		
		assertEquals(TestData.userUsername, existingUser.getUsername());
		assertEquals(TestData.userFirstname, existingUser.getFirstName());
		assertEquals(TestData.userLastname, existingUser.getLastName());
		assertEquals(TestData.userEmail, existingUser.getEmail());
		assertEquals(TestData.userBirthday, existingUser.getBirthday());
		assertEquals(TestData.userPassword, existingUser.getPassword());
	}
	
	@Test
	public void testUpdateUserNonExist() {
		String error = "";

		
		User existingUser = new User();
		existingUser.setUsername(TestData.userUsername);
		existingUser.setPassword(TestData.userPassword);
		existingUser.setFirstName(TestData.userFirstname);
		existingUser.setLastName(TestData.userLastname);
		existingUser.setBirthday(TestData.userBirthday);
		existingUser.setEmail(TestData.userEmail);
		
		when(userRepository.existsByUsername(TestData.userUsername2)).thenReturn(false);;
		
		try {
			existingUser = userService.updateUser(TestData.userUsername2, TestData.userPassword, null, null, null, null, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("User does not exist.", error);
	}
	
	@Test
	public void testUpdateUserWrongPassword() {
		String error = "";

		
		User existingUser = new User();
		existingUser.setUsername(TestData.userUsername);
		existingUser.setPassword(TestData.userPassword);
		existingUser.setFirstName(TestData.userFirstname);
		existingUser.setLastName(TestData.userLastname);
		existingUser.setBirthday(TestData.userBirthday);
		existingUser.setEmail(TestData.userEmail);
		
		when(userRepository.findUserByUsername(TestData.userUsername)).thenReturn(existingUser);
		when(userRepository.existsByUsername(TestData.userUsername)).thenReturn(true);;
		
		try {
			existingUser = userService.updateUser(TestData.userUsername, TestData.userPassword2, null, null, null, null, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Incorrect Password.", error);
	}
	
	@Test
	public void testUpdateUserInvalid() {
		String error = "";

		
		User existingUser = new User();
		existingUser.setUsername(TestData.userUsername);
		existingUser.setPassword(TestData.userPassword);
		existingUser.setFirstName(TestData.userFirstname);
		existingUser.setLastName(TestData.userLastname);
		existingUser.setBirthday(TestData.userBirthday);
		existingUser.setEmail(TestData.userEmail);
		
		when(userRepository.findUserByUsername(TestData.userUsername)).thenReturn(existingUser);
		when(userRepository.existsByUsername(TestData.userUsername)).thenReturn(true);
		
		try {
			existingUser = userService.updateUser(TestData.userUsername, TestData.userPassword, TestData.invalidUserPassword, null, null, TestData.invalidUserBirthday, TestData.invalidUserEmail);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Password is invalid. Birthday is invalid. Email is invalid.", error);
	}
	
}
