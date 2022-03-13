Feature: Update Profile Info
  As a user,
  I want to update my profile info, so that I can change my information.
  
  Background:
    Given the following user exists in the system:
      | username | password | firstname | lastname |  birthday  |         email         |
      |  user1   | pass123  |   John    |  Smith   | 1998-02-15 | john.smith@gmail.com  |
      |  user2   | pass111  |   Jane    |  Smith   | 1998-01-03 | jane.smith@gmai.com   |
    
  Scenario Outline: Update account successfully
    Given that "user1" is logged in
    When the user tries to update their account with "<curPassword>", "<newPassword>", "<newFirstname>", "<newLastname>", "<newBirthday>", and "<newEmail>"
    Then the account with username "user1" shall have "<updatedPassword>", "<updatedFirstname>", "<updatedLastname>", "<updatedBirthday>", and "<updatedEmail>"
    
    Examples:
      | curPassword | newPassword | newFirstname | newLastname | newBirthday |       newEmail        | updatedPassword | updatedFirstname | updatedLastname | updatedBirthday |    updatedEmail      |
      |   pass123   |  pass1234   |    James     |    Doe      | 1998-02-16  |  james.doe@gmail.com  |    pass1234     |       James      |       Doe       |   1998-02-16    | james.doe@gmail.com  |
      |   pass123   |  pass1234   |              |             |             |                       |    pass1234     |       John       |     Smith       |   1998-02-15    | john.smith@gmail.com |
      |   pass123   |             |    John      |             |             |                       |     pass123     |       John       |     Smith       |   1998-02-15    | john.smith@gmail.com |
      
   Scenario Outline: Invalid Update Attempt
    Given that "user1" is logged in
    When the user tries to update their account with "<curPassword>", "<newPassword>", "<newFirstname>", "<newLastname>", "<newBirthday>", and "<newEmail>"
    Then the error "<errorMsg>" should be displayed
    
    Examples:
      | curPassword |          newPassword         | newFirstname | newLastname | newBirthday |       newEmail        |                   erorMsg                    |
      |   pass123   |               p              |    James     |    Doe      | 1998-02-16  |  james.doe@gmail.com  | Password must be between 6 and 26 characters |      
      |   pass123   | abcdefghijklmnopqrstuvwxyz12 |    James     |    Doe      | 1998-02-16  |  james.doe@gmail.com  | Password must be between 6 and 26 characters |
      |   pass123   |           pass1234           |    James     |    Doe      | 2023-02-16  |  james.doe@gmail.com  |             Birthday is invalid              |
      |   pass111   |           pass1234           |    James     |    Doe      | 1998-02-16  |  james.doe@gmail.com  |             Incorrect password               |
      |   pass123   |           pass1234           |    James     |    Doe      | 1998-02-16  |  james.doe@gmail      |                Invalid Email                 |     
      |   pass123   |           pass1234           |    James     |    Doe      | 1998-02-16  |  james.doegmail.com   |                Invalid Email                 |          
   
  
    
  