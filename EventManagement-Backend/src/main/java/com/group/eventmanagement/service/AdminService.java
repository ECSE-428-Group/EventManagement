package com.group.eventmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group.eventmanagement.model.Admin;
import com.group.eventmanagement.repository.AdminRepository;

@Service
public class AdminService {

	AdminRepository adminRepository;

	@Autowired
	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	///// CREATION OF ADMIN /////
	@Transactional
	public Admin createAdmin(String username, String firstName, String lastName, String email, String password) {
		String error = "";

		// Input validation
		if(adminRepository.existsByUsername(username)) {
			error += "This username already exists! ";
		}
		if(username == null || username.trim().length() <= 0) {
			error += "Username cannot be empty! ";
		}
		if(password == null || password.trim().length() <= 0) {
			error += "Password cannot be empty! ";
		}
		if(firstName == null || firstName.trim().length() <= 0) {
			error += "First name cannot be empty. ";
		}
		if(lastName == null || lastName.trim().length() <= 0) {
			error += "Last name cannot be empty. ";
		}
		if(email == null || !email.trim().matches(".+@.+")) {
			error += "Email is incorrect. ";
		}

		error = error.trim();
		if(error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		// If no errors, then create admin account
		Admin newAdmin = new Admin();
		newAdmin.setUsername(username);
		newAdmin.setFirstName(firstName);
		newAdmin.setLastName(lastName);
		newAdmin.setEmail(email);
		newAdmin.setPassword(password);

		adminRepository.save(newAdmin);

		return newAdmin;
	}
}
