Feature: Create an Event
  As a user,
  I want to be able to post pictures on the event page of the event I am attending, so that I can share my pictures of the event.

  Background:
    Given the following users exists in the system:
      | username |   password  | firstname | lastname |  birthday  |         email         |
      |  user1   |    pass123  |   John    |  Smith   | 1998-02-15 | john.smith@gmail.com  |
      |  xz1000  | testing1234 |   Saba    |  Jun     | 1994-05-23 |  saba.jun@gmail.com   |

    Given the following events exists in the system:
      | eventId | eventDate    | isPrivate | isVirtual |        location        |     description     |    image    |
      |    1    | 2022-04-10   |  True     |   False   |    688 Sherbrooke W.   |   Painting session  |  Paint.png  |
      |    2    | 2022-10-22   |  False    |   True    |      On Zoom           |   Code Bootcamp     |  Code.png   |


  Scenario Outline: Create post successfully
    Given that "user1" is logged in
    When the user tries to create an post on the event with "<eventId>", with "<title>", "<description>"
    Then the event with eventId "<eventId>" shall have a post with "<title>", "<description>"

    Examples:
      | eventId |     title            |        description            |    username   |
      |    1    |  Look at this thing  |  Found this during the event  |      user1    |
      |    2    |  Nice picture I took | I took this during the event  |     xz1000    |

  Scenario Outline: Invalid Post Creation Attempt
    Given that "<user>" is logged in
    When the user tries to create an post on the event with "<eventId>", with "<title>", "<description>"
    Then the error "<errorMsg>" should be displayed

    Examples:
      |  eventId |          title        |        description           |  user   |                errorMsg                     |
      |          |   Look at this thing  |  Found this during the event |  user1  |  Post needs to be associated with an event! |
      |    2     |  Nice picture I took  | I took this during the event |         |  Post needs to be associated with a user!   |
      |    2     |                       | I took this during the event | xz1000  |  Post needs to have a title!                |
      |    2     |  Nice picture I took  |                              |  user1  |  Post needs to have a description!          |
