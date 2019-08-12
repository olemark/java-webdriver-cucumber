@predefined
Feature: Smoke steps

  @predefined1
  Scenario: Predefined steps for Google
    Given I open url "https://google.com"
    Then I should see page title as "Google"
    Then element with xpath "//input[@name='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='q']"
    Then I click on element using JavaScript with xpath "//*[@name='btnK']"
    Then I wait for element with xpath "//*[@id='ires']" to be present
    Then element with xpath "//*[@id='ires']" should contain text "Cucumber"

  @predefined2
  Scenario: Predefined steps for Bing
    Given I open url "https://www.bing.com"
    Then I should see page title as "Bing"
    Then element with xpath "//input[@id='sb_form_q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='sb_form_q']"
    Then I click on element using JavaScript with xpath "//*[@id='sb_form_go']"
    Then I wait for element with xpath "//*[@id='b_content']" to be present
    Then element with xpath "//*[@id='b_content']" should contain text "Cucumber"

  @predefined3
  Scenario: Predefined steps for Gibiru
    Given I open url "http://www.gibiru.com"
    Then I should see page title as "Gibiru - Uncensored Anonymous Search"
    Then element with xpath "//input[@name='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='q']"
    Then I click on element using JavaScript with xpath "//*[@id='button-addon2']"
    Then I wait for element with xpath "//*[@id='___gcse_0']" to be present
    Then element with xpath "//*[@id='___gcse_0']" should contain text "Cucumber"

  @predefined4
  Scenario: Predefined steps for DuckDuckGo
    Given I open url "https://duckduckgo.com"
    Then I should see page title as "DuckDuckGo — Privacy, simplified."
    Then element with xpath "//input[@id='search_form_input_homepage']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='search_form_input_homepage']"
    Then I click on element using JavaScript with xpath "//*[@id='search_button_homepage']"
    Then I wait for element with xpath "//*[@class='results--main']" to be present
    Then element with xpath "//*[@class='results--main']" should contain text "BDD"

  @predefined5
  Scenario: Predefined steps for Swisscows
    Given I open url "https://swisscows.ch"
    Then I should see page title as "Swisscows - secure search engine"
    Then element with xpath "//input[@name='query']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='query']"
    Then I click on element using JavaScript with xpath "//*[@class='search-submit']"
    Then I wait for element with xpath "//*[@class='item item--web']/.." to be present
    Then element with xpath "//*[@class='item item--web']/.." should contain text "BDD"

  @predefined6
  Scenario: Predefined steps for Search Encrypt
    Given I open url "https://www.searchencrypt.com"
    Then I should see page title as "Search Encrypt - The Privacy Based Search Engine"
    Then element with xpath "//input[@name='q'][contains(@class,'focus')]" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='q'][contains(@class,'focus')]"
    Then I click on element using JavaScript with xpath "//*[contains(@class,'focus')]/..//*[@id='btn-secure-search']"
    Then I wait for element with xpath "//*[@id='results-list']" to be present
    Then element with xpath "//*[@id='results-list']" should contain text "BDD"

  @predefined7
  Scenario: Predefined steps for Startpage
    Given I open url "https://www.startpage.com"
    Then I should see page title as "Startpage.com - The world's most private search engine"
    Then element with xpath "//input[@id='query']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='query']"
    Then I click on element using JavaScript with xpath "//*[@class='search-form__button']"
    Then I wait for element with xpath "//*[@class='column column--main']" to be present
    Then element with xpath "//*[@class='column column--main']" should contain text "Cucumber"

  @predefined8
  Scenario: Predefined steps for Yandex
    Given I open url "https://yandex.com"
    Then I should see page title as "Yandex"
    Then element with xpath "//input[@id='text']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='text']"
    Then I click on element using JavaScript with xpath "//*[@type='submit']"
    Then I wait for element with xpath "//*[@class='content__left']" to be present
    Then element with xpath "//*[@class='content__left']" should contain text "BDD"

  @predefined9
  Scenario: Predefined steps for BoardReader
    Given I open url "https://boardreader.com"
    Then I should see page title as "Boardreader - Forum Search Engine"
    Then element with xpath "//input[@id='title-query']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='title-query']"
    Then I click on element using JavaScript with xpath "//*[@id='title-submit']"
    Then I wait for element with xpath "(//*[@class='text'])[1]" to be present
#    Then I wait for element with xpath "(//li)[1]" to be present
    Then element with xpath "//*[@class='mdl-list']" should contain text "Cucumber"


  @predefined10
  Scenario: Predefined steps for Ecosia
    Given I open url "https://www.ecosia.org"
    Then I should see page title as "Ecosia - the search engine that plants trees"
    Then element with xpath "//input[@name='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='q']"
    Then I click on element using JavaScript with xpath "//*[@type='submit']"
    Then I wait for element with xpath "//div[@class='container results']" to be present
    Then element with xpath "//div[@class='container results']" should contain text "cucumber"

