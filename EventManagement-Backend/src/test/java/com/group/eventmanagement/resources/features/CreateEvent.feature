Feature: Create an Event
  As a user,
  I want to create an event, so I can plan and attend an event with other JoinIt users.

  Background:
    Given the following user exists in the system:
      | username |   password  | firstname | lastname |  birthday  |         email         |
      |  user1   |    pass123  |   John    |  Smith   | 1998-02-15 | john.smith@gmail.com  |
      |  xz1000  | testing1234 |   Saba    |  Jun     | 1994-05-23 |  saba.jun@gmail.com   |

  Scenario Outline: Create event successfully
    Given that "user1" is logged in
    When the user tries to create an event with "<eventDate>", "<isPrivate>", "<isVirtual>", "<location>", "<description>", and "<image>"
    Then the account with username "user1" shall be the organizer of the event with "<eventDate>", "<isPrivate>", "<isVirtual>", "<location>", "<description>", and "<image>"

    Examples:
      | eventDate    | isPrivate | isVirtual |        location        |     description     |    image    |
      | 2022-04-10   |  True     |   False   |    688 Sherbrooke W.   |   Painting session  |  Paint.png  |
      | 2022-10-22   |  False    |   True    |      On Zoom           |   Code Bootcamp     |  Code.png   |



   Scenario Outline: Invalid Event Creation Attempt
    Given that "user1" is logged in
    When the user tries to create an event with "<eventDate>", "<isPrivate>", "<isVirtual>", "<location>", "<description>", and "<image>"
    Then the error "<errorMsg>" should be displayed

    Examples:
      | eventDate    | isPrivate | isVirtual |        location        |     description     |    image    |                errorMsg                        |
      | 2021-04-10   |  True     |   False   |    688 Sherbrooke W.   |   Painting session  |  Paint.png  |         This date has already passed           |
      | 2022-10-22   |  False    |   True    |        On Zoom         |                     |  Test.png   | This event has no description associated to it |
      |              |  False    |   True    |        On Zoom         |     Code Bootcamp   |  Code.png   | This event has no date associated to it        |
      | 2023-11-26   |  False    |   False   |                        |     Cake Baking     |  Cake.png   | This event has no location associated to it    |
      | 2023-01-01   |  True     |   False   |      5 Ch. Grimes      |   Christmas Party   |             | This event has no image associated to it       |
