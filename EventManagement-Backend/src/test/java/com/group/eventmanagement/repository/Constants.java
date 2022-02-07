package com.group.eventmanagement.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.group.eventmanagement.model.Comment;
import com.group.eventmanagement.model.Tag;
import com.group.eventmanagement.model.User;

public class Constants {

    /////////////////////////// USERS /////////////////////////////////

    // USER 1
    static String user1Username = "user1";
    static String user1Firstname = "Saba";
    static String user1Lastname = "Fathi";
    static String user1Email = "saba@email.com";
    static String user1Password = "test1234";
    static Timestamp user1String = new Timestamp(System.currentTimeMillis());
    static List<Tag> user1TagList = new ArrayList<Tag>();

    // USER 2
    static String user2Username = "user2";
    static String user2Firstname = "John";
    static String user2Lastname = "Smith";
    static String user2Email = "test@email.com";
    static String user2Password = "test1234";
    static Timestamp user2Birthday = new Timestamp(System.currentTimeMillis());
    static List<Tag> user2Tags = new ArrayList<Tag>();


    /////////////////////////// TAGS /////////////////////////////////

    // Tag 1
    static String name1 = "TestTag1";
    static String tag1Description = "TestDescription1";
    static List<User> tag1Users = new ArrayList<User>();
    static List<User> tag1Events = new ArrayList<User>();

    // Tag 2
    static String name2 = "TestTag2";
    static String tag2Description = "TestDescription2";
    static List<User> tag2Users = new ArrayList<User>();
    static List<User> tag2Events = new ArrayList<User>();


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
