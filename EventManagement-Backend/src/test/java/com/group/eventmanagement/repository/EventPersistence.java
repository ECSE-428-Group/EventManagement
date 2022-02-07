package com.group.eventmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.Tag;
import com.group.eventmanagement.model.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
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
        Tag tag = TestData.createTag(1);
        tagRepository.save(tag);

        // User
        User user = TestData.createUser(true);
        userRepository.save(user);

        // Organizer
        User organizer = TestData.createUser(false);
        userRepository.save(organizer);

        // Event
        Event event = TestData.createEvent(true);
        eventRepository.save(event);

        Long id = event.getEventId();
        event = null;
        event = eventRepository.findByEventId(id);

        // Assertions
        assertNotNull(event);
        assertTrue(eventRepository.existsByEventId(id));
        assertEquals(1, eventRepository.findAll().size());

    }
}
