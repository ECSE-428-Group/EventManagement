package com.group.eventmanagement.repository;

import java.util.List;

import com.group.eventmanagement.model.Tag;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository <Tag, String> {

    Tag findByName (String name);
    Boolean existsByName (String name);
    List<Tag> findAll ();

}
