@sample
Feature: Sample page predefined steps

#  Classwork
  @sample1
  Scenario: Responsive UI
    Given I open url "https://skryabin.com/webdriver/html/sample.html"
    When I resize window to 1200 and 800
    Then element with xpath "//*[@id='location']" should be displayed
    And element with xpath "//*[@id='currentDate']" should be displayed
    And element with xpath "//*[@id='currentTime']" should be displayed
    When I resize window to 800 and 800
    Then element with xpath "//*[@id='location']" should be displayed
    And element with xpath "//*[@id='currentDate']" should be displayed
    And element with xpath "//*[@id='currentTime']" should be displayed
    When I resize window to 400 and 800
    Then element with xpath "//*[@id='location']" should not be displayed
    And element with xpath "//*[@id='currentDate']" should not be displayed
    And element with xpath "//*[@id='currentTime']" should not be displayed


  @sample2
  Scenario: Username validation
    Given I open url "https://skryabin.com/webdriver/html/sample.html"
    When I type "a" into element with xpath "//*[@name='username']"
    And I click on element with xpath "//*[@name='email']"
    Then element with xpath "//*[@id='username-error']" should contain text "2 characters"


  @sample3
  Scenario: Email field validation
    Given I open url "https://skryabin.com/webdriver/html/sample.html"
    When I type "cheburashka#com" into element with xpath "//*[@name='email']"
    And I click on element with xpath "//*[@name='username']"
    Then element with xpath "//*[@id='email-error']" should contain text "valid email"







#   Homework
  @sample4
  Scenario: Confirm password validation
    Given I open url "https://skryabin.com/webdriver/html/sample.html"
    When I type "12" into element with xpath "//*[@id='password']"
    And I clear element with xpath "//*[@id='password']"
    And I click on element with xpath "//*[@id='confirmPassword']"
    Then element with xpath "//*[@id='confirmPassword']" should be disabled
#      Then element with xpath "//input[@id='confirmPassword']" should have attribute "disabled" as "disabled"


  @sample5
  Scenario: Name field behavior validation
    Given I open url "https://skryabin.com/webdriver/html/sample.html"
    When I click on element with xpath "//*[@id='name']"
    Then element with xpath "//*[@aria-describedby='nameDialog']" should be displayed
    When I click on element with xpath "//*[@aria-describedby='nameDialog']//*[text()='Cancel']"
    Then element with xpath "//*[@id='name']" should have attribute "value" as ""
    When I click on element with xpath "//*[@id='name']"
    And I type "Oleg" into element with xpath "//*[@id='firstName']"
    And I type "Markov" into element with xpath "//*[@id='lastName']"
    And I click on element with xpath "//*[@aria-describedby='nameDialog']//*[text()='Save']"
    Then element with xpath "//*[@id='name']" should have attribute "value" as "Oleg Markov"

  @sample6
  Scenario: Accepting Privacy Policy validation
    Given I open url "https://skryabin.com/webdriver/html/sample.html"
    When I click on element with xpath "//*[@id='formSubmit']"
    Then element with xpath "//*[@id='agreedToPrivacyPolicy-error']" should be displayed
    And element with xpath "//*[@id='agreedToPrivacyPolicy-error']" should contain text "Must check"
    Then I click on element with xpath "//*[@name='agreedToPrivacyPolicy']"

  @sample7 @sample8
  Scenario: Non-required fields entering and submit
    Given I open url "https://skryabin.com/webdriver/html/sample.html"
    When I type "olegmarkov" into element with xpath "//*[@name='username']"
    And I type "testboxus1+cucumber@gmail.com" into element with xpath "//*[@name='email']"
    And I type "testpass" into element with xpath "//*[@id='password']"
    And I type "testpass" into element with xpath "//*[@id='confirmPassword']"
    And I click on element with xpath "//*[@id='name']"
    And I type "Oleg" into element with xpath "//*[@id='firstName']"
    And I type "Markov" into element with xpath "//*[@id='lastName']"
    And I click on element with xpath "//*[@aria-describedby='nameDialog']//*[text()='Save']"
    And I type "6501111111" into element with xpath "//*[@name='phone']"
    And I click on element with xpath "//select[@name='countryOfOrigin']/option[@value='Austria']"
    And I click on element with xpath "//*[@value='male']"
    And I click on element with xpath "//*[@id='dateOfBirth']"
    And I click on element with xpath "(//select[@class='ui-datepicker-month']/option)[1]"
    And I click on element with xpath "//select[@class='ui-datepicker-year']/option[@value='1990']"
    And I click on element with xpath "//td[@data-handler='selectDay']//*[text()='31']"
    And I wait for element with xpath "//input[@id='dateOfBirth']" to be present
    And I click on element with xpath "//input[@name='allowedToContact']"
    And I type "222 Main St" into element with xpath "//*[@id='address']"
    And I click on element with xpath "//*[@name='carMake']/option[@value='Toyota']"
    And I click on element with xpath "//*[@id='thirdPartyButton']"
    And I accept alert
    And I click on element with xpath "//*[@name='agreedToPrivacyPolicy']"
    And I click on element with xpath "//*[@id='formSubmit']"
    Then element with xpath "//div[@id='samplePageResult']//section" should contain text "Oleg"
