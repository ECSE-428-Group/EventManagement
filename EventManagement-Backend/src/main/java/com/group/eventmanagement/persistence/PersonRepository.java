package com.group.eventmanagement.persistence;

import com.group.eventmanagement.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface PersonRepository extends CrudRepository<Person, String>{

    Person findPersonByName(String name);

}