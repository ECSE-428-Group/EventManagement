package com.group.eventmanagement.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.Tag;
import com.group.eventmanagement.model.User;
import com.group.eventmanagement.service.EventService;

@WebMvcTest(EventController.class)
public class EventControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EventService eventService;
	
	@Test
	public void testEventCreation() {
		User eventOrg = new User();
		Tag eventTag = new Tag();
		TestData.eventTags.add(eventTag);
		TestData.eventOrganizers.add(eventOrg);
		
		Event mockEvent = TestData.createEventObject(TestData.eventID, TestData.eventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage, TestData.eventTags, TestData.eventOrganizers, TestData.eventAttendees, TestData.eventPosts);
		when(eventService.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage, TestData.eventTags, TestData.eventOrganizers, TestData.eventAttendees, TestData.eventPosts))
		.thenReturn(mockEvent);		
		
		try {
			this.mockMvc.perform(post("/event/"+TestData.eventID)
					.param("eventDate", TestData.eventDate.toString())
					.param("isPrivate", "false")
					.param("isVirtual", "false")
					.param("location", TestData.eventLocation)
					.param("description", TestData.eventDescription)
					.param("image", TestData.eventImage)
					.param("tags", TestData.eventTags.toString())
					.param("organizers", TestData.eventOrganizers.toString())
					.param("attendees", TestData.eventAttendees.toString())
					.param("posts", TestData.eventAttendees.toString())
					).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testInvalidEventCreation() {
		User eventOrg = new User();
		Tag eventTag = new Tag();
		TestData.eventTags.add(eventTag);
		TestData.eventOrganizers.add(eventOrg);
		
		when(eventService.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage, TestData.eventTags, TestData.eventOrganizers, TestData.eventAttendees, TestData.eventPosts))
		.thenThrow(IllegalArgumentException.class);	
		
		try {
			this.mockMvc.perform(post("/event/"+TestData.eventID)
					.param("eventDate", TestData.invalidEventDate.toString())
					.param("isPrivate", "false")
					.param("isVirtual", "false")
					.param("location", TestData.eventLocation)
					.param("description", TestData.eventDescription)
					.param("image", TestData.eventImage)
					.param("tags", TestData.eventTags.toString())
					.param("organizers", TestData.eventOrganizers.toString())
					.param("attendees", TestData.eventAttendees.toString())
					.param("posts", TestData.eventAttendees.toString())
					).andExpect(status().isInternalServerError());
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}
