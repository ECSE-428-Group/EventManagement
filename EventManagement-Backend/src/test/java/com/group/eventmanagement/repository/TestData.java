package com.group.eventmanagement.repository;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.group.eventmanagement.model.Admin;
import com.group.eventmanagement.model.Comment;
import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.Post;
import com.group.eventmanagement.model.Tag;
import com.group.eventmanagement.model.User;

public class TestData {

    /////////////////////////// USERS /////////////////////////////////

    // USER 1
    static final String user1Username = "user1";
    static final String user1Firstname = "Saba";
    static final String user1Lastname = "Fathi";
    static final String user1Email = "test1@email.com";
    static final String user1Password = "test1234";
    static final Timestamp user1Birthday = new Timestamp(System.currentTimeMillis());

    // USER 2
    static final String user2Username = "user2";
    static final String user2Firstname = "John";
    static final String user2Lastname = "Smith";
    static final String user2Email = "test2@email.com";
    static final String user2Password = "test1234";
    static final Timestamp user2Birthday = new Timestamp(System.currentTimeMillis());

    // USER 3
    static final String user3Username = "user3";
    static final String user3Firstname = "John";
    static final String user3Lastname = "Smith";
    static final String user3Email = "test3@email.com";
    static final String user3Password = "test1334";
    static final Timestamp user3Birthday = new Timestamp(System.currentTimeMillis());

    public static final void setUser(User user, int num) {

        if (num == 1) { // vax user
            user.setUsername(user1Username);
            user.setPassword(user1Password);
            user.setFirstName(user1Firstname);
            user.setLastName(user1Lastname);
            user.setEmail(user1Email);
            user.setBirthday(user1Birthday);
            user.setVaccinationStatus(true);
            user.setTags(new ArrayList<Tag>());
            user.setPosts(new ArrayList<Post>());

        } else if (num == 2) { // No vax user
            user.setUsername(user2Username);
            user.setPassword(user2Password);
            user.setFirstName(user2Firstname);
            user.setLastName(user2Lastname);
            user.setEmail(user2Email);
            user.setBirthday(user2Birthday);
            user.setVaccinationStatus(false);
            user.setTags(new ArrayList<Tag>());
            user.setPosts(new ArrayList<Post>());

        } else if (num == 3) {
            user.setUsername(user3Username);
            user.setPassword(user3Password);
            user.setFirstName(user3Firstname);
            user.setLastName(user3Lastname);
            user.setEmail(user3Email);
            user.setBirthday(user3Birthday);
            user.setVaccinationStatus(true);
            user.setTags(new ArrayList<Tag>());
            user.setPosts(new ArrayList<Post>());

        }
    }


    /////////////////////////// TAGS /////////////////////////////////

    // Tag 1
    static final String tag1Name = "TestTag1";
    static final String tag1Description = "TestDescription1";

    // Tag 2
    static final String tag2Name = "TestTag2";
    static final String tag2Description = "TestDescription2";

    public static final void setTag(Tag tag, int num) {

        if (num == 1) {
            // Tag 1
            tag.setDescription(TestData.tag1Description);
            tag.setName(TestData.tag1Name);
            tag.setEvents(new ArrayList<Event>());
            tag.setUsers(new ArrayList<User>());

        } else {
            // Tag 2
            tag.setDescription(TestData.tag2Description);
            tag.setName(TestData.tag2Name);
            tag.setEvents(new ArrayList<Event>());
            tag.setUsers(new ArrayList<User>());
        }
    }

    /////////////////////////// EVENTS /////////////////////////////////

    // Event 1
    static final Timestamp event1Time = new Timestamp(System.currentTimeMillis());
    static final boolean event1Private = true;
    static final boolean event1Public = false;
    static final String event1Location = "Test Location 1";
    static final String event1Description = "Test Description 1";
    static final String event1Image = "image1";

    // Event 2
    static final Timestamp event2Time = new Timestamp(System.currentTimeMillis());
    static final boolean event2Private = true;
    static final boolean event2Public = false;
    static final String event2Location = "Test Location 2";
    static final String event2Description = "Test Description 2";
    static final String event2Image = "image2";

    public static final void setEvent(Event event, boolean isPrivate) {

        if (isPrivate) {
            // Event 1
            event.setAttendees(new ArrayList<User>());
            event.setDate(event1Time);
            event.setDescription(event1Description);
            event.setIsPrivate(true);
            event.setIsVirtual(false);
            event.setLocation(event1Location);
            event.setOrganizers(new ArrayList<User>());
            event.setImage(event1Image);

        } else {
            // Event 2
            event.setAttendees(new ArrayList<User>());
            event.setDate(event2Time);
            event.setDescription(event2Description);
            event.setIsPrivate(true);
            event.setIsVirtual(false);
            event.setLocation(event2Location);
            event.setOrganizers(new ArrayList<User>());
            event.setImage(event2Image);

        }

    }

    /////////////////////////// POSTS /////////////////////////////////

    // Post 1
    static final String post1Title = "Post 1 Title";
    static final String post1Description = "Post 1 Description";

    // Post 2
    static final String post2Title = "Post 2 Title";
    static final String post2Description = "Post 2 Description";


    public static final void setPost(Post post, Event event, User user, int num) {

        if (num == 1) {
            post.setTitle(post1Title);
            post.setDescription(post1Description);
            post.setUser(user);
            post.setEvent(event);
            post.setComments(new ArrayList<Comment>());

        } else {
            post.setTitle(post2Title);
            post.setDescription(post2Description);
            post.setUser(user);
            post.setEvent(event);
            post.setComments(new ArrayList<Comment>());

        }
    }


    /////////////////////////// COMMENTS /////////////////////////////////

    // Comment 1
    static final String comment1Content = "Comment 1 Content";

    // Comment 2
    static final String comment2Content = "Comment 2 Content";


    public static final void setComment(Comment comment, Post post, User commenter, int num) {

        if (num == 1) {
            comment.setCommenter(commenter);
            comment.setPost(post);
            comment.setContent(comment1Content);
        } else {
            comment.setCommenter(commenter);
            comment.setPost(post);
            comment.setContent(comment2Content);
        }

    }


	/////////////////////////// ADMINS /////////////////////////////////

	// ADMIN 1
	static final String admin1Username = "Admin1";
	static final String admin1Password = "password1";
	static final String admin1FirstName = "Admin1";
	static final String admin1LastName = "Test1";
	static final String admin1Email = "admin1@mail.com";

	// ADMIN 2
	static final String admin2Username = "Admin2";
	static final String admin2Password = "password2";
	static final String admin2FirstName = "Admin2";
	static final String admin2LastName = "Test2";
	static final String admin2Email = "admin2@mail.com";

	public static final Admin createAdmin(String username, String firstName, String lastName, String email, String password) {
		Admin newAdmin = new Admin();
		newAdmin.setUsername(username);
		newAdmin.setFirstName(firstName);
		newAdmin.setLastName(lastName);
		newAdmin.setPassword(password);
		newAdmin.setEmail(email);

		return newAdmin;
	}

}
