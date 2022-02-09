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

import com.group.eventmanagement.model.Admin;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AdminPersistenceTest {
	
	@Autowired
	AdminRepository adminRepository;
	
	@AfterEach
	public void clearDatabase() {
		adminRepository.deleteAll();
	}
	
	@Test
	public void testAndLoadAdminPersistence() {
		// Create a valid admin
		Admin newAdmin = TestData.createAdmin(TestData.admin1Username, TestData.admin1FirstName, TestData.admin1LastName, TestData.admin1Email, TestData.admin1Password);
		
		adminRepository.save(newAdmin);
		
		newAdmin = null;
		newAdmin = adminRepository.findAdminByUsername(TestData.admin1Username);
		
		assertNotNull(newAdmin);
		assertEquals(TestData.admin1Username, newAdmin.getUsername());
	}
	
	@Test
	public void testExistsAdminByUsername() {
		// Save an admin in repository
		Admin admin = TestData.createAdmin(TestData.admin1Username, TestData.admin1FirstName, TestData.admin1LastName, TestData.admin1Email, TestData.admin1Password);
		adminRepository.save(admin);
		
		// Check if able to retrieve it back
		assertTrue(adminRepository.existsByUsername(TestData.admin1Username));
	}
	
	@Test
	public void testNonExistentAdminByUsername() {
		assertFalse(adminRepository.existsByUsername(TestData.admin1Username));
	}
	
	@Test
	public void testFindAllAdmins() {
		// Create and save multiple admins
		Admin admin1 = TestData.createAdmin(TestData.admin1Username, TestData.admin1FirstName, TestData.admin1LastName, TestData.admin1Email, TestData.admin1Password);
		Admin admin2 = TestData.createAdmin(TestData.admin2Username, TestData.admin2FirstName, TestData.admin2LastName, TestData.admin2Email, TestData.admin2Password);
		Admin admin3 = TestData.createAdmin(TestData.admin1Username, TestData.admin2FirstName, TestData.admin1LastName, TestData.admin2Email, TestData.admin1Password);
		
		adminRepository.save(admin1);
		adminRepository.save(admin2);
		adminRepository.save(admin3);
		
		// Should be two instead of three because cannot have two people
		// with the same username
		assertEquals(2, adminRepository.findAll().size());
	}

}
