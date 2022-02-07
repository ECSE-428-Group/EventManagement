package com.group.eventmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
public class UserPersistence {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        tagRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testAndLoadUserPersistence() {

        // Tag
        Tag tag = TestData.createTag(1);
        tagRepository.save(tag);

        // User
        User user = TestData.createUser(true);
        user.getTags().add(tag);
        userRepository.save(user);

        User noVax_user = TestData.createUser(false);
        user.getTags().add(tag);
        userRepository.save(noVax_user);

        tagRepository.save(tag);

        user = null;
        user = userRepository.findUserByUsername(TestData.user1Username);

        noVax_user = null;
        noVax_user = userRepository.findUserByUsername(TestData.user2Username);

        assertNotNull(user);
        assertNotNull(noVax_user);
        assertNotNull(tag);
        assertTrue(userRepository.existsByUsername(TestData.user2Username));
        assertTrue(userRepository.existsByUsername(TestData.user1Username));
        assertEquals(2, userRepository.findAll().size());
    }




}