#    Then element with xpath "//div[@id='samplePageResult']//b/../../.." should contain text "Oleg"
    And element with xpath "//div[@id='samplePageResult']//section" should contain text "Markov"
    And element with xpath "//div[@id='samplePageResult']//section" should contain text "olegmarkov"
    And element with xpath "//div[@id='samplePageResult']//section" should contain text "Austria"
    And element with xpath "//div[@id='samplePageResult']//section" should contain text "testboxus1+cucumber@gmail.com"
    And element with xpath "//div[@id='samplePageResult']//section" should contain text "6501111111"
    And element with xpath "//div[@id='samplePageResult']//section" should contain text "Toyota"
    And element with xpath "//div[@id='samplePageResult']//section" should contain text "222 Main St"
    And element with xpath "//b[@name='allowedToContact']" should have text as "true"
    And element with xpath "//b[@name='agreedToPrivacyPolicy']" should have text as "true"
    And element with xpath "//*[@name='password']" should have text as "[entered]"
    And element with xpath "//div[@id='samplePageResult']//section" should not contain text "testpass"



#          Classwork 12/3/2018
  @sample9
  Scenario: Validate date picker
    Given I open url "https://skryabin.com/webdriver/html/sample.html"
    When I click on element with xpath "//input[@id='dateOfBirth']"
    Then element with xpath "//div[@id='ui-datepicker-div']" should be displayed
    And I click on element with xpath "(//*[@class='ui-datepicker-month']/option)[1]"
    And I click on element with xpath "//*[@class='ui-datepicker-year']/*[@value='1990']"
    And I click on element with xpath "//*[@data-handler='selectDay']//*[text()='31']"
    Then element with xpath "//input[@id='dateOfBirth']" should have attribute "value" as "01/31/1990"

  @sample10
  Scenario: Dealing with iframes
    Given I open url "https://skryabin.com/webdriver/html/sample.html"
    And I switch to iframe with xpath "//iframe[@name='additionalInfo']"
    When I type "Oleg Markov" into element with xpath "//*[@id='contactPersonName']"
    And I switch to default content

  @sample11
  Scenario: Dealing with windows
    Given I open url "https://skryabin.com/webdriver/html/sample.html"
    And I click on element with xpath "//button[contains(@onclick,'window')]"
    When I switch to new window
    Then element with xpath "//body" should contain text "Document 1"
    And I switch to first window


#    Homework
  @sample12
    Scenario: Alert validation
      Given I open url "https://skryabin.com/webdriver/html/sample.html"
      When I click on element with xpath "//*[@id='thirdPartyButton']"
      And I dismiss alert
      Then element with xpath "//*[@id='thirdPartyResponseMessage']" should contain text "did not accept"
      When I click on element with xpath "//*[@id='thirdPartyButton']"
      And I accept alert
      Then element with xpath "//*[@id='thirdPartyResponseMessage']" should contain text "You accepted"
