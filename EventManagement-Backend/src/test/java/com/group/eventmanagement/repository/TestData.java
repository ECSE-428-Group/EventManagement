package com.group.eventmanagement.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.group.eventmanagement.model.Admin;
import com.group.eventmanagement.model.Comment;
import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.Post;
import com.group.eventmanagement.model.Tag;
import com.group.eventmanagement.model.User;

public class TestData {

    /////////////////////////// USERS /////////////////////////////////

    // USER 1
    static String user1Username = "user1";
    static String user1Firstname = "Saba";
    static String user1Lastname = "Fathi";
    static String user1Email = "test1@email.com";
    static String user1Password = "test1234";
    static Timestamp user1Birthday = new Timestamp(System.currentTimeMillis());
    static List<Tag> user1Tags = new ArrayList<Tag>();

    // USER 2
    static String user2Username = "user2";
    static String user2Firstname = "John";
    static String user2Lastname = "Smith";
    static String user2Email = "test2@email.com";
    static String user2Password = "test1234";
    static Timestamp user2Birthday = new Timestamp(System.currentTimeMillis());
    static List<Tag> user2Tags = new ArrayList<Tag>();

    // USER 3
    static String user3Username = "user3";
    static String user3Firstname = "John";
    static String user3Lastname = "Smith";
    static String user3Email = "test3@email.com";
    static String user3Password = "test1334";
    static Timestamp user3Birthday = new Timestamp(System.currentTimeMillis());
    static List<Tag> user3Tags = new ArrayList<Tag>();

    public static void setUser(User user, int num) {

        if (num == 1) { // vax user
            user.setUsername(user1Username);
            user.setPassword(user1Password);
            user.setFirstName(user1Firstname);
            user.setLastName(user1Lastname);
            user.setEmail(user1Email);
            user.setBirthday(user1Birthday);
            user.setVaccinationStatus(true);
            user.setTags(user1Tags);

        } else if (num == 2) { // No vax user
            user.setUsername(user2Username);
            user.setPassword(user2Password);
            user.setFirstName(user2Firstname);
            user.setLastName(user2Lastname);
            user.setEmail(user2Email);
            user.setBirthday(user2Birthday);
            user.setVaccinationStatus(false);
            user.setTags(user2Tags);

        } else if (num == 3) {
            user.setUsername(user3Username);
            user.setPassword(user3Password);
            user.setFirstName(user3Firstname);
            user.setLastName(user3Lastname);
            user.setEmail(user3Email);
            user.setBirthday(user3Birthday);
            user.setVaccinationStatus(true);
            user.setTags(user3Tags);

        }
    }


    /////////////////////////// TAGS /////////////////////////////////

    // Tag 1
    static String tag1Name = "TestTag1";
    static String tag1Description = "TestDescription1";
    static List<User> tag1Users = new ArrayList<User>();
    static List<Event> tag1Events = new ArrayList<Event>();

    // Tag 2
    static String tag2Name = "TestTag2";
    static String tag2Description = "TestDescription2";
    static List<User> tag2Users = new ArrayList<User>();
    static List<Event> tag2Events = new ArrayList<Event>();


    public static void setTag(Tag tag, int num) {

        if (num == 1) {
            // Tag 1
            tag.setDescription(TestData.tag1Description);
            tag.setName(TestData.tag1Name);
            tag.setEvents(TestData.tag1Events);
            tag.setUsers(TestData.tag1Users);

        } else {
            // Tag 2
            tag.setDescription(TestData.tag2Description);
            tag.setName(TestData.tag2Name);
            tag.setEvents(TestData.tag2Events);
            tag.setUsers(TestData.tag2Users);
        }
    }

    /////////////////////////// EVENTS /////////////////////////////////

    // Event 1
    static Timestamp event1Time = new Timestamp(System.currentTimeMillis());
    static boolean event1Private = true;
    static boolean event1Public = false;
    static String event1Location = "Test Location 1";
    static String event1Description = "Test Description 1";
    static String event1Image = "image1";
    static List<User> event1Attendees = new ArrayList<User>();
    static List<User> event1Organizers = new ArrayList<User>();

    // Event 2
    static Timestamp event2Time = new Timestamp(System.currentTimeMillis());
    static boolean event2Private = true;
    static boolean event2Public = false;
    static String event2Location = "Test Location 2";
    static String event2Description = "Test Description 2";
    static String event2Image = "image2";
    static List<User> event2Attendees = new ArrayList<User>();
    static List<User> event2Organizers = new ArrayList<User>();


    public static void setEvent(Event event, boolean isPrivate) {

        if (isPrivate) {
            // Event 1
            event.setAttendees(event1Attendees);
            event.setDate(event1Time);
            event.setDescription(event1Description);
            event.setIsPrivate(true);
            event.setIsVirtual(false);
            event.setLocation(event1Location);
            event.setOrganizers(event1Organizers);
            event.setImage(event1Image);

        } else {
            // Event 2
            event.setAttendees(event2Attendees);
            event.setDate(event2Time);
            event.setDescription(event2Description);
            event.setIsPrivate(true);
            event.setIsVirtual(false);
            event.setLocation(event2Location);
            event.setOrganizers(event2Organizers);
            event.setImage(event2Image);

        }

    }

    /////////////////////////// POSTS /////////////////////////////////

    // Post 1
    static String post1Title = "Post 1 Title";
    static String post1Description = "Post 1 Description";
    static List<Comment> post1Comments = new ArrayList<Comment>();

    // Post 2
    static String post2Title = "Post 2 Title";
    static String post2Description = "Post 2 Description";
    static List<Comment> post2Comments = new ArrayList<Comment>();


    public static void setPost(Post post, Event event, User user, int num) {

        if (num == 1) {
            post.setTitle(post1Title);
            post.setDescription(post1Description);
            post.setUser(user);
            post.setEvent(event);
            post.setComments(post1Comments);

        } else {
            post.setTitle(post2Title);
            post.setDescription(post2Description);
            post.setUser(user);
            post.setEvent(event);
            post.setComments(post2Comments);

        }
    }


    /////////////////////////// COMMENTS /////////////////////////////////

    // Comment 1
    static String comment1Content = "Comment 1 Content";

    // Comment 2
    static String comment2Content = "Comment 2 Content";

	/////////////////////////// ADMINS /////////////////////////////////

	// ADMIN 1
	static String admin1Username = "Admin1";
	static String admin1Password = "password1";
	static String admin1FirstName = "Admin1";
	static String admin1LastName = "Test1";
	static String admin1Email = "admin1@mail.com";

	// ADMIN 2
	static String admin2Username = "Admin2";
	static String admin2Password = "password2";
	static String admin2FirstName = "Admin2";
	static String admin2LastName = "Test2";
	static String admin2Email = "admin2@mail.com";

	public static Admin createAdmin(String username, String firstName, String lastName, String email, String password) {
		Admin newAdmin = new Admin();
		newAdmin.setUsername(username);
		newAdmin.setFirstName(firstName);
		newAdmin.setLastName(lastName);
		newAdmin.setPassword(password);
		newAdmin.setEmail(email);

		return newAdmin;
	}

}
