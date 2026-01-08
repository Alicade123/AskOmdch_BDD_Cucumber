Feature: Navigation Functionality
  As a customer
  I want to navigate using navigation bar
  So that I can access all section of the AskOmDch Website.

  Scenario Outline: Navigate Successfully
    Given I'm On any page of the AskOmDch website
    When I click on "<link>"
    Then I should be directed to the page with "<pageTitle>"
    Examples:
    |link    |pageTitle|
    |AskOmDch|AskOmDch – Become a Selenium automation expert!   |
    |Home    | AskOmDch – Become a Selenium automation expert!  |
    |Store   | Products – AskOmDch                              |
    |Men     |  Men – AskOmDch                                  |
    |Women   |  Women – AskOmDch                                |
    |Accessories| Accessories – AskOmDch                        |
    |Account |  Account – AskOmDch                              |
    |About   |  About – AskOmDch                                |
    |Contact Us|Contact Us – AskOmDch                           |