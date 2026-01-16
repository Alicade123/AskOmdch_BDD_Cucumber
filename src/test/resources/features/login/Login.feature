Feature: Login Functionality
  As a Customer
  I want to login AskOmDch OnlineStore
  So that I can access my personalized data and preferences.
Background:
  Given As I'm on the AskOmDch Landing page I navigate to account page
  Scenario Outline: Login Successfully
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
      | invaliduser | Test@12345      | Error: The username invaliduser is not registered on this site. If you are unsure of your username, try your email address instead.         |
      | fadi        | WrongPassword   | Error: The password you entered for the username fadi is incorrect. Lost your password?  |


