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
        String username = "saba";
        String firstname = "Saba";
        String lastname = "Fathi";
        String email = "test@email.com";
        String password = "test1234";
        Timestamp birthday = new Timestamp(1644162880059L);
        List<Tag> tagList = new ArrayList<Tag>();
        List<Post> postList = new ArrayList<Post>();
        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setBirthday(birthday);
        user.setVaccinationStatus(true);
        user.setTags(tagList);
        user.setPosts(postList);

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
        List<Post> postListEvent = new ArrayList<Post>();
        event1.setPosts(postListEvent);

        event1.setDate(time1);
        event1.setIsPrivate(private1);
        event1.setDescription(description1);
        event1.setIsVirtual(virtual1);
        event1.setLocation(location1);
        event1.setImage(image1);
        event1.setOrganizers(organizers1);
        event1.setAttendees(attendees1);

        eventRepository.save(event1);

        // Post
        Post post = new Post();
        String title = "Post Title";
        String description = "Post Description";

        post.setUser(user);
        post.setDescription(description);
        post.setTitle(title);
        post.setEvent(event1);

        userRepository.save(user);
        userRepository.save(organizer);
        eventRepository.save(event1);

        assertEquals(1, userRepository.findAll().size());

        postRepository.save(post);

        Long postId = post.getPostId();

        post = null;
        post = postRepository.findByPostId(postId);

        // Assertions
        assertNotNull(post);
        assertEquals(1, postRepository.findAll().size());
        assertTrue(postRepository.existsById(postId));
        assertNotNull(event1);
    }
}
