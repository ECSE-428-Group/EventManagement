package com.group.eventmanagement.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.group.eventmanagement.model.Admin;
import com.group.eventmanagement.service.AdminService;

@WebMvcTest(AdminController.class)
public class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AdminService adminService;
	
	@Test
	public void testAdminCreation() {
		Admin mockAdmin = TestData.createAdminObject(TestData.userUsername, TestData.userPassword, TestData.userFirstname, TestData.userLastname, TestData.userEmail);
		when(adminService.createAdmin(TestData.adminUsername, TestData.adminFirstname, TestData.adminLastname, TestData.adminEmail, TestData.adminPassword))
		.thenReturn(mockAdmin);
		
		try {
			this.mockMvc.perform(post("/admin/"+TestData.adminUsername)
					.param("firstName", TestData.adminFirstname)
					.param("lastName", TestData.adminLastname)
					.param("password", TestData.adminPassword)
					.param("email", TestData.adminEmail)
					).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void testInvalidAdminCreation() {
		when(adminService.createAdmin(TestData.invalidAdminUsername, TestData.adminFirstname, TestData.adminLastname, TestData.adminEmail, TestData.adminPassword))
		.thenThrow(IllegalArgumentException.class);
		
		try {
			this.mockMvc.perform(post("/admin/"+TestData.invalidAdminUsername)
					.param("firstName", TestData.adminFirstname)
					.param("lastName", TestData.adminLastname)
					.param("password", TestData.adminPassword)
					.param("email", TestData.adminEmail)
					).andExpect(status().isInternalServerError());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
}
