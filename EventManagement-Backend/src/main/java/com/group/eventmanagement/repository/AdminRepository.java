package com.group.eventmanagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.group.eventmanagement.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, String>{
	
	Admin findAdminByUsername(String username);
	Boolean existsByUsername(String username);
	List<Admin> findAll();
	
}
