Feature: User Registration


  As a new user
  I want to create a account through the registration form
  So that my credentials are saved in the system


  Background:
    Given I am on any page of AskomDch website
    When I click on the "Account" link in the navigation bar

  Scenario Outline: Registering successful

    When I enter "<Username>" "<Email address>" and "<Password>" in the registration form
    And I click the "REGISTER" button
    Then I should be directed to Dashboard


    Examples:
      | Username    | Email address     | Password      |
      | newAppUser1        | newAppUser1@example01.com      | Pass123! |
      | newAppUser2   | newAppUser21@example01.com        | Pass456!  |


  @registration @negative
  Scenario Outline: Registration with missing required fields
    When I enter username "<username>" in registration form
    And I enter email "<email>" in registration form
    And I enter password "<password>" in registration form
    And I click on Register button
    Then I should see error "<error_message>"


    Examples:
      | username     | email                      | password    | error_message                          |
      |              | newuser999@example.com     | Test@12345  | Please enter a valid account username  |
      | testuser123  |                            | Test@12345  | Please provide a valid email address   |
      | testuser999  | testuser999@example.com    |             | Please enter an account password       |


  @registration @negative
  Scenario Outline: Registration fails when username or email already exists
    When I enter username "<username>" in registration form
    And I enter email "<email>" in registration form
    And I enter password "<password>" in registration form
    And I click on Register button
    Then I should see error "<error_message>"

    Examples:
      | username           | email               | password        | error_message                                              |
      | newAppUser1        | newAppUser1@example01.com  | Pass123!    | Error: An account is already registered with your email address. Please log in.       |
      | newAppUser2        | newAppUser21@example01.com    | Pass456!      | Error: An account is already registered with your email address. Please log in.   |


