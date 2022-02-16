package com.group.eventmanagement.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.group.eventmanagement.repository.EventRepository;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

	@Mock
	private EventRepository eventRepository;

	@InjectMocks
	private EventService eventService;

	@Test
	//Creation of a valid event
	public void createValidEvent() {
		
		
		
	}
	
	@Test
	//An event with this ID already exists
	public void createEventWithDuplicateID() {
		
		
		
	}
	
	@Test
	//An event must have a date
	public void createEventWithNoDate() {
		
		
		
	}
	
	@Test
	//An event cannot be scheduled for a date in the past
	public void createEventWithInvalidDate() {
		
		
		
	}
	
	@Test
	//An event's location cannot be empty
	public void createEventWithNoLocation() {
		
		
		
	}
	
	@Test
	//An event's description cannot be empty
	public void createEventWithNoDescription() {
		
		
		
	}
	
	@Test
	//An event must have an image associated with it
	public void createEventWithNoImage() {
		
		
		
	}
	
	@Test
	//An event must have at least one tag
	public void createEventWithNoTags() {
		
		
		
	}
	
	@Test
	//An event must have at least one organizers
	public void createEventWithNoOrganizers() {
		
		
		
	}
}
