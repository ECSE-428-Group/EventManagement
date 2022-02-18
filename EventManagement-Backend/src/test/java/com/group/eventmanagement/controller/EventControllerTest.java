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
import com.group.eventmanagement.service.EventService;

@WebMvcTest(EventController.class)
public class EventControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EventService eventService;
	
	@Test
	public void testEventCreation() {
		Event mockEvent = TestData.createEventObject(TestData.eventID, TestData.eventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage);
		when(eventService.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage))
		.thenReturn(mockEvent);		
		
		try {
			this.mockMvc.perform(post("/event/"+TestData.eventID.toString())
					.param("date", TestData.eventDate.toString())
					.param("isPrivate", String.valueOf(TestData.isPrivate))
					.param("isVirtual", String.valueOf(TestData.isVirtual))
					.param("location", TestData.eventLocation)
					.param("description", TestData.eventDescription)
					.param("image", TestData.eventImage)
					).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testInvalidEventCreation() {		
		when(eventService.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage))
		.thenThrow(IllegalArgumentException.class);	
		
		try {
			this.mockMvc.perform(post("/event/"+TestData.eventID)
					.param("date", TestData.invalidEventDate.toString())
					.param("isPrivate", "false")
					.param("isVirtual", "false")
					.param("location", TestData.eventLocation)
					.param("description", TestData.eventDescription)
					.param("image", TestData.eventImage)
					).andExpect(status().isInternalServerError());
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}
