package com.group.eventmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.Post;
import com.group.eventmanagement.model.User;
import com.group.eventmanagement.repository.EventRepository;
import com.group.eventmanagement.repository.PostRepository;
import com.group.eventmanagement.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @Mock
	private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private EventRepository eventRepository;

	@InjectMocks
	private PostService postService;
    @InjectMocks
    private UserService userService;
    @InjectMocks
    private EventService eventService;

    @Test
	public void testCreateValidPost() {
        when(postRepository.save(any(Post.class))).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(null);
        when(eventRepository.save(any(Event.class))).thenReturn(null);
        Post newPost = null;
        try {
            User user = userService.createUser(TestData.userUsername, TestData.userPassword, TestData.userFirstname, TestData.userLastname, TestData.userBirthday, TestData.userEmail);
            TestData.eventOrganizers.add(user);
            Event event = eventService.createEvent(TestData.eventID, TestData.eventDate, false, false, TestData.eventLocation, TestData.eventDescription, TestData.eventImage);
            newPost = postService.createPost(event, TestData.postTitle, TestData.postDescription, user);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            fail();
        }
        assertNotNull(newPost);
        assertEquals("Shopping in Akihabara", newPost.getTitle());
        assertEquals("Yo", newPost.getDescription());
    }

    @Test
	public void testCreateInvalidPost() {
        Post invalidPost = null;
        String error = "";

		try {
			invalidPost = postService.createPost(null, "", "", null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

        assertNull(invalidPost);
        assertEquals("Post needs to be associated with an event! Post needs to be associated with a user! Post needs to have a title! Post needs to have a description! ", error);
    }


}
