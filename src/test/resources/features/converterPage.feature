@converterPage
Feature: Converter functions using POM

  @converterPage1
  Scenario Outline: Validate converted result of units with POM
    Given I go to converter webpage
    When I choose and click on "<tab>"
    And I set options "<from_unit>" to "<to_unit>"
    Then I enter value into From field "<from_value>"
    And I verify "<expected_value>" result
    Examples:
      | tab         | from_unit  | to_unit   | from_value | expected_value |
      | Temperature | Fahrenheit | Celsius   | 54         | 12.2           |
      | Weight      | Pound      | Kilogram  | 170        | 77             |
      | Length      | Mile       | Kilometer | 50         | 80.4           |
