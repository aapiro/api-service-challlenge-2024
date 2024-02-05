Feature: Price Query Scenarios

  Scenario: Case 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    Given I query the price for product 35455 from brand 1 at "2020-06-14 10:00:00"
    Then I receive a status 200
    And the start price date is "2020-06-14T00:00:00"
    And the end price date is "2020-12-31T23:00:59.059"
    And the product ID is 35455
    And the brand ID is 1
    And I receive a price of 35.5 with price list 1

  Scenario: Case 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    Given I query the price for product 35455 from brand 1 at "2020-06-14 16:00:00"
    Then I receive a status 200
    And the start price date is "2020-06-14T15:00:00"
    And the end price date is "2020-06-14T18:00:30"
    And the product ID is 35455
    And the brand ID is 1
    And I receive a price of 25.45 with price list 2

  Scenario: Case 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    Given I query the price for product 35455 from brand 1 at "2020-06-14 21:00:00"
    Then I receive a status 200
    And the start price date is "2020-06-14T00:00:00"
    And the end price date is "2020-12-31T23:00:59.059"
    And the product ID is 35455
    And the brand ID is 1
    And I receive a price of 35.5 with price list 1

  Scenario: Case 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
    Given I query the price for product 35455 from brand 1 at "2020-06-15 10:00:00"
    Then I receive a status 200
    And the start price date is "2020-06-15T00:00:00"
    And the end price date is "2020-06-15T11:00:00"
    And the product ID is 35455
    And the brand ID is 1
    And I receive a price of 30.5 with price list 3

  Scenario: Case 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
    Given I query the price for product 35455 from brand 1 at "2020-06-16 21:00:00"
    Then I receive a status 200
    And the start price date is "2020-06-15T16:00:00"
    And the end price date is "2020-12-31T23:00:59.059"
    And the product ID is 35455
    And the brand ID is 1
    And I receive a price of 38.95 with price list 4
