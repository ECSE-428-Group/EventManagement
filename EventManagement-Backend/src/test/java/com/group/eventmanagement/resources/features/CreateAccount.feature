Feature: Create an Account
    As a user,
    I want to create an account, so I can access and utilize the services offered by JoinIt.

Scenario Outline: Create account successfully
    Given that "<username>" has not been selected by another user
    When the user tries to create an account with "<username>", "<password>", "<firstname>", "<lastname>", "<birthday>", "<email>"
    Then the account is created
    And the user can log in using the credentials "<username>" and "<password>"

Scenario Outline: Unsuccessful Account Creation
    Given the following user exists in the system:
      | username | password | firstname | lastname | birthday   | email                |
      | user1    | pass123  | John      | Smith    | 1998-02-15 | john.smith@gmail.com |
    When the user tried to create an account with "user1", "<password>", "<firstname>", "<lastname>", "<birthday>", "<email>"
    Then the error "<errorMsg>" should be displayed

    Examples:
      | username | password | firstname | lastname | birthday   | email                    | errorMsg
      | user1    | openme   | Johnny    | Smith    | 1996-10-23 | johnny.smith@gmail.com   | This username already exists!
      | user2    |          | Jane      | Doe      | 2000-11-10 | jane.doe@gmail.com       | Password is invalid.
      | user3    | pass123  |           | Smith    | 2002-02-15 | blank.smith@gmail.com    | First name cannot be empty.
      | user4    | abcd1234 | Jonathan  |          | 1998-07-13 | jonathan.blank@gmail.com | Last name cannot be empty.
      | user5    | pass123  | John      | Smith    | 0000-12-15 | john.smith@gmail.com     | Birthday is invalid.
      | user6    | passw234 | Baba      | Lee      | 2004-12-31 | babalee3                 | Email is invalid
     
