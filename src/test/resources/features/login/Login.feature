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

