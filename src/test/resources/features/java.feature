@java
  Feature: Java feature

    @java1
    Scenario: Initial java scenario
      Given I write first java step
      And I print "Say Hello!" in console
      And I print "Another text!" in console
      And I do actions with "apple+orange" and "apple"
      And I do actions with "Orange" and "Apple"
      And I do actions with "Orange" and "Orange"
      And I compare "str1" and "str2"
      And I print url for "google" page
      And I print url for "sample" page
      And I print url for "amazon" page

      @java2
      Scenario: Arrays exercise
        Given I work with arrays

        @java3
        Scenario: Map exercise
          Given I work with maps
