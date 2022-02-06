package com.group.eventmanagement.persistence;

import com.group.eventmanagement.modelOld.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface PersonRepository extends CrudRepository<Person, String>{

    Person findPersonByName(String name);

}