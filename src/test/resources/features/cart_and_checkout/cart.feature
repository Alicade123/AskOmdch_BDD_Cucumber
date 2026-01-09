
Feature: Cart and checkout process
  As a customer
  I want to review my cart and checkout
  So that I can place an order successfully

  Scenario: Complete checkout with a product in cart
    Given user is on the home page
    When user navigates to the Store page
    And user adds a product to the cart
    And user opens the cart page
    Then product should be listed in the cart

    When user proceeds to checkout
    And user fills billing details
    And user places the order
    Then order should be placed successfully
