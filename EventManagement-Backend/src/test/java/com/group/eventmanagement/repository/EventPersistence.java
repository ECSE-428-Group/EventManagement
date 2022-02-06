package com.group.eventmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.Tag;
import com.group.eventmanagement.model.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EventPersistence {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void clearDatabase() {
        tagRepository.deleteAll();
        userRepository.deleteAll();
        eventRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testAndLoadEventPersistence() {

        // Tag
        String name = "TestTag";
        String description = "TestDescription";
        Tag tag = new Tag();

        tag.setDescription(description);
        tag.setName(name);

        tagRepository.save(tag);

        // User
        String username = "saba";
        String firstname = "Saba";
        String lastname = "Fathi";
        String email = "test@email.com";
        String password = "test1234";
        Timestamp birthday = new Timestamp(1644162880059L);
        List<Tag> tagList = new ArrayList<Tag>();
        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setBirthday(birthday);
        user.setVaccinationStatus(true);
        tagList.add(tag);
        user.setTags(tagList);

        userRepository.save(user);

        // Organizer
        String usernameOrganizer = "saba";
        String firstnameOrganizer = "Saba";
        String lastnameOrganizer = "Fathi";
        String emailOrganizer = "test@email.com";
        String passwordOrganizer = "test1234";
        Timestamp birthdayOrganizer = new Timestamp(1644162880059L);
        List<Tag> tagListOrganizer = new ArrayList<Tag>();
        User organizer = new User();

        organizer.setUsername(usernameOrganizer);
        organizer.setPassword(passwordOrganizer);
        organizer.setFirstName(firstnameOrganizer);
        organizer.setLastName(lastnameOrganizer);
        organizer.setEmail(emailOrganizer);
        organizer.setBirthday(birthdayOrganizer);
        organizer.setVaccinationStatus(true);
        tagListOrganizer.add(tag);
        organizer.setTags(tagListOrganizer);

        userRepository.save(organizer);

        // Event 1
        Event event1 = new Event();
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        boolean private1 = true;
        boolean virtual1 = false;
        String location1 = "Test Location";
        String description1 = "Test Description";
        String image1 = "image1";
        List<User> attendees1 = new ArrayList<User>();
        attendees1.add(user);
        List<User> organizers1 = new ArrayList<User>();
        organizers1.add(organizer);

        event1.setDate(time1);
        event1.setIsPrivate(private1);
        event1.setDescription(description1);
        event1.setIsVirtual(virtual1);
        event1.setLocation(location1);
        event1.setImage(image1);
        event1.setOrganizers(organizers1);
        event1.setAttendees(attendees1);

        // Event 2
        Event event2 = new Event();
        Timestamp time2 = new Timestamp(System.currentTimeMillis());
        boolean private2 = true;
        boolean virtual2 = false;
        String location2 = "Test Location";
        String description2 = "Test Description";
        String image2 = "image2";
        List<User> attendees2 = new ArrayList<User>();
        attendees2.add(organizer);
        List<User> organizers2 = new ArrayList<User>();
        organizers2.add(user);

        event2.setDate(time2);
        event2.setIsPrivate(private2);
        event2.setDescription(description2);
        event2.setIsVirtual(virtual2);
        event2.setLocation(location2);
        event2.setImage(image2);
        event2.setOrganizers(organizers2);
        event2.setAttendees(attendees2);

        eventRepository.save(event1);
        eventRepository.save(event2);

        Long id1 = event1.getEventId();
        Long id2 = event2.getEventId();

        event1 = null;
        event2 = null;

        event1 = eventRepository.findByEventId(id1);
        event2 = eventRepository.findByEventId(id2);

        // Assertions
        assertNotNull(event1);
        assertNotNull(event2);
        assertTrue(eventRepository.existsByEventId(id1));
        assertTrue(eventRepository.existsByEventId(id2));
        assertEquals(2, eventRepository.findAll().size());

    }
}