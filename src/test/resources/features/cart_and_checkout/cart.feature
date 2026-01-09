Feature: checkout functionality

  As a customer
  I want to proceed to checkout
  so that I can purchase the products

  Scenario: Successful checkout with valid shipping and payment details
    Given I have product number 1 in my cart
    And I am on the checkout page
    When I provide valid shipping details
      | firstName     | The-User1          |
      | lastName      | Aline              |
      | country       | US             |
      | streetAddress | st34566            |
      | town          | Gasabo             |
      | state         | AL             |
      | postCode      | 0000               |
      | phone         | 0788888888         |
      | email         | theuser2@gmail.com |
    And I select
    And I place the order
    Then I should see an order confirmation message

