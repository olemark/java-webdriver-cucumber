@ups
  Feature: UPS scenarios

    @ups1 @smoke
    Scenario: UPS end to end
      Given I go to "ups_global" page
      And I select "North America" and "United States - English" on global page
      And I open Shipping menu
      And I go to Create a Shipment
      When I fill out origin shipment fields: "United States", "Oleg", "2245 Latham St", "94040", "testboxus1@gmail.com", "6504951122"
      And I submit the shipment form
      Then I verify origin shipment fields submitted: "US", "Oleg", "2245 Latham St", "94040", "testboxus1@gmail.com", "6504951122"
      And I cancel the shipment form
      Then I verify shipment form is reset

      @ups2 @regression
      Scenario:	UPS	end to end full
        Given I go to "ups" page
        And I open Shipping menu
        And I go to Create a Shipment
        When I fill out origin shipment fields: "United States", "Administrator", "4970 El Camino Real", "94022", "mail@example.com", "1234567890"
        And I submit the shipment form
        Then I verify origin shipment fields submitted: "US", "Administrator", "4970 El Camino Real", "94022", "mail@example.com", "1234567890"
        When I fill out destination shipment fields: "John Doe", "870 7th Ave", "10019"
        Then I verify total charges appear
        When I submit the shipment form
        And I set packaging type
        Then I verify total charges changed
        When I submit the shipment form
        And I select cheapest delivery option
        And I submit the shipment form
        And I set Saturday Delivery type
        Then I verify total charges changed
        When I submit the shipment form
        And I select Paypal payment type
        And I submit the shipment form
        Then I review all recorded details on the review page
        And I cancel the shipment form
        Then I verify shipment form is reset
