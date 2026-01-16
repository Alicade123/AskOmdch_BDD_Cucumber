Feature: checkout functionality

  As a customer
  I want to proceed to checkout
  so that I can purchase the products

  Scenario: Successful checkout with valid shipping and payment details
    Given I add "Blue Shoes" in my cart
    And I am on the checkout page
    When I provide valid shipping details
      | firstName     | The-User1          |
      | lastName      | Anitha              |
      | country       | US             |
      | streetAddress | st34566            |
      | town          | China City             |
      | state         | AL             |
      | postCode      | 00000               |
      | phone         | 0788888888         |
      | email         | user2@gmail.com |
    And I select
      | Cash on delivery |
    And I place the order
    Then I should see an order confirmation message