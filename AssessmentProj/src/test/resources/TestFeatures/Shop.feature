Feature: Shopping Page

  @Test3
  Scenario: Purchase items and verify checkout details
    Given The user navigates to Jupitor Toys application
    When The user navigates to shop page
    When The user buys '2-Stuffed Frog, 5-Fluffy Bunny, 3-Valentine Bear'
    And The user goes to the shopping cart
    Then The user verifies that subtotal for each product is correct
    And The user verifies the price for each product
    And The user verifies that total payable amount is equal to sum of all subtotals
