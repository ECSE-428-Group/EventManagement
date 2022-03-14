Feature: Create an Event
  As an attendee of an event
  I want to be able to comment under other peoples posts, so I can share my thought with other users

  Background:
    Given the following users exists in the system:
      | username |   password  | firstname | lastname |  birthday  |         email         |
      |  user1   |    pass123  |   John    |  Smith   | 1998-02-15 | john.smith@gmail.com  |
      |  xz1000  | testing1234 |   Saba    |  Jun     | 1994-05-23 |  saba.jun@gmail.com   |

    Given the following events exists in the system:
      | eventId | eventDate    | isPrivate | isVirtual |        location        |     description     |    image    |
      |    1    | 2022-04-10   |  True     |   False   |    688 Sherbrooke W.   |   Painting session  |  Paint.png  |
      |    2    | 2022-10-22   |  False    |   True    |      On Zoom           |   Code Bootcamp     |  Code.png   |

    Given the following posts exist in the system:
      |  postId   | eventId |     title            |        description                    |    username   |
      |     3     |    1    |  Look at this thing  |  Found this during the event          |      user1    |
      |     4     |    2    |  Nice picture I took | I took this picture during the event  |     xz1000    |

  Scenario Outline: Create comment successfully
    Given that user with "<username>" is logged in
    When the user tries to create an comment on the post with "<postId>", with content "<content>"
    Then the post with postId "<postId>" shall have a comment with "<content>" and "<username>" as the commenter

    Examples:
      |   postId  |    content     |    username   |
      |     3     |  Nice picture  |      user1    |
      |     4     |    Cool view   |     xz1000    |



  Scenario Outline: Invalid Comment Creation Attempt
    Given that "<username>" is logged in
    When the user tries to create an comment on the post with "<postId>", with content "<content>"
    Then the error "<errorMsg>" should be displayed

    Examples:
      |   postId  |    content     |    username   |        errorMsg                    |
      |     3     |                |      user1    |  Comment cannot be empty!          |
      |           |    Cool view   |     xz1000    |  Comment must be linked to a post! |
      |     4     |    Cool view   |               |  Comment must be linked to a user! |
