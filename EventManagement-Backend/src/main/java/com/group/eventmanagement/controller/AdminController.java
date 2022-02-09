package com.group.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.eventmanagement.model.Admin;
import com.group.eventmanagement.service.AdminService;

@CrossOrigin(origins = "*")
@RestController
public class AdminController {
	
	private AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	/////////// CREATE ADMIN ACCOUNT ///////////
	
	@PostMapping(value = {
			"/admin/{username}",
			"/admin/{username}/"
	})
	public Admin createAdmin(
			@PathVariable("username") String username,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName,
			@RequestParam(name = "email") String email) throws IllegalArgumentException {
		Admin newAdmin = adminService.createAdmin(username, firstName, lastName, email, password);
		return newAdmin;
	}

}
