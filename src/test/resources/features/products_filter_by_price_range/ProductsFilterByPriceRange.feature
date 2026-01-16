Feature: Products Filter By Price Range
  As a Customer
  I want to filter the products by specifying the price range
  So that I can finds the products easy and quickly in the range I can afford instantly without passing through all products.

  Scenario Outline: Products filtering by price range
    Given I'm on the landing page of the AskOmDch Website
    When I click on Store tab I navigate to products page
    And  I choose the <minimumPrice> range and <maximumPrice> range
    Then I get products that fall in the price range I chosen <minimumPrice> and <maximumPrice>.
    Examples:
      |minimumPrice|maximumPrice|
      |50|100|
      |50|60|
      |100|5000|
