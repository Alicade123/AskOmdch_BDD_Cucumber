Feature: Login Functionality
  As a Customer
  I want to login AskOmDch OnlineStore
  So that I can access my personalized data and preferences.

  Scenario Outline: Login Successfully
    Given As I'm on the AskOmDch Landing page I navigate to account page
    When I enter valid credentials "<username>" and "<password>"
    Then I get redirected to Dashboard Page
    Examples:
      |username|password|
      |USADeltaForce|Pass123!|
      |USANavy|Pass456!|

  @login @negative
  Scenario Outline: Login fails with invalid credentials
    When I enter username "<username>" in login form
    And I enter password "<password>" in login form
    And I click on Login button
    Then I should see login error "<error_message>"

    Examples:
      | username     | password        | error_message                                                     |
      | invaliduser | Test@12345      | Unknown username. Check again or try your email address           |
      | fadi        | WrongPassword   | The password you entered for the username testuser is incorrect  |
