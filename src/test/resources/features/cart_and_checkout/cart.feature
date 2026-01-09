Feature: Cart and checkout process
  As a customer
  I want to review my cart and checkout
  So that I can place an order successfully

  Scenario: Complete checkout with a product in cart
    Given user is on the home page
    When user navigates to the Store page
    And user adds a "<products name>" to the cart
<<<<<<< HEAD
      |products name |
      |Black Over-the-shoulder Handbag |
      |Blue Denim Shorts |
      |Blue Shoes       |
=======
    |products name |
    |Black Over-the-shoulder Handbag |
    |Blue Denim Shorts |
    |Blue Shoes       |
>>>>>>> 7bcead70f69f87c734f6f5f38c112a1df35247d3
    And user opens the cart page
    Then product should be listed in the cart

    When user proceeds to checkout
    And user fills billing details:
      | firstName | Jean paul        |
      | lastName  | Iradukunda         |
      | company name  | Test Ltd    |
      | country   | Rwanda      |
      | Street address   | KN 12 Road  |
      | city      | Kigali      |
      | state     | Kigali City |
      | zip code      | 250        |
      | phone     | 0781234567 |
      | email address     | john@test.com |

    And user places the order
    Then order should be placed successfully