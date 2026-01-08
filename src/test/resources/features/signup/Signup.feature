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
      | USADeltaForce   | B2Sprit@example.com      | Pass123! |
      | USANavy   | JetFighter1@example.com        | Pass456!  |