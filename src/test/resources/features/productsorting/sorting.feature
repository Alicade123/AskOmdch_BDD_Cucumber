Feature: Sorting products in the store

  Scenario Outline: Sort products using dropdown options
    Given I am on the store page of the askomdch website
    When I select "<sortOption>" from the sorting dropdown
    Then I should see products sorted by "<expectedCriteria>"

    Examples:
      | sortOption               | expectedCriteria   |
      | Sort by popularity       | popularity         |
      | Sort by average rating   | average rating     |
      | Sort by latest           | latest             |
      | Sort by price: low to high | price ascending  |
      | Sort by price: high to low | price descending |
