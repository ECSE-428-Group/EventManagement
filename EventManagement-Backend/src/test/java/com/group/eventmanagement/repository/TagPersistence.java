package com.group.eventmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import javax.transaction.Transactional;

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
public class TagPersistence {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        userRepository.deleteAll();
        tagRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testAndLoadTagPersistence() {

        // Tag 1
        Tag tag = new Tag();
        TestData.setTag(tag, 1);
        tagRepository.save(tag);

        // Tag 2
        Tag tag2 = new Tag();
        TestData.setTag(tag2, 2);
        tagRepository.save(tag2);

        // User
        User user = new User();
        TestData.setUser(user, 1);
        userRepository.save(user);
        System.out.println(user.getTags().size());

        // Add tags
        user.getTags().add(tag);
        user.getTags().add(tag2);
        System.out.println(user.getTags().size());

        userRepository.save(user);

        tag = null;
        tag2 = null;
        user = null;

        user = userRepository.findUserByUsername(TestData.user1Username);
        tag = tagRepository.findByName(TestData.tag1Name);
        tag2 = tagRepository.findByName(TestData.tag2Name);

        // Assertions
        assertNotNull(tag);
        assertNotNull(tag2);
        assertEquals(2, user.getTags().size());

    }

}
