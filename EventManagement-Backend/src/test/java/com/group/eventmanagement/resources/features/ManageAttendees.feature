Feature: Manage attendees
  I want to be able to remove attendees from an event I'm an organizer of.

	Background:
    Given the following user exists in the system
      | username | password | firstname | lastname| birthday   | email                  |
      | org1234  | orga999  | Thierry   | Smith   | 2001-11-09 | thsmith@gmail.com      |
      | org5678  | orga111  | Moe     	| Henry   | 1987-09-24 | moeh@gmail.com         |
      | org0000  | orga000  | Alan    	| Dwayne  | 1992-12-13 | alandw@gmail.com       |
      | user0000 | user000  | Jorje  		| Suarez  | 1998-08-20 | jorjsuarez@gmail.com   |
      | user1234 | user999  | Norman    | Brown   | 1978-05-03 | norman.brown@gmail.com |
      | user5678 | user111  | Angela    | White   | 1999-09-17 | angwht@gmail.com       |
      
      
    Given the following events exist in the system
      | eventId | eventDate  | isPrivate | isVirtual | location                  | description | image         | organizers | attendees |
      | 2234    | 2022-10-21 | True      | False     | 99 Sherbrooke Street      | Photoshoot  | studio.png    | org1234    | user1234  |
      | 6953    | 2022-05-18 | True      | False     | 221 Saint-Catherine Blvd  | Boxing Club | boxinggym.png | org5678   	|           |
      | 7777    | 2022-06-21 | True      | True      | Zoom                      | Conference  | molecule.png  | org5678   	| user5678  |
      
  Scenario: Remove an attendee successfully
    Given that "org1234" is logged in
    When the user tries to remove "user1234" from the event with the ID "2234"
    Then "user1234" is removed from the list of attendees of the event
    	| eventId | eventDate  | isPrivate | isVirtual | location                  | description | image         | organizers | attendees |
      | 2234    | 2022-10-21 | True      | False     | 99 Sherbrooke Street      | Photoshoot  | studio.png    | org1234    |           |
    
    
  Scenario: Remove an attendee unsuccessfully
    Given that "org5678" is logged in
    When the user tries to remove "user1234" from the event with the ID "6593"
    Then the error message "The attendee does not exist or is not subscribed to this event." is displayed.
    
  Scenario: Remove all attendees successfully
    Given that "orga0000" is logged in
    When the user tries to remove all attendees from the event with the ID "7777"
    Then  the list of attendees becomes empty
    	| eventId | eventDate  | isPrivate | isVirtual | location                  | description | image         | organizers | attendees |
    	| 7777    | 2022-06-21 | True      | True      | Zoom                      | Conference  | molecule.png  | org5678   	|           |
    
  Scenario: Remove all attendees unsuccessfully
    Given that "org5678" is logged in
    When the user tries to remove all users from the event with the ID "5678"
    Then the error message "This event does not have any attendees." is displayed.

