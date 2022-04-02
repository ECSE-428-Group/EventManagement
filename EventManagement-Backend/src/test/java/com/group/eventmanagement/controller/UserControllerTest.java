package com.group.eventmanagement.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.group.eventmanagement.model.User;
import com.group.eventmanagement.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void testUserCreation() {
		User mockUser = TestData.createUserObject(TestData.userUsername, TestData.userPassword, TestData.userFirstname, TestData.userLastname, TestData.userBirthday, TestData.userEmail);
		when(userService.createUser(TestData.userUsername, TestData.userPassword, TestData.userFirstname, TestData.userLastname, TestData.userBirthday, TestData.userEmail))
		.thenReturn(mockUser);
		
		try {
			this.mockMvc.perform(post("/userprofile/"+TestData.userUsername)
					.param("firstName", TestData.userFirstname)
					.param("lastName", TestData.userLastname)
					.param("password", TestData.userPassword)
					.param("birthday", TestData.userBirthday3)
					.param("email", TestData.userEmail)
					).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInvalidUserCreation() {
		when(userService.createUser(TestData.invalidUserUsername, TestData.userPassword, TestData.userFirstname, TestData.userLastname, TestData.userBirthday2Con, TestData.userEmail))
		.thenThrow(IllegalArgumentException.class);
		
		try {
			this.mockMvc.perform(post("/userprofile/"+TestData.invalidUserUsername)
					.param("firstName", TestData.userFirstname)
					.param("lastName", TestData.userLastname)
					.param("password", TestData.userPassword)
					.param("birthday", TestData.userBirthday2.toString())
					.param("email", TestData.userEmail)
					).andExpect(status().isInternalServerError());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUserUpdate() {
		User mockUser = TestData.createUserObject(TestData.userUsername, TestData.userPassword, TestData.userFirstname, TestData.userLastname, TestData.userBirthday2Con, TestData.userEmail);
		when(userService.updateUser(TestData.userUsername, TestData.userPassword, TestData.userPassword, TestData.userFirstname, TestData.userLastname, TestData.userBirthday2Con, TestData.userEmail))
		.thenReturn(mockUser);
		
		try {
			this.mockMvc.perform(put("/userprofile/"+TestData.userUsername)
					.param("curPassword", TestData.userPassword)
					.param("newPassword", TestData.userPassword)	
					.param("firstName", TestData.userFirstname)
					.param("lastName", TestData.userLastname)
					.param("birthday", TestData.userBirthday2.toString())
					.param("email", TestData.userEmail)
					).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUserUpdateInvalid() {
		when(userService.updateUser(TestData.userUsername, TestData.userPassword, TestData.invalidUserPassword, TestData.userFirstname, TestData.userLastname, TestData.userBirthday2Con, TestData.userEmail))
		.thenThrow(IllegalArgumentException.class);
		
		try {
			this.mockMvc.perform(put("/userprofile/"+TestData.userUsername)
					.param("curPassword", TestData.userPassword)
					.param("newPassword", TestData.invalidUserPassword)	
					.param("firstName", TestData.userFirstname)
					.param("lastName", TestData.userLastname)
					.param("birthday", TestData.userBirthday2.toString())
					.param("email", TestData.userEmail)
					).andExpect(status().isInternalServerError());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
} 
