package com.group.eventmanagement.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

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
	
	//Tests if controller can validly add attendee
	@Test
	public void testValidAddAttendee() {
		
		doNothing().when(eventService).addAttendee(anyString(), anyLong(), anyString());
		
		try {
			this.mockMvc.perform(put("/event/123")
					.param("callerUsername", "randomOrganizer")
					.param("attendeeUsername", "randomAttendee")
					).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Tests invalid request
	@Test
	public void testInvalidAddAttendee() {
		
		doThrow().when(eventService).addAttendee(anyString(), anyLong(), anyString());
		
		try {
			this.mockMvc.perform(put("/event/123")
					.param("callerUsername", "randomOrganizer")
					.param("attendeeUsername", "randomAttendee")
					).andExpect(status().isInternalServerError());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*@Test
	public void testValidGetAttendee() {
		User attendee = new User();
		when(eventService.addAttendee(anyString(), anyLong(), anyString())).thenReturn(attendee);
		
		try {
			this.mockMvc.perform(put("/event/123")
					.param("callerUsername", "randomOrganizer")
					.param("attendeeUsername", "randomAttendee")
					).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/*public void testEventCreation() {
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
	}*/

}
