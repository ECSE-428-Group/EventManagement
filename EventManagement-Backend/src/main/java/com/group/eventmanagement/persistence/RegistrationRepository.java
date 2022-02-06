package com.group.eventmanagement.persistence;

import java.util.List;

import com.group.eventmanagement.modelOld.Event;
import com.group.eventmanagement.modelOld.Person;
import com.group.eventmanagement.modelOld.Registration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RegistrationRepository extends CrudRepository<Registration, Integer> {

    List<Registration> findByPerson(Person personName);

    boolean existsByPersonAndEvent(Person person, Event eventName);

    Registration findByPersonAndEvent(Person person, Event eventName);

}