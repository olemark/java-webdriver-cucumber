@wd
  Feature: WD feature

    @wd1
    Scenario: WD scenario
      Given I go to "workday" page
      When I select any position
      And I go to apply with LinkedIn
      Then I verify LinkedIn login page opens
