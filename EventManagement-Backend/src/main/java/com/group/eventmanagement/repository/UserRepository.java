package com.group.eventmanagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.group.eventmanagement.model.User;

public interface UserRepository extends CrudRepository<User, String>{

	User findUserByUsername(String username);
	Boolean existsByUsername(String username);
	List<User> findAll();

}
