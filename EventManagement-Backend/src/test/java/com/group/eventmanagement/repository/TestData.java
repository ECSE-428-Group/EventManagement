package com.group.eventmanagement.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.group.eventmanagement.model.Comment;
import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.Tag;
import com.group.eventmanagement.model.User;

public class TestData {

    /////////////////////////// USERS /////////////////////////////////

    // USER 1
    static String user1Username = "user1";
    static String user1Firstname = "Saba";
    static String user1Lastname = "Fathi";
    static String user1Email = "saba@email.com";
    static String user1Password = "test1234";
    static Timestamp user1Birthday = new Timestamp(System.currentTimeMillis());
    static List<Tag> user1Tags = new ArrayList<Tag>();

    // USER 2
    static String user2Username = "user2";
    static String user2Firstname = "John";
    static String user2Lastname = "Smith";
    static String user2Email = "test@email.com";
    static String user2Password = "test1234";
    static Timestamp user2Birthday = new Timestamp(System.currentTimeMillis());
    static List<Tag> user2Tags = new ArrayList<Tag>();

    public static User createUser(boolean vaxStatus) {

        if (vaxStatus) { // vax user
            User user = new User();

            user.setUsername(user1Username);
            user.setPassword(user1Password);
            user.setFirstName(user1Firstname);
            user.setLastName(user1Lastname);
            user.setEmail(user1Email);
            user.setBirthday(user1Birthday);
            user.setVaccinationStatus(true);
            user.setTags(user1Tags);

            return user;
        } else { // No vax user
            User user = new User();

            user.setUsername(user2Username);
            user.setPassword(user2Password);
            user.setFirstName(user2Firstname);
            user.setLastName(user2Lastname);
            user.setEmail(user2Email);
            user.setBirthday(user2Birthday);
            user.setVaccinationStatus(false);
            user.setTags(user2Tags);

            return user;
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


    public static Tag createTag(int num) {

        if (num == 1) {
            // Tag 1
            Tag tag1 = new Tag();

            tag1.setDescription(TestData.tag1Description);
            tag1.setName(TestData.tag1Name);
            tag1.setEvents(TestData.tag1Events);
            tag1.setUsers(TestData.tag1Users);

            return tag1;
        } else {
            // Tag 2
            Tag tag2 = new Tag();

            tag2.setDescription(TestData.tag2Description);
            tag2.setName(TestData.tag2Name);
            tag2.setEvents(TestData.tag2Events);
            tag2.setUsers(TestData.tag2Users);

            return tag2;
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


    /////////////////////////// POSTS /////////////////////////////////

    // Post 1
    static String post1Title = "Post 1 Title";
    static String post1Description = "Post 1 Description";
    static List<Comment> post1Comments = new ArrayList<Comment>();

    // Post 2
    static String post2Title = "Post 2 Title";
    static String post2Description = "Post 2 Description";
    static List<Comment> post2Comments = new ArrayList<Comment>();


    /////////////////////////// COMMENTS /////////////////////////////////

    // Comment 1
    static String comment1Content = "Comment 1 Content";

    // Comment 2
    static String comment2Content = "Comment 2 Content";


}
