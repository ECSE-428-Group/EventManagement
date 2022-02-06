package com.group.eventmanagement.repository;

import javax.transaction.Transactional;

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

    @AfterEach
    public void clearDatabase() {
        tagRepository.deleteAll();
        eventRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testAndLoadEventPersistence() {


    }
}
