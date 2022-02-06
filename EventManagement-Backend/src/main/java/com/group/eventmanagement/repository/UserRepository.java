package com.group.eventmanagement.repository;

import java.util.List;

import com.group.eventmanagement.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, String> {

    User findUserByUsername (String username);
    Boolean existsByUsername (String username);
    List<User> findAll ();

}
