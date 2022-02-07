package com.group.eventmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.Post;
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
public class PostPersistence {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        postRepository.deleteAll();
        eventRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @Transactional
    public void testAndLoadPostPersistence() {

        // User
        User user = TestData.createUser(true);
        userRepository.save(user);

        // Organizer
        User organizer = TestData.createUser(false);
        userRepository.save(organizer);

        // Event 1
        Event event = TestData.createEvent(false);
        eventRepository.save(event);

        // Post
        Post post = TestData.createPost(event, user, 1);

        userRepository.save(user);
        userRepository.save(organizer);
        eventRepository.save(event);

        assertEquals(2, userRepository.findAll().size());

        postRepository.save(post);

        Long postId = post.getPostId();

        post = null;
        post = postRepository.findByPostId(postId);

        // Assertions
        assertNotNull(post);
        assertEquals(1, postRepository.findAll().size());
        assertTrue(postRepository.existsById(postId));
    }
}
