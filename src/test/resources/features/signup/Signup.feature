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
      | USADeltaForce01   | B2Sprit@example01.com      | Pass123! |
      | USANavy01   | JetFighter1@example01.com        | Pass456!  |
      
  @registration @negative
  Scenario: Registration without username
    When I enter email "newuser999@example.com" in registration form
    And I enter password "Test@12345" in registration form
    And I click on Register button
    Then I should see error "Please enter a valid account username"

  @registration @negative
  Scenario: Registration without email
    When I enter username "testuser123" in registration form
    And I enter password "Test@12345" in registration form
    And I click on Register button
    Then I should see error "Please provide a valid email address"

  @registration @negative
  Scenario: Registration without password
    When I enter username "testuser999" in registration form
    And I enter email "testuser999@example.com" in registration form
    And I click on Register button
    Then I should see error "Please enter an account password"
    
  @registration  
  Scenario: Registration with existing username
    When I enter username "USADeltaForce01" in registration form
    And I enter email "hali01@example.com" in registration form
    And I enter password "Test@1234569" in registration form
    And I click on Register button
    Then I should see error "An account is already registered with that username"
    
  @registration 
  Scenario: Registration with existing email
    When I enter username "newuser12312345" in registration form
    And I enter email "hali@example.com" in registration form
    And I enter password "Test@12345" in registration form
    And I click on Register button
    Then I should see error "An account is already registered with your email address"
  