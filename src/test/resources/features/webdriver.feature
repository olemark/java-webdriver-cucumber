@webdriver
Feature: WebDriver feature

  @webdriver1
  Scenario: Basic Sample Scenario
    Given I go to "sample" page
    And I print page details


  @webdriver2
  Scenario: JavaScript login Scenario
    Given I go to "sample" page
    And I login in alert pop-up window
    And I go back and forward and refresh
    And I change resolution to "phone"
    And I change resolution to "desktop"
    And I fill out all required fields
    And I verify "Document 2" in related documents
    And I "accept" third party agreement
    And I add contact "John Doe" with phone "1234567890"
    And I print browser logs
    And I submit the page
    Then I verify required fields
    Then I verify next required fields
