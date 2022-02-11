package com.group.eventmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import com.group.eventmanagement.model.Comment;
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
public class CommentPersistenceTest {

    @Autowired
    private UserRepository userRepository;

	@Autowired
	private CommentRepository commentRepository;

    @Autowired
	private PostRepository postRepository;

    @Autowired
    private EventRepository eventRepository;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        commentRepository.deleteAll();
        postRepository.deleteAll();
        eventRepository.deleteAll();
        userRepository.deleteAll();

    }

    @Test
    @Transactional
    public void testAndLoadCommentPersistence() {

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
        TestData.setPost(post, event, user, 2);

        // Comment
        Comment comment = new Comment();
        TestData.setComment(comment, post, user, 1);

        userRepository.save(user);
        userRepository.save(organizer);
        eventRepository.save(event);

        assertEquals(2, userRepository.findAll().size());

        postRepository.save(post);

        commentRepository.save(comment);

        Long commentId = comment.getId();

        comment = null;
        comment = commentRepository.findCommentById(commentId);

        assertNotNull(comment);
        assertEquals(1, commentRepository.findAll().size());
        assertEquals(user, comment.getCommenter());

        commentRepository.delete(commentRepository.findCommentById(commentId));
        assertEquals(0, commentRepository.findAll().size());

    }
}
