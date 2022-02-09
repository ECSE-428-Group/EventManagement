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

import com.group.eventmanagement.model.Admin;
import com.group.eventmanagement.repository.AdminRepository;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

	@Mock
	private AdminRepository adminRepository;

	@InjectMocks
	private AdminService adminService;

	@Test
	public void createValidAdmin() {
		// Whenever a user is saved, return nothing
		when(adminRepository.save(any(Admin.class))).thenReturn(null);

		Admin newlyCreatedAdmin = null;
		try {
			newlyCreatedAdmin = 
					adminService.createAdmin(TestData.adminUsername, TestData.adminFirstName, TestData.adminLastName, TestData.adminEmail, TestData.adminPassword);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(newlyCreatedAdmin);
		assertEquals(TestData.adminUsername, newlyCreatedAdmin.getUsername());
		assertEquals(TestData.adminFirstName, newlyCreatedAdmin.getFirstName());
		assertEquals(TestData.adminLastName, newlyCreatedAdmin.getLastName());
		assertEquals(TestData.adminEmail, newlyCreatedAdmin.getEmail());
		assertEquals(TestData.adminPassword, newlyCreatedAdmin.getPassword());
	}

	@Test
	public void createCreateInvalidAdmin() {
		Admin invalidAdmin = null;
		String error = "";

		try {
			invalidAdmin = adminService.createAdmin(" ", "", TestData.adminLastName, "", "");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(invalidAdmin);
		assertEquals("Username cannot be empty! Password cannot be empty! First name cannot be empty. "
				+ "Email is incorrect.", error);
	}

	@Test
	public void testCreateAdminWithDuplicateUsername() {
		// Existent username is a username that already exists in database
		when(adminRepository.existsByUsername(TestData.existentUsername)).thenReturn(true);
		Admin admin = null;
		String error = "";

		try {
			admin = adminService.createAdmin(TestData.existentUsername, TestData.adminFirstName, TestData.adminLastName, TestData.adminEmail, TestData.adminPassword);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(admin);
		assertEquals("This username already exists!", error);
	}

	@Test
	public void testCreateAdminWithInvalidEmailFormat() {
		Admin admin = null;
		String error = "";

		try {
			admin = adminService.createAdmin(TestData.adminUsername, TestData.adminFirstName, TestData.adminLastName, TestData.invalidAdminEmail, TestData.adminPassword);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(admin);
		assertEquals("Email is incorrect.", error);
	}

}
