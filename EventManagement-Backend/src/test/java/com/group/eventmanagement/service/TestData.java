package com.group.eventmanagement.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.group.eventmanagement.model.Comment;
import java.util.List;

import com.group.eventmanagement.model.Post;
import com.group.eventmanagement.model.Tag;
import com.group.eventmanagement.model.User;


public class TestData {

	/////////////////////////// USERS /////////////////////////////////

	// USER CONSTANTS
	static String userUsername = "user2";
	static String userFirstname = "John";
	static String userLastname = "Smith";
	static String userEmail = "test@email.com";
	static String userPassword = "test1234";
	static Timestamp userBirthday = new Timestamp(System.currentTimeMillis());

	// INVALID CONSTANTS
	static String invalidUserEmail = "something@";

	// EXISTENT USERNAME
	static String existentUsername = "Mia123";

	
	/////////////////////////// ADMINS /////////////////////////////////
	
	// ADMIN CONSTANTS
	static String adminUsername = "admin";
	static String adminEmail = "admin@mail.com";
	static String adminFirstName = "Admin";
	static String adminLastName = "Test";
	static String adminPassword = "adminpassword123*";
	
	// INVALID CONSTANTS
	static String invalidAdminEmail = "@yahoo.com";

	/////////////////////////// EVENT /////////////////////////////////
	
	// EVENT CONSTANTS
	static Long eventID = (long) 123;
	static Timestamp eventDate = new Timestamp(System.currentTimeMillis()+24*60*60*1000);
	static boolean isPrivate = false;
	static boolean isVirtual = false;
	static String eventLocation = "Shibuya, Tokyo, Japan";
	static String eventDescription = "Sightseeing";
	static String eventImage = "sample picture";
	static List<Tag> eventTags = new ArrayList<Tag>();
	static List<User> eventOrganizers = new ArrayList<User>();
	static List<User> eventAttendees = new ArrayList<User>();
	static List<Post> eventPosts = new ArrayList<Post>();
	
	// INVALID CONSTANTS
	static Timestamp invalidEventDate = new Timestamp(System.currentTimeMillis()-24*60*60*1000); //Date before current Date	

	// POST CONSTANTS
	static String postTitle = "Shopping in Akihabara";
	static String postDescription = "Yo";
	static ArrayList<Comment> postComments = new ArrayList<Comment>();
}
