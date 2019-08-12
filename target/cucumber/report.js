$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/converter.feature");
formatter.feature({
  "name": "Converter functions",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@converter"
    }
  ]
});
formatter.scenarioOutline({
  "name": "Validate Farenheit to Celsius",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@converter1"
    }
  ]
});
formatter.step({
  "name": "I go to \"converter\" page",
  "keyword": "Given "
});
formatter.step({
  "name": "I click on \"\u003ctab\u003e\"",
  "keyword": "When "
});
formatter.step({
  "name": "I set \"\u003cfrom_unit\u003e\" to \"\u003cto_unit\u003e\"",
  "keyword": "And "
});
formatter.step({
  "name": "I enter into From field \"\u003cfrom_value\u003e\" and verify \"\u003cexpected_value\u003e\" result",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "tab",
        "from_unit",
        "to_unit",
        "from_value",
        "expected_value"
      ]
    },
    {
      "cells": [
        "Temperature",
        "Fahrenheit",
        "Celsius",
        "54",
        "12.2"
      ]
    },
    {
      "cells": [
        "Weight",
        "Pound",
        "Kilogram",
        "170",
        "77"
      ]
    },
    {
      "cells": [
        "Length",
        "Mile",
        "Kilometer",
        "50",
        "80.4"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Validate Farenheit to Celsius",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@converter"
    },
    {
      "name": "@converter1"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I go to \"converter\" page",
  "keyword": "Given "
});
formatter.match({
  "location": "WebDriverStepDefs.iGoToPage(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I click on \"Temperature\"",
  "keyword": "When "
});
formatter.match({
  "location": "ConverterStepdefs.iClickOn(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I set \"Fahrenheit\" to \"Celsius\"",
  "keyword": "And "
});
formatter.match({
  "location": "ConverterStepdefs.iSetTo(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I enter into From field \"54\" and verify \"12.2\" result",
  "keyword": "Then "
});
formatter.match({
  "location": "ConverterStepdefs.iEnterIntoFromFieldAndVerifyResult(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenario({
  "name": "Validate Farenheit to Celsius",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@converter"
    },
    {
      "name": "@converter1"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I go to \"converter\" page",
  "keyword": "Given "
});
formatter.match({
  "location": "WebDriverStepDefs.iGoToPage(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I click on \"Weight\"",
  "keyword": "When "
});
formatter.match({
  "location": "ConverterStepdefs.iClickOn(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I set \"Pound\" to \"Kilogram\"",
  "keyword": "And "
});
formatter.match({
  "location": "ConverterStepdefs.iSetTo(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I enter into From field \"170\" and verify \"77\" result",
  "keyword": "Then "
});
formatter.match({
  "location": "ConverterStepdefs.iEnterIntoFromFieldAndVerifyResult(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenario({
  "name": "Validate Farenheit to Celsius",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@converter"
    },
    {
      "name": "@converter1"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I go to \"converter\" page",
  "keyword": "Given "
});
formatter.match({
  "location": "WebDriverStepDefs.iGoToPage(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I click on \"Length\"",
  "keyword": "When "
});
formatter.match({
  "location": "ConverterStepdefs.iClickOn(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I set \"Mile\" to \"Kilometer\"",
  "keyword": "And "
});
formatter.match({
  "location": "ConverterStepdefs.iSetTo(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I enter into From field \"50\" and verify \"80.4\" result",
  "keyword": "Then "
});
formatter.match({
  "location": "ConverterStepdefs.iEnterIntoFromFieldAndVerifyResult(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});