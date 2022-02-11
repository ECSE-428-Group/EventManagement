package com.group.eventmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.Post;
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
public class PostPersistenceTest {

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
        User user = new User();
        TestData.setUser(user, 2);
        userRepository.save(user);

        // Organizer
        User organizer = new User();
        TestData.setUser(organizer, 3);
        userRepository.save(organizer);

        // Event 1
        Event event = new Event();
        TestData.setEvent(event, false);
        eventRepository.save(event);

        // Post
        Post post = new Post();
        TestData.setPost(post, event, user, 1);

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
        for (Post posting: postRepository.findAll()){
            System.out.println(posting.getDescription());
        }
        assertEquals(1, postRepository.findAll().size());
        assertTrue(postRepository.existsById(postId));
    }
}
