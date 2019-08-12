@careers @regression
  Feature: Careers scenarios


    @careers1
    Scenario: Recruiter creates position
      Given I open "careers" page
      And I login as "recruiter"
      Then I verify "recruiter" login
      When I create new position
      And I verify position created

    @careers2 @create_position     //@create_position executes in @before in Hooks
    Scenario: Careers candidate scenario
      Given I open "careers" page
      And I apply to a new position
      Then I verify profile is created
      And I see position in my jobs

    @careers3
    Scenario: Careers adds new job
      Given I open "careers" page
      When I login as "candidate"
      Then I verify "candidate" login
      When I apply for a new job
      And I see position in my jobs

    @careers4
    Scenario: REST api
      Given I login to REST as "recruiter"
      When I create via REST new position
      Then I verify via REST a new position in the list
      And I update via REST new position
      Then I verify via REST position details
      And I delete via REST new position

